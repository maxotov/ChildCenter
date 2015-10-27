<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources.property" />
<html><head>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/app.js"></script>
        <title>Заңдар</title>
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
                        <div style="width: 692px; height: 100%" class="newsStyle">
                            <h4 align="center">Заңдар</h4>
                            <c:forEach items="${laws}" var="law">
                                <div style="margin-left: 5px;">
                                    <div>
                                        <a href="${pageContext.servletContext.contextPath}/controller?command=show_law&id=${law.id}">${law.title}</a>
                                        <div class="law_clear"></div>
                                        ${advice.text}
                                    </div>
                                    <hr/>
                                </div>
                            </c:forEach>
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
        <a href="#" class="scrollup">Наверх</a>
    </body></html>
