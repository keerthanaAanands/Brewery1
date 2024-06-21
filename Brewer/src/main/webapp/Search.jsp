<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Breweries</title>
    <style>
        .brewery {
            border: 1px solid #ddd;
            margin-bottom: 20px;
            padding: 10px;
            border-radius: 5px;
        }
        .review {
            margin-left: 20px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <h1>Search Breweries</h1>
    <form method="GET" action="SearchServlet">
        <label for="searchType">Search by:</label>
        <select name="searchType" id="searchType">
            <option value="city">City</option>
            <option value="name">Name</option>
            <option value="type">Type</option>
        </select>
        <input type="text" name="searchValue" placeholder="Enter search value" required>
        <button type="submit">Search</button>
    </form>
    
    <h2>Results:</h2>
    <div id="results">
        <!-- Assuming data is passed correctly to the JSP -->
        <c:forEach var="brewery" items="${breweries}">
            <div class="brewery">
                <h3>${brewery.name}</h3>
                <p>Type: ${brewery.breweryType}</p>
                <p>Address: ${brewery.address1}, ${brewery.city}, ${brewery.state}</p>
                <p>Website: <a href="${brewery.websiteUrl}" target="_blank">${brewery.websiteUrl}</a></p>
                <h4>Reviews:</h4>
                <div class="reviews">
                    <c:forEach var="review" items="${brewery.reviews}">
                        <div class="review">
                            <p><strong>${review.userName}</strong> (<span class="review-date">${review.reviewDate}</span>):</p>
                            <p>Rating: ${review.rating}</p>
                            <p>${review.comment}</p>
                        </div>
                    </c:forEach>
                </div>
                <h4>Add a Review:</h4>
                <form method="POST" action="SearchServlet">
                    <input type="text" name="breweryId" required>
                    <input type="text" name="userName" placeholder="Your name" required>
                    <input type="number" name="rating" min="1" max="5" placeholder="Rating (1-5)" required>
                    <textarea name="comment" placeholder="Your review" required></textarea>
                    <button type="submit">Submit Review</button>
                </form>
            </div>
        </c:forEach>
    </div>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const reviewDates = document.querySelectorAll('.review-date');
            reviewDates.forEach(function(dateElement) {
                const rawDate = new Date(dateElement.textContent);
                dateElement.textContent = rawDate.toLocaleString();
            });
        });
    </script>
</body>
</html>
