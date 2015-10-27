<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resources.property" />
<html><head>
        <link href="css/style.css" rel="stylesheet" type="text/css"></link>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/map_api.js"></script>
        <script type="text/javascript" src="js/app.js"></script>
            <script type="text/javascript">
  function loadMap(lat, lng) {
    var latlng = new google.maps.LatLng(lat, lng);
    var myOptions = {
      zoom: 13,
      center: latlng,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
	  mapTypeControlOptions: {
		style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
		position: google.maps.ControlPosition.TOP_CENTER
	  }
    };
    var map = new google.maps.Map(document.getElementById("map_container"),myOptions);
 
    var marker = new google.maps.Marker({
      position: latlng, 
      map: map, 
      title: 'Балаларды оқыту орталығы'
    }); 
 
  }
        </script>
        <title>${center.name}</title>
    </head>
    <body onload="loadMap(${center.lat},${center.lng})">
        <%@include  file="/jsp/menu.jsp" %>
         <%@include  file="/jsp/banner.jsp" %>
        <div class="maindiv" id="main_div">
            <table><tr><td valign="top">
                        <%@include  file="/jsp/right.jsp" %></td>
                    <td valign="top" style="padding-left: 3px; padding-right: 3px;">
                        <div style="width: 692px;" class="newsStyle">
                            <p align="center" style="margin-top: 5px; margin-bottom: 20px;"><strong  style="color: #0094D6;font-size: 20px;">${center.name}</strong></p>
                        <input id="idNews" type="hidden" value="${center.id}"/>
                        <table style="margin-top: -8px;" border="0"><tr>
                                <td><strong>Рейтинг:</strong> <img title="${r}" src="images/${r}.png"/>|</td>  
                                <td><span><img width="20" height="20" src="images/eye.png" title="Қаралу саны"/> ${center.view} |</span></td>
                                <td><script type="text/javascript">(function() {
  if (window.pluso)if (typeof window.pluso.start == "function") return;
  if (window.ifpluso==undefined) { window.ifpluso = 1;
    var d = document, s = d.createElement('script'), g = 'getElementsByTagName';
    s.type = 'text/javascript'; s.charset='UTF-8'; s.async = true;
    s.src = ('https:' == window.location.protocol ? 'https' : 'http')  + '://share.pluso.ru/pluso-like.js';
    var h=d[g]('body')[0];
    h.appendChild(s);
  }})();</script>
<div class="pluso" data-background="#ebebeb" data-options="small,square,line,horizontal,counter,theme=04" data-services="vkontakte,odnoklassniki,facebook,twitter,google,moimir,email,print"></div></td>
                            </tr>
                        </table>
                         
                                <img style="float: left; margin: 10px;" title="logo" width="123px" height="93px" <c:if test="${empty center.encodedImage}">src="images/no-image120.png"</c:if><c:if test="${not empty center.encodedImage}">src="data:image/jpg;base64,${center.encodedImage}"</c:if>/>
                                <p style="margin-top: 10px;"><strong>Мекен-жай:</strong> ${center.address}</p>
                        <p><strong>Сайты:</strong> ${center.site}</p>
                        <p><strong>Телефон:</strong> ${center.phone}</p>
                        <div style="margin-top: 22px;">
                            <div class="alert alert-info">
                                <p><strong>Оқытылатын пәндер:</strong> ${center.items}</p>
                                <p><strong>Бір айға оқу бағасы: </strong> ${center.price} теңге</p>
                            </div>
                            <c:if test="${not empty user}">
                            <form action="Control" method="post">
                            <p class="pvote">Орталықты бағалаңыз: 1 <input name="score" type="radio" value="1"> 2 <input name="score" type="radio" value="2"> 3 <input name="score" type="radio" value="3"> 4 <input name="score" type="radio" value="4"> 5 <input name="score" type="radio" value="5" checked>
                                <input name="submit" type="submit" value="Бағалау">
                                <input name="id_center" type="hidden" value="${center.id}">
                                <input name="action" type="hidden" value="vote">
                            </p></form>
                            </c:if>
                            <c:if test="${empty user}">
                                <div class="alert alert-warning">
                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                    <h4>Назар аударыңыз!</h4>
                                    Орталықты бағалау үшін сайтқа тіркелу қажет!
                                </div>
                            </c:if>
                        </div>
                        <div id="map_container" style="width: 682px; height: 300px"></div>
                        </div>
                        <c:if test="${empty comments}">
                            <h4 style="color: tomato;">Орталыққа әзірше пікірлер жоқ. Бірінші сіз болыңыз!</h4>
                        </c:if>
                        <c:if test="${not empty comments}">
                            <h4 style="color: tomato;">Орталыққа жазылған пікірлер</h4>
                        </c:if>
                        <c:forEach items="${comments}" var="comment">
                            <div class="otzivStyle">
                                <p style="margin:5px;"><label>Аты:</label> ${comment.name}</p>
                                <p style="margin:5px; margin-bottom: 16px;"><label>Пікір:</label> ${comment.text}</p>
                            </div>
                        </c:forEach>
                        <h3 style="color: tomato;">Пікір қалдыру</h3>
                        <form method="POST" action="controller">
                                <input type="hidden" name="command" value="add_otziv" />
                                <input type="hidden" name="center_id" value="${center.id}" />
                                <div class="newsStyle" style="width: 74%;">
                                <table border="0">
                                    <tr>
                                        <td>Аты:<br/>
                                            <c:if test="${not empty user}">
                                                <input style="border-radius: 7px; border: 1px solid tomato; width: 98%;" type="text" name="name" value="${user}"/>
                                            </c:if>
                                            <c:if test="${empty user}">
                                                <input style="border-radius: 7px; border: 1px solid tomato; width: 98%;" type="text" name="name" value=""/>
                                            </c:if>
                                        </td>
                                        <td>E-Mail:<br/>
                                            <c:if test="${not empty email}">
                                                <input style="border-radius: 7px; border: 1px solid tomato; width: 100%;" type="text" name="email" value="${email}"/>
                                            </c:if>
                                                <c:if test="${empty email}">
                                                <input style="border-radius: 7px; border: 1px solid tomato; width: 100%;" type="text" name="email" value=""/>
                                            </c:if>
                                            </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">Мәтін:<br/><textarea style="border-radius: 7px; border: 1px solid tomato;" name="text" maxlength="600" rows="4" cols="60"></textarea></td>
                                   </tr>
                                </table>
                                    <input style="border-radius: 7px;margin: 3px;" type="submit" value="Қалдыру"/>
                                </div>
                            </form>
                            <p class="for_error">${errorComment}</p>
                        <a align="right" href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=about">артқа</a>
                    </td>
                    <td valign="top">
                        <%@include  file="/jsp/left.jsp" %>
                    </td>
                </tr>
            </table>
            <hr/>
           <%@include  file="/jsp/footer.jsp" %>
        </div>
        <a href="#" class="scrollup">Наверх</a>
    </body></html>
