package uk.co.blog.dtos.posts;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import uk.co.blog.dtos.authors.AuthorDTO;
import uk.co.blog.dtos.comments.CommentDTO;

import java.time.Instant;
import java.util.List;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
    private String authorName;
    private String authorEmail;
    private List<CommentDTO> comments;
}
