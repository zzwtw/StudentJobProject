package control;

import bean.JobInfo;
import dao.IJobInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/jobInfo")
public class JobInfoApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // 创建输出流对象，返回值
        ServletOutputStream outputStream = resp.getOutputStream();
        // 用于接收IJobInfo传回的对象列表
        ArrayList<JobInfo> jobInfo;
        // 创建JSON数组，用来存JSON对象
        JSONArray jsonArray = new JSONArray();
        try {
            jobInfo = IJobInfo.SearchAllInfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        outputStream.write(String.valueOf(jsonArray).getBytes("utf-8"));

    }

}
