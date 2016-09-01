package configuration;

import lab.model.Country;
import lab.model.Person;
import lab.model.UsualPerson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
//@PropertySource("person.properties")
public class HelloWorldConfig {



    @Bean
    public Person person() {
        return new UsualPerson()
                .setName("John Smith")
                .setHeight(1.78f)
                .setAge(35)
                .setProgrammer(true);
    }

    @Bean
    public List<String> contacts() {
        return Arrays.asList("asd@asd.ru", "+7-234-456-67-89");
    }

    @Bean
    public Country country() {
        return new Country(1, "Russia", "RU");
    }
}