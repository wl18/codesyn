package jdbc2;

import java.sql.*;

/**
 * @Author: weilong
 * @Date: 2020/3/13 20:49
 *
 */
public class JDBCTest1 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //下面写的是JDBC的事务
        /**
         * 以下程序验证jdbc的事务是否是自动提交机制！
         * 设置断点，测试结果：JDBC只要执行任意一条DML语句，就提交一次。
         * */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root"
                    ,"root");
            String sql = "update student set name = ? where id = ?";
            ps = conn.prepareStatement(sql);

            //第一次给占位符传值
            ps.setString(1,"老王王");
            ps.setInt(2,101);
            int count = ps.executeUpdate(); //执行第一条update 语句
            System.out.println(count);

            //重新给占位符传值
            ps.setString(1,"老丽里");
            ps.setInt(2,1002);
            count = ps.executeUpdate();//执行第二条update 语句
            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
