package uk.co.blog.dtos.comments;

import lombok.Data;

import java.time.Instant;

@Data
public class CommentDTO {
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
    private String authorName;
}
