local ttl = struct.unpack('>h', KEYS[1])
local node_id = KEYS[2]

local nonexistent_user_ids = {}
local nonexistent_user_count = 0

local user_id
local key_exists
local field_exists
local key_count = #KEYS
local now = tonumber(redis.call('TIME')[1])
for i = 3, key_count do
    user_id = KEYS[i]
    local id = struct.unpack('>l', user_id)
    field_exists = redis.call('HEXISTS', user_id, node_id) > 0
    if field_exists then
        key_exists = redis.call('EXPIRE', user_id, ttl) > 0
        if key_exists then
            redis.call('HSET', user_id, node_id, now)
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