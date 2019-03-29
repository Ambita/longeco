<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:simplegenericpage>
  <jsp:body>
    <div class="container">
      <div class="jumbotron">
        <h1>Nullstill passord</h1>

        <spring:url value='/resetpassword' var="formUrl"/>
        <form:form action="${formUrl}" modelAttribute="passwords">

          <table>
            <tr>
              <td><label for="password">Passord</label></td>
              <td><form:password  class="form-control" path="password"/></td>
            </tr>

            <tr>
              <td><label for="repeatPassword">Gjenta passord</label></td>
              <td><form:password class="form-control" path="repeatPassword"/></td>
              <form:hidden path="uid"  />
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