package com.study.hello.mybatis;

import com.study.hello.mybatis.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;

public class MybatisSql {

    /**
     * INSERT_INTO(String) 开始新的 insert 语句，并指定插入数据表的表名。后面应该会跟着一个或多个 VALUES() 调用，或 INTO_COLUMNS() 和 INTO_VALUES() 调用。
     * VALUES(String, String) 追加数据值到 insert 语句中。第一个参数是数据插入的列名，第二个参数则是数据值。
     * INTO_COLUMNS(String…) 追加插入列子句到 insert 语句中。应与 INTO_VALUES() 一同使用。
     * INTO_VALUES(String…)	追加插入值子句到 insert 语句中。应与 INTO_COLUMNS() 一同使用。
     */
    public static String insertProvider() {
        // insert into student (id, user_name, user_phone, user_age)
        // values (#{id}, #{userName}, #{userPhone}, #{userAge});
        return new SQL()
                .INSERT_INTO("student")
                .VALUES("id", "#{id}")
                .INTO_COLUMNS("user_name", "user_phone", "user_age")
                .INTO_VALUES("#{userName}", "#{userPhone}", "#{userAge}")
                .toString();
    }

    /**
     * ADD_ROW() 添加新的一行数据，以便执行批量插入。（于 3.5.2 引入）
     */
    public static String insertBatchProvider() {
        // insert into student (user_name, user_phone, user_age)
        // values (#{userName}, #{userPhone}, #{userAge}),
        //        (#{userName}, #{userPhone}, #{userAge});
        return new SQL()
                .INSERT_INTO("student")
                .INTO_COLUMNS("user_name", "user_phone", "user_age")
                .INTO_VALUES("#{userName}", "#{userPhone}", "#{userAge}")
                .ADD_ROW()
                .INTO_VALUES("#{userName}", "#{userPhone}", "#{userAge}")
                .toString();
    }

    /**
     * DELETE_FROM(String) 开始新的 delete 语句，并指定删除表的表名。通常它后面都会跟着一个 WHERE 子句！
     */
    public static String deleteProvider() {
        // delete from student where (id = #{id});
        return new SQL()
                .DELETE_FROM("student")
                .WHERE("id = #{id}")
                .toString();
    }

    /**
     * UPDATE(String) 开始新的 update 语句，并指定更新表的表名。后面都会跟着一个或多个 SET() 调用，通常也会有一个 WHERE() 调用。
     * SET(String/String…) 对 update 语句追加 “set” 属性的列表
     */
    public static String updateProvider() {
        // update student
        // set user_name = #{userName}, user_phone = #{userPhone}, user_age = #{userAge}
        // where (id = #{id});
        return new SQL()
                .UPDATE("student")
                .SET("user_name = #{userName}")
                .SET("user_phone = #{userPhone}", "user_age = #{userAge}")
                .WHERE("id = #{id}")
                .toString();
    }

    /**
     * SELECT(String/String…) 开始新的或追加到已有的 SELECT子句。可以被多次调用，参数会被追加到 SELECT 子句。 参数通常使用逗号分隔的列名和别名列表，但也可以是数据库驱动程序接受的任意参数。
     * SELECT_DISTINCT(String/String…) 开始新的或追加到已有的 SELECT子句，并添加 DISTINCT 关键字到生成的查询中。可以被多次调用，参数会被追加到 SELECT 子句。 参数通常使用逗号分隔的列名和别名列表，但也可以是数据库驱动程序接受的任意参数。
     * FROM(String/String…) 开始新的或追加到已有的 FROM子句。可以被多次调用，参数会被追加到 FROM子句。 参数通常是一个表名或别名，也可以是数据库驱动程序接受的任意参数。
     * JOIN(String/String…) 基于调用的方法，添加新的合适类型的 JOIN 子句。 参数可以包含一个由列和连接条件构成的标准连接。
     * WHERE(String/String…) 插入新的 WHERE 子句条件，并使用 AND 拼接。可以被多次调用，对于每一次调用产生的新条件，会使用 AND 拼接起来。要使用 OR 分隔，请使用 OR()。
     * OR()	使用 OR 来分隔当前的 WHERE 子句条件。 可以被多次调用，但在一行中多次调用会生成错误的 SQL。
     * AND() 使用 AND 来分隔当前的 WHERE子句条件。 可以被多次调用，但在一行中多次调用会生成错误的 SQL。由于 WHERE 和 HAVING都会自动使用 AND 拼接, 因此这个方法并不常用，只是为了完整性才被定义出来。
     */
    public static String selectJoinProvider() {
        // SELECT DISTINCT id, user_name, user_phone FROM student WHERE (user_name like '%张三%') AND (user_phone like '%177%') OR (user_age > 10);
        return new SQL()
                // .SELECT("id")
                .SELECT_DISTINCT("user_name", "user_phone")
                .FROM("student")
                .WHERE("user_name like '%张三%'")
                .AND()
                .WHERE("user_phone like '%177%'")
                .OR()
                .WHERE("user_age > 10")
                .toString();
    }

    /**
     * GROUP_BY(String/String…) 追加新的 GROUP BY 子句，使用逗号拼接。可以被多次调用，每次调用都会使用逗号将新的条件拼接起来。
     * HAVING(String/String…) 追加新的 HAVING 子句。使用 AND 拼接。可以被多次调用，每次调用都使用AND来拼接新的条件。要使用 OR 分隔，请使用 OR()。
     * ORDER_BY(String/String…) 追加新的 ORDER BY 子句，使用逗号拼接。可以多次被调用，每次调用会使用逗号拼接新的条件。
     */
    public static String selectGroupProvider() {
        // SELECT user_name, count(user_name) count
        // FROM student
        // GROUP BY user_name
        // HAVING (count > 1) AND (count < 5) OR (user_name like '%李四%')
        // ORDER BY count desc;
        return new SQL()
                .SELECT("user_name, count(user_name) count")
                .FROM("student")
                .GROUP_BY("user_name")
                .HAVING("count > 1")
                .AND()
                .HAVING("count < 5")
                .OR()
                .HAVING("user_name like '%李四%'")
                .ORDER_BY("count desc")
                .toString();
    }

}
