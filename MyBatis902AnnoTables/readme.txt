MyBatis 的 多表 CRUD操作 注解方式实现

    @Results(
        id,
        value={
            @Result(id=true, column, property),
            @Result(column, property),
            ...
            @Result(property, column,
                many=@Many(select, fetchType))
        }
    )

    @ResultMap
