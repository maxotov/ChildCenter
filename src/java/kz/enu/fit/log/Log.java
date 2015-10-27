package kz.enu.fit.log;

import java.io.File;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {

    private static final Logger log = Logger.getLogger(Log.class);
  
    public Log(){
        check();
    }
    private void check() {
        File file = new File("WEB-INF/log4j.properties");

        if (file.exists()) {
            try {
                PropertyConfigurator.configure(file.getPath());
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    public void error(String text) {
        log.error(text);
    }

    public void trace(String text) {
        log.trace(text);
    }

    public void debug(String text) {
        log.debug(text);
    }

    public void info(String text) {
        log.info(text);
    }

    public void warn(String text) {
        log.warn(text);
    }

    public void fatal(String text) {
        log.fatal(text);
    }
}