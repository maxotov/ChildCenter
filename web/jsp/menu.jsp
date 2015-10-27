<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav style="  width: 1123px;
     margin-left: 113px;
     margin-bottom: 5px;" role="navigation" class="navbar navbar-default">
    <div id="navbarCollapse" class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="."><fmt:message key="home"/></a></li>
            <li><a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=news"><fmt:message key="news"/></a></li>
            <li><a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=forum">Форум</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=advice"><fmt:message key="menu.useful"/></a></li>
            <li><a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=quiz">Сауалнама</a></li>
            <li><a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=contact"><fmt:message key="contact.us"/></a></li>
            <li><a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=law">Заң</a></li>
                <c:if test="${not empty user}">
                <li><a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=add_center">Орталық қосу</a></li>
                <li><a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=cabinet">Жеке паракша</a></li>
                </c:if>
        </ul>
        <c:if test="${empty user}">
            <ul class="nav navbar-nav navbar-right">                
                <li><a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=register"><fmt:message key="register"/></a></li>
            </ul>
        </c:if>
    </div>
</nav>