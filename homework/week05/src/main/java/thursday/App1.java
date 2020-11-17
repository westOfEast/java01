package thursday;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//注解配置
public class App1 {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("thursday/ApplicationContext01.xml");
        Object department = ctx.getBean("department");
        System.out.println(department.toString());
    }
}
