package com.pranay.pranay_social_youtube.services;

import com.pranay.pranay_social_youtube.model.Chat;
import com.pranay.pranay_social_youtube.model.User;

import java.util.List;

public interface ChatService {
    public Chat createChat(User reqUser,User user2);
    public Chat findChatById(Integer chatId) throws Exception;
    public List<Chat> findUsersChat(Integer userId);
}
