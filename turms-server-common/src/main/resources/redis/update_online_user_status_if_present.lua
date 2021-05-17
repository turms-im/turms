local user_id = KEYS[1]
local status = KEYS[2]

local exists = redis.call('hexists', user_id, 's') > 0

if exists then
    redis.call('hset', user_id, 's', status)
    return true
end

return false