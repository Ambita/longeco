<%@tag description="Overall Page template" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="pagefooter" class="container">
  <spring:url value='/public/js/jquery-3.2.1.slim.min.js' var="jqueryUrl"/>
  <script src="${jqueryUrl}" type="application/javascript"></script>
  <spring:url value='/public/js/tether.min.js' var="tetherUrl"/>
  <script src="${tetherUrl}" type="application/javascript"></script>
  <spring:url value='/public/js/bootstrap.min.js' var="bootstrapUrl"/>
  <script src="${bootstrapUrl}" type="application/javascript"></script>
  <script src="http://code.highcharts.com/highcharts.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.30.4/js/jquery.tablesorter.min.js"></script>
</div>