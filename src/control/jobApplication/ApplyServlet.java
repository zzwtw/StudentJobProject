package control.jobApplication;

import bean.JobApplicationInfo;
import dao.IJobApplicationInfo;
import org.json.JSONObject;
import until.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/jobApplication/apply")
public class ApplyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        JobApplicationInfo jobApplicationInfo = new JobApplicationInfo(
                Integer.parseInt(req.getParameter("jid")),
                Integer.parseInt(req.getParameter("uid")),
                Integer.parseInt(req.getParameter("status"))
        );

        int rs = IJobApplicationInfo.apply(jobApplicationInfo);

        ServletUtil.WriteJSONToResponse(resp, jsonObject -> {
            if (rs > 0) {
                //申请成功
                jsonObject.put("msg","success");
            } else {
                //申请失败
                jsonObject.put("msg","error");
            }
        });
    }
}
