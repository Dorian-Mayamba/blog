package uk.co.blog.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.co.blog.models.Author;
import uk.co.blog.repositories.AuthorRepository;


@Service
@RequiredArgsConstructor
public class AuthorService extends UserService {
    private final AuthorRepository authorRepository;

    public UserDetails findAuthorById(Long authorId){
        return authorRepository.findById(authorId).orElseThrow(()-> new UsernameNotFoundException("Could not find authorname by id "+authorId));
    }
}
