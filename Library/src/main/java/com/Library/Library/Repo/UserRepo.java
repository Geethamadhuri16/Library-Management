package com.Library.Library.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Library.Library.Model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
