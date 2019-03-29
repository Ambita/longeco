<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:simplegenericpage>
  <jsp:body>
    <div class="container">
      <div class="jumbotron">
        <h1>Nullstill passord</h1>
        <spring:url value='/requestpassword' var="formUrl"/>
        <form:form action="${formUrl}" modelAttribute="email">
          <table>
            <tr>
              <td><label for="email">Epost</label></td>
              <td><form:input class="form-control" path="email"/></td>
            </tr>
            <tr>
              <td>
                <button type="submit" class="btn btn-primary" value="Submit">Utfør</button>
              </td>
            </tr>
          </table>
        </form:form>
      </div>
    </div>
  </jsp:body>
</t:simplegenericpage>