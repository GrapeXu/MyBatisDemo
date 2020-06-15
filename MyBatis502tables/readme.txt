多表查询——多对多

    用户 有 多个 角色
    角色 可以 赋予 多个用户

    通常使用中间表 来建立 两表关系

    ·用户表：User
            一个用户 可以具备 多个角色 private List<Role> roles
    ·角色表：Role
            一个角色 可以赋给 多个用户 private List<User> users
    ·中间表：
            映射配置时 在查询时使用
