local ttl
for i, val in ipairs(KEYS) do
    if i == 1 then
        ttl = struct.unpack('>h', val)
    else
        redis.call('expire', val, ttl)
    end
end