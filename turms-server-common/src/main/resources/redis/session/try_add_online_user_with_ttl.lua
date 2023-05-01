local user_id = KEYS[1]
local device = KEYS[2]
local node_id = KEYS[3]
local ttl = struct.unpack('>h', KEYS[4])
local status = KEYS[5]
local expected_existing_node_id = KEYS[6]
local expected_device_timestamp = KEYS[7]
if expected_device_timestamp and expected_device_timestamp ~= '' then
    expected_device_timestamp = struct.unpack('>l', expected_device_timestamp)
end

local values = redis.call('HMGET', user_id, device, '$', node_id)
local existing_node_id = values[1]
local existing_status = values[2]
local now
if existing_node_id then
    if existing_node_id == node_id then
        return false
    end
    local existing_device_timestamp = tonumber(values[3])
    if not existing_device_timestamp then
        return false
    end
    now = tonumber(redis.call('TIME')[1])
    local diff = now - existing_device_timestamp
    if (diff <= DEVICE_STATUS_TTL)
            and (not expected_existing_node_id
            or expected_existing_node_id == ''
            or not expected_device_timestamp
            or expected_device_timestamp == ''
            or expected_existing_node_id ~= existing_node_id
            or expected_device_timestamp ~= existing_device_timestamp) then
        return false
    end
end

if now == nil then
    now = tonumber(redis.call('TIME')[1])
end
local update_status = status ~= nil and status ~= ''
if existing_status == nil then
    redis.call('HMSET', user_id,
            device, node_id,
            '$', update_status and status or string.char(0),
            node_id, now)
elseif update_status and existing_status ~= status then
    redis.call('HMSET', user_id,
            device, node_id,
            '$', status,
            node_id, now)
else
    redis.call('HMSET', user_id,
            device, node_id,
            node_id, now)
end
redis.call('EXPIRE', user_id, ttl)

local count = #KEYS
if count - 7 > 0 then
    local key = user_id .. ':d'
    redis.call('HMSET', key, unpack(KEYS, 8, count))
    if DEVICE_DETAILS_TTL > 0 then
        redis.call('EXPIRE', key, DEVICE_DETAILS_TTL)
    end
end

return true