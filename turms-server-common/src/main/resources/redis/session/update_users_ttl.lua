local keys = KEYS
local redis_call = redis.call
local struct_unpack = struct.unpack

local ttl = struct_unpack('>h', keys[1])
local node_id = keys[2]

local nonexistent_user_ids = {}
local nonexistent_user_count = 0

local user_id
local key_exists
local field_exists
local key_count = #keys
local now = tonumber(redis_call('TIME')[1])
for i = 3, key_count do
    user_id = keys[i]
    field_exists = redis_call('HEXISTS', user_id, node_id) > 0
    if field_exists then
        key_exists = redis_call('EXPIRE', user_id, ttl) > 0
        if key_exists then
            redis_call('HSET', user_id, node_id, now)
        else
            nonexistent_user_count = nonexistent_user_count + 1
            nonexistent_user_ids[nonexistent_user_count] = user_id
        end
    else
        nonexistent_user_count = nonexistent_user_count + 1
        nonexistent_user_ids[nonexistent_user_count] = user_id
    end
end

if nonexistent_user_count == 0 then
    return nil
end

return nonexistent_user_ids