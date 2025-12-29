package com.dispatchwhatsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dispatchwhatsapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByPhone(String phone);  // <-- ADD THIS LINE
}


