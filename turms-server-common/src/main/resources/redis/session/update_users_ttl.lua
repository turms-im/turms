local ttl = struct.unpack('>h', KEYS[1])
for i = 2, #KEYS do
    redis.call('expire', KEYS[i], ttl)
end