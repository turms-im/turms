local keys = KEYS
local redis_call = redis.call

local user_id_1 = keys[1]
local user_id_2 = keys[2]

redis_call('SADD', RELATED_USER_IDS_KEY .. user_id_1, user_id_2)
redis_call('SADD', RELATED_USER_IDS_KEY .. user_id_2, user_id_1)

return redis_call('HINCRBY', PRIVATE_MESSAGE_SEQUENCE_ID_KEY, user_id_1 .. user_id_2, 1)