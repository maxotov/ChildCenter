<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resources.property" />
<html>
    <head>
         <link href="css/style.css" rel="stylesheet" type="text/css"></link>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen"/>
        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/map_api.js"></script>
        <script type="text/javascript">
    var marker;
    var infowindow;

    function initialize() {
      var latlng = new google.maps.LatLng(51.160523, 71.470356);
      var options = {
        zoom: 13,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
      }
      var map = new google.maps.Map(document.getElementById("map-canvas"), options);
      var html = "<table>" +
                 "<tr><td>Название:</td> <td><input type='text' id='name'/> </td> </tr>" +
                 "<tr><td></td><td><input type='button' value='Получить данные' onclick='savePos()'/></td></tr>";
    infowindow = new google.maps.InfoWindow({
     content: html
    });

    google.maps.event.addListener(map, "click", function(event) {
        marker = new google.maps.Marker({
          position: event.latLng,
          map: map
        });
        google.maps.event.addListener(marker, "click", function() {
          infowindow.open(map, marker);
        });
    });
    }
    
    function savePos() {
      var name = escape(document.getElementById("name").value);
      var latlng = marker.getPosition();
      $("#lat").val(latlng.lat());
      $("#lng").val(latlng.lng());
    }

    function downloadUrl(url, callback) {
      var request = window.ActiveXObject ?
          new ActiveXObject('Microsoft.XMLHTTP') :
          new XMLHttpRequest;

      request.onreadystatechange = function() {
        if (request.readyState == 4) {
          request.onreadystatechange = doNothing;
          callback(request.responseText, request.status);
        }
      };

      request.open('GET', url, true);
      request.send(null);
    }

    function doNothing() {}
    </script>
        <title>Орталық қосу</title>
    </head>
    <body onload="initialize()">
         <%@include  file="/jsp/menu.jsp" %>
         <%@include  file="/jsp/banner.jsp" %>
        <div class="maindiv" id="main_div">
            <table><tr>
                    <td valign="top">
                        <%@include  file="/jsp/right.jsp" %>
                    </td>
                    <td valign="top" style="padding-left: 3px; padding-right: 3px;">
                        <div class="newsStyle">
                            <h4 align="center">Орталық туралы мәліметтер</h4>
                            <form enctype="multipart/form-data" action="AddCenterServlet" method="post">
                                <input name="id_user" type="hidden" value="${id}" />
                                <table style="margin-left: 10px;" border="0">
                                    <tr><td>Аты:</td><td><input style="margin-bottom: 10px;" size="65" type="text" name="title" class="form-control"/></td></tr>
                                <tr><td>Мекен-жайы:</td><td><input style="margin-bottom: 10px;" type="text" name="address" class="form-control"/></td></tr>
                                <tr><td>Телефоны:</td><td><input style="margin-bottom: 10px;" type="text" name="phone" class="form-control"/></td></tr>
                                <tr><td>Оқылатын пәндер:</td><td><input style="margin-bottom: 10px;" type="text" name="items" class="form-control"/></td></tr>
                                <tr><td>Бағасы(тг):</td><td><input style="margin-bottom: 10px;" type="text" name="price" class="form-control"/></td></tr>
                                <tr><td>Сайты:</td><td><input style="margin-bottom: 10px;" type="text" name="site" class="form-control"/></td></tr>
                                </table>
                                <div class="alert alert-info">
                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                    <h4>Назар аударыңыз!</h4>
                                    Мекен-жайды көрсету үшін карта үстінде тышқанмен шертіңіз
                                </div>
                                Картада белгілеу:
                                <div id="map-canvas" style="width: 682px; height: 300px"></div>
                                Ендік: <input class="form-control" type='text' id="lat" name="lat"/><br>
                                Бойлық: <input class="form-control" type='text' id="lng" name="lng"/><br>
                                Логотип: <input name="filename" type="file" /><br/>
                                <input class="btn btn-primary" type='submit' value='Қосу'/>
                                <div><span class="label label-info">${message}</span></div>
                            </form>
                        </div>
                        </td>
                    <td valign="top">
                        <%@include  file="/jsp/left.jsp" %>
                    </td>
                    </tr>
            </table>
            <hr/>
            <%@include  file="/jsp/footer.jsp" %>
        </div>
    </body>
</html>
