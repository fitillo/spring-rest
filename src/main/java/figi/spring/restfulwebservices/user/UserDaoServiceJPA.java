package figi.spring.restfulwebservices.user;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("jpa")
public class UserDaoServiceJPA implements UserDaoService {

    private final UserRepository userRepository;

    public UserDaoServiceJPA(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        final List<User> users = new ArrayList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public boolean deleteById(Long id) {
        boolean exists = findById(id) != null;
        if (exists) this.userRepository.deleteById(id);
        return exists && (findById(id) == null);
    }
}
