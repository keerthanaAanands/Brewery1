package com.Brewery.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class Home extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("un");
        String password = req.getParameter("up");
        
        PrintWriter writer = resp.getWriter();
        Database d = new Database();
        
        String s = d.log(userName, password);
        
        if (s.equals(password)) {
            resp.setContentType("text/html");
            req.setAttribute("User_name", userName);
            RequestDispatcher rd = req.getRequestDispatcher("Search.jsp");
            rd.forward(req, resp);
        } else {
            resp.setContentType("text/html");
            RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
            rd.include(req, resp);
            writer.println("<div class='text'><h3 style='color:red;position:absolute;bottom:150px;right:670px;font-family:Bahnschrift SemiBold'>Wrong Email/Password!!!</h3></div>");
        }
    }
}
