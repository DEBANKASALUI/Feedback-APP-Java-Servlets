<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <%@include file="links.jsp"%>
</head>
<body>
<%@include file="header.jsp"%>

<div style="height:100vh" class="content_container py-5 d-flex flex-column justify-content-center align-items-center">
    <h3>Fill the Feedback Form</h3>
    <form action="<%=application.getContextPath()%>/feedback" method="post" class="mt-3 text-black">
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input placeholder="Enter email here" name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
            <div id="emailHelp" class="form-text text-grey">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Phone Number</label>
            <input placeholder="Enter phone number" name="phone" type="text" class="form-control" id="exampleInputPassword1">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Your Feedback Area</label>
            <textarea rows="8" name="message" placeholder="Enter here" class="form-control"></textarea>
        </div>
        <div class="container text-center">
            <button type="submit" class="btn btn-dark">Submit</button>
            <button type="reset" class="btn btn-dark">Reset</button>
        </div>
    </form>
</div>
<%@include file="scripts.jsp"%>
</body>
</html>