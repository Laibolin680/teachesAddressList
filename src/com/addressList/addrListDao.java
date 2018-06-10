package com.addressList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.*;
import java.util.ArrayList;
import java.util.List;
public class addrListDao {

    public static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/txt";
        String username = "root";
        String password = "180504";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static teachersHNU search(int n) {
        teachersHNU result = new teachersHNU();
        Connection conn = getConn();
        String sql = "select * from teachers where num='" + n + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                result.setNum(n);
                result.setTeachersName(rs.getString("teachersName"));
                result.setOffNumber(rs.getString("offNumber"));
                result.setpoSition(rs.getString("poSition"));
                result.setinstituteHNU(rs.getString("instituteHNU"));
                result.setOffAdd(rs.getString("offAdd"));
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getAll(String college) {

        Connection conn = getConn();
        String sql = "select * from teachers where instituteHNU='" + college + "'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<String> all = new ArrayList<>();
            //int i = 0;
            while (rs.next()){
                /*all.add(new teachersHNU());
                all.get(i).setOffNumber(rs.getString("offNumber"));
                all.get(i).setpoSition(rs.getString("poSition"));
                all.get(i).setQqNumber(rs.getString("qqNumber"));
                all.get(i).setTelNumber(rs.getString("telNumber"));
                all.get(i).setNum(rs.getInt("num"));
                all.get(i).setTeachersName(rs.getString("teachersName"));
                all.get(i).setinstituteHNU(rs.getString("instituteHNU"));
                i++;*/
                all.add(rs.getString("num"));
                all.add(rs.getString("teachersName"));
            }
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getBlur(String name) {

        Connection conn = getConn();
        String sql = "select * from teachers where teachersName='" + name + "'or teachersName like '%" + name + "'or teachersName like '%" + name + "%' or teachersName like '" + name + ",%'";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<String> all = new ArrayList<>();
            //int i = 0;
            while (rs.next()){
                all.add(rs.getString("teachersName")+"-"+rs.getString("num"));
                all.add(rs.getString("instituteHNU"));
            }
            return all;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String args[]) {
        //teachersHNU test = search("苍井空");
        //System.out.println(test.toString());
        //System.out.println();
        //List<String> t = getAll("computer");
        //for(int i = 0; i < t.size(); i++)System.out.println(t.get(i));
        //System.out.println();
        List<String> s = getBlur("办公室");
        for(int i = 0; i < s.size(); i++)System.out.println(s.get(i));
    }
}
