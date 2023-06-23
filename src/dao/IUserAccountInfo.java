package dao;

import bean.UserAccountInfo;
import until.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IUserAccountInfo {
    public static UserAccountInfo getUserAccountInfo(int uid) {
        Connection cnn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserAccountInfo userAccountInfo = null;
        try {
            pstmt = cnn.prepareStatement("select * from useracountinfo where uid = ?");
            pstmt.setInt(1, uid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                userAccountInfo = new UserAccountInfo(uid,
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

    // 账号注册 将账号密码插入到数据库中
    public static int register(UserAccountInfo userAccountInfo) {
        Connection cnn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = cnn.prepareStatement("insert into useracountinfo(`account`, `password`) values(?, ?)");
            pstmt.setString(1, userAccountInfo.account);
            pstmt.setString(2, userAccountInfo.password);
            rs = pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(cnn, pstmt, null);
        }

        return rs;
    }

    public static int login(UserAccountInfo userAccountInfo) {
        Connection cnn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int uid = 0;
        try {
            pstmt = cnn.prepareStatement("select uid from useracountinfo where account = ? and password = ?");
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
