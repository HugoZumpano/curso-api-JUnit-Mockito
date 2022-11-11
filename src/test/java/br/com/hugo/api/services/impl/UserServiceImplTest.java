package br.com.hugo.api.services.impl;


import br.com.hugo.api.domain.User;
import br.com.hugo.api.domain.dto.UserDTO;
import br.com.hugo.api.repositories.UserRepository;
import br.com.hugo.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;


import static javax.swing.Action.NAME;
import static javax.swing.text.html.parser.DTDConstants.ID;

import static org.h2.engine.DbObject.INDEX;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest
class UserServiceImplTest {

    public static final String PASSWORD = "123";
    public static final String EMAIL = "email@hotmail";
    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

   private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        User response = service.findById(ID);

        Assertions.assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(1,response.getId());
        assertEquals("hugo",response.getName());
        assertEquals("email@hotmail",response.getEmail());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado."));

        try{
            service.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado.", ex.getMessage());
        }

    }
    @Test
    void whenfindAllThenReturnAnListOfUsers() {
        when(repository.findAll()).thenReturn(List.of(user));
        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());

        assertEquals(1, response.get(0).getId());
        assertEquals("hugo", response.get(0).getName());
        assertEquals("email@hotmail", response.get(0).getEmail());
        assertEquals("123", response.get(0).getPassword());

    }

    @Test
    void whenCreateThenReturnSuccess() {
       when(repository.save(any())).thenReturn(user);
        User response = service.create(userDTO);

        Assertions.assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(1,response.getId());
        assertEquals("hugo",response.getName());
        assertEquals("email@hotmail",response.getEmail());
        assertEquals("123",response.getPassword());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        User response = service.create(userDTO);

        try{
            optionalUser.get().setId(1);
            service.create(userDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema.",ex.getMessage());
        }
    }
    @Test
    void whenUpdateThenReturnSuccess() {

        when(repository.save(any())).thenReturn(user);
        User response = service.update(userDTO);

        Assertions.assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(1,response.getId());
        assertEquals("hugo",response.getName());
        assertEquals("email@hotmail",response.getEmail());
        assertEquals("123",response.getPassword());
    }
    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(repository.findByEmail(anyString())).thenReturn(optionalUser);
        User response = service.create(userDTO);

        try{
            optionalUser.get().setId(1);
            service.create(userDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals("E-mail já cadastrado no sistema.",ex.getMessage());
        }
    }


    @Test
    void deleteWithSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalUser);
        Mockito.doNothing().when(repository).deleteById(anyInt());

        service.delete(ID);
        Mockito.verify(repository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithObjectNotFoundException() {
        when(repository.findById(anyInt())).
                thenThrow(new ObjectNotFoundException("Objeto não encontrado."));

        try {
            service.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Objeto não encontrado.", ex.getMessage());
        }
    }

    private void startUser(){
        user = new User(1, "hugo", "email@hotmail", "123");
        userDTO = new UserDTO(1, "hugo", "email@hotmail", "123");
        optionalUser = Optional.of(new User(1,"hugo","email@hotmail","123"));
    }
}


