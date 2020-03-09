package jdbclogin;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * 解决SQL语句的注入问题，用PreparedStatement
 * @Author: weilong
 * @Date: 2020/3/9 20:15
 */
public class LoginDemo2 {
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
        PreparedStatement ps = null;//这里使用PreparedStatement（预编译的数据库操作对象）
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
            //3.获取预编译的数据库操作对象
            //sql语句的框子。其中一个？，表示一个占位符，一个？将来接收一个“值”，注意：占位符不能使用单引号括起来。
            String sql = "select * from t_user where loginName = ? and loginPwd = ?";
            //程序执行到这里，会发送sql语句框子到DBMS,然后DBMS进行sql语句的预先编译。
            ps = conn.prepareStatement(sql);
            //给占位符？传值（第一个问号下标是1，第二个问号下标是2，JDBC中所有的下标从1开始）
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);

            //4.执行sql
            rs = ps.executeQuery();
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
