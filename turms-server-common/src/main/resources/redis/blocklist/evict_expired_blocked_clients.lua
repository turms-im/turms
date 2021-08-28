local now = tonumber(redis.call('TIME')[1]) - 1630022400
redis.call('ZREMRANGEBYSCORE', 'blocklist:ip', 0, now)
redis.call('ZREMRANGEBYSCORE', 'blocklist:uid', 0, now)