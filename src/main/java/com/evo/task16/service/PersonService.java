package com.evo.task16.service;

import com.evo.task16.dto.Message;
import com.evo.task16.dto.Person;
import com.evo.task16.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public ResponseEntity<Person> addMessageToPerson(int personId, Message message) {
        Person person;
        if (!repository.existsById(personId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        person = repository.findById(personId).get();
        message.setPerson(person);
        message.setTime(LocalDateTime.now());
        person.addMessage(message);
        return new ResponseEntity<>(repository.save(person), HttpStatus.OK);
    }

    public Person personSave(int id, Person person) {
        Person temp = repository.findById(id).orElse(person);
        temp.setFirstname(person.getFirstname());
        temp.setSurname(person.getSurname());
        temp.setLastname(person.getLastname());
        temp.setBirthday(person.getBirthday());
        repository.save(temp);
        return temp;
    }

    public void deleteMessagesById(int PersonId, int MesID) {
        Person person = repository.findById(PersonId).get();
        person.deleteMessageById(MesID);
        repository.save(person);
    }

    public Message getPersonMessage(int PersonId, int MesID) {
        Person person = repository.findById(PersonId).get();
        Message message = person.getMessageById(MesID);
        return message;
    }

    public List<Message> getMessagesByPersonId(int PersonId, List<Message> messages) {
        Person person = repository.findById(PersonId).get();
        messages = person.getMessages();
        return messages;
    }
}