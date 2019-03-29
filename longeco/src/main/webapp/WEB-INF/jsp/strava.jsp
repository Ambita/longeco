<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<t:genericpage>
  <jsp:body>
    <h1>Strava</h1>
    <p>Logger du dine turer på appen <a href="https://www.strava.com">Strava</a>?</p>
    <p>Registrer deg som medlem her : <a href="https://www.strava.com/clubs/ambita">"https://www.strava.com/clubs/ambita"</a></p>
    <h4>Ambita på Strava: </h4>
    <div class="row">
      <div class="col-md-6">
        <iframe allowtransparency frameborder='0' height='600' scrolling='no' src='https://www.strava.com/clubs/447994/latest-rides/1beb4eb508d0edda4dfff029db1e529150fe50ed?show_rides=true' width='300'></iframe>
      </div>
      <div class="col-md-6">
        <iframe allowtransparency frameborder='0' height='160' scrolling='no' src='https://www.strava.com/clubs/447994/latest-rides/1beb4eb508d0edda4dfff029db1e529150fe50ed?show_rides=false' width='350'></iframe>
      </div>
    </div>
  </jsp:body>
</t:genericpage>