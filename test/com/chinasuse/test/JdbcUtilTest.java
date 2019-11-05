package com.chinasuse.test;

import com.chinasuse.MysqlDbUtil;
import com.chinasuse.test.model.Salary;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JdbcUtilTest {

    @Test
    public void TestMysqlDBUtil() throws SQLException {
        Connection con=null;
        try {
             con = MysqlDbUtil.getConn("10.10.10.101",
                    "test",
                    "root",
                    "123@abc");

            QueryRunner queryRunner = new QueryRunner();

            List salarys = (List) queryRunner.query(con, "select * from salary", new BeanListHandler(Salary.class));

            for (Object s : salarys) {
                Salary s1 = (Salary) s;
                System.out.println(s1.getEmpname());
            }
        }finally {
            MysqlDbUtil.CloseConn(con);
        }
    }
}
