MyBatis缓存
    缓存：存在于内存中的临时数据
    使用场景：
        ·经常查询 数据不经常改变
        ·数据的正确与否 对 最终结果影响不大

MyBatis的缓存策略
    ·一级缓存：默认开启，不用管
    ·二级缓存：需要配置开启

一级缓存：
    ·SqlSession级别的缓存。
    ·当执行查询后，结果会同时存入SqlSession中的一个Map
    ·清空一级缓存的情况：让缓存中的数据是"最新的"，避免脏读
        ·调用SqlSession的 update、添加、删除、commit、flush或close
        ·SqlSession对象消失，缓存消失
        ·clearCache 清空缓存
    同一查询，执行两次，在日志中可以看到，只有一次操作。

二级缓存：
    ·mapper 映射级别的缓存
    ·存入 SqlSessionFactory对象中。缓存位置
        ·同一factory创建的 SqlSession 共享 该缓存

    使用：
        ·主配置文件：设置支持二级缓存
        ·映射文件：设置支持二级缓存
                ·xml
                ·注解
        ·映射配置时，操作也需支持二级缓存。eg:<select useCache="true">
                ·xml
                ·注解



