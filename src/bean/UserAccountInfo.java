package bean;

public class UserAccountInfo {
    /**
     * 用户id
     * */
    public int uid;
    /**
     * 用户账号
     * */
    public String account;
    /**
     * 用户密码
     * */
    public String password;

    public UserAccountInfo() {

    }

    public UserAccountInfo(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public UserAccountInfo(int uid, String account, String password) {
        this(account,password);
        this.uid = uid;
    }
}
