<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setBundle basename="resources.property" />

<html>
    <head>

        <link href="css/style.css" rel="stylesheet" type="text/css"></link>

        <script type="text/javascript" src="js/jquery.js"></script>

        <script type="text/javascript" src="js/jquery.wgslider.js"></script>

        <title><fmt:message key="log.in"/></title>

    </head>

    <body>

        <jsp:useBean id="now" class="java.util.Date" />
         <%@include  file="/jsp/banner.jsp" %>
        <div class="maindiv" id="main_div">

            <!-- block header -->
             
            <div id="container">
                 <%@include  file="/jsp/menu.jsp" %>  
                <%@include  file="/jsp/viewer.jsp" %>
            </div>

            <h2><fmt:message key="login.system"/></h2>

            <fmt:message key="today"/><fmt:formatDate value="${now}" /><br/><br/>

            <form name="loginForm" method="POST" action="controller">

                <input type="hidden" name="command" value="login" />

                <fmt:message key="login"/>:<br/>

                <input type="text" name="login" value=""/>

                <br/><fmt:message key="password"/>:<br/>  

                <input type="password" name="password" value=""/>
                <br/>
                <br/>

                <a href="${pageContext.servletContext.contextPath}/controller?command=page&cur_page=register">[ <fmt:message key="register"/> ]</a>

                ${errorLoginPassMessage}
                <br/>
                ${wrongAction}
                <br/>

                <input type="submit" value="<fmt:message key="log.in"/>"/>
            </form>

            <!-- BEGIN PRODENGI.KZ INFORMER -->
            <a href="http://prodengi.kz" target="_blank" title="Курсы валют: курс доллара, курс евро">

                <img src="http://prodengi.kz/informer.php?inf=6" width="170" height="110" border="" title="Курсы валют: курс доллара, курс евро" 
                     alt="Курсы валют: курс доллара, курс евро" style="margin-left: 1px;"/>
            </a><!-- END PRODENGI.KZ INFORMER -->
            <hr/>

            <%@include  file="/jsp/footer.jsp" %>

        </div>

    </body>
</html>