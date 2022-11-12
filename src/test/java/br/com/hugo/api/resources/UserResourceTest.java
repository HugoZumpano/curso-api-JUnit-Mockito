package br.com.hugo.api.resources;

import br.com.hugo.api.config.ModelMappperConfig;
import br.com.hugo.api.domain.User;
import br.com.hugo.api.domain.dto.UserDTO;
import br.com.hugo.api.services.impl.UserServiceImpl;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserResourceTest {

    private User user;

    private UserDTO userDTO;


    @InjectMocks
    private UserResource resource;
    @Mock
    private UserServiceImpl service;
    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = resource.findById(5);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(1, response.getBody().getId());
        assertEquals("hugo", response.getBody().getName());
        assertEquals("email@hotmail", response.getBody().getEmail());
        assertEquals("123", response.getBody().getPassword());


    }

    @Test
    void WhenFindAllThenReturnAListOfUserDTO() {
        when(service.findAll()).thenReturn( List.of(user));
        when(mapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(0).getClass());

        assertEquals(1, response.getBody().get(0).getId());
        assertEquals("hugo", response.getBody().get(0).getName());
        assertEquals("email@hotmail", response.getBody().get(0).getEmail());
        assertEquals("123", response.getBody().get(0).getPassword());


    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {

        user = new User(1, "hugo", "email@hotmail", "123");
        userDTO = new UserDTO(1, "hugo", "email@hotmail", "123");

    }
}