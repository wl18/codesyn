package jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @Author: weilong
 * @Date: 2020/3/9 00:32
 */
public class JDBCTest1 {
    public static void main(String[] args) {
        //使用资源绑定器绑定属性配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement stmt = null;
        try{
            //1、注册驱动
            Class.forName(driver);
            //2.获取连接
            conn = DriverManager.getConnection(url, user, password);
            //3.获取数据库操作对象
            stmt = conn.createStatement();
            //4.执行SQL语句
            String sql = "update goods set name = 'lola' where id = 1001";
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? "修改成功" : "修改失败");
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
//            e.printStackTrace();
        }finally{
            //6.释放资源
            if(stmt != null){
                try{
                    stmt.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }


}
