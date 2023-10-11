local keys = KEYS
local redis_call = redis.call
local struct_unpack = struct.unpack
local tonumber = tonumber
local unpack = unpack

local user_id = keys[1]
local device = keys[2]
local node_id = keys[3]
local ttl = struct_unpack('>h', keys[4])
local status = keys[5]
local expected_existing_node_id = keys[6]
local expected_device_timestamp = keys[7]
if expected_device_timestamp and expected_device_timestamp ~= '' then
    expected_device_timestamp = struct_unpack('>l', expected_device_timestamp)
end

local values = redis_call('HMGET', user_id, device, '$')
local existing_node_id = values[1]
local existing_status = values[2]
local now
if existing_node_id then
    if existing_node_id == node_id then
        return '0'
    end
    local existing_device_timestamp = tonumber(redis_call('HGET', user_id, existing_node_id))
    if not existing_device_timestamp then
        return '0'
    end
    now = tonumber(redis_call('TIME')[1])
    local diff = now - existing_device_timestamp
    if (diff <= DEVICE_STATUS_TTL)
            and (not expected_existing_node_id
            or expected_existing_node_id == ''
            or not expected_device_timestamp
            or expected_device_timestamp == ''
            or expected_existing_node_id ~= existing_node_id
            or expected_device_timestamp ~= existing_device_timestamp) then
        return '0'
    end
end

if now == nil then
    now = tonumber(redis_call('TIME')[1])
end
local update_status = status ~= nil and status ~= ''
if existing_status == nil then
    redis_call('HMSET', user_id,
            device, node_id,
            '$', update_status and status or string.char(0),
            node_id, now)
elseif update_status and existing_status ~= status then
    redis_call('HMSET', user_id,
            device, node_id,
            '$', status,
            node_id, now)
else
    redis_call('HMSET', user_id,
            device, node_id,
            node_id, now)
end
redis_call('EXPIRE', user_id, ttl)

if existing_node_id then
    local has_related_device = false
    local values = redis_call('HVALS', user_id)
    local value_count = #values
    for i = 1, value_count do
        if values[i] == existing_node_id then
            has_related_device = true
            break
        end
    end
    if not has_related_device then
        redis_call('HDEL', user_id, existing_node_id)
    end
end

local count = #keys
if count - 7 > 0 then
    local key = user_id .. ':d'
    redis_call('HMSET', key, unpack(keys, 8, count))
    if DEVICE_DETAILS_TTL > 0 then
        redis_call('EXPIRE', key, DEVICE_DETAILS_TTL)
    end
end

if redis_call('HLEN', user_id) == 3 then
    return '1'
else
    return '2'
end