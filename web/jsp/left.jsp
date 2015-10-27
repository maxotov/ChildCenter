<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    setTimeout(latest_forums(),1);
function latest_forums(){
    $.ajax({
            type: "POST",
            url: "Control",
            data: {action: "latest_forums"},
            dataType: "json",            
            success: function(data) {               
                if (data.success) {
                    $("#forumsResponse").html("");                                        
                    var htmlContent = "";
                    for (var i = 0; i < data.forums.length; i++) {
                        htmlContent += "<p class='comment_line'><span class='news_date_sm'>"+data.forums[i].date_create+"</span><a id='read_more' href='${pageContext.servletContext.contextPath}/controller?command=show_forum&id_forum="+data.forums[i].id+"'>"+data.forums[i].title+"</a></p>";
                    }
                                                           
                    $("#forumsResponse").html(htmlContent);
                }
                else {
                    $("#forumsResponse").html("<div>Пустой результат</div>");                    
                }
            }
        });
}

</script>
<div class="newsStyle">
<div class="leftblog"><fmt:message key="login.system"/></div>
                        <c:if test="${empty user}">
                        <div>
                        <form method="POST" action="controller">
                            <input type="hidden" name="command" value="login" />                            
                            <input style="margin-bottom: 10px;" type="text" name="login" class="form-control" placeholder="<fmt:message key="login"/>">
                            <input style="margin-bottom: 10px;" type="password" name="password" class="form-control" placeholder="<fmt:message key="password"/>">
                      
                            <input class="btn btn-primary" style="width: 200px;" type="submit" value="<fmt:message key="log.in"/>"/>
                        ${errorLoginPassMessage}
                        </form>
                        </div></c:if>
                        <c:if test="${not empty user}">
                            <div style="margin:10px 15px 15px 0px;"><strong><fmt:message key="welcome.title"/> ${user} </strong>
                            <a href="controller?command=logout" class="for_a" id="read_more"><fmt:message key="exit"/></a>
                            </div>
                        </c:if>
</div>
<div class="newsStyle">
<div class="leftblog">Форум талқылауда</div>
<div id="forumsResponse"></div>
</div>