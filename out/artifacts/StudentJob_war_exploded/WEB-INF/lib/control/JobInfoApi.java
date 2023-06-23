package control;

import bean.JobInfo;
import com.alibaba.fastjson.JSON;
import dao.IJobInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/jobInfo")
public class JobInfoApi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String json = "{\n" +
//                "\t\"userId\": 1,\n" +
//                "\t\"userName\": \"pan_junbiao的博客\",\n" +
//                "\t\"blogUrl\": \" https: //blog.csdn.net/pan_junbiao\",\n" +
//                "\t\"sex\": \"男\"\n" +
//                "}";
        Class.forName("com.mysql.cj.jdbc.Driver");
        ArrayList<JobInfo> jobInfoList = new ArrayList<JobInfo>();
        Connection conn = DriverManager.getConnection(url,uerName,password);
        String sql = "select date,publish_name,work_content,address from jobinfo";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){
            String date = rs.getString(1);
            String publish_name = rs.getString(2);
            String work_content = rs.getString(3);
            String address = rs.getString(4);
            JobInfo jobInfo = new JobInfo(date,publish_name,work_content,address);
            jobInfoList.add(jobInfo);
        }
        System.out.println(jobInfoList);
        pstmt.close();
        conn.close();
        //返回结果
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // 获取PrintWriter对象
        PrintWriter out = resp.getWriter();
        out.print(json);
        // 释放PrintWriter对象
        out.flush();
        out.close();
    }

}
