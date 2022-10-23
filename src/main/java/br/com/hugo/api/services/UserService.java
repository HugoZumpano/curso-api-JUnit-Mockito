package br.com.hugo.api.services;

import br.com.hugo.api.domain.User;

public interface UserService {

    User findById(Integer id);

}
