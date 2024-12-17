package uk.co.blog.dtos.responses;

import lombok.*;
import uk.co.blog.dtos.authors.AuthorDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterResponse {
    private String message;
    private AuthorDTO authorDTO;
}
