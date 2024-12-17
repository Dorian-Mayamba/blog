package uk.co.blog.dtos.responses;

import lombok.*;
import uk.co.blog.dtos.roles.RoleDTO;
import uk.co.blog.dtos.users.UserDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String message;
    private String accessToken;
    private UserDTO user;
    private String email;
    private String username;
}
