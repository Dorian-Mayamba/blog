package uk.co.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.co.blog.factories.RepositoryFactory;
import uk.co.blog.repositories.AuthorRepository;
import uk.co.blog.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private RepositoryFactory repositoryFactory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRepository userRepository = repositoryFactory.getUserRepository(AuthorRepository.class);
        return userRepository.findUserByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("Could not find username with email "+username));
    }


}
