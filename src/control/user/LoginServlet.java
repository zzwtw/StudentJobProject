package control.user;

import bean.UserInfo;
import dao.IUserAccountInfo;
import org.json.JSONObject;
import until.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录接口
 */
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // 获取前端传来的数据
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        UserInfo user = new UserInfo(account, password);
        // 登录账号
        int uid = IUserAccountInfo.login(user);
        ServletUtil.WriteJSONToResponse(resp, jsonObject -> {
            if (uid > 0) {
                // 登录成功
                jsonObject.put("msg", "success");
                jsonObject.put("uid", uid);
            } else {
                // 登录失败
                jsonObject.put("msg", "账号或密码错误");
            }
        });
    }
}
