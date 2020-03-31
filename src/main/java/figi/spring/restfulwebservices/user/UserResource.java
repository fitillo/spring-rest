package figi.spring.restfulwebservices.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

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
    public User getUserById(@PathVariable Long id) {
        User user = this.userDaoService.findById(id);

        if (user == null) {
            throw new UserNotFoundException("id - "+id);
        }

        return user;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
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
}
