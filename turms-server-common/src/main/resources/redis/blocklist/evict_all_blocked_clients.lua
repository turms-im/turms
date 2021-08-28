local remove_all = #KEYS == 0

local function removeblocklist(name)
    local now = tonumber(redis.call('TIME')[1]) - 1630022400
    redis.call('SET', name .. ':timestamp', now)
    redis.call('DEL', name .. ':log_id')
    redis.call('DEL', name .. ':log')
    redis.call('DEL', name .. ':target')
end

if remove_all then
    removeblocklist('blocklist:ip')
    removeblocklist('blocklist:uid')
else
    local blocklist_key = KEYS[1] == 'i' and 'blocklist:ip' or 'blocklist:uid'
    removeblocklist(blocklist_key)
end