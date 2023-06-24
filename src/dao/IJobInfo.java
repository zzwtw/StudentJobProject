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
                        resource.resultSet.getFloat("salary"),
                        resource.resultSet.getString("address"),
                        resource.resultSet.getInt("uid")
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
        return search("select * from job", null);
    }

    /**
     * 搜索指定jid的工作信息
     */
    public static ArrayList<JobInfo> search(int jid, int uid) {
        String sql = "select * from job where 1 = 1";

        if (jid != 0)
            sql += "and jid = " + jid;
        if (uid != 0)
            sql += "and uid = ?" + uid;

        return search(sql, null);
    }

    /**
     * 发布工作
     */
    public static int publish(JobInfo jobInfo) {
        return JDBCUtil.query("insert into job(name, description, time, content, salary, address, uid) value(?, ?, ?, ?, ?, ?, ?)", resource -> {
            resource.statement.setString(1, jobInfo.name);
            resource.statement.setString(2, jobInfo.description);
            resource.statement.setString(3, jobInfo.time);
            resource.statement.setString(4, jobInfo.content);
            resource.statement.setFloat(5, jobInfo.salary);
            resource.statement.setString(6, jobInfo.address);
            resource.statement.setInt(5, jobInfo.uid);
            resource.intResult = resource.statement.executeUpdate();
        }).intResult;
    }

    interface ISearchHandler {
        public void invoke(PreparedStatement pstmt) throws SQLException;
    }
}
