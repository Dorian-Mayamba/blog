package uk.co.blog.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uk.co.blog.dtos.authors.CreateAuthorDTO;
import uk.co.blog.dtos.responses.LoginResponse;
import uk.co.blog.factories.RepositoryFactory;
import uk.co.blog.mappers.AuthorMapper;
import uk.co.blog.models.Author;
import uk.co.blog.models.Role;
import uk.co.blog.models.User;
import uk.co.blog.repositories.AuthorRepository;
import uk.co.blog.repositories.UserRepository;
import uk.co.blog.dtos.responses.RegisterResponse;
import uk.co.blog.util.TokenGenerator;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private RepositoryFactory repositoryFactory;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRepository userRepository = repositoryFactory.getUserRepository(AuthorRepository.class);
        return userRepository.findUserByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("Could not find username with email "+username));
    }
    public RegisterResponse register(CreateAuthorDTO createAuthorDTO){
        try{
            loadUserByUsername(createAuthorDTO.email());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "The email "+
                    createAuthorDTO.email() + " is already in our records");

        }catch (UsernameNotFoundException ex){
            Author newAuthor = new Author();
            newAuthor.setName(createAuthorDTO.name());
            newAuthor.setEmail(createAuthorDTO.email());
            newAuthor.setPassword(encoder.encode(createAuthorDTO.password()));
            UserRepository userRepository = repositoryFactory.getUserRepository(AuthorRepository.class);
            assignRoleToUser(newAuthor, "USER", "AUTHOR");
            Author savedAuthor = userRepository.save(newAuthor);
            return new RegisterResponse("You have successfully register", authorMapper.authorToDTO(savedAuthor));
        }
    }
    public LoginResponse login(Authentication authentication){
        try{
            User user = (User) loadUserByUsername(authentication.getName());
            String token = tokenGenerator.token(authentication);
            LoginResponse response = LoginResponse
                    .builder()
                    .user(authorMapper.authorToDTO((Author) user))
                    .accessToken(token)
                    .message("Hello "+ user.getName())
                    .email(user.getEmail())
                    .username(user.getName())
                    .build();
            return response;
        }catch (UsernameNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }

    public void assignRoleToUser(User user, String... roles){
        if(roles.length > 0){
            for(String role: roles){
                Role roleToAdd = roleService.findByName(role);
                user.getRoles().add(roleToAdd);
            }
        }
    }

}
