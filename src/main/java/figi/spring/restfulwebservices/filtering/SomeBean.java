package figi.spring.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@JsonFilter("SomeBeanFilter")
public class SomeBean {

    private String field1;
    private String field2;
    private String field3;
}
