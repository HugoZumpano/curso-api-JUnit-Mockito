package br.com.hugo.api.services;

import br.com.hugo.api.domain.User;
import br.com.hugo.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO obj);
}
