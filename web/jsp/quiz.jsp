<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources.property" />
<html><head>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <script type="text/javascript" src="js/jquery-2.1.3.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="js/app.js"></script>
        <title>Ата-аналарға арналған тест «Мен және менің балам»</title>
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
                        <div style="width: 692px; height: 100%" class="newsStyle">
                            <h4 align="center">Ата-аналарға арналған тест «Мен және менің балам»</h4>
                            <p>Ата-ананың бала тәрбиесіндегі атқаратын ролі аса зор. Олар бала өмірінде  «құрастырушылар, жобалаушылар және құрлысшылар»  болып табылады. Бұл тест ата-аналарға бала тәрбиесіннің мәселелерін  толықтырып, анықтауға көмектеседі.</p>
                            <h4>Жауаптар:</h4>
                            <ul>
                                <li>Қолымнан келеді, әрі әрқашанда осылай жасаймын.(<strong>A</strong>)</li>
                                <li>Қолымнан келеді, бірақ әрқашанда олай жасамаймын.(<strong>B</strong>)</li>
                                <li>Қолымнан келмейді.(<strong>C</strong>)</li>
                            </ul>
                            <h4>Сұрақтар:</h4>
                            <form action="controller" method="post">
                                <input name="command" type="hidden" value="quiz">
                                <ol>
                                    <li>
                                        <p>Қандай жағдай болмасын өз жұмысымды тастап баламмен айналысамын.</p>
                                        <p>A <input name="question1" type="radio" value="3"> B <input name="question1" type="radio" value="2"> C <input name="question1" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Баламның жасына қарамастан мен онымен кеңесемін.</p>
                                        <p>A <input name="question2" type="radio" value="3"> B <input name="question2" type="radio" value="2"> C <input name="question2" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Егер қате жіберген жағдайымда баламнан кешірім сұраймын.</p>
                                        <p>A <input name="question3" type="radio" value="3"> B <input name="question3" type="radio" value="2"> C <input name="question3" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Баламмен қарым-қатынас арасында жіберген қателігімді оның алдында мойындай аламын.</p>
                                        <p>A <input name="question4" type="radio" value="3"> B <input name="question4" type="radio" value="2"> C <input name="question4" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Егер баламның әрекеті өте қатты әсер етсе де, мен өзімді ұстай алар едім.</p>
                                        <p>A <input name="question5" type="radio" value="3"> B <input name="question5" type="radio" value="2"> C <input name="question5" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Баламның орнына өзімді қоя аламын.</p>
                                        <p>A <input name="question6" type="radio" value="3"> B <input name="question6" type="radio" value="2"> C <input name="question6" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Мен өзімді 1 минут болса да, «сұлу перизат» немесе «мейірімді сұлтан» ретінде сезіне аламын.</p>
                                        <p>A <input name="question7" type="radio" value="3"> B <input name="question7" type="radio" value="2"> C <input name="question7" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Мен өзімнің балалық шағымдағы бейнем қолайсыз болып көрінетін және сабақ болатындай жағдайда айтып бере аламын.</p>
                                        <p>A <input name="question8" type="radio" value="3"> B <input name="question8" type="radio" value="2"> C <input name="question8" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Бала жанына ауыр тиетін сөздерді көп қолданатын сияқтымын.</p>
                                        <p>A <input name="question9" type="radio" value="3"> B <input name="question9" type="radio" value="2"> C <input name="question9" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Мен баламның жақсы тәртібі үшін, оның тілегін орындауға сөзіме берік боламын.</p>
                                        <p>A <input name="question10" type="radio" value="3"> B <input name="question10" type="radio" value="2"> C <input name="question10" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Балаға не істегісі келсе, соны істеуге, өзін қалай ұстаса, солай ұстауға, яғни еркіне жіберіп, өзім араласпауға, бір күнді соған бөлемін.</p>
                                        <p>A <input name="question11" type="radio" value="3"> B <input name="question11" type="radio" value="2"> C <input name="question11" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Егер менің балам біреудің баласын себепсіз ренжітіп, қол жұмсап, дөрекі мінездерін көрсетіп жатқан кезде мен үндемеймін.</p>
                                        <p>A <input name="question12" type="radio" value="3"> B <input name="question12" type="radio" value="2"> C <input name="question12" type="radio" value="1"></p>
                                    </li>
                                    <li>
                                        <p>Егер баламның ерке қылығын көрсетіп жылағанына немесе баламның көз жасымен әр нәрсені сұрауына қарсы тұра аламын.</p>
                                        <p>A <input name="question13" type="radio" value="3"> B <input name="question13" type="radio" value="2"> C <input name="question13" type="radio" value="1"></p>
                                    </li>
                                </ol>
                                <input type="submit" class="btn btn-primary" value="нәтижесін көрсету">
                            </form>
                            <p>${message}</p>
                            <c:if test="${not empty result}">
                                    <h4>Сауалнама нәтижесі:</h4>
                                    <p>${result}</p>
                            </c:if>
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
        <a href="#" class="scrollup">Наверх</a>
    </body></html>
