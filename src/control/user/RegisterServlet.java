package control.user;

import bean.UserInfo;
import dao.IUserAccountInfo;
import org.json.JSONObject;
import until.ServletUtil;

import javax.servlet.Servlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户注册接口
 * */
@WebServlet("/user/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        // 获取前端传来的数据
        UserInfo user = new UserInfo(
                req.getParameter("account"),
                req.getParameter("password")
        );

        // 注册账号 存入数据库中
        int rs = IUserAccountInfo.register(user);

        ServletUtil.WriteJSONToResponse(resp,jsonObject -> {
            if (rs > 0) {
                // 注册成功,跳转登录界面
                jsonObject.put("msg","success");
            } else {
                // 注册失败,继续注册界面
                jsonObject.put("msg","error");
            }
        });
    }
}
