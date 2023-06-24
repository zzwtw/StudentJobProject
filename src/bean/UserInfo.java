package bean;

/**
 * 用户信息
 * */
public class UserInfo {
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

    public UserInfo() {

    }

    public UserInfo(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public UserInfo(int uid, String account, String password) {
        this(account,password);
        this.uid = uid;
    }
}
