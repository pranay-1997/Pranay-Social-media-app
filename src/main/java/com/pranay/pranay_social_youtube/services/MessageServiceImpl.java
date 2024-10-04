package com.pranay.pranay_social_youtube.services;

import com.pranay.pranay_social_youtube.model.Chat;
import com.pranay.pranay_social_youtube.model.Message;
import com.pranay.pranay_social_youtube.model.User;
import com.pranay.pranay_social_youtube.repository.ChatRepository;
import com.pranay.pranay_social_youtube.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;
    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {
        Chat chat= chatService.findChatById(chatId);

        Message message=new Message();
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());
        Message savedMessage=messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) throws Exception {
        Chat chat= chatService.findChatById(chatId);
        return  messageRepository.findByChatId(chatId);

    }
}
