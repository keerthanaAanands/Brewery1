<!DOCTYPE html>
<html>
<head>
    <title>Brewery Details</title>
</head>
<body>
    <h2>Brewery Details</h2>
    <!-- Include brewery details here -->

    <h3>Reviews</h3>
    <div id="reviews">
        ${reviews}
    </div>

    <h3>Add a Review</h3>
    <form action="addReview" method="post">
        <input type="hidden" name="breweryId" value="${param.breweryId}">
        <input type="hidden" name="userId" value="${sessionScope.userId}">
        Rating: <input type="number" name="rating" min="1" max="5"><br>
        Description: <textarea name="description"></textarea><br>
        <input type="submit" value="Add Review">
    </form>
</body>
</html>
