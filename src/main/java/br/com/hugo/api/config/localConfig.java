package br.com.hugo.api.config;

import br.com.hugo.api.domain.User;
import br.com.hugo.api.repositories.UserRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class localConfig {

    @Autowired
    private UserRepository repository;

   @Bean
    public void startDB(){
        User u1 = new User( null,"HugoZumpano","teste@gmail.comss", "123");
        User u2 = new User( null,"Joao","Joao_zump@hotmail.comss", "123");
        User u3 = new User( null,"Joao2","JJoao_zump@hotmail.comssss2323ss", "123");
        User u4 = new User( null,"Joao3","Joao_zump@hotmail.comssss2323s", "123");

        repository.saveAll(List.of(u1,u2,u3,u4));





    }
}
