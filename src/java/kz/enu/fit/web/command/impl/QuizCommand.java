package kz.enu.fit.web.command.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.web.command.ActionCommand;

public class QuizCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = "";
        List<Integer> questions = new ArrayList<Integer>();
        int currentQuestion = 0;
        int emptyAnswer = 0;
        for (int i = 1; i <= 13; i++) {            
            String param = request.getParameter("question"+i);            
            if (param != null) {
                currentQuestion = Integer.parseInt(param);
                questions.add(currentQuestion);
            } else {
                emptyAnswer++;
            }
        }
        int count = 0;
        if (emptyAnswer != 0) {
            request.setAttribute("message", "Сіз барлық сұраққа жауап беруіңіз қажет!");
        } else {
            for (int i = 0; i < questions.size(); i++) {
                count += questions.get(i);
            }
            if (count > 30 && count < 39) {
                request.setAttribute("result", "Өміріңіздегі жанұяда ең үлкен әрі қымбатты адам балаңыз деп санайсыз. Сіз балаңызды түсініп қана қоймай, оны тереңірек білгіңіз келеді және оған үлкен құрметпен қарайсыз. "
                        + "Сіз ең таңдаулы, озық тәрбиелеу әдістерді және тұрақты тәртіп жолдарын ұсынасыз. Сіз өте дұрыс бағыттасыз және болашақта жақсы нәтижеге ие боласыз.");
            } else if (count > 16 && count < 30) {
                request.setAttribute("result", "Бала Сіз үшін бірінші орындағы маңызды мәселе. Сіздің бойыңызда тәрбиешілік қасиет қалыптасқан, бірақ өмірде оны жүйелі және мақсатты түрде қолдана бермейсіз. "
                        + "Кейде өзіңізді қатал ұстайсыз, кейде жұмсақ ұстайсыз, тіпті кейде тәрбиелік тиімділікті әлсірететіндей келісімге барасыз. Сізге бала тәрбиесі жөніндегі өз әдісіңіз жөнінде кеңірек ойлану қажет.");
            } else if (count < 16) {
                request.setAttribute("result", "Бала тәрбиесі Сіз үшін үлкен қиыншылық туғызады. Сізге не білім жетіспейді, не болмаса балаңызды жеке тұлға ретінде жасауға ниетіңіз, ұмтылысыңыз жетіспейді. "
                        + "Сол себептен сіз педагогтан және психолгтан кеңес алғаныңыз жөн.");
            }
        }
        page = "/jsp/quiz.jsp";
        return page;
    }
}
