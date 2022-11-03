--rateLimiter.lua
redis.call('ZREMRANGEBYSCORE', KEYS[1], 0, ARGV[1] - 60 * 1000)
if tonumber(redis.call('ZCARD', KEYS[1])) < 5
then
    redis.call('ZADD', KEYS[1], ARGV[1], ARGV[1])
    return true
else
    return false
end
