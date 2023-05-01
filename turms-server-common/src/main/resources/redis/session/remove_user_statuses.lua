local keys = KEYS
local redis_call = redis.call

local user_id = keys[1]
local node_id = keys[2]
local key_count = #keys

local device_count = key_count - 2
if device_count == 0 then
    device_count = 6
    key_count = key_count + 6
    local string_char = string.char
    keys[3] = string_char(0)
    keys[4] = string_char(1)
    keys[5] = string_char(2)
    keys[6] = string_char(3)
    keys[7] = string_char(4)
    keys[8] = string_char(5)
end

local node_ids = redis_call('HMGET', user_id, unpack(keys, 3, key_count))
local devices_to_delete = {}
local devices_to_delete_count = 0
for i = 1, device_count do
    if node_ids[i] == node_id then
        devices_to_delete_count = devices_to_delete_count + 1
        devices_to_delete[devices_to_delete_count] = keys[2 + i]
    end
end

local has_devices_to_delete = devices_to_delete_count > 0
if has_devices_to_delete then
    redis_call('HDEL', user_id, unpack(devices_to_delete))
end

local field_count = redis_call('HLEN', user_id)
if field_count <= 2 then
    redis_call('DEL', user_id)
else
    local has_related_device = false
    local values = redis_call('HVALS', user_id)
    local value_count = #values
    for i = 1, value_count do
        if values[i] == node_id then
            has_related_device = true
            break
        end
    end
    if not has_related_device then
        redis_call('HDEL', user_id, node_id)
    end
end

return has_devices_to_delete