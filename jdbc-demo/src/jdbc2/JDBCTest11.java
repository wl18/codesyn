package jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: weilong
 * @Date: 2020/3/13 21:35
 */
public class JDBCTest11 {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root"
            ,"root");
            //将自动提交机制改为手动提交
            conn.setAutoCommit(false);//开启事务

            //3.获取预编译的数据库操作对象
            String sql = "update t_act set balance = ? where actno = ?";
            ps = conn.prepareStatement(sql);

            //给？号传值
            ps.setDouble(1,10000);
            ps.setInt(2,111);
            int count = ps.executeUpdate();

            //这里出现异常，上面的事务却已提交了，下面的没有提交，则会出现丢失10000块钱
            //所以我们应该把JDBC事务的自动提交改为手动提交
//            String s = null;
//            s.toString();

            //给？号传值
            ps.setDouble(1,10000);
            ps.setInt(2,222);
            count += ps.executeUpdate();

            System.out.println(count == 2 ? "转账成功" : "转账失败");

            //程序能够走到这里说明以上程序没有异常，事务结束，手动提交数据
            conn.commit();//提交事务

        } catch (Exception e) {
            //回滚事务
            if(conn != null) {
                try {
                    conn.rollback(); //回滚事务
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            //释放资源
            if (ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
