package dao;

import bean.UserInfo;
import until.JDBCUtil;

public class IUserAccountInfo {
    /**
     * 获取用户信息
     * */
    public static UserInfo getUserInfo(int uid) {
        UserInfo userInfo = new UserInfo();
        JDBCUtil.query("select * from user where uid = ?", resource -> {
            resource.statement.setInt(1, uid);
            resource.resultSet = resource.statement.executeQuery();
            if (resource.resultSet.next()) {
                userInfo.uid = uid;
                userInfo.account = resource.resultSet.getString("account");
            }
        });
        return userInfo;
    }

    /**
     * 账号注册
     */
    public static int register(UserInfo userInfo) {
        return JDBCUtil.query("insert into userinfo(account, password) values('"+userInfo.account+"', '"+userInfo.password+"')", resource -> {
//            resource.statement.setString(1, userInfo.account);
//            resource.statement.setString(2, userInfo.password);
            resource.intResult = resource.statement.executeUpdate();
        }).intResult;
    }

    /**
     * 账号登录
     * */
    public static int login(UserInfo userAccountInfo) {
        return JDBCUtil.query("select uid from userinfo where account = '"+userAccountInfo.account+"' and password = '"+userAccountInfo.password+"'", resource -> {
//            resource.statement.setString(1, userAccountInfo.account);
//            resource.statement.setString(2, userAccountInfo.password);
            resource.resultSet = resource.statement.executeQuery();
            if (resource.resultSet.next())
                resource.intResult = resource.resultSet.getInt(1);
        }).intResult;
    }
}
