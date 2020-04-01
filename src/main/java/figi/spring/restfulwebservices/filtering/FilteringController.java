package figi.spring.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter",
                SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2"));
        mapping.setFilters(filterProvider);

        return mapping;
    }
}
