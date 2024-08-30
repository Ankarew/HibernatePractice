package org.example;

import org.example.dao.PersonDao;
import org.example.dto.PersonDto;
import org.example.models.Person;
import org.example.models.Sales;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class QueriesController {

    private final QueriesRepository queriesRepository;
    private PersonDao personDao;

    public QueriesController (QueriesRepository queriesRepository, PersonDao personDao) {
        this.queriesRepository = queriesRepository;
        this.personDao = personDao;
    }

    @GetMapping("/sales")
    public List<Sales> getAllSales() {
        List<Sales> sales = queriesRepository.getAllSales();
        return sales;
    }

    @GetMapping("/persons")
    public List<Person> getAllPersons() {
        return queriesRepository.getAllPersons();
    }

    @PostMapping("/person")
    public String addPerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return "New person id - " + queriesRepository.addPerson(firstName, lastName);
    }

    @ResponseBody @PostMapping("/sale")
    public  String addSale(@RequestBody Sales sale) {
        return "New sale id - " + queriesRepository.addSale(sale.getPersonId(), sale.getQuantity());
    }

    @PatchMapping("/person/update")
    public Person editPerson(@RequestParam("id") UUID id, @RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName) {
        Person person = queriesRepository.searchPerson(id);
        if (!lastName.isEmpty()) {
            person.setLastName(lastName);
        }
        if (!firstName.isEmpty()) {
            person.setFirstName(firstName);
        }
        queriesRepository.editPerson(person);
        return person;
    }

    @PatchMapping("/sales/update")
    public Sales editSale(@RequestBody Sales sale) {
        Sales saleOld = queriesRepository.searchSale(sale.getId());
        if (sale.getPersonId().equals((UUID.fromString("00000000-0000-0000-0000-000000000000")))){
            sale.setPersonId(saleOld.getPersonId());
        }
        if (sale.getQuantity() == 0 ){
            sale.setQuantity(sale.getQuantity());
        }
        queriesRepository.editSale(sale);
        return sale;
    }

    @DeleteMapping("/sales/delete")
    public String deleteSale(@RequestParam("id") UUID id, @RequestParam("isPersonId") Boolean isPersonId) {
        if (isPersonId) {
            queriesRepository.deleteSalesByPersonId(id);
            return "Deleted by personId";
        }
        queriesRepository.deleteSalesById(id);
        return "deleted by id";
    }

    @DeleteMapping("/person/delete")
    public String deletePerson(@RequestBody Person person){
        if (person.getId() == UUID.fromString("00000000-0000-0000-0000-000000000000") && person.getFirstName().isEmpty()
                && person.getLastName().isEmpty()) {
            return "Empty body";
        } else {
            queriesRepository.deletePerson(person);
            return "Deleted";
        }
    }

    @GetMapping("/person/search-{id}")
    public Person searchPerson(@PathVariable("id") UUID id) {
        return queriesRepository.searchPerson(id);
    }

    @GetMapping("/person/search-")
    public List<Person> searchPerson(@RequestBody Person person){
        return queriesRepository.searchPerson(person);
    }

    @GetMapping("/sales/search-{id}")
    public Sales searchSale(@PathVariable("id") UUID id){
        return queriesRepository.searchSale(id);
    }

    @GetMapping("/sales/search-")
    public List<Sales> searchSale(@RequestBody Sales sale){
        return queriesRepository.searchSale(sale);
    }

    @GetMapping("/sales/search/quantity-greater")
    public List<Sales> searchSaleQuantity(@RequestParam("quantity") Integer quantity) {
        return queriesRepository.searchSaleQuantity(quantity);
    }

    @GetMapping("/hibernate/person/all")
    public List<Person> getAllPerson() {
        return personDao.getAll();
    }

    @GetMapping("/hibernate/person/get")
    public Person getPersonById(@RequestParam("id") UUID uuid) {
        return personDao.getById(uuid);
    }

    @PostMapping("/hibernate/person/")
    public Person addPerson(@RequestBody Person person) {
        return personDao.create(person);
    }

    @PutMapping("/hibernate/person/")
    public String updatePerson(@RequestBody PersonDto personDto) {
        return personDao.update(personDto);
    }

    @DeleteMapping("/hibernate/person/")
    public String deletePerson(@RequestParam("id") UUID uuid) {
        return personDao.delete(uuid);
    }
}
