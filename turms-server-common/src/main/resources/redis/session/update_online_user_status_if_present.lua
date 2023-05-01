local keys = KEYS
local redis_call = redis.call

local user_id = keys[1]
local status = keys[2]

local exists = redis_call('HEXISTS', user_id, '$') > 0

if exists then
    redis_call('HSET', user_id, '$', status)
    return true
end

return false