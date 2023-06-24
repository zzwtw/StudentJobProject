package control.job;

import bean.JobInfo;
import dao.IJobInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 获取工作信息接口
 * */
@WebServlet("/job/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 创建JSON数组，用来存JSON对象
        JSONArray jsonArray = new JSONArray();
        ArrayList<JobInfo> jobInfos = IJobInfo.search(null);
        // 对返回的对象进行处理，封装成json对象
        for (int i = 0; i < jobInfos.size(); i++) {
            JobInfo jobInfo = jobInfos.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("jid",jobInfo.jid);
            jsonObject.put("name",jobInfo.name);
            jsonObject.put("description",jobInfo.description);
            jsonObject.put("time", jobInfo.time);
            jsonObject.put("content", jobInfo.content);
            jsonObject.put("salary", jobInfo.salary);
            jsonObject.put("address", jobInfo.address);
            jsonObject.put("uid", jobInfo.uid);
            jsonArray.put(jsonObject);
        }

        // 对返回值进行编码的规范 并返回
        resp.getOutputStream().write(String.valueOf(jsonArray).getBytes("utf-8"));

    }

}
