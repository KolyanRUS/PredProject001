package com.javamaster.dao;

import com.javamaster.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AppUserRepo extends JpaRepository<AppUser, String> {

    AppUser findByUserName(@Param("username") String username);
}