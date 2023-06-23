package bean;

public class JobInfo {
    /**
     * 工作id
     * */
    public int jid;
    /**
     * 发布日期
     * */
    public String date;
    /**
     * 工作名称
     * */
    public String publish_name;
    /**
     * 工作内容
     * */
    public String work_content;
    /**
     * 地址
     * */
    public String address;
    /**
     * 发布者
     * */
    public int uid;

    public JobInfo(){

    }

    public JobInfo(String date,String publish_name,String work_content,String address,int uid){
        this.date = date;
        this.publish_name = publish_name;
        this.work_content = work_content;
        this.address = address;
        this.uid = uid;
    }

    public JobInfo(int jid,String date,String publish_name, String work_content,String address,int uid){
        this(date,publish_name,work_content,address,uid);
        this.jid = jid;
    }
}
