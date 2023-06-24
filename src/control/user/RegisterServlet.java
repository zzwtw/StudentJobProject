package control.user;

import bean.UserInfo;
import dao.IUserAccountInfo;

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
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // 获取前端传来的数据
        UserInfo user = new UserInfo(
                req.getParameter("account"),
                req.getParameter("password")
        );
        // 注册账号 存入数据库中
        int rs = IUserAccountInfo.register(user);

        try {
            if (rs > 0) {
                // 注册成功,跳转登录界面
                resp.sendRedirect("login.jsp");
            } else {
                // 注册失败,继续注册界面
                req.setAttribute("info", "注册失败");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
