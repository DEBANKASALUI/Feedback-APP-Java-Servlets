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
<div style="height:60vh" class="content_container py-5 d-flex flex-column justify-content-center align-items-center">
    <h1>Press the Below button to access the Feedback Form!</h1>
<div style="height:100vh" class="content_container py-5 d-flex flex-column justify-content-center align-items-center">
    <a href="<%=application.getContextPath()%>/feedback.jsp" class="btn btn-dark">Give us Feedback</a>
    <a href="<%=application.getContextPath()%>/servlet1" class="btn mt-5 btn-dark">Go to Servlet1</a>
    <a href="<%=application.getContextPath()%>/servlet2" class="btn mt-5 btn-dark">Go to Servlet2</a>
</div>
<%@include file="scripts.jsp"%>
</div>
</body>
</html>