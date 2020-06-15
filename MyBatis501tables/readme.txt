多表查询——一对多|多对一

    一个 用户 可以有多个 账户
    主表：User
        ·主表实体 应该包含 从表实体的引用集合 private List<Account> accounts;
    从表：Account
        ·从表实体 应该包含 主表实体的对象引用 private User user;

    展示结果 AccountUser

    映射文件，配置 结果类型
        <resultMap>
            <collection></collection>引用集合
            <association></association>对象引用
        </resultMap>