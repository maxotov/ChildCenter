<%@page import="kz.enu.fit.dao.CommentDAO"%>
<%@page import="kz.enu.fit.dao.CenterDAO"%>
<%@page import="kz.enu.fit.dao.UserDAO"%>
<script>
    setTimeout(latest_news, 1);
    function latest_news() {
        $.ajax({
            type: "POST",
            url: "Control",
            data: {action: "latest_news"},
            dataType: "json",
            success: function(data) {
                if (data.success) {
                    $("#newsResponse").html("");
                    var htmlContent = "";
                    for (var i = 0; i < data.news.length; i++) {
                        htmlContent += "<p class='comment_line'><span class='news_date_sm'>" + data.news[i].date + "</span><a id='read_more' onclick='viewCounter(" + data.news[i].id + ");' href='${pageContext.servletContext.contextPath}/controller?command=data&id_news=" + data.news[i].id + "'>" + data.news[i].title + "</a></p>";
                    }

                    $("#newsResponse").html(htmlContent);
                }
                else {
                    $("#newsResponse").html("<div>Пустой результат</div>");
                }
            }
        });
    }

</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="newsStyle">
    <div class="leftblog">Соңғы жаңалықтар</div>
    <div id="newsResponse"></div>
</div>
<div class="newsStyle">
    <div class="leftblog">Статистика</div>
    <%
        String ip = request.getRemoteAddr();
        UserDAO.deleteOldSession();
        String tmp = UserDAO.checkIsExist(ip);
        if (tmp.equals(ip)) {
            UserDAO.updateSession(ip);
        } else {
            UserDAO.insertSession(ip);
        }
        int count = UserDAO.countSession();
        int countCenter = CenterDAO.countCenter();
        int countComment = CommentDAO.countComment();
    %>

    <div style="font-size: 14px; color: #337ab7;"><label>Орталықтар саны: <%= countCenter %></label> <br/>
        <label>Пікірлер саны: <%= countComment %></label> <br/>
        <label>Сайттағы адам саны: <%= count%></label></div>
</div>