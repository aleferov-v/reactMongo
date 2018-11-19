package com.test.mongo.react.repositories;

import com.test.mongo.react.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, String> {

    @Override
    void delete(Contact entity);

    @Override
    Iterable<Contact> findAll();
}
