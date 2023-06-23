package dao;

import bean.JobInfo;
import until.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

public class IJobInfo {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(url,uerName,password);
//            String sql = "insert into jobinfo(date,publish_name,work_content,address) values('2023-06-21','李四','发传单','长沙理工大学')";
//            PreparedStatement pstmt = conn.prepareStatement(sql);
//            int i = pstmt.executeUpdate();
//            if(i==1){
//                System.out.println("sql语句执行成功");
//            }
//            pstmt.close();
//            conn.close();
//        }catch (ClassNotFoundException e){
//            System.out.println("驱动加载失败");
//        } catch (SQLException e) {
//            System.out.println("SQL语句执行失败");
//            throw new RuntimeException(e);
//
//        }
//        ArrayList<JobInfo> jobInfoList = SearchAllInfo();
//        for(int i=0;i<jobInfoList.size();i++){
//            System.out.println(jobInfoList.get(i).address);
//            System.out.println(jobInfoList.get(i).work_content);
//            System.out.println(jobInfoList.get(i).publish_name);
//            System.out.println(jobInfoList.get(i).date);
//        }

    }

    public static ArrayList<JobInfo> search(JobInfo jobInfoCondition) {
        ArrayList<JobInfo> jobInfoList = new ArrayList<JobInfo>();
        Connection conn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 拼接sql
            String sql = "select jid, date, publish_name, work_content, address from jobinfo";
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
                int jid = rs.getInt("jid");
                String date = rs.getString("date");
                String publish_name = rs.getString("publish_name");
                String work_content = rs.getString("work_content");
                String address = rs.getString("address");
                int uid = rs.getInt("uid");
                JobInfo jobInfo = new JobInfo(jid, date, publish_name, work_content, address, uid);
                jobInfoList.add(jobInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pstmt, rs);
        }

        return jobInfoList;
    }

    public static int publish(JobInfo job) {
        Connection conn = JDBCUtil.getConn();
        PreparedStatement pstmt = null;
        int rs = 0;
        try {
            pstmt = conn.prepareStatement("insert into jobinfo(date, publish_name, work_content, address, uid) value(?, ?, ?, ?, ?)");
            pstmt.setString(1, job.date);
            pstmt.setString(2, job.publish_name);
            pstmt.setString(3, job.work_content);
            pstmt.setString(4, job.address);
            pstmt.setInt(5, job.uid);
            rs = pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(conn, pstmt, null);
        }
        return rs;
    }

}
