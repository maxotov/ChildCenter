<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>          
<fmt:setBundle basename="resources.property" />
<html>
    <head>
        <link href="css/style.css" rel="stylesheet" type="text/css"></link>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <title>Жеке парақша</title>
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
                        <div style="width: 692px; height: 300px;" class="newsStyle">
                            <h4>Сіздің орталықтарыңыз:</h4>
                            <c:if test="${empty centers}">
                                <h4 style="color: tomato;">Сізде орталықтар жоқ</h4>
                            </c:if>
                            <c:if test="${not empty centers}">
                                <c:forEach var="center" items="${centers}">
                                    <a id="read_more" href="${pageContext.servletContext.contextPath}/controller?command=center&id_center=${center.id}"><strong>${center.name}</strong></a><br/>
                                </c:forEach>
                            </c:if>
                                <c:if test="${not empty centers}">
                                        <h4>Орталықты өңдеу:</h4>
                                        <c:forEach var="center" items="${centers}">
                                            <p><a id="read_more" style="color: violet;" href="${pageContext.servletContext.contextPath}/controller?command=edit_center&id_center=${center.id}"><strong>${center.id}  ${center.name}</strong></a></p>
                                        <c:if test="${not empty center.comments}">
                                            <a style="margin-left: 40px;   color: green;" id="read_more" href="#"><strong>Келіп түскен пікірлер</strong></a><br/>
                                            <div align="center">
                                            <table cellpadding="4px" border="1">
                                                <tr><th><label>Аты: </label></th><th><label>Мәтін </label></th><th><label>Email: </label></th></tr>
                                            <c:forEach var="comment" items="${center.comments}">
                                                <tr><td>${comment.name}</td><td>${comment.text}</td><td>${comment.email}</td></tr>
                                            </c:forEach>
                                                </table>
                                            </div>
                                        </c:if>
                                        </c:forEach>
                                </c:if>
                            
                            <a href="controller?command=page&cur_page=about"><fmt:message key="back"/></a>
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