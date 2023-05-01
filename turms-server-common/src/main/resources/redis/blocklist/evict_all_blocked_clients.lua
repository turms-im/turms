local keys = KEYS
local redis_call = redis.call
local tonumber = tonumber

local remove_all = #keys == 0

local function remove_blocklist(name)
    local now = tonumber(redis_call('TIME')[1])
    redis_call('SET', name .. ':timestamp', now)
    redis_call('DEL', name .. ':log_id')
    redis_call('DEL', name .. ':log')
    redis_call('DEL', name .. ':target')
end

if remove_all then
    remove_blocklist('blocklist:ip')
    remove_blocklist('blocklist:uid')
else
    local blocklist_key = keys[1] == 'i' and 'blocklist:ip' or 'blocklist:uid'
    remove_blocklist(blocklist_key)
end