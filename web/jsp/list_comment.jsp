<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<fmt:setBundle basename="resources.property" />
<html>
    <head>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/jquery-ui-1.8.21.custom.css" type="text/css" rel="stylesheet"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <title>Барлық пікірлер</title>
    </head>
    <body>
        <%@include  file="/jsp/menu.jsp" %>
        <%@include  file="/jsp/banner.jsp" %>
        <div class="maindiv" id="main_div">
            <table><tr>
                    <td valign="top">
                        <%@include  file="/jsp/right.jsp" %>
                    </td>
                    <td valign="top" style="padding-left: 3px; padding-right: 3px;">
                        <div style="width: 692px; height: 368px;" class="newsStyle">
                            <div class="layer" style="width: 100%; height: 100%;">
                            <table border="1" align="center" cellpadding="2px" class="main_table"> 
                                <tr>
                                    <td>Аты</td>
                                    <td>Мәтіні</td>
                                    <td>Email</td>
                                    <td>Жою</td>
                                </tr>
                                <c:forEach items="${otzivs}" var="otziv">
                                    <tr>
                                        <td>${otziv.name}</td>
                                        <td>${otziv.text}</td>
                                        <td>${otziv.email}</td>
                                        <td><form action="${pageContext.servletContext.contextPath}/controller" method="POST">
                                                <input type="hidden" name="command" value="delete" />
                                                <input type="hidden" name="id" value="${otziv.id}" />
                                                <input class="btn btn-danger" type="submit" value="Жою"/>
                                            </form></td>
                                    </tr>
                                </c:forEach>
                            </table>
                            </div>
                            <p>${error}</p>
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

