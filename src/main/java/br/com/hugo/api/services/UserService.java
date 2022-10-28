package br.com.hugo.api.services;

import br.com.hugo.api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
