package com.test.mongo.react.controllers;

import com.test.mongo.react.models.Contact;
import com.test.mongo.react.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ContactController {

    @Autowired
    ContactRepository repository;

    @RequestMapping(method = RequestMethod.GET, value = "/contacts")
    public Iterable<Contact> contact() {
        return repository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contacts")
    public Contact save(@RequestBody Contact contact) {
        Contact result = repository.save(contact);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contacts/{id}")
    public Optional<Contact> show(@PathVariable String id) {
        Optional<Contact> result = repository.findById(id);
        return result;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "contacts/{id}")
    public Contact update(@PathVariable String id, @RequestBody Contact contact) {
        Optional<Contact> findById = repository.findById(id);
        if (findById.isPresent()) {
            Contact c = findById.get();
            if (contact.getName() != null)
                c.setName(contact.getName());
            if (contact.getAddress() != null)
                c.setAddress(contact.getAddress());
            if (contact.getCity() != null)
                c.setCity(contact.getCity());
            if (contact.getPhone() != null)
                c.setPhone(contact.getPhone());
            if (contact.getEmail() != null)
                c.setEmail(contact.getEmail());
            repository.save(c);
            return c;
        }
        return null;
    }

    @DeleteMapping(value = "contacts/{id}")
    public void delete(@PathVariable String id) {
        Optional<Contact> byId = repository.findById(id);
        byId.ifPresent(contact -> repository.delete(contact));
    }
}
