package uk.co.blog.dtos.posts;

import uk.co.blog.dtos.comments.CommentDTO;

import java.time.Instant;
import java.util.List;

public record PostDTO(String content, Instant createdAt, Instant updatedAt, List<CommentDTO> comments) {
}
