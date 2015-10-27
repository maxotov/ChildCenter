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
        <title>${data.title}</title>
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
                            <form action="${pageContext.servletContext.contextPath}/controller" method="post">
                                <input type="hidden" name="command" value="savenews"/>
                                <input type="hidden" name="id" value="${data.id}"/>
                                <table border="0" align="center" cellpadding="2px" class="main_table"> 
                                    <tr><td>Тақырып</td><td><input type="text" name="title" value="${data.title}" style="width: 300px;"></td></tr>
                                    <tr><td>Күні</td><td><input type="text" name="date" value="${data.date}"></td></tr>
                                    <tr><td>Сипаттамасы</td><td><textarea name="description" rows="5" cols="60">${data.description}</textarea></td></tr>
                                    <tr><td>Мәтін</td><td><textarea name="text" rows="5" cols="60">${data.text}</textarea></td></tr>
                                    <tr><td colspan="2"><input type="submit" value="Сақтау"></td></tr>
                                </table>
                            </form>
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

