<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<jsp:useBean id="week" scope="request" type="com.ambita.gui_model.Week"/>
<t:genericpage>
  <jsp:body>
    <br/>
    <ul class="nav">
      <li class="nav-item">
        <input type="week" id="jumpWeek" value="${week.year}-W<fmt:formatNumber minIntegerDigits="2" value="${week.weekNumber}" />" oninput="jumpWeek()" onclick="return checkUnsavedData()"/>
      </li>

      <li class="nav-item">
        <a class="nav-link" href="<spring:url value='/week?weekYear=${week.previousWeekNumber}.${week.previousYear}'/>" onclick="return checkUnsavedData()">
          <i class="fa fa-arrow-circle-left" aria-hidden="true"></i> Forrige uke
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link" href="<spring:url value='/week?weekYear=${week.nextWeekNumber}.${week.nextYear}'/>" onclick="return checkUnsavedData()">
          <i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Neste uke
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link" href="<spring:url value='/week'/>" onclick="return checkUnsavedData()">
          <i class="fa fa-circle" aria-hidden="true"></i> I dag
        </a>
      </li>
    </ul>

    <div class="jumbotron">
      <h1>Uke</h1>
      <spring:url value='/week' var="saveUrl"/>
      <form:form action="${saveUrl}" modelAttribute="week">
        <form:hidden path="year"/>
        <form:hidden path="weekNumber"/>
        <div class="form-group form-inline">
          <c:forEach items="${week.days}" var="day" varStatus="loopStatus">
            <div>
              <%--<fmt:setLocale value="en_GB" scope="session"/>--%>
              <fmt:setLocale value="nb_NO" scope="session"/>
              <fmt:formatDate value="${day.date}"  pattern="EEEE" var="nameOfDay"/>
              <fmt:formatDate value="${day.date}"  pattern="d/M" var="dateInMonth"/>
              <div>${nameOfDay}</div>
              <div>${dateInMonth}</div>
                 <%--@elvariable id="hasError" type="java.util.List"--%>
              <c:set var="hasError" >
                <form:errors path="days[${loopStatus.index}].distance"/>
              </c:set>
              <form:input cssClass="form-control distance" style="${!hasError.isEmpty() ? \"background-color:red\":\"\"}" path="days[${loopStatus.index}].distance"/>
              <form:hidden path="days[${loopStatus.index}].date"/>
            </div>
          </c:forEach>
        </div>

        <div class="form-group">
          <button type="submit" class="btn btn-primary" value="Submit">Save</button>
          <button type="reset" class="btn btn-primary" value="Reset">Reset</button>
        </div>
      </form:form>

      <spring:hasBindErrors name="week">
        <div class="alert alert-danger" role="alert">
          <strong>Ops!</strong>Distanser må være tall mellom 0 and 999.
        </div>
      </spring:hasBindErrors>
    </div>
    <script src="<spring:url value='/public/js/longeco.js'/>" type="application/javascript"></script>
  </jsp:body>
</t:genericpage>