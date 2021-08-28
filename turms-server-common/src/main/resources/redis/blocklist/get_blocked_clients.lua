local blocklist_key = KEYS[1] == 'i' and 'blocklist:ip' or 'blocklist:uid'

local timestamp = tonumber(redis.call('GET', blocklist_key .. ':timestamp'));
if not timestamp then
    return { -1, -1 }
end

local blocked_clients = redis.call('ZRANGE', blocklist_key .. ':target', 0, -1, 'WITHSCORES')

for i = 1, #blocked_clients, 2 do
    blocked_clients[i + 1] = tonumber(blocked_clients[i + 1])
end

return {
    timestamp,
    tonumber(redis.call('GET', blocklist_key .. ':log_id')) or -1,
    blocked_clients
}