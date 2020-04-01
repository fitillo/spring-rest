package figi.spring.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import figi.spring.restfulwebservices.model.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Post extends BaseEntity {

    @Size(min = 2, message = "{post.description.validation}")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @JsonIgnore
    private User user;

    @Builder
    public Post(Long id, String description) {
        super(id);
        this.description = description;
    }
}
