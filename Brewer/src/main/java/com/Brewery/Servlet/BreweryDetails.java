package com.Brewery.Servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/brewery.jsp")
public class BreweryDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int breweryId = Integer.parseInt(req.getParameter("breweryId"));

        Database d = new Database();
        ResultSet reviews = d.getReviewsByBreweryId(breweryId);

        try {
            StringBuilder reviewList = new StringBuilder();
            while (reviews.next()) {
                reviewList.append("<div>");
                reviewList.append("<p>Rating: ").append(reviews.getInt("rating")).append("</p>");
                reviewList.append("<p>Description: ").append(reviews.getString("description")).append("</p>");
                reviewList.append("</div>");
            }
            req.setAttribute("reviews", reviewList.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = req.getRequestDispatcher("brewery.jsp");
        rd.forward(req, resp);
    }
}
