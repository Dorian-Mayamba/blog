package uk.co.blog.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import uk.co.blog.dtos.authors.AuthorDTO;
import uk.co.blog.models.Author;

@Mapper()
public interface AuthorMapper {
    AuthorDTO authorToDTO(Author author);
}
