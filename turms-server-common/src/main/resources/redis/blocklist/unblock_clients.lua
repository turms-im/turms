local blocklist_key = KEYS[1] == 'i' and 'blocklist:ip' or 'blocklist:uid'

local id_count = #KEYS - 1
local blocklist_target_key = blocklist_key .. ':target'
local blocklist_timestamp_key = blocklist_key .. ':timestamp'
local blocklist_log_key = blocklist_key .. ':log'
local blocklist_log_id_key = blocklist_key .. ':log_id'

if id_count <= 0 then
    return {
        tonumber(redis.call('GET', blocklist_timestamp_key)) or -1,
        tonumber(redis.call('GET', blocklist_log_id_key)) or -1,
        0
    }
end

local count = 0
for i = 1, id_count do
    local id = KEYS[i + 1]
    local num = redis.call('ZREM', blocklist_target_key, id)
    if num > 0 then
        local type = string.char(0)
        redis.call('RPUSH', blocklist_log_key, type, id, type)
        count = count + 1
    end
end

local new_log_id = redis.call('INCRBY', blocklist_log_id_key, count)
if (new_log_id - count) == 0 then
    local current_timestamp = redis.call('GET', blocklist_timestamp_key)
    if not current_timestamp then
        local now = tonumber(redis.call('TIME')[1])
        redis.call('SET', blocklist_timestamp_key, now)
    end
end

local diff = redis.call('LLEN', blocklist_log_key) - MAX_LOG_QUEUE_SIZE * 3
if diff > 0 then
    redis.call('LTRIM', blocklist_log_key, diff, -1)
end

return {
    tonumber(redis.call('GET', blocklist_timestamp_key)) or -1,
    new_log_id,
    count
}