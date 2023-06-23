package control.userAccount;

import bean.UserAccountInfo;
import dao.IUserAccountInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String userAccount = req.getParameter("userAccount");
        String password = req.getParameter("userPassword");
        UserAccountInfo user = new UserAccountInfo(userAccount, password);
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
