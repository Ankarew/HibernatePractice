package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public class PersonDao {

    @PersistenceContext
    private EntityManager entityManager;
    //Read
    public List<Person> getAll() {
        return entityManager.createQuery("from Person",
                Person.class).getResultList();
    }

    public Person getById(UUID id) {
        return entityManager.find(Person.class, id);
    }

    //Create
    public Person create(Person person) {
        entityManager.persist(person);
        return person;
    }

    //Update
    public Person update(UUID id, Person person) {
        Person old = entityManager.find(Person.class, id);
        if (old != null) {
            old.setFirstName(person.getFirstName());
            old.setLastName(person.getLastName());
            old.setPosition(person.getPosition());
            old.setBirthdate(person.getBirthdate());
            entityManager.merge(old);
        }
        return old;
    }

    //Delete
    public void delete(UUID id) {
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}
