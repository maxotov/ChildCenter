package kz.enu.fit.logic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import kz.enu.fit.entities.User;
import kz.enu.fit.logic.exceptions.IncorrectInfoClient;
import kz.enu.fit.logic.exceptions.IncorrectRegister;
import kz.enu.fit.dao.RegisterDAO;
import kz.enu.fit.dao.UserDAO;
import kz.enu.fit.logic.exceptions.IncorrectEmailException;
import kz.enu.fit.logic.exceptions.IncorrectLogin;
import kz.enu.fit.logic.exceptions.IncorrectLoginException;
import kz.enu.fit.managers.ConfigurationManager;

public class RegisterLogic {
    private RegisterDAO dao;
    private UserDAO daoUser;
    
    public RegisterLogic() {
        dao = new RegisterDAO();
        daoUser = new UserDAO();
    }
    
    public void registerClient(String log, String lastname, String pass, String phone, String email) throws IncorrectInfoClient{
        if(log.isEmpty() || pass.isEmpty() || phone.isEmpty() || email.isEmpty()){
            throw new IncorrectInfoClient();
        }
        dao.Register(log, lastname, getHashMD5(pass), phone, email);
        //sendMail(log, email, pass, phone);
    }
    
    public void checkLoginRegister(String email) throws IncorrectRegister {
        User user = daoUser.findUser(email);
        if((user!=null) && (email.equals(user.getEmail()))){
           throw new IncorrectRegister();   
        } 
    }
    
    public void checkLoginPassword(String login, String password) throws IncorrectLoginException, IncorrectLogin, IncorrectInfoClient{
        if(login.isEmpty() || password.isEmpty()){
            throw new IncorrectInfoClient();
        } else if((login.length() < 4) || (password.length() < 4)){
            throw new IncorrectLoginException();
        } else if(!Character.isLetter(login.charAt(0))){
            throw new IncorrectLogin();
        } 
    }
    
    public void checkEmail(String phone, String email) throws IncorrectEmailException, IncorrectInfoClient{
        Pattern patternNumber = Pattern.compile("7\\d{9}");
        Matcher matchArr = patternNumber.matcher(phone);
        Pattern patternEmail = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matchEmail = patternEmail.matcher(email);
        if(phone.isEmpty() || email.isEmpty()){
            throw new IncorrectInfoClient();
        } else if(!matchArr.matches()){
            throw new  IncorrectEmailException();
        } else if(!matchEmail.matches()){
            throw new IncorrectEmailException();
        }
    }
    
    public static String getHashMD5(String str) {
        if (str == null){
            throw new IllegalArgumentException("String can't be null");
        }
        MessageDigest md5;
        StringBuffer hexString = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("md5");
            md5.reset();
            md5.update(str.getBytes());
            byte messageDigest[] = md5.digest();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }
        return hexString.toString();
    }
    
    private static void sendMail(String firstname, String email, String pass, String phone) {
        final String username = "aibol93@gmail.com";
        final String password = "";

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

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aibol93@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(ConfigurationManager.getProperty("mail.register.title"));
            String content = "Добрый день,  " + firstname + " !\n";
            content += ConfigurationManager.getProperty("mail.register.msg") + "\n";
            content += ConfigurationManager.getProperty("mail.register.data") + "\n";
            content += ConfigurationManager.getProperty("mail.login") + "  " + firstname + "\n";
            content += ConfigurationManager.getProperty("mail.pass") + "  " + pass + "\n";
            content += ConfigurationManager.getProperty("mail.phone") + "  " + phone + "\n";
            content += ConfigurationManager.getProperty("mail.email") + "  " + email + "\n";
            content += ConfigurationManager.getProperty("mail.register.respect") + "  www.itdamu.kz";
            //message.setContent(content, "text/html; charset=utf-8");
            message.setText(content);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
