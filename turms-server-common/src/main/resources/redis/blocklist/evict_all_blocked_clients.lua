local remove_all = #KEYS == 0

local function remove_blocklist(name)
    local now = tonumber(redis.call('TIME')[1])
    redis.call('SET', name .. ':timestamp', now)
    redis.call('DEL', name .. ':log_id')
    redis.call('DEL', name .. ':log')
    redis.call('DEL', name .. ':target')
end

if remove_all then
    remove_blocklist('blocklist:ip')
    remove_blocklist('blocklist:uid')
else
    local blocklist_key = KEYS[1] == 'i' and 'blocklist:ip' or 'blocklist:uid'
    remove_blocklist(blocklist_key)
end