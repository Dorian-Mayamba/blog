package uk.co.blog.dtos.authors;

import uk.co.blog.dtos.posts.PostDTO;
import uk.co.blog.dtos.roles.RoleDTO;

import java.util.List;

public record AuthorDTO(String name, String email, String password, List<PostDTO> posts, List<RoleDTO> roles) {
}
