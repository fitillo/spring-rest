package figi.spring.restfulwebservices.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

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
        User user = this.userDaoService.findById(id);

        if (user == null) {
            throw new UserNotFoundException("id - "+id);
        }

        //HATEOAS "all-users"
        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(linkTo.withRel("all-users"));

        return model;
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
