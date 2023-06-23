package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IUserAccountInfo {
    private static String uerName = "root";
    private static String password = "123456";
    private static String url = "jdbc:mysql://localhost:3306/student_job?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    // 账号注册 将账号密码插入到数据库中
    public static int insertUserInfo(String userAccount, String passWord) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection cnn = DriverManager.getConnection(url,uerName,password);
        String sql = "insert into useracountinfo(`account`,`password`) values('"+userAccount+"','"+passWord+"')";
        PreparedStatement pstmt = cnn.prepareStatement(sql);
        int rs = pstmt.executeUpdate();
        System.out.println(rs);
        return rs;
    }
}
