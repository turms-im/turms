local redis_call = redis.call

local now = tonumber(redis_call('TIME')[1])
redis_call('ZREMRANGEBYSCORE', 'blocklist:ip', 0, now)
redis_call('ZREMRANGEBYSCORE', 'blocklist:uid', 0, now)