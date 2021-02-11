local user_id = KEYS[1]
local device = KEYS[2]
local node_id = KEYS[3]
local status = ARGV[1]
local ttl = struct.unpack('>h', ARGV[2])

local result = redis.call('hsetnx', user_id, device, node_id)

if result > 0 then
    redis.call('hset', user_id, 's', status)
    redis.call('expire', user_id, ttl)
    return true
end

return false