package com.exam.examserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examserver.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

   public User findByUserName(String UserName);
   
    
}
