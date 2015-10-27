<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%> 
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
        <title>Жаңалық қосу</title>
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
                            <p><span class="for_span">*</span><fmt:message key="fields"/></p>
                            <form method="post" enctype="multipart/form-data" action="AddNewsServlet">
                                <table border="0" align="left" cellpadding="2px" class="main_table">
                                    <tr>
                                        <td><label>Тақырыбы:</label><span class="for_span">*</span></td>
                                        <td><input style="width: 500px; margin-bottom: 10px;" class="form-control" type="text" name="title" value=""/></td>
                                    </tr>
                                    <tr>
                                        <td><label>Құрылған күні:</label><span class="for_span">*</span></td>
                                        <td><input style="width: 200px; margin-bottom: 10px;" class="form-control" type="text" name="date" id="cur_date" value=""/></td>
                                    </tr>
                                    <tr>
                                        <td><label>Сипаттама:</label><span class="for_span">*</span></td>
                                        <td><textarea style="width: 500px; margin-bottom: 10px;" class="form-control" name="description" value=""></textarea></td>
                                    </tr>
                                    <tr>
                                        <td><label>Мәтіні:</label><span class="for_span">*</span></td>
                                        <td><textarea style="width: 500px; margin-bottom: 10px;" class="form-control" name="text" value=""></textarea></td>
                                    </tr>
                                    <tr>
                                        <td><label>Авторы:</label><span class="for_span">*</span></td>
                                        <td><input style="width: 500px; margin-bottom: 10px;" class="form-control" type="text" name="author" value=""/></td>
                                    </tr>
                                    <tr>
                                        <td><label>Суреті</label><span class="for_span">*</span></td>
                                        <td><input name="filename" type="file" /></td>
                                    </tr>
                                    <tr><td>${success}</td></tr>
                                    <tr>
                                        <td><input class="btn btn-primary" type="submit" value="Қосу"/></td>
                                        <td>${errorRegisterMessage}</td>
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
