package jdbc1;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @Author: weilong
 * @Date: 2020/3/9 15:33
 */
public class JDBCTest2 {
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
            //1.注册驱动
            Class.forName(driver);
            //2.获取链接
            conn = DriverManager.getConnection(url,user,password);
            //3.获取数据库操作对象
            stmt = conn.createStatement();
            //4.执行SQL语句
            String sql = "create table if not exists student(id int primary key,name varchar(20),score int)";
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 0 ? "建表成功" : "建表失败");
        }catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
//            e.printStackTrace();
        }finally {
            try{
                if(stmt != null){
                    stmt.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            try{
                if(conn != null){
                    conn.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
