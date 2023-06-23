package control;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import org.json.JSONObject;

@WebServlet(urlPatterns = "/servlet/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//        System.out.println("@@@");
//        resp.setContentType("application/json;charset=UTF-8");
//        ServletOutputStream out = resp.getOutputStream();
        HashMap<String,String> ha = new HashMap<String,String>();
        ha.put("code","1");
//        System.out.println(ha);
//        JSONObject jsonObject = new JSONObject(ha);
//        System.out.println(jsonObject);
//        out.print(jsonObject.toString());

//        resp.getWriter().write("@@@");
//        String result = "您好，欢迎访问 pan_junbiao的博客！";
//
//        //返回结果
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//        // 获取PrintWriter对象
//        PrintWriter out = resp.getWriter();
//        out.print(result);
//        // 释放PrintWriter对象
//        out.flush();
//        out.close();
        String json = "{\n" +
                "\t\"userId\": 1,\n" +
                "\t\"userName\": \"pan_junbiao的博客\",\n" +
                "\t\"blogUrl\": \" https: //blog.csdn.net/pan_junbiao\",\n" +
                "\t\"sex\": \"男\"\n" +
                "}";

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