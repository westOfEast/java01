package saturday;

import org.springframework.boot.context.properties.ConfigurationProperties;
import saturday.bean.Klass;
import saturday.bean.School;
import saturday.bean.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("school.starter")
@ConditionalOnProperty(prefix = "school.starter",value = "enable",havingValue = "true",matchIfMissing = false)
public class SchoolStarterConfiguration {

    private String name;

    private Integer age;

    @Bean
    @ConditionalOnMissingBean
    public School school(){
        Student student = new Student(name,age);
        Klass klass = new Klass(student);
        return new School(klass);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
