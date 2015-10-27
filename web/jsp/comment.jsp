<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources.property" />
<html><head>
        <link href="css/style.css" rel="stylesheet" type="text/css"></link>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.wgslider.js"></script>
        <title><fmt:message key="otziv"/></title>
    </head>
    <body>
        <jsp:useBean id="now" class="java.util.Date" />
         <%@include  file="/jsp/banner.jsp" %>
        <div class="maindiv" id="main_div">

            <!-- block header -->
            <div id="container">
                <%@include  file="/jsp/menu.jsp" %>
            </div>
            <table><tr><td valign="top">
                        <%@include  file="/jsp/left.jsp" %></td>
                    <td valign="top">
                        <c:forEach items="${otzivs}" var="otziv">
                            <div class="otzivStyle">
                                <c:if test="${not empty otziv.path}">
                                    <img style="float:left; margin:5px;" src="${otziv.path}" width="50" height="50" title="${otziv.name}"/>
                                </c:if>
                                    <c:if test="${empty otziv.path}">
                                    <img style="float:left; margin:5px;" src="images/nofoto.jpg" width="50" height="50" title="нет фото"/>
                                </c:if>
                                <p style="margin:5px;">Имя: ${otziv.name}</p>
                                <p style="margin:5px; margin-bottom: 16px;">Отзыв: ${otziv.text}</p>
                            </div>
                        </c:forEach>
                        <div>
                            <h2 style="color: tomato;">Оставить отзыв</h2>
                            <form method="POST" enctype="multipart/form-data" action="${pageContext.servletContext.contextPath}/Control">
                                <input type="hidden" name="action" value="otziv" />
                                <div class="newsStyle" style="width: 74%;">
                                <table border="0">
                                    <tr>
                                        <td>Имя:<br/>
                                            <c:if test="${not empty user}">
                                                <input style="border-radius: 7px; border: 1px solid tomato;" type="text" name="name" value="${user}"/>
                                            </c:if>
                                            <c:if test="${empty user}">
                                                <input style="border-radius: 7px; border: 1px solid tomato;" type="text" name="name" value=""/>
                                            </c:if>      
                                         </td>
                                        <td>Фото:<br><input name="filename" type="file" /></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">Текст:<br/><textarea style="border-radius: 7px;border: 1px solid tomato;" name="text" maxlength="600" rows="4" cols="60"></textarea></td>
                                   </tr>
                                </table>
                                    <input style="border-radius: 7px;margin: 3px;" type="submit" value="Сказать"/>
                                </div>
                            </form>
                            <p class="for_error">${errorCommentMessage}</p>
                        </div>           
                    </td>
                </tr>
            </table>
            <hr/>
            <%@include  file="/jsp/footer.jsp" %>
        </div>
    </body></html>
