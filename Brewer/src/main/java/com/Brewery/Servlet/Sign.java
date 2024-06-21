package com.Brewery.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sign")
public class Sign extends HttpServlet {

    private RequestDispatcher rd;

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("uid");
        String email = req.getParameter("uem");
        String pass = req.getParameter("npass");
        String conf = req.getParameter("conf");
        PrintWriter pw = resp.getWriter();

        Database d = new Database();

        String i = d.check(name);

        if (i.equals("0")) {
            // User doesn't exist
            if (!pass.equals(conf)) {
            	resp.setContentType("text/html");
                RequestDispatcher rd = req.getRequestDispatcher("Sign.jsp");
            	rd.include(req, resp);
            	pw.println("<div class='text'><h3 style='color:orange;position:absolute;bottom:90px;font-size:20px;right:200px;font-family:Bahnschrift SemiBold'>Password is not matching!!!</h3></div>");
            	
            } else {
                d.insert(name, pass, email);
                rd = req.getRequestDispatcher("home.jsp");
                rd.include(req, resp);
            }
        } else {
            // User already exists
            resp.setContentType("text/html");
            RequestDispatcher rd = req.getRequestDispatcher("Sign.jsp");
            rd.include(req, resp);
            pw.println("<div class='text'><h3 style='color:orange;position:absolute;bottom:60px;right:700px;font-size:20px;font-family:Bahnschrift SemiBold'>User already exists!!!</h3></div>");
        }
    }
}
