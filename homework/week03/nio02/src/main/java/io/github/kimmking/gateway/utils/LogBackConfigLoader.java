package io.github.kimmking.gateway.utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.classic.joran.JoranConfigurator;
//import ch.qos.logback.core.joran.spi.JoranException;
//import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * Simple Utility class for loading an external config file for logback
 * @author daniel
 */
public class LogBackConfigLoader {

//    public static void load (String externalConfigFileLocation) throws IOException, JoranException {
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
//
//        File externalConfigFile = new File(externalConfigFileLocation);
//        if(!externalConfigFile.exists()){
//            throw new IOException("Logback External Config File Parameter does not reference a file that exists");
//        }else{
//            if(!externalConfigFile.isFile()){
//                throw new IOException("Logback External Config File Parameter exists, but does not reference a file");
//            }else{
//                if(!externalConfigFile.canRead()){
//                    throw new IOException("Logback External Config File exists and is a file, but cannot be read.");
//                }else{
//                    JoranConfigurator configurator = new JoranConfigurator();
//                    configurator.setContext(lc);
//                    lc.reset();
//                    configurator.doConfigure(externalConfigFileLocation);
//                    StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
//                }
//            }
//        }
//    }

//    public static BufferedReader getReader(String name) {
//        // maven工程修改词典加载方式
//        InputStream in = this.class.getResourceAsStream("/" + name);
//        try {
//            return new BufferedReader(new InputStreamReader(in, "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            logger.warn("不支持的编码", e);
//        }
//        return null;
//    }
//
//    public static InputStream getInputStream(String name) {
//        // maven工程修改词典加载方式
//        InputStream in = this.class.getResourceAsStream("/" + name);
//        return in;
//    }

}
