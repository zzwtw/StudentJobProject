package control.jobApplication;

import bean.JobApplicationInfo;
import dao.IJobApplicationInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/jobApplication/apply")
public class ApplyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JobApplicationInfo jobApplicationInfo = new JobApplicationInfo(
                Integer.parseInt(req.getParameter("jid")),
                Integer.parseInt(req.getParameter("uid"))
        );

        int rs = IJobApplicationInfo.apply(jobApplicationInfo);
        try {
            if (rs > 0) {
                //申请成功

            } else {
                //申请失败

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
