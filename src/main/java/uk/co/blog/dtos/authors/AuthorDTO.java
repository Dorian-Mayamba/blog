package uk.co.blog.dtos.authors;

import lombok.*;
import uk.co.blog.dtos.posts.PostDTO;
import uk.co.blog.dtos.users.UserDTO;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO extends UserDTO {
    private List<PostDTO> posts;

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
