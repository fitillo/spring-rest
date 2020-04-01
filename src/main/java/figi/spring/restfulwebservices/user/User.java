package figi.spring.restfulwebservices.user;

import figi.spring.restfulwebservices.model.BaseEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
//@ApiModel(description = "User details")
public class User extends BaseEntity {

    @NotBlank
    @Size(min = 2, message = "{user.name.validation}")
    //@ApiModelProperty(notes = "Name should have at least 2 characters")
    private String name;

    @NotNull
    @Past(message = "{user.birthDate.validation}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@ApiModelProperty(notes = "Birthdate has to be a past date")
    private LocalDate birthDate;

    @Builder
    public User(Long id, @NotBlank String name, @NotNull LocalDate birthDate) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
    }
}
