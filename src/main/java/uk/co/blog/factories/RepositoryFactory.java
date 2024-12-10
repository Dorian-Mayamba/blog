package uk.co.blog.factories;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import uk.co.blog.repositories.UserRepository;

@Component
public class RepositoryFactory {
    private final ApplicationContext applicationContext;

    public RepositoryFactory(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }

    public <T extends UserRepository> T getUserRepository (Class<T> repositoryClass){
        return applicationContext.getBean(repositoryClass);
    }
}
