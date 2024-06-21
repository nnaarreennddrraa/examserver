package com.exam.examserver.services;

import java.util.Set;

import com.exam.examserver.model.User;
import com.exam.examserver.model.UserRole;
import com.exam.response.UserResponse;

public interface UserService {
    public UserResponse createUser(User user,Set<UserRole> userRoles);

    public User getUser(String username);

    public void deleteUser(Long userId);

    
    
    
}
