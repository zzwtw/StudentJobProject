package bean;

/**
 * 工作信息
 * */
public class JobInfo {
    /**
     * 工作id
     */
    public int jid;
    /**
     * 工作名称
     */
    public String name;
    /**
     * 工作描述
     */
    public String description;
    /**
     * 工作时间 描述为 周一到周五 8：00-12：00 14：00-18：00
     */
    public String time;
    /**
     * 工作内容
     */
    public String content;
    /**
     * 薪水
     */
    public String salary;
    /**
     * 工作地址
     */
    public String address;
    /**
     * 发布者
     */
    public int uid;
    /**
     * 申请状态
     */
    public int status;
    public JobInfo() {

    }

    public JobInfo(int jid,String name, String description, String time, String content, String salary, String address, int uid) {
        this.time = time;
        this.name = name;
        this.content = content;
        this.address = address;
        this.uid = uid;
        this.salary = salary;
        this.description = description;
        this.jid = jid;
    }

    public JobInfo(int jid, String name, String description, String time, String content, String salary, String address, int uid,int status) {
//        this(name, description, time, content, salary, address, uid,status);
        this.jid = jid;
        this.name = name;
        this.description = description;
        this.time = time;
        this.content = content;
        this.salary = salary;
        this.address = address;
        this.uid = uid;
        this.status = status;
    }
    public JobInfo(String name, String description, String time, String content, String salary, String address, int uid,int status) {
//        this(name, description, time, content, salary, address, uid,status);
        this.name = name;
        this.description = description;
        this.time = time;
        this.content = content;
        this.salary = salary;
        this.address = address;
        this.uid = uid;
        this.status = status;
    }
}
