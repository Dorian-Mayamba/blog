package uk.co.blog.dtos.comments;

import java.time.Instant;

public record CommentDTO(String content, Instant createdAt, Instant updatedAt) {
}
