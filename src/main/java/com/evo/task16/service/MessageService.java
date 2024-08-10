package com.evo.task16.service;

import com.evo.task16.dto.Message;
import com.evo.task16.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageRepository repository;

    public Message SaveMessage(int id, Message message) {
        Message temp = repository.findById(id).orElse(message);
        temp.setText(message.getText());
        temp.setTime(message.getTime());
        temp.setTitle(message.getTitle());
        repository.save(temp);
        return temp;
    }

}
