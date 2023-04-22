local blocklist_key = KEYS[1] == 'i' and 'blocklist:ip' or 'blocklist:uid'
local timestamp = struct.unpack('>l', KEYS[2])
local log_id = struct.unpack('>i', KEYS[3])
if log_id < 0 then
    log_id = 0
end

local current_timestamp = tonumber(redis.call('GET', blocklist_key .. ':timestamp'))
local last_log_id = tonumber(redis.call('GET', blocklist_key .. ':log_id'));

if not current_timestamp then
    return { -1, -1 }
end

if timestamp ~= current_timestamp and timestamp >= 0 then
    return {
        current_timestamp,
        last_log_id
    }
end

local first_log_id = last_log_id - MAX_LOG_QUEUE_SIZE
if first_log_id < 0 then
    first_log_id = 0
end
if first_log_id <= log_id and log_id < last_log_id then
    local logs = redis.call('LRANGE', blocklist_key .. ':log', (log_id - first_log_id) * 3, -1)
    for i = 1, #logs, 3 do
        logs[i + 2] = tonumber(logs[i + 2])
    end
    return {
        current_timestamp,
        last_log_id,
        logs
    }
else
    return {
        current_timestamp,
        last_log_id
    }
end