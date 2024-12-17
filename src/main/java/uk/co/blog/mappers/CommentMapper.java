package uk.co.blog.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import uk.co.blog.dtos.comments.CommentDTO;
import uk.co.blog.models.Comment;

@Mapper()
public interface CommentMapper {
    CommentDTO commentToDTO(Comment comment);
}
