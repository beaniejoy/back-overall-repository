package io.beaniejoy.springdatajpa.data;

import io.beaniejoy.springdatajpa.entity.cafe.Cafe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import static io.beaniejoy.springdatajpa.data.CafeSpecification.*;
import static org.springframework.data.jpa.domain.Specification.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CafeSearch {
    private String name;
    private String address;

    public Specification<Cafe> toSpecification() {
        return where(nameEqual(name)).and(addressLike(address));
    }
}
