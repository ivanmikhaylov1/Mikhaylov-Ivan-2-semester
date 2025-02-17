package org.example.mikhaylovivan2semester.controller;

import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.service.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {
  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  private UUID userId;
  private UserDTO userDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    userId = UUID.randomUUID();
    userDTO = new UserDTO(userId, "Test User");
  }

  @Test
  void testFindAllUsers() throws Exception {
    when(userService.findAll()).thenReturn(List.of(userDTO));

    mockMvc.perform(get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data[0].id").value(userDTO.id().toString()))
        .andExpect(jsonPath("$.data[0].name").value(userDTO.name()));
  }

  @Test
  void testSaveUser() throws Exception {
    when(userService.save(anyString(), anyString())).thenReturn(java.util.Optional.of(userDTO));

    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Test User\",\"password\":\"password123\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.data.name").value("Test User"));

    verify(userService, times(1)).save(anyString(), anyString());
  }

  @Test
  void testSaveUserBadRequest() throws Exception {
    when(userService.save(any(), any())).thenReturn(Optional.empty());

    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Test User\",\"password\":\"password123\"}"))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Не удалось создать пользователя"));
  }


  @Test
  void testGetUserById() throws Exception {
    when(userService.getById(userId)).thenReturn(Optional.of(userDTO));

    mockMvc.perform(get("/users/{userId}", userId))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id").value(userDTO.id().toString()))
        .andExpect(jsonPath("$.data.name").value(userDTO.name()));
  }

  @Test
  void testGetUserByIdNotFound() throws Exception {
    when(userService.getById(userId)).thenReturn(Optional.empty());

    mockMvc.perform(get("/users/{userId}", userId))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Пользователь не найден"));
  }


  @Test
  void testFindUserByName() throws Exception {
    when(userService.findByName("Test User")).thenReturn(Optional.of(userDTO));

    mockMvc.perform(get("/users/by-name?name=Test User"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.id").value(userDTO.id().toString()))
        .andExpect(jsonPath("$.data.name").value(userDTO.name()));
  }

  @Test
  void testGetUserByNameNotFound() throws Exception {
    when(userService.findByName("NonExistingUser")).thenReturn(java.util.Optional.empty());

    mockMvc.perform(get("/users/by-name?name=NonExistingUser"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Пользователь не найден"));

    verify(userService, times(1)).findByName("NonExistingUser");
  }

  @Test
  void testUserExists() throws Exception {
    when(userService.exists("Test User")).thenReturn(true);

    mockMvc.perform(get("/users/exists?name=Test User"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data").value(true));
  }

  @Test
  void testUserNotExists() throws Exception {
    when(userService.exists("Nonexistent User")).thenReturn(false);

    mockMvc.perform(get("/users/exists?name=Nonexistent User"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data").value(false));
  }
}
