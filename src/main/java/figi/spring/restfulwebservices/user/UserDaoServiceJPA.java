package figi.spring.restfulwebservices.user;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("jpa")
public class UserDaoServiceJPA implements UserDaoService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserDaoServiceJPA(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
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
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id);
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

    @Override
    public Post addPost(User user, Post post) {
        post.setUser(user);
        return postRepository.save(post);
    }
}
