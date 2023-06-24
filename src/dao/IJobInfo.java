package dao;

import bean.JobInfo;
import until.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

public class IJobInfo {
    /**
     * 搜索符合条件的工作
     * */
    public static ArrayList<JobInfo> search(JobInfo jobInfoCondition) {
        ArrayList<JobInfo> jobInfoList = new ArrayList<>();
        Connection conn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 拼接sql
            String sql = "select jid, date, publish_name, work_content, address from job";
//            if (jobInfoCondition != null) {
//                sql += "where 1 <> 1 and";
//                if (jobInfoCondition.jid != 0) {
//                    sql += "jid =="
//                }
//            }

            //获取查询结果
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                JobInfo jobInfo = new JobInfo(
                        rs.getInt("jid"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("time"),
                        rs.getString("content"),
                        rs.getFloat("salary"),
                        rs.getString("address"),
                        rs.getInt("uid")
                );

                jobInfoList.add(jobInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pstmt, rs);
        }

        return jobInfoList;
    }

    /**
     * 发布工作
     * */
    public static int publish(JobInfo jobInfo) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = conn.prepareStatement("insert into job(name, description, time, content, salary, address, uid) value(?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, jobInfo.name);
            pstmt.setString(2, jobInfo.description);
            pstmt.setString(3, jobInfo.time);
            pstmt.setString(4, jobInfo.content);
            pstmt.setFloat(5, jobInfo.salary);
            pstmt.setString(6, jobInfo.address);
            pstmt.setInt(5, jobInfo.uid);
            rs = pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pstmt, null);
        }
        return rs;
    }

}
