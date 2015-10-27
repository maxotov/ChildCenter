<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resources.property" />
<html>
    <head>
        <link href="css/style.css" rel="stylesheet" type="text/css"></link>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <title>${forum.title}</title>
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
                            <div style="width: 100%; height: 100%;">
                                <div class="alert alert-info">        
                                    <h4>Тақырып/сұрақ: ${forum.title}</h4>
                                </div>
                                <c:if test="${empty answers}">
                                    <div class="alert alert-danger">
                                        <h5>Әзірше жауаптар жоқ. Бірінші сіз болыңыз!</h5>
                                    </div>
                                </c:if>
                                <c:if test="${not empty answers}">
                                    <div class="alert alert-warning">
                                        <table border="1">
                                            <c:forEach items="${answers}" var="answer">
                                                <tr><td colspan="2">${answer.dateString}</td></tr>
                                                <tr>
                                                    <td>
                                                        <img style="margin: 5px; width: 40%;" src="images/nofoto.jpg"/>
                                                        <p style="margin-left: 5px;">${answer.user_name}</p>
                                                    </td>
                                                    <td>
                                                        <div style="width: 500px; height: 100px;"><p style="margin: 5px;">${answer.answer}</p></div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </c:if>
                                <c:if test="${empty user}">
                                    <div class="alert alert-danger">
                                        <h5>Жауап қалдыру үшін сайтқа тіркелу керек!</h5>
                                    </div>    
                                </c:if>
                                <c:if test="${not empty user}">
                                    <div class="alert alert-info">
                                        <c:if test="${forum.isOpen==1}">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="add_answer"/>
                                            <input type="hidden" name="id_user" value="${id}">
                                            <input type="hidden" name="id_forum" value="${forum.id}">
                                            <table border="0">
                                                <tr>
                                                    <td><label>Сіздің жауабыңыз: </label></td>
                                                </tr>
                                                <tr>
                                                   <td><textarea style="width: 550px;   margin-bottom: 5px;" class="form-control" name="answer"></textarea></td>                                                    
                                                </tr>
                                                <tr>
                                                    <td colspan="2"><input type="submit" class="btn btn-success" value="қалдыру"/></td>
                                                </tr>
                                            </table>
                                        </form>
                                        </c:if>
                                        <p>${message}</p>
                                        <c:if test="${id==forum.id_user}">
                                            <c:if test="${forum.isOpen==1}">
                                                <form action="controller" method="post">
                                                    <input type="hidden" name="command" value="update_forum_status"/>
                                                    <input type="hidden" name="id_forum" value="${forum.id}"/>
                                                    <input type="hidden" name="status" value="0"/>
                                                    <input type="submit" class="btn btn-danger" value="тақырыпты жабу"/>
                                                </form>
                                            </c:if>
                                            <c:if test="${forum.isOpen==0}">
                                                <form action="controller" method="post">
                                                    <input type="hidden" name="command" value="update_forum_status"/>
                                                    <input type="hidden" name="id_forum" value="${forum.id}"/>
                                                    <input type="hidden" name="status" value="1"/>
                                                    <input type="submit" class="btn btn-danger" value="тақырыпты ашу"/>
                                                </form>
                                            </c:if>
                                        </c:if>
                                    </div>    
                                </c:if>
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
