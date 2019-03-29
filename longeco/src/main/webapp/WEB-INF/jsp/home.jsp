<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="usersSummary" scope="request" type="java.util.List<com.ambita.model.report.UserSum>"/>
<jsp:useBean id="eventName" scope="request" type="java.lang.String"/>
<meta http-equiv="refresh" content="60" />
<t:genericpage>
  <jsp:body>
    <div class="row">
      <div class="col-6">
        <h2>${eventName}</h2>
        <h3>Periode: ${rangeText}</h3>
      </div>
      <div class="col-6">
        <div class="text-info text-right"><small>Klikk på overskriftene for å sortere</small></div>

      </div>
    </div>
    <div class="row">
      <div class="col">
        <table id="tabell1" class="table">
          <thead>
          <tr>
            <th>Rangering etter<br>distanse</th>
            <th>Navn</th>
            <th class="text-right">Siste<br>aktivitet</th>
            <th class="text-right">Distanse</th>
            <th class="text-right">Antall<br>dager</th>
            <th class="text-right">Snitt<br>per dag</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${usersSummary}" var="userDistance" varStatus="loopStatus">
            <tr>
              <th>
                ${loopStatus.index+1}
              </th>
              <td>
                  ${userDistance.name}
              </td>
              <td class="text-right">
                <span>${ userDistance.lastDateText()}</span>
              </td>
              <td class="numberInTable">
                  ${userDistance.distance}
              </td>
              <td class="numberInTable">
                  ${userDistance.count}
              </td>
              <td class="numberInTable">
                  ${userDistance.avgDistancePerDay}
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
     </div>
    <div class="row">
      <div class="col">
        <div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
      </div>
    </div>
  </jsp:body>
</t:genericpage>
<script>
  $(document).ready(function()
          {
            $("#tabell1").tablesorter({
              dateFormat : "ddmmyyyy"
            });
          }
  );

  Highcharts.chart('container', {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Total distanse'
    },
    tooltip: {
      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          format: '<b>{point.name}</b>: {point.percentage:.1f} %',
          style: {
            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
          }
        }
      }
    },
    series: [{
      name: 'Brands',
      colorByPoint: true,
      data: [
      <c:forEach items="${usersSummary}" var="userDistance" varStatus="loopStatus">
      {
        name: '${userDistance.name}',
        y: ${userDistance.distance}
      }<c:if test="${!loopStatus.last}">,</c:if>
      </c:forEach>
      ]
    }]
  });
</script>