package figi.spring.restfulwebservices.user;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Profile("list")
public class UserDaoServiceList implements UserDaoService {

    private final List<User> users = new ArrayList<>();
    private static final AtomicLong idGenerator = new AtomicLong(1L);

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
        }
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(User object) {

    }

    @Override
    public boolean deleteById(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

    @Override
    public Post addPost(User user, Post post) {
        return null;
    }
}
