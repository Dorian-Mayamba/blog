package uk.co.blog.mappers;


import org.mapstruct.Mapper;
import uk.co.blog.dtos.posts.PostDTO;
import uk.co.blog.models.Post;
@Mapper()
public abstract class PostMapper {
    public abstract PostDTO postToDTO(Post post);

}
