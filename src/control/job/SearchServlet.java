package control.job;

import bean.JobInfo;
import dao.IJobInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import until.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 获取工作信息接口
 */
@WebServlet("/job/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int jid = req.getParameter("jid").matches("\\d+") ? Integer.parseInt(req.getParameter("jid")) : 0;
        int uid = req.getParameter("uid").matches("\\d+") ? Integer.parseInt(req.getParameter("uid")) : 0;
        ArrayList<JobInfo> jobInfos;
        if (jid == 0 && uid == 0) {
            jobInfos = IJobInfo.search();
        } else if (uid != 0 && jid == 0) {
            jobInfos = IJobInfo.search(uid);
        } else {
            jobInfos = IJobInfo.search(jid, uid);
        }
        ArrayList<JobInfo> finalJobInfos = jobInfos;
        ServletUtil.WriteJSONToResponse(resp, jsonObject -> {
            // 创建JSON数组，用来存JSON对象
            JSONArray jsonArray = new JSONArray();
            // 对返回的对象进行处理，封装成json对象
            for (JobInfo jobInfo : finalJobInfos) {
                JSONObject temp = new JSONObject();
                temp.put("jid", jobInfo.jid);
                temp.put("name", jobInfo.name);
                temp.put("description", jobInfo.description);
                temp.put("time", jobInfo.time);
                temp.put("content", jobInfo.content);
                temp.put("salary", jobInfo.salary);
                temp.put("address", jobInfo.address);
                temp.put("uid", jobInfo.uid);
                temp.put("status",jobInfo.status);
                jsonArray.put(temp);
            }
            jsonObject.put("msg", "success");
            jsonObject.put("result", jsonArray);
        });

    }

}
