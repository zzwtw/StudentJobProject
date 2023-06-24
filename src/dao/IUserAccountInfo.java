package dao;

import bean.UserInfo;
import until.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IUserAccountInfo {
    /**
     * 获取用户信息
     * */
    public static UserInfo getUserInfo(int uid) {
        Connection cnn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserInfo userAccountInfo = null;
        try {
            pstmt = cnn.prepareStatement("select * from user where uid = ?");
            pstmt.setInt(1, uid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                userAccountInfo = new UserInfo(
                        uid,
                        rs.getString("account"),
                        rs.getString("password"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(cnn, pstmt, null);
        }

        return userAccountInfo;
    }

    /**
     * 账号注册
     */
    public static int register(UserInfo userInfo) {
        Connection cnn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = cnn.prepareStatement("insert into user(account, password) values(?, ?)");
            pstmt.setString(1, userInfo.account);
            pstmt.setString(2, userInfo.password);
            rs = pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(cnn, pstmt, null);
        }

        return rs;
    }

    /**
     * 账号登录
     * */
    public static int login(UserInfo userAccountInfo) {
        Connection cnn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int uid = 0;
        try {
            pstmt = cnn.prepareStatement("select uid from user where account = ? and password = ?");
            pstmt.setString(1, userAccountInfo.account);
            pstmt.setString(2, userAccountInfo.password);
            rs = pstmt.executeQuery();
            if (rs.next())
                uid = rs.getInt(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(cnn, pstmt, null);
        }

        return uid;
    }
}
