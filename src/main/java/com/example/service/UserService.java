package com.example.service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.example.dto.request.UserRequestDto;
import com.example.dto.response.UserResponseDto;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.util.IDGenerator;
import com.example.exception.ResourcesNotFoundException;
import com.example.exception.DuplicateResourcesException;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public UserResponseDto save(UserRequestDto request){
         if(userRepository.existsByEmail(request.getEmail())){
                throw new DuplicateResourcesException("Mail Already Exists Use Another Mail " +request.getEmail());
            }
        if(userRepository.existsByPhoneNumber(request.getPhoneNumber())){
            throw new DuplicateResourcesException("Phone Number Already Exists Use Another Phone Number "+request.getPhoneNumber());
        }
        User user = new User();
        {
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setAddress(request.getAddress());
            user.setStatus(request.getStatus());
                  
        }
        String userId;
        do { 
            userId = IDGenerator.generatorUserId(request.getName());
            
        } while (userRepository.existsById(userId));
         user.setUserId(userId);

        User savedUser = userRepository.save(user);
        
        return new UserResponseDto(
            savedUser.getUserId(),
            savedUser.getName(),
            savedUser.getEmail(),
            savedUser.getPhoneNumber(),
            savedUser.getAddress(),
            savedUser.getStatus()
         );
        
    }

    public List<UserResponseDto> saveAll(List<UserRequestDto> requests){
        List<User> users = requests.stream().map(request->{
            if(userRepository.existsByEmail(request.getEmail())){
                throw new DuplicateResourcesException("Mail Already Exists Use Another Mail " +request.getEmail());
            }
            if(userRepository.existsByPhoneNumber(request.getPhoneNumber())){
                throw new DuplicateResourcesException("Phone Number Already Exists Use Another Phone Number "+request.getPhoneNumber());
            }

            User user = new User();
            {
                user.setName(request.getName());
                user.setEmail(request.getEmail());
                user.setPhoneNumber(request.getPhoneNumber());
                user.setAddress(request.getAddress());
                user.setStatus(request.getStatus());
            }
            String userId;
            do { 
                userId = IDGenerator.generatorUserId(request.getName());
                
            } while (userRepository.existsById(userId));
         
             user.setUserId(userId);

            return  user;   
            } ).toList();
            

            return userRepository.saveAll(users).stream().map(user->new UserResponseDto(
                   user.getUserId(),
                   user.getName(),
                   user.getEmail(),
                   user.getPhoneNumber(),
                   user.getAddress(),
                   user.getStatus()
            )).collect(Collectors.toList());
    }

    public Page<UserResponseDto> findAll(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        if(users.isEmpty()){
            throw new ResourcesNotFoundException("Users  Data Not Found");
        }

        return users.map(user-> new UserResponseDto(
            user.getUserId(),
            user.getName(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getAddress(),
            user.getStatus()
        ));
    }
    public UserResponseDto findById(String Id){
        User user = userRepository.findById(Id).orElseThrow(()-> new ResourcesNotFoundException("User with Id Not Found! "+Id));

        return new UserResponseDto(
            user.getUserId(),
            user.getName(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getAddress(),
            user.getStatus()
        );
    }

    public UserResponseDto findByName(String name){
        User user = userRepository.findByName(name);
        if(user == null){
            throw new ResourcesNotFoundException("User with name Not Found! " +name);
        }

        return new UserResponseDto(
            user.getUserId(),
            user.getName(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getAddress(),
            user.getStatus()

        );
    }

    public void deleteById(String id){
        if (!userRepository.existsById(id)) {
        throw new ResourcesNotFoundException(
            "User with Id Not Found! " + id
        );
    }
       userRepository.deleteById(id);
    }


}
