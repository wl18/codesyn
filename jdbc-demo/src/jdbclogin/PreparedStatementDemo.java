package jdbclogin;

import java.sql.*;

/**
 * @Author: weilong
 * @Date: 2020/3/9 22:37
 */
public class PreparedStatementDemo {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
//        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root"
                    ,"root");
//            String sql = "insert into student(id,name,score) values(?,?,?)";
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1,102);
//            ps.setString(2,"李四");
//            ps.setInt(3,99);

//            String sql = "update student set name = ?,score = ? where id = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1,"卢拉");
//            ps.setInt(2,100);
//            ps.setInt(3,102);

            String sql = "delete from student where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,102);
            //执行sql
            int count = ps.executeUpdate();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
