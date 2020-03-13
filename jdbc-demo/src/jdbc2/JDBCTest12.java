package jdbc2;

import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author: weilong
 * @Date: 2020/3/13 22:33
 * 这个程序两个任务:
 *      第一：测试DBUtil是否好用
 *      第二：模糊查询怎么写？
 */
public class JDBCTest12 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //获取连接
            conn = DBUtil.getConnection();
            //获取预编译的数据库操作对象

            //错误的写法
            /*
            String sql = "select name from student where name like '_a%'";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"a");
            */

            //获取预编译处理
            String sql = "select name from student where name like ?";
            ps = conn.prepareStatement(sql);

            ps.setString(1,"_A%");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            DBUtil.close(conn, ps, rs);
        }
    }

}
