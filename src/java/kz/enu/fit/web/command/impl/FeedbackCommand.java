package kz.enu.fit.web.command.impl;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import kz.enu.fit.managers.ConfigurationManager;
import kz.enu.fit.web.command.ActionCommand;

public class FeedbackCommand implements ActionCommand {
    
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String text = request.getParameter("text");
        
        if(name!=null && email!=null && text != null){
            if(name.isEmpty() || email.isEmpty() || text.isEmpty() || !checkEmail(email)){
                request.setAttribute("errorFeedback", "Сіз мәнді қате бердіңіз!");
            } else {       
                sendMail(name, email, text);
                request.setAttribute("errorFeedback", "Сіздің хабарлама сәтті жіберілді!");
            }
        }        
        page = "/jsp/contact.jsp";
        return page;
    }
    
    private boolean checkEmail(String email) {
        boolean flag = false;
        Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matchEmail = patternEmail.matcher(email);
        if(matchEmail.matches()){
            flag = true;
        }
        return flag;
    }
    
    private static void sendMail(String name, String email, String text) {
        final String username = "aibol93@gmail.com";
        final String password = "26091984maxotov";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aibol93@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("aibol93@gmail.com"));
            message.setSubject(ConfigurationManager.getProperty("mail.register.title"), "UTF-8");
            String content = ConfigurationManager.getProperty("mail.register.feedback") +"\n";
            content += ConfigurationManager.getProperty("mail.login") + "  " + name + "\n";
            content += ConfigurationManager.getProperty("mail.email") + "  " + email + "\n";
            content += ConfigurationManager.getProperty("mail.text") + "  " + text + "\n";
            content += ConfigurationManager.getProperty("mail.register.respect");
            message.setText(content, "UTF-8");
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
