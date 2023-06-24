package control.jobApplication;

import bean.JobApplicationInfo;
import dao.IJobApplicationInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 修改工作申请状态
 * */
@WebServlet("/jobApplication/alter")
public class AlterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        JobApplicationInfo jobApplicationInfo = new JobApplicationInfo(
                Integer.parseInt(req.getParameter("jid")),
                Integer.parseInt(req.getParameter("uid")),
                Integer.parseInt(req.getParameter("status"))
        );

        int rs = IJobApplicationInfo.alter(jobApplicationInfo);

        if(rs> 0){
            //修改成功
        }else {
            //修改失败
        }
    }
}
