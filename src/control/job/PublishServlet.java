package control.job;

import bean.JobInfo;
import dao.IJobInfo;
import org.json.JSONObject;
import until.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 发布工作的接口
 */
@WebServlet("/job/publish")
public class PublishServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        JobInfo jobInfo = new JobInfo(
                req.getParameter("name"),
                req.getParameter("description"),
                req.getParameter("time"),
                req.getParameter("content"),
//                Float.parseFloat(req.getParameter("salary")),
                req.getParameter("salary"),
                req.getParameter("address"),
                Integer.parseInt(req.getParameter("uid"))
        );

        int rs = IJobInfo.publish(jobInfo);
        ServletUtil.WriteJSONToResponse(resp, jsonObject -> {
            if (rs > 0) {
                // 发布成功
                jsonObject.put("msg","success");
            } else {
                // 发布失败
                jsonObject.put("msg","error");
            }
        });
    }
}
