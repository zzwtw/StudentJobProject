package control.job;

import bean.JobInfo;
import dao.IJobInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/jobInfo/publish")
public class PublishServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");
        String address = req.getParameter("address");
        String publish_name = req.getParameter("publish_name");
        String work_content = req.getParameter("work_content");
        int uid = Integer.parseInt(req.getParameter("uid"));

        JobInfo jobInfo = new JobInfo(date, publish_name, work_content, address, uid);

        int rs = IJobInfo.publish(jobInfo);
        if (rs > 0) {
            // 发布成功

        } else {
            // 发布失败
        }
    }
}
