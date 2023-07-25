--在删除key之前，一定要判断服务A持有的value与Redis内存储的value是否一致。如果贸然使用服务A持有的key来删除锁，则会误将服务B的锁释放掉
if redis.call("get", KEYS[1])==ARGV[1] then
    return redis.call("del", KEYS[1])
else
    return 0
end
