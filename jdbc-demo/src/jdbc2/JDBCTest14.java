package jdbc2;

import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Author: weilong
 * @Date: 2020/3/13 23:12
 * 这个程序负责修改被锁定的记录
 */
public class JDBCTest14 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);

            String sql = "update student set score = score + 1 where score = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,100);
            int count = ps.executeUpdate();
            System.out.println(count);

            conn.commit();
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }
}
