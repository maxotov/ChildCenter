<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resources.property" />
<html><head>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="js/app.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>        
        <title>${data.title}</title>
    </head>
    <body>
        <%@include  file="/jsp/menu.jsp" %>
         <%@include  file="/jsp/banner.jsp" %>
        <div class="maindiv" id="main_div">
            <table><tr><td valign="top">
                        <%@include  file="/jsp/right.jsp" %></td>
                    <td valign="top" style="padding-left: 3px; padding-right: 3px;">
                        <div style="width: 692px;" class="newsStyle">
                            <p align="center" style="margin-top: 5px;   margin-bottom: 20px;"><strong  style="color: #0094D6;font-size: 20px;">${data.title}</strong></p>
                        <input id="idNews" type="hidden" value="${data.id}"/>
                        <table style="margin-top: -8px;   font-size: 14px;" border="0">
                            <tr>
                                <td>Қосылған уақыты: ${data.date} |</td>  
                                <td><span><img width="20" height="20" src="images/eye.png" title="Қаралу саны"/> ${data.view} |</span></td>  
                                <td>Автор: ${data.author} |</td>  
                                <td><img id="like" width="15" height="15" src="images/like1.png" title="нравится"/><span id="count">${data.like}</span></td>  
                            </tr>
                        </table>
                        <p align="center" style="margin-top: 5px;"><img alt="Good" width="520" src="data:image/jpg;base64,${data.encodedImage}"/></p>
                        <p>${data.text}</p>
                        </div>
                        <c:if test="${empty comments}">
                            <h4 style="color: tomato;">Жаңалыққа әзірше пікір жоқ. Сіз бірінші болыңыз!</h4>
                        </c:if>
                        <c:if test="${not empty comments}">
                            <h4 style="color: tomato;">жаңалыққа жазылған пікірлер</h4>
                        </c:if>
                        <c:forEach items="${comments}" var="comment">
                            <div class="otzivStyle">
                                <p style="margin:5px;"><label>Аты: </label>${comment.name}</p>
                                <p style="margin:5px; margin-bottom: 16px;"><label>Пікір:</label> ${comment.text}</p>
                            </div>
                        </c:forEach>
                        <h3 style="color: tomato;">Пікір қалдыру</h3>
                        <form method="POST" enctype="multipart/form-data" action="${pageContext.servletContext.contextPath}/Control">
                                <input type="hidden" name="action" value="comment" />
                                <input type="hidden" name="data_id" value="${data.id}" />
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
                        <a align="right" href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=news">артқа</a>
                    </td>
                    <td valign="top">
                       <%@include  file="/jsp/left.jsp" %></td>
                </tr>
            </table>
            <hr/>
           <%@include  file="/jsp/footer.jsp" %>
        </div>
        <a href="#" class="scrollup">Наверх</a>
    </body></html>
