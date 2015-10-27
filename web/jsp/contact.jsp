<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resources.property" />
<html><head>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/map_api.js"></script>
        <script type="text/javascript">
            function loadMap(lat, lng, name) {
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
                var map = new google.maps.Map(document.getElementById("map_container"), myOptions);

                var marker = new google.maps.Marker({
                    position: latlng,
                    map: map,
                    title: name
                });

            }
        </script>
        <title><fmt:message key="contact.header" /></title>
    </head>
    <body onload="loadMap(51.160212, 71.462025, 'Балаларды оқыту орталықтары')">
        <%@include  file="/jsp/menu.jsp" %>
        <%@include  file="/jsp/banner.jsp" %>
        <div class="maindiv" id="main_div">
            <table><tr>
                    <td valign="top">
                        <%@include  file="/jsp/right.jsp" %>
                    </td>
                    <td valign="top" style="padding-left: 3px; padding-right: 3px;">
                        <div style="width: 692px; height: 100%;" class="newsStyle">
                            <h3 align="center" style="margin-top: 5px; width: 700px;"><fmt:message key="contact.header" /></h3>
                            <div class="alert alert-info">
                                <p><fmt:message key="contact.inf1" /></p>
                                <p><fmt:message key="contact.inf2" /></p>
                                <p><fmt:message key="contact.inf4" /></p>
                            </div>
                            <div class="alert alert-success">
                                <form method="post" action="controller">
                                <input type="hidden" name="command" value="feedback" />
                                <div style="width: 100%;">
                                <table border="0">
                                    <tr>
                                        <td>Сіздің атыңыз:<br/>
                                            <c:if test="${not empty user}">
                                                <input style="border-radius: 7px; border: 1px solid tomato; width: 98%;" class="form-control" size="33" type="text" name="name" value="${user}"/>
                                            </c:if>
                                            <c:if test="${empty user}">
                                                <input style="border-radius: 7px; border: 1px solid tomato; width: 98%;" class="form-control" size="33" type="text" name="name" value=""/>
                                            </c:if>
                                        </td>
                                        <td>Сіздің email:<br/>
                                            <c:if test="${not empty email}">
                                                <input style="border-radius: 7px; border: 1px solid tomato;" class="form-control" size="35" type="text" name="email" value="${email}"/>
                                            </c:if>
                                                <c:if test="${empty email}">
                                                <input style="border-radius: 7px; border: 1px solid tomato;" class="form-control" size="35" type="text" name="email" value=""/>
                                            </c:if>
                                            </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">Мәтін:<br/><textarea class="form-control" style="border-radius: 7px; border: 1px solid tomato; resize: none;" name="text" maxlength="600" rows="4" cols="88"></textarea></td>
                                   </tr>
                                </table>
                                    <input style="margin: 3px;" class="btn btn-danger" type="submit" value="Жіберу"/>
                                    <c:if test="${not empty errorFeedback}">
                                    <label>${errorFeedback}</label>
                                    </c:if>
                                </div>
                            </form>
                            </div>
                            <div id="map_container" style="width: 682px; height: 300px"></div>
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
    </body></html>
