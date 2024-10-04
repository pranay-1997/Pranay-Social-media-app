package com.pranay.pranay_social_youtube.repository;

import com.pranay.pranay_social_youtube.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {

    public List<Message> findByChatId(Integer chatId);
}
