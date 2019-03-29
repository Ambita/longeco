<%@tag description="Overall Page template" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Longeco</title>
  <spring:url value='/public/css/bootstrap.min.css' var="bootstrapCssUrl"/>
  <link rel="stylesheet" href="${bootstrapCssUrl}"  >
</head>
<body>
<t:simpleheader/>
<div id="body">
  <jsp:doBody/>
</div>
<t:footer/>
</body>
</html>