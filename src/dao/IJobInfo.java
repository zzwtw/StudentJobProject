package dao;

import bean.JobInfo;

import java.sql.*;
import java.util.ArrayList;
public class IJobInfo {
    private static String uerName = "root";
    private static String password = "123456";
    private static String url = "jdbc:mysql://localhost:3306/student_job?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

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
    public static ArrayList<JobInfo> SearchAllInfo() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        ArrayList<JobInfo> jobInfoList = new ArrayList<JobInfo>();
        Connection conn = DriverManager.getConnection(url,uerName,password);
        String sql = "select date,publish_name,work_content,address from jobinfo";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            String date = rs.getString(1);
            String publish_name = rs.getString(2);
            String work_content = rs.getString(3);
            String address = rs.getString(4);
            JobInfo jobInfo = new JobInfo(date,publish_name,work_content,address);
            jobInfoList.add(jobInfo);
        }
        System.out.println(jobInfoList);
        return jobInfoList;

    }

}
