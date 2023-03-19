local field_count = struct.unpack('>b', KEYS[1])
if field_count <= 0 then
    return {}
end
local fields = {}
for i = 1, field_count do
    fields[i] = KEYS[i + 1]
end
local details = {}
local detail_index = 0
for i = 2 + field_count, #KEYS do
    local user_id = KEYS[i]
    local values = redis.call('HMGET', user_id .. ':d', unpack(fields))
    local value_count = 0
    for j = 1, field_count do
        local value = values[j]
        if value then
            value_count = value_count + 1
        end
    end
    if value_count > 0 then
        detail_index = detail_index + 1
        details[detail_index] = user_id
        detail_index = detail_index + 1
        details[detail_index] = value_count
        for j = 1, field_count do
            local value = values[j]
            if value then
                detail_index = detail_index + 1
                details[detail_index] = fields[j]
                detail_index = detail_index + 1
                details[detail_index] = value
            end
        end
    end
end

return details