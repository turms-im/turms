local blocklist_key = KEYS[1] == 'i' and 'blocklist:ip' or 'blocklist:uid'
local block_end_date = struct.unpack('>l', KEYS[2])

local id_count = #KEYS - 2
local blocklist_target_key = blocklist_key .. ':target'
local blocklist_timestamp_key = blocklist_key .. ':timestamp'
local blocklist_log_key = blocklist_key .. ':log'
local blocklist_log_id_key = blocklist_key .. ':log_id'

if id_count <= 0 then
    return {
        tonumber(redis.call('GET', blocklist_timestamp_key)) or -1,
        tonumber(redis.call('GET', blocklist_log_id_key)) or -1
    }
end

local new_log_id = redis.call('INCRBY', blocklist_log_id_key, id_count)
if (new_log_id - id_count) == 0 then
    local current_timestamp = redis.call('GET', blocklist_timestamp_key)
    if not current_timestamp then
        local now = tonumber(redis.call('TIME')[1])
        redis.call('SET', blocklist_timestamp_key, now)
    end
end

for i = 1, id_count do
    local id = KEYS[i + 2]
    redis.call('ZADD', blocklist_target_key, block_end_date, id)
    redis.call('RPUSH', blocklist_log_key, string.char(1), id, block_end_date)
end

local diff = redis.call('LLEN', blocklist_log_key) - MAX_LOG_QUEUE_SIZE * 3
if diff > 0 then
    redis.call('LTRIM', blocklist_log_key, diff, -1)
end

return {
    tonumber(redis.call('GET', blocklist_timestamp_key)) or -1,
    new_log_id
}