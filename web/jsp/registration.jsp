<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%> 
<!DOCTYPE html>
<fmt:setBundle basename="resources.property" />
<html>
    <head>
       <link href="css/style.css" rel="stylesheet" type="text/css"></link>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <title><fmt:message key="register"/></title>
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
                        <div style="width: 692px; height: 400px;" class="newsStyle">
                            <h4 align="center">Жаңа қолданушыны тіркеу</h4>
                        <p><span class="for_span">*</span><fmt:message key="fields"/></p>
                        <form name="RegistrationForm" method="POST" action="${pageContext.servletContext.contextPath}/controller">
                            <input type="hidden" name="command" value="register" />
                            <table border="0" align="left" cellpadding="2px" class="main_table">
                                <tr>
                                    <td>Қолданушы есімі<span class="for_span">*</span></td>
                                    <td><input style="margin-bottom: 10px;" class="form-control" type="text" name="firstname" value=""/></td>
                                </tr>
                                <tr>
                                    <td>Фамилиясы:<span class="for_span">*</span></td>
                                    <td><input style="margin-bottom: 10px;" class="form-control" type="text" name="lastname" value=""/></td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="password"/>:<span class="for_span">*</span></td>
                                    <td><input style="margin-bottom: 10px;" class="form-control" type="password" name="password" value=""/></td>
                                </tr>
                                <tr>
                                    <td><fmt:message key="phone"/>:<span class="for_span">*</span></td>
                                    <td><input  style="margin-bottom: 10px;" class="form-control" type="text" placeholder="777 555 44 99" name="phone" value="" /></td>
                                </tr>
                                <tr>
                                    <td>Email:<span class="for_span">*</span></td>
                                    <td><input style="margin-bottom: 10px;" class="form-control" type="text" placeholder="info@info.com" name="email" value=""/></td>
                                </tr>
                                <tr>
                                    <td><input class="btn btn-success" type="submit" value="<fmt:message key="register"/>"/></td>
                                    <td class="for_error">${error}</td>
                                    <td>${success}</td>
                                </tr>
                                <tr>
                                    <td><a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=about"><fmt:message key="back"/></a></td>
                                </tr>
                            </table>
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
