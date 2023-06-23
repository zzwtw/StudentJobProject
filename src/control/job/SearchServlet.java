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

@WebServlet("/jobInfo/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 创建JSON数组，用来存JSON对象
        JSONArray jsonArray = new JSONArray();
        ArrayList<JobInfo> jobInfo = IJobInfo.search(null);
        // 对返回的对象进行处理，封装成json对象
        for (int i = 0; i < jobInfo.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("address", jobInfo.get(i).address);
            jsonObject.put("work_content", jobInfo.get(i).work_content);
            jsonObject.put("publish_name", jobInfo.get(i).publish_name);
            jsonObject.put("date", jobInfo.get(i).date);
            jsonArray.put(jsonObject);
        }

        // 对返回值进行编码的规范 并返回
        resp.getOutputStream().write(String.valueOf(jsonArray).getBytes("utf-8"));

    }

}
