package control.jobApplication;

import bean.JobApplicationInfo;
import dao.IJobApplicationInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import until.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * 搜索接收到的工作申请信息
 */
@WebServlet("/jobApplication/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        int uid = Integer.parseInt(req.getParameter("uid"));
        // 0 代表搜索用户收到的工作申请，1 代表搜索用户发出的工作申请
        int filter = Integer.parseInt(req.getParameter("filter"));

        ArrayList<JobApplicationInfo> jobApplicationInfos = filter == 0 ? IJobApplicationInfo.serachRecieved(uid):IJobApplicationInfo.searchSend(uid);

        ServletUtil.WriteJSONToResponse(resp, jsonObject -> {
            JSONArray jsonArray = new JSONArray();

            for (JobApplicationInfo jobApplicationInfo : jobApplicationInfos) {
                JSONObject temp = new JSONObject();
                temp.put("jid", jobApplicationInfo.jid);
                temp.put("uid", jobApplicationInfo.uid);
                temp.put("status", jobApplicationInfo.status);
                jsonArray.put(temp);
            }

            jsonObject.put("msg", "success");
            jsonObject.put("result", jsonArray);
        });
    }
}
