package com.addressList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.ObjectUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

public class MyController extends javax.servlet.http.HttpServlet {
    private addrListDao dao = new addrListDao();

    private JSONObject getNames(String college) {
        List<String> all = dao.getAll(college);
        JSONObject jsonObject = new JSONObject();
        for(int i=0;i<all.size();i+=2){
            jsonObject.put(all.get(i), all.get(i+1));
        }
        System.out.println(jsonObject.toString());
        return jsonObject;
//        String[] s = new String[all.size()];
//        for(int i=0;i<all.size();i++){
//            s[i] = all.get(i);
//        }
//        System.out.println(s[0]);
//        jsonObject.put("hide", s);
//        return jsonObject;
    }

    private JSONObject getDetail(int n) {
        teachersHNU t = dao.search(n);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("poSition", t.getpoSition());
        jsonObject.put("teachersName", t.getTeachersName());
        jsonObject.put("offAdd", t.getOffAdd());
        //jsonObject.put("qqNumber", t.getQqNumber());
        //jsonObject.put("telNumber", t.getTelNumber());
        jsonObject.put("offNumber", t.getOffNumber());
        return jsonObject;
    }

    private JSONObject getBlur(String n) {
        List<String> all = dao.getBlur(n);
        JSONObject jsonObject = new JSONObject();
        //String[] s = new String[all.size()];
        for(int i=0;i<all.size();i+=2){
            jsonObject.put(all.get(i), all.get(i+1));
        }
        System.out.println(jsonObject.toString());
        return jsonObject;
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String action = (String) request.getParameter("action");
        String key = (String) request.getParameter("gid");
        System.out.println(action+" "+key);
        if(action != null && key != null) {
            if (action.equals("getEveryAll")) {
                System.out.println("getAll " + key);
                if (key != null){
                    out.print(getNames(key));
                    System.out.println("sent succcessfully");
                }
            }
            if (action.equals("getDetail")) {
                System.out.println("getdetail " + key);
//                key = URLDecoder.decode(key, "utf-8");
                System.out.println("getdetail " + key);
                int temp = Integer.parseInt(key);
                if (key != null) out.print(getDetail(temp));
            }
            if (action.equals("getSearchData")) {
                System.out.println("getSearchData " + key);
                key = URLDecoder.decode(key, "utf-8");
                System.out.println("getSearchData " + key);
                if (key != null) out.print(getBlur(key));
            }
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}