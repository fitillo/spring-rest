package figi.spring.restfulwebservices.bootstrap;

import figi.spring.restfulwebservices.user.User;
import figi.spring.restfulwebservices.user.UserDaoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserDaoService userDaoService;

    public DataLoader(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userDaoService.save(User.builder().name("Adam")
                .birthDate(LocalDate.of(2019,1,1)).build());
        this.userDaoService.save(User.builder().name("Bruce")
                .birthDate(LocalDate.of(2018,2,2)).build());
        this.userDaoService.save(User.builder().name("Mark")
                .birthDate(LocalDate.of(2017,3,3)).build());
    }
}
