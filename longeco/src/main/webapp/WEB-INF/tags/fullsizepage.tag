<%@tag description="Overall Page template" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Longeco</title>
  <link rel="stylesheet" href="<spring:url value='/public/css/bootstrap.min.css'/>" type="text/css">
  <link rel="stylesheet" href="<spring:url value='/public/fonts/css/font-awesome.min.css'/> ">
  <link rel="stylesheet" href="<spring:url value='/public/css/longeco.css'/> ">
</head>
<body>
<t:header/>
  <jsp:doBody/>
<t:footer/>
</body>
</html>