package dao;

import bean.JobInfo;
import until.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;

public class IJobInfo {
    /**
     * 根据指定sql获取job信息
     */
    private static ArrayList<JobInfo> search(String sql, ISearchHandler searchHandler) {
        ArrayList<JobInfo> jobInfoList = new ArrayList<>();
        JDBCUtil.query(sql, resource -> {
            //获取查询结果
            if (searchHandler != null)
                searchHandler.invoke(resource.statement);
            resource.resultSet = resource.statement.executeQuery();
            while (resource.resultSet.next()) {

                JobInfo jobInfo = new JobInfo(
                        resource.resultSet.getInt("jid"),
                        resource.resultSet.getString("name"),
                        resource.resultSet.getString("description"),
                        resource.resultSet.getString("time"),
                        resource.resultSet.getString("content"),
                        resource.resultSet.getString("salary"),
                        resource.resultSet.getString("address"),
                        resource.resultSet.getInt("uid")
                );
                jobInfoList.add(jobInfo);
            }
        });
        return jobInfoList;
    }

    /**
     * 根据指定sql获取jobApplication信息
     */
    private static ArrayList<JobInfo> searchApplication(String sql, ISearchHandler searchHandler) {
        ArrayList<JobInfo> jobInfoList = new ArrayList<>();
        JDBCUtil.query(sql, resource -> {
            //获取查询结果
            if (searchHandler != null)
                searchHandler.invoke(resource.statement);
            resource.resultSet = resource.statement.executeQuery();
            while (resource.resultSet.next()) {

                JobInfo jobInfo = new JobInfo(
                        resource.resultSet.getInt("jid"),
                        resource.resultSet.getString("name"),
                        resource.resultSet.getString("description"),
                        resource.resultSet.getString("time"),
                        resource.resultSet.getString("content"),
                        resource.resultSet.getString("salary"),
                        resource.resultSet.getString("address"),
                        resource.resultSet.getInt("uid"),
                        resource.resultSet.getInt("status")
                );
                jobInfoList.add(jobInfo);
            }
        });
        return jobInfoList;
    }

    /**
     * 获取全部信息
     */
    public static ArrayList<JobInfo> search() {
        return search("select * from jobinfo", null);
    }

    /**
     * 搜索指定jid的工作信息
     */
    public static ArrayList<JobInfo> search(int jid, int uid) {
        String sql = "select * from jobinfo where 1 = 1";

        if (jid != 0)
            sql += "and jid = " + jid;
        if (uid != 0)
            sql += "and uid = ?" + uid;

        return search(sql, null);
    }

    /**
     * 搜索指定uid的工作信息
     */
    public static ArrayList<JobInfo> search(int uid) {
        String sql = "select * from jobinfo,jobapplicationinfo where jobapplicationinfo.uid = '" + uid + "' and jobinfo.jid = jobapplicationinfo.jid";

//        if (uid != 0)
//            sql += "and uid = ?" + uid;

        return searchApplication(sql, null);
    }

    /**
     * 发布工作
     */
    public static int publish(JobInfo jobInfo) {
        return JDBCUtil.query("insert into jobinfo(name, description, time, content, salary, address, uid) value('" + jobInfo.name + "', '" + jobInfo.description + "', '" + jobInfo.time + "', '" + jobInfo.content + "', '" + jobInfo.salary + "', '" + jobInfo.address + "', '" + jobInfo.uid + "')", resource -> {
            resource.intResult = resource.statement.executeUpdate();
        }).intResult;
    }

    interface ISearchHandler {
        public void invoke(PreparedStatement pstmt) throws SQLException;
    }
}
