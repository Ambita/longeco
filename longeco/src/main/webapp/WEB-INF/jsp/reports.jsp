<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style></style>
<t:fullsizepage>
  <jsp:body>
    <div id="chart" style="height:80%; width: 95% ; margin: 10px auto"></div>
  </jsp:body>
</t:fullsizepage>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/series-label.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment-timezone/0.5.13/moment-timezone-with-data-2012-2022.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script>
  var chartConfig;
  var chart;
  initChart();
  hentData();


  function hentData() {
    axios.get('/longeco/reports/progress')
            .then(function (response) {
              visData(response)

            });

  }

  function visData(response) {
    console.log(response);
    chartConfig.series = response.data.series;
    console.log(chartConfig);
    chart = Highcharts.chart('chart',chartConfig);
  }

  function initChart() {
    chartConfig = {
      chart: {
        type: 'spline'
      },
      title: {
        text: 'Sykle til jobben'
      },
      subtitle: {
        text: 'Fremgang dag for dag'
      },
      xAxis: {
        type: 'datetime',
        dateTimeLabelFormats: {
          day: '%e/%m',
          month: '%e/%m',
          year: '%b'
        },
        title: {
          text: 'Tid (dato)'
        }
      },
      yAxis: {
        title: {
          text: 'Distanse (km)'
        },
        min: 0
      },
      tooltip: {
        headerFormat: '<b>{series.name}</b><br>',
        pointFormat: 'Dato: {point.x:%e/%m-%Y}<br>Distanse: {point.y} km'
      },

      plotOptions: {
        spline: {
          marker: {
            enabled: true
          }
        }
      },
      time: {
        timezone: 'Europe/Oslo'
      }
    };

  }
</script>