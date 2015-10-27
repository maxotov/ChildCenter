<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="resources.property" />
<html><head>
        <link href="css/style.css" rel="stylesheet" type="text/css"></link>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/app.js"></script>
        <title><fmt:message key="news"/></title>
    </head>
    <body>
        <%@include  file="/jsp/menu.jsp" %>
         <%@include  file="/jsp/banner.jsp" %>
        <div class="maindiv" id="main_div">
            <table><tr><td valign="top">
                       <%@include  file="/jsp/right.jsp" %></td>
                    <td valign="top" style="padding-left: 3px; padding-right: 3px;">
                        <div>
                       <c:forEach var="news" items="${data}">
                           <div id="newsBlog" class="newsStyle">
                               <p align="center" style="margin-top: 2px;"><a id="read_more" onclick="viewCounter(${news.id});" href="${pageContext.servletContext.contextPath}/controller?command=data&id_news=${news.id}"><strong>${news.title}</strong></a></p>
                           <p style="margin-left: 5px;">Қаралу саны: ${news.view}</p>
                           <p style="margin-top: -10px;"><img alt="Embedded Image" width="180px" height="100px" style="float: left; padding-left: 5px; padding-right: 5px;" src="data:image/jpg;base64,${news.encodedImage}"/>
                             ${news.description}</p>
                           <p style="margin-bottom: 10px;"><a id="read_more" style="margin-left: 586px;" onclick="viewCounter(${news.id});" href="${pageContext.servletContext.contextPath}/controller?command=data&id_news=${news.id}">толығырақ..</a></p>
                           </div>
                       </c:forEach>
                        
	<%--For displaying Previous link except for the 1st page --%>
        <br/>
	<c:if test="${currentPage != 1}">
		<a href="${pageContext.servletContext.contextPath}/Pagination?page=${currentPage - 1}">&larr; алдыңғы</a>
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
                                            <td style="padding-right: 10px;"><a href="${pageContext.servletContext.contextPath}/Pagination?page=${i}">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>
	
	<%--For displaying Next link --%>
	<c:if test="${currentPage lt noOfPages}">
		<a href="${pageContext.servletContext.contextPath}/Pagination?page=${currentPage + 1}">келесі &rarr;</a>
	</c:if>
        </div>
                        
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
