package uk.co.blog.seeders;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import uk.co.blog.models.Role;
import uk.co.blog.repositories.RoleRepository;
import java.util.List;


public class RoleSeeder implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.count() == 0){
            roleRepository.saveAll(
                    List.of(Role.builder().name("ADMIN").build(),
                            Role.builder().name("USER").build(),
                            Role.builder().name("AUTHOR").build())
            );
        }
    }
}
