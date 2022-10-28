package br.com.hugo.api.repositories;

import br.com.hugo.api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


 @Repository
public  interface UserRepository extends  JpaRepository<User ,Integer>{
        }


