package control.user;

import bean.UserInfo;
import dao.IUserAccountInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录接口
 * */
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        // 获取前端传来的数据
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        UserInfo user = new UserInfo(account, password);
        // 登录账号
        int uid = IUserAccountInfo.login(user);

        try {
            if (uid > 0) {
                // 登录成功,设置session值
                req.getSession().setAttribute("uid", uid);
                resp.sendRedirect("index.jsp");
            } else {
                // 登录失败,继续登录状态
                req.setAttribute("info", "登录失败");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
