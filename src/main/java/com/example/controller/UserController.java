package com.example.controller;

import com.example.service.UserService;

import com.example.dto.response.UserResponseDto;

import com.example.dto.request.UserRequestDto;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("users/save")
    public UserResponseDto save(@Valid @RequestBody  UserRequestDto user){
        return userService.save(user);
    }

    @PostMapping("users/saveAll")
    public List<UserResponseDto> saveAll(@Valid @RequestBody List<UserRequestDto> users){
        return userService.saveAll(users);
    }

    @GetMapping("users")
    public Page<UserResponseDto> findAll(Pageable pageable){
        return userService.findAll(pageable);
    }
    @GetMapping("users/Id/{id}")
    public UserResponseDto findById(@PathVariable String Id){
        return userService.findById(Id);
    }
    @GetMapping("users/name/{name}")
    public UserResponseDto findByName(@PathVariable String name){
        return userService.findByName(name);
    }
    @DeleteMapping("users/delete/{id}")
    public void deleteById(@PathVariable String Id){
        userService.deleteById(Id);
    }
    
}
