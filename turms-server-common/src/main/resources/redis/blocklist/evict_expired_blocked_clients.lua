local redis_call = redis.call

local now = redis_call('TIME')
now = math.floor(now[1] * 1000 + now[2] / 1000)
redis_call('ZREMRANGEBYSCORE', 'blocklist:ip', 0, now)
redis_call('ZREMRANGEBYSCORE', 'blocklist:uid', 0, now)