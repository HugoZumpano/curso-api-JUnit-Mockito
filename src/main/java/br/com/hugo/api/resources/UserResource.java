package br.com.hugo.api.resources;

import br.com.hugo.api.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @GetMapping(value = "{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){

        return ResponseEntity.ok().body(new User(1,"Hugo","hugo125@email.com","123"));



    }



}
