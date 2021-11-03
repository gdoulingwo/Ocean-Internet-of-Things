--
-- Created by IntelliJ IDEA.
-- User: YY
-- Date: 2021/3/4
-- Time: 15:10
-- To change this template use File | Settings | File Templates.
--

local flag = redis.call("GET", KEYS[1]);
if (flag == nil) then
    redis.call("SET", KEYS[1], 1)
    return true;
else
    return false;
end
