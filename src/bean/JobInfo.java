package bean;

public class JobInfo {
    public String date;
    public String publish_name;
    public String work_content;
    public String address;
    public JobInfo(String date,String publish_name,String work_content,String address){
        this.date = date;
        this.publish_name = publish_name;
        this.work_content = work_content;
        this.address = address;
    }
}
