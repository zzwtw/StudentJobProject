package bean;

import org.json.JSONPropertyName;

/**
 * 工作申请信息
 * */
public class JobApplicationInfo {
    /**
     * 被工作的工作id
     * */
    public int jid;
    /**
     * 申请用户
     * */
    public int uid;
    /**
     * 申请状态: 0表示申请中, 1表示申请成功, 2表示拒绝
     * */
    public int status;

    public JobApplicationInfo(){

    }

    public JobApplicationInfo(int jid,int uid){
        this.jid = jid;
        this.uid = uid;
    }

    public JobApplicationInfo(int jid,int uid,int status){
        this(jid, uid);
        this.status = status;
    }
}
