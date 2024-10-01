package com.pranay.pranay_social_youtube.repository;

import com.pranay.pranay_social_youtube.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
