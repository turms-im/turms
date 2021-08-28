local user_id = KEYS[1]
local device = KEYS[2]
local node_id = KEYS[3]
local ttl = struct.unpack('>h', KEYS[4])
local status = KEYS[5]

local result = redis.call('HSETNX', user_id, device, node_id)

if result == 0 then
    return false
end

local previous_status = redis.call('HGET', user_id, 's')
if previous_status == nil then
    redis.call('HSET', user_id, 's', status or string.char(0))
elseif status ~= nil and previous_status ~= status then
    redis.call('HSET', user_id, 's', status)
end
redis.call('EXPIRE', user_id, ttl)

return true