package uk.co.blog.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.blog.mappers.*;

@Configuration
public class EntityMappingConfigs {
    @Bean
    public AuthorMapper authorMapper(){
        return new AuthorMapperImpl();
    }

    @Bean
    public RoleMapper createRoleMapper(){
        return new RoleMapperImpl();
    }

    @Bean
    public PostMapper createPostMapper(){
        return new PostMapperImpl();
    }

    @Bean
    public CommentMapper createCommentMapper(){
        return new CommentMapperImpl();
    }
}
