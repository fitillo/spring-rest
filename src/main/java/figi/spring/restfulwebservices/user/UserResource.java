package figi.spring.restfulwebservices.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userDaoService.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {
        Optional<User> user = this.userDaoService.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id - "+id);
        }

        //HATEOAS "all-users"
        EntityModel<User> model = new EntityModel<>(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(linkTo.withRel("all-users"));

        return model;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = this.userDaoService.save(user);

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri()).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        if (! this.userDaoService.deleteById(id)) {
            throw new UserNotFoundException("id - " + id);
        }

        //return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/posts")
    public List<Post> getAllUserPosts(@PathVariable Long id) {
        Optional<User> user = this.userDaoService.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id - " + id);
        }
        return user.get().getPosts();
    }

    @PostMapping("/{id}/posts")
    public ResponseEntity<Post> createUserPost(@PathVariable Long id, @Valid @RequestBody Post post) {
        Optional<User> savedUser = this.userDaoService.findById(id);

        if (savedUser.isEmpty()) {
            throw new UserNotFoundException("User with id - " + id);
        }

        User user = savedUser.get();
        Post savedPost = this.userDaoService.addPost(user, post);

        this.userDaoService.save(user);

        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPost.getId()).toUri()).build();
    }
}
