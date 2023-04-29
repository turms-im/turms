local user_id = KEYS[1]
local status = KEYS[2]

local exists = redis.call('HEXISTS', user_id, '$') > 0

if exists then
    redis.call('HSET', user_id, '$', status)
    return true
end

return false