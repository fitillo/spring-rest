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
public class User extends BaseEntity {

    @NotBlank
    @Size(min = 2, message = "{user.name.validation}")
    private String name;

    @NotNull
    @Past(message = "Birthdate has to be a past date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Builder
    public User(Long id, @NotBlank String name, @NotNull LocalDate birthDate) {
        super(id);
        this.name = name;
        this.birthDate = birthDate;
    }
}
