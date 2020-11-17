package thursday;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//xml 配置bean
public class App {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("thursday/ApplicationContext.xml");
        Object school = ctx.getBean("school");
        System.out.println(school.toString());
    }
}
