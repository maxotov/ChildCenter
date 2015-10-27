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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                        <div style="width: 692px; height: 100%;" class="newsStyle">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=addnews" class="for_a"><fmt:message key="add.news"/></a>
                        <a href="${pageContext.servletContext.contextPath}/controller?command=shownews" class="for_a"><fmt:message key="edit.news"/></a>
                        <a href="${pageContext.servletContext.contextPath}/controller?command=showotziv" class="for_a"><fmt:message key="check.comment"/></a>
                        <a href="${pageContext.servletContext.contextPath}/controller?command=showcomments" class="for_a">Түсініктемелерді қарау</a>
                        <hr/>
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
                                        </c:forEach>
                                </c:if>
                        <h3><fmt:message key="list.client"/></h3>
                        <div class="layer">
                            <table border="1" align="center" cellpadding="2px" class="main_table" style="float: left;"> 
                                <tr>
                                    <td>Аты</td>
                                    <td>Фамилиясы</td>
                                    <td>Телефоны</td>
                                    <td>Email</td>
                                </tr>
                                <c:forEach items="${clients}" var="client">
                                    <tr>
                                        <td>${client.fisrtname}</td>
                                        <td>${client.lastname}</td>
                                        <td>${client.phone}</td>
                                        <td>${client.email}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
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
