package com.Brewery.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private Database database;

    @Override
    public void init() throws ServletException {
        super.init();
        database = new Database();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchType = request.getParameter("searchType");
        String searchValue = request.getParameter("searchValue");

        if (searchType == null || searchValue == null || searchValue.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid search parameters.");
            return;
        }

        // Load the JSON data from the file
        try (InputStream is = getServletContext().getResourceAsStream("/WEB-INF/data.json")) {
            if (is == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load brewery data.");
                return;
            }
            String jsonText = IOUtils.toString(is, "UTF-8");
            JSONArray breweries = new JSONArray(jsonText);

            // Filter the JSON array based on search criteria
            List<JSONObject> filteredBreweries = new ArrayList<>();
            for (int i = 0; i < breweries.length(); i++) {
                JSONObject brewery = breweries.getJSONObject(i);
                if (searchType.equals("city") && brewery.getString("city").equalsIgnoreCase(searchValue)) {
                    filteredBreweries.add(brewery);
                } else if (searchType.equals("name") && brewery.getString("name").equalsIgnoreCase(searchValue)) {
                    filteredBreweries.add(brewery);
                } else if (searchType.equals("type") && brewery.getString("brewery_type").equalsIgnoreCase(searchValue)) {
                    filteredBreweries.add(brewery);
                }
            }

            // Fetch reviews for each filtered brewery using Database class
            for (JSONObject brewery : filteredBreweries) {
                String breweryId = brewery.getString("id");
                JSONArray reviews = getReviewsForBrewery(breweryId);
                brewery.put("reviews", reviews);
            }

            // Return the filtered list as JSON
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println(new JSONArray(filteredBreweries).toString(2)); // Pretty-print JSON with an indentation of 2 spaces
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
            e.printStackTrace();
        }
    }

    private JSONArray getReviewsForBrewery(String breweryId) {
        JSONArray reviewsArray = new JSONArray();
        try {
            ResultSet resultSet = database.getReviewsByBreweryId(breweryId);
            while (resultSet.next()) {
                JSONObject review = new JSONObject();
                review.put("user_name", resultSet.getString("user_name"));
                review.put("rating", resultSet.getInt("rating"));
                review.put("comment", resultSet.getString("comment"));
                review.put("review_date", resultSet.getTimestamp("review_date"));
                reviewsArray.put(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviewsArray;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle adding new reviews
        try {
            String breweryId = request.getParameter("breweryId");
            String userName = request.getParameter("userName");
            int rating = Integer.parseInt(request.getParameter("rating"));
            String comment = request.getParameter("comment");

            database.addReview(breweryId, userName, rating, comment);

            // Print a thank you message after the review is successfully added
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Thank you for the review!</h2>");
            out.println("<p>Your review has been successfully added.</p>");
            out.println("<a href=\"Search.jsp\">Return to search</a>");
            out.println("</body></html>");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding your review.");
            e.printStackTrace();
        }
    }

}