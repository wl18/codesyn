package jdbclogin;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: weilong
 * @Date: 2020/3/9 17:37
 */
public class LoginDemo {

    public static void main(String[] args) {
        //初始化界面
        Map<String,String> userLoginInfo = initUI();

        //验证用户名和密码
        boolean loginSuccess = login(userLoginInfo);
        //最后输出结果
        System.out.println(loginSuccess ? "登录成功" : "登录失败");
    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return false表示失败，true表示成功
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        //打标记的意识
        boolean loginSuccess = false;

        //单独定义变量
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");

        //JDBC代码
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
            //3.获取数据库操作对象
            stmt = conn.createStatement();
            //4.执行sql
            String sql = "select * from t_user where loginName = '"+loginName+"' and loginPwd = '"+loginPwd+"'";
            rs = stmt.executeQuery(sql);
            //5.处理结果集
            if (rs.next()){
                //登录成功
                loginSuccess = true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            //6.释放资源
            if (rs != null){
               try {
                   rs.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
            }
            if (stmt != null){
                try {
                    stmt.close();
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



        return loginSuccess;
    }

    /**
     * 初始化用户界面
     * @return 用户输入的用户名和登录信息
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);

        System.out.println("用户名：");
        String loginName = s.nextLine();

        System.out.println("密码：");
        String loginPwd = s.nextLine();

        Map<String,String> userLoginInfo  = new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("loginPwd",loginPwd);

        return userLoginInfo;
    }
}
