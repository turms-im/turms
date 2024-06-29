local keys = KEYS
local redis_call = redis.call

local id_count = #keys

local deleted_count = 0
for i = 1, id_count do
    local user_id = keys[i]
    local related_user_id_key = RELATED_USER_IDS_KEY .. user_id
    local related_user_ids = redis_call('SMEMBERS', related_user_id_key)
    local related_user_id_count = #related_user_ids
    if related_user_id_count > 0 then
        for j = 1, related_user_id_count do
            local related_user_id = related_user_ids[j]
            redis_call('HDEL', PRIVATE_MESSAGE_SEQUENCE_ID_KEY, user_id .. related_user_id, related_user_id .. user_id)
            redis_call('SREM', RELATED_USER_IDS_KEY .. related_user_id, user_id)
        end
        deleted_count = deleted_count + 1
        redis_call('DEL', related_user_id_key)
    end
end

return deleted_count