<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<t:simplegenericpage>
  <jsp:body>
    <jsp:useBean id="message" scope="request" type="java.lang.String"/>

    <h1>Feil</h1>
    <h2>${message}</h2>
    <a href="<spring:url value='/home'/>">Hjem</a>
  </jsp:body>
</t:simplegenericpage>