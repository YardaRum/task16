package com.evo.task16.controller;

import com.evo.task16.dto.Message;
import com.evo.task16.dto.Person;
import com.evo.task16.service.MessageService;
import com.evo.task16.service.PersonService;
import com.evo.task16.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonService personService;

    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }

    @GetMapping("/person/{p_id}")
    public Optional<Person> findPersonById(@PathVariable int p_id) {
        return repository.findById(p_id);
    }

    @GetMapping("/person/{p_id}/message")
    public List<Message> getMessagesByPersonId(@PathVariable int p_id) {
        return personService.getMessagesByPersonId(p_id);
    }

    @GetMapping("/person/{p_id}/message/{m_id}")
    public Message getPersonMessage(@PathVariable int p_id, @PathVariable int m_id) {
        return personService.getPersonMessage(p_id, m_id);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }

    @PutMapping("/person/{p_id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int p_id, @RequestBody Person person) {
        HttpStatus status = repository.existsById(p_id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(personService.personSave(p_id, person), status);
    }

    @DeleteMapping("/person/{p_id}")
    public void deletePerson(@PathVariable int p_id) {
        repository.deleteById(p_id);
    }

    @PostMapping("/person/{p_id}/message")
    public ResponseEntity<Person> addMessage(@PathVariable int p_id, @RequestBody Message message) {
        return personService.addMessageToPerson(p_id, message);
    }

    @DeleteMapping("/person/{p_id}/message/{m_id}")
    public void deleteMessageById(@PathVariable int p_id, @PathVariable int m_id) {
        personService.deleteMessagesById(p_id, m_id);
    }

}
