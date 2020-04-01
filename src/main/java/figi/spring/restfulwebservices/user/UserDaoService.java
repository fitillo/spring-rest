package figi.spring.restfulwebservices.user;

import figi.spring.restfulwebservices.services.CrudService;

public interface UserDaoService extends CrudService<User, Long> {

    public Post addPost(User user, Post post);
}
