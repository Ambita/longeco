<%@tag description="Overall Page template" language="java" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="pageheader">

  <nav class="navbar navbar-toggleable-md navbar-light bg-faded">

    <img src="<spring:url value='/public/images/logo_small.png'/>" width="60" height="60" class="d-inline-block align-top" alt="">
    <a class="navbar-brand" href="#">
      <h1 class="navbar-brand mb-0">Longeco</h1>
    </a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
          <a class="nav-link" href="<spring:url value='/home'/>">Hjem</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<spring:url value='/week'/>">Uke</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<spring:url value='/reports'/>">Fremdrift</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<spring:url value='/strava'/>">Strava</a>
        </li>
      </ul>

      <c:choose>
        <%--@elvariable id="loggedInUser" type="com.ambita.gui_model.LoggedInUser"--%>
        <c:when test="${not empty loggedInUser}">
          <form action="<spring:url value='/logout'/>" class="form-inline my-2 my-lg-0">
            <i class="fa fa-user" aria-hidden="true">&nbsp;</i>${loggedInUser.name}&nbsp;<button class="btn btn-danger my-2 my-sm-0" type="submit" value="Sign Out">Logg av</button>
          </form>
        </c:when>
        <c:otherwise>
          <form action="<spring:url value='/login'/>" class="form-inline my-2 my-lg-0">
            <button class="btn btn-success my-2 my-sm-0" type="submit" value="Login">Innlogging</button>
          </form>
        </c:otherwise>
      </c:choose>

    </div>
  </nav>

</div>