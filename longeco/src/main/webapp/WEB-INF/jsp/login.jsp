<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<t:genericpage>
  <jsp:body>
    <div class="container">
      <div class="jumbotron">
        <h1>Innlogging</h1>
        <form action="<spring:url value='/login'/>" method='POST'>
          <table>
            <tr>
              <td><label for="login.username">Brukernavn</label></td>
              <td><input class="form-control" id="login.username" type='text' name='username'></td>
            </tr>
            <tr>
              <td><label for="login.password">Passord</label></td>
              <td><input class="form-control" id="login.password" type='password' name='password'/></td>
            </tr>
            <tr>
              <td><button type="submit" class="btn btn-primary" value="Login">Logg inn</button></td>
            </tr>
          </table>
        </form>
        <a href="<spring:url value='/requestpassword'/>">Endre eller nullstill passord</a>
      </div>
    </div>
  </jsp:body>
</t:genericpage>