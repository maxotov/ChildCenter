<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resources.property" />
<html>
    <head>
        <link href="css/style.css" rel="stylesheet" type="text/css"></link>
        <link href="css/jquery-ui-1.8.21.custom.css" type="text/css" rel="stylesheet"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.21.custom.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <title>Форум</title>
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
                        <div style="width: 692px; height: height: 100%;" class="newsStyle">
                            <div style="width: 100%; height: 100%;">
                                <c:if test="${empty forums}">
                                    <div class="alert alert-info">
                                        <h5>Әзірше форум жоқ. Сіз бірінші болыңыз!</h5>
                                    </div>    
                                </c:if>
                                <c:if test="${not empty forums}">
                                    <div class="alert alert-warning">
                                    <table border="1">
                                        <tr>
                                        <th><label style="margin: 5px;"><img style="margin-right: 5px;" src="images/mailing1.png">Тақырып</label></th>
                                        <th><label style="margin: 5px;"><img style="margin-right: 5px;" src="images/user58.png">Автор</label></th>
                                        <th><label><img style="margin-right: 5px;" src="images/calendar68.png">Құрылған уақыты</label></th>
                                        <th><label>Жауап</label></th>
                                        </tr>
                                        <c:forEach items="${forums}" var="forum">
                                            <tr>
                                                <td>
                                                    <c:if test="${forum.isOpen==1}"><img src="images/forum_open.png" title="${forum.title}" alt="${forum.title}"/></c:if>
                                                    <c:if test="${forum.isOpen==0}"><img src="images/forum_lock.png" title="${forum.title}" alt="${forum.title}"/></c:if>
                                                    <a href="${pageContext.servletContext.contextPath}/controller?command=show_forum&id_forum=${forum.id}">${forum.title}</a>
                                                </td>
                                                <td>${forum.author_name}</td>
                                                <td style="text-align: center;">${forum.date_create}</td>
                                                <td style="text-align: center;">${forum.answer_count}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                    </div>
                                </c:if>
        <%--For displaying Previous link except for the 1st page --%>
	<c:if test="${currentPage != 1}">
		<a href="${pageContext.servletContext.contextPath}/ForumPagination?page=${currentPage - 1}">&larr; алдыңғы</a>
	</c:if>
	<%--For displaying Page numbers. 
	The when condition does not display a link for the current page--%>
	<table border="0" cellpadding="5" cellspacing="5">
		<tr>
			<c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<td style="padding-right: 10px;">${i}</td>
					</c:when>
					<c:otherwise>
                                            <td style="padding-right: 10px;"><a href="${pageContext.servletContext.contextPath}/ForumPagination?page=${i}">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>	
	<%--For displaying Next link --%>
	<c:if test="${currentPage lt noOfPages}">
		<a href="${pageContext.servletContext.contextPath}/ForumPagination?page=${currentPage + 1}">келесі &rarr;</a>
	</c:if>
                                <c:if test="${empty user}">
                                    <div class="alert alert-danger">
                                        <h5>Тақырып ашу үшін сайтқа тіркелу керек!</h5>
                                    </div>    
                                </c:if>
                                <c:if test="${not empty user}">
                                    <div class="alert alert-info">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="add_forum"/>
                                            <input type="hidden" name="id_user" value="${id}">
                                            <table border="0" style="border-spacing: 15px;">
                                                <tr>
                                                    <td><label>Тақырып/Сұрақ: </label></td><td><input style="margin-bottom: 10px;" size="65" type="text" class="form-control" name="title"/></td>                                                    
                                                </tr>
                                                <tr>
                                                    <td><label>Уақытын көрсету: </label></td><td><input style="width: 150px;" id="cur_date" type="text" class="form-control" name="date_create"/></td>
                                                </tr>
                                                <tr>
                                                    <td><input type="submit" class="btn btn-success" value="қосу"/></td><td>${message}</td>
                                                </tr>
                                            </table>
                                        </form>
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
