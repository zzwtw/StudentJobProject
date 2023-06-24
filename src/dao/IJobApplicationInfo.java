package dao;

import bean.JobApplicationInfo;
import until.JDBCUtil;

import java.util.ArrayList;

public class IJobApplicationInfo {
    /**
     * 工作申请
     */
    public static int apply(JobApplicationInfo jobApplicationInfo) {
        return JDBCUtil.query("insert into jobApplication(jid, uid, status) values(?, ?, ?)", resource -> {
            resource.statement.setInt(1, jobApplicationInfo.jid);
            resource.statement.setInt(2, jobApplicationInfo.uid);
            resource.statement.setInt(3, jobApplicationInfo.status);
            resource.intResult = resource.statement.executeUpdate();
        }).intResult;
    }

    /**
     * 修改工作申请状态
     */
    public static int alter(JobApplicationInfo jobApplicationInfo) {
        return JDBCUtil.query("update jobApplication set status = ? where jid = ? and uid = ?", resource -> {
            resource.statement.setInt(1, jobApplicationInfo.status);
            resource.statement.setInt(2, jobApplicationInfo.jid);
            resource.statement.setInt(3, jobApplicationInfo.uid);
            resource.intResult = resource.statement.executeUpdate();
        }).intResult;
    }

    /**
     * 搜索用户申请的所有工作
     */
    public static ArrayList<JobApplicationInfo> searchSend(int uid) {
        ArrayList<JobApplicationInfo> jobApplicationInfos = new ArrayList<>();
        JDBCUtil.query("select jid, status from jobApplication where uid = ?", resource -> {
            resource.statement.setInt(1,uid);
            resource.resultSet = resource.statement.executeQuery();
            while(resource.resultSet.next()){
                jobApplicationInfos.add(new JobApplicationInfo(
                        uid,
                        resource.resultSet.getInt("jid"),
                        resource.resultSet.getInt("status")
                ));
            }
        });
        return jobApplicationInfos;
    }
    /**
     * 搜索用户接收到的工作申请
     * */
    public static ArrayList<JobApplicationInfo> serachRecieved(int uid){
        ArrayList<JobApplicationInfo> jobApplicationInfos = new ArrayList<>();
        JDBCUtil.query("select job.jid, status from jobApplication，job where job.jid = jobApplication.jid and job.uid = ?", resource -> {
            resource.statement.setInt(1,uid);
            resource.resultSet = resource.statement.executeQuery();
            while(resource.resultSet.next()){
                jobApplicationInfos.add(new JobApplicationInfo(
                        uid,
                        resource.resultSet.getInt("jid"),
                        resource.resultSet.getInt("status")
                ));
            }
        });
        return jobApplicationInfos;
    }
}
