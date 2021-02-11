local user_id = KEYS[1]
local device = KEYS[2]
local node_id = KEYS[3]
local ttl = struct.unpack('>h', KEYS[4])
local status = KEYS[5]

local result = redis.call('hsetnx', user_id, device, node_id)

if result > 0 then
    if status == nil and redis.call('hget', user_id, 's') == nil then
        redis.call('hset', user_id, 's', '\x00')
    else
        redis.call('hset', user_id, 's', status)
    end
    redis.call('expire', user_id, ttl)
    return true
end

return false