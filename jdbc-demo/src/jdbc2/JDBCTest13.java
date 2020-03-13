package jdbc2;

import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: weilong
 * @Date: 2020/3/13 23:11
 * 这个程序开启一个事务，这个事务专门进行查询，并且使用行级锁/悲观锁，锁住相关的记录
 */
public class JDBCTest13 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            //开启事务
            conn.setAutoCommit(false);

            String sql = "select id,name,score from student where score = ? for update";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,100);

            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "," +
                        rs.getString("name") + "," + rs.getInt("score"));
            }

            //提交事务（事务结束）
            conn.commit();
        } catch (Exception e) {
            if(conn != null) {
                try {
                    //回滚事务，事务结束
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }
}
