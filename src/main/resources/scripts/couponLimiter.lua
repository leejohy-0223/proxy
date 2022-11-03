--couponLimiter.lua
redis.call('SETNX', KEYS[1], 0)
if tonumber(redis.call('GET', KEYS[1])) < 10
then
    redis.call('INCR', KEYS[1])
    return true
else
    return false
end
