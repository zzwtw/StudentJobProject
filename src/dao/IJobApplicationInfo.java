package dao;

import bean.JobApplicationInfo;
import until.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class IJobApplicationInfo {
    /**
     * 工作申请
     * */
    public static int apply(JobApplicationInfo jobApplicationInfo){
        Connection cnn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = cnn.prepareStatement("insert into jobApplication(jid, uid, status) values(?, ?)");
            pstmt.setInt(1, jobApplicationInfo.jid);
            pstmt.setInt(2, jobApplicationInfo.uid);
            pstmt.setInt(3, jobApplicationInfo.status);
            rs = pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(cnn, pstmt, null);
        }

        return rs;
    }

    /**
     * 修改工作申请状态
     * */
    public static int alter(JobApplicationInfo jobApplicationInfo){
        Connection cnn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = cnn.prepareStatement("update jobApplication set status = ? where jid = ? and uid = ?");
            pstmt.setInt(1, jobApplicationInfo.status);
            pstmt.setInt(2, jobApplicationInfo.jid);
            pstmt.setInt(3, jobApplicationInfo.uid);
            rs = pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(cnn, pstmt, null);
        }

        return rs;
    }

}
