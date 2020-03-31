package figi.spring.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private final MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world/path-variable/{message}")
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String message) {
        return new HelloWorldBean(String.format("Hello World, %s", message));
    }

    @GetMapping(path = "/hello-world-int")
    public String helloWorldInt() {
        return messageSource.getMessage("hello.world", null, LocaleContextHolder.getLocale());
    }
}
