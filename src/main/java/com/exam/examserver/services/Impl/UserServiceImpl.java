package com.exam.examserver.services.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.examserver.repository.RoleRepository;
import com.exam.examserver.repository.UserRepository;
import com.exam.examserver.services.UserService;
import com.exam.response.UserResponse;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponse createUser(User user, Set<UserRole> userRoles) {
        User existingUser = this.userRepository.findByUserName(user.getUserName());

        if (existingUser != null) {
            throw new RuntimeException("User already exists with username: " + user.getUserName());
        } else {
            // Save the user first
            existingUser = this.userRepository.save(user);

            if (userRoles != null) {
                for (UserRole ur : userRoles) {
                    // Set the saved user to UserRole before saving
                    ur.setUser(existingUser);
                    roleRepository.save(ur.getRole());
                    existingUser.getUserRoles().add(ur);
                }
            }

            // Save the user with roles attached
            existingUser = this.userRepository.save(existingUser);
        }

        UserResponse newUserResponse = new UserResponse();

        newUserResponse.setId(existingUser.getId());
        newUserResponse.setUserName(existingUser.getUserName());
        newUserResponse.setFirstName(existingUser.getFirstName());
        newUserResponse.setLastName(existingUser.getLastName());
        newUserResponse.setEmail(existingUser.getEmail());
        


        newUserResponse.setUserRoleId(existingUser.getUserRoles().iterator().next().getUserRoleId());

        return newUserResponse;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUserName(username);
        


    
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
        
        
    }
}
