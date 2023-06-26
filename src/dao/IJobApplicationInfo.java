package dao;

import bean.JobApplicationInfo;
import until.JDBCUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public class IJobApplicationInfo {
    /**
     * 工作申请
     */
    public static int apply(JobApplicationInfo jobApplicationInfo){
        return JDBCUtil.query("insert  into jobApplicationinfo(jid, uid, status) values('" + jobApplicationInfo.jid + "', '" + jobApplicationInfo.uid + "', '" + jobApplicationInfo.status + "') on duplicate key update jid='" + jobApplicationInfo.jid + "',uid='" + jobApplicationInfo.uid + "',status='"+jobApplicationInfo.status+"'", resource -> {
//            resource.statement.setInt(1, jobApplicationInfo.jid);
//            resource.statement.setInt(2, jobApplicationInfo.uid);
//            resource.statement.setInt(3, jobApplicationInfo.status);
            resource.intResult = resource.statement.executeUpdate();
        }).intResult;
    }

    /**
     * 修改工作申请状态
     */
    public static int alter(JobApplicationInfo jobApplicationInfo) {
        return JDBCUtil.query("update jobApplicationinfo set status = ? where jid = ? and uid = ?", resource -> {
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
        JDBCUtil.query("select jid, status from jobApplicationinfo where uid = ?", resource -> {
            resource.statement.setInt(1, uid);
            resource.resultSet = resource.statement.executeQuery();
            while (resource.resultSet.next()) {
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
     */
    public static ArrayList<JobApplicationInfo> serachRecieved(int uid) {
        ArrayList<JobApplicationInfo> jobApplicationInfos = new ArrayList<>();
        JDBCUtil.query("select jobApplicationinfo.jid, jobApplicationinfo.uid,jobApplicationinfo.status from jobApplicationinfo, jobinfo where jobinfo.jid = jobApplicationinfo.jid and jobinfo.uid = '"+uid+"'", resource -> {
            resource.resultSet = resource.statement.executeQuery();
            while (resource.resultSet.next()) {
                jobApplicationInfos.add(new JobApplicationInfo(
                        resource.resultSet.getInt(1),
                        resource.resultSet.getInt(2),
                        resource.resultSet.getInt(3)
                ));
            }
        });
        return jobApplicationInfos;
    }
}
