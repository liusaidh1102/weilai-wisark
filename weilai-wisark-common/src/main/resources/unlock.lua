-- 第一个参数是要操作的键名
-- 第二个参数是要比较的值
local key = KEYS[1]
local expectedValue = ARGV[1]

-- 获取当前键的值
local currentValue = redis.call('GET', key)

-- 判断当前值是否与预期值相同
if currentValue == expectedValue then
    -- 相同则删除键
    redis.call('DEL', key)
    return 1  -- 返回1表示删除成功
else
    return 0  -- 返回0表示未删除
end
