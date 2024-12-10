package uk.co.blog.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue(value = "Admin")
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Admin extends User{

}
