package org.example;

import org.example.mappers.PersonRowMapper;
import org.example.mappers.SalesRowMapper;
import org.example.models.Person;
import org.example.models.Sales;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class QueriesRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public QueriesRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public UUID addPerson(String firstName, String lastName) {
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(
                "INSERT INTO new_schema.person (id, first_name, last_name) " +
                        "VALUES (?, ?, ?)", firstName, lastName);
        return id;
    }

    public void addPersonNamed(String firstName, String lastName) {
        String sql = "INSERT INTO new_schema.person " +
                "VALUES (:uuid, :first_name, :last_name)";
        SqlParameterSource namedParameters = new MapSqlParameterSource("uuid", UUID.randomUUID());
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("first_name", firstName);
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("last_name", lastName);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public UUID addSale(UUID personID, Integer quantity) {
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(
                "INSERT INTO new_schema.sales " +
                        "VALUES (?, ?, ?)", id, personID, quantity);
        return id;
    }

    public void addSalesParam(Integer quantity, UUID personID) {
        String sql = "INSERT INTO new_schema.sales " +
                "VALUES (:uuid, :uuid_person, :quantity)";
        SqlParameterSource namedParameters = new MapSqlParameterSource("uuid", UUID.randomUUID());
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("uuid_person", personID);
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("quantity", quantity);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public UUID searchPersonUUID(String firstName, String lastName) {
        String sql = "SELECT id " +
                "FROM new_schema.person " +
                "WHERE first_name = :first_name and last_name = :last_name";
        SqlParameterSource namedParameters = new MapSqlParameterSource("first_name", firstName);
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("last_name", lastName);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, UUID.class);
    }

    public UUID searchSalesId(UUID personId) {
        String sql = "SELECT id " +
                "FROM new_schema.sales " +
                "WHERE person_id = :person_id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", personId);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, UUID.class);
    }

    public void alterPersonSales(UUID salesId, Integer quantity) {
        String sql = "UPDATE new_schema.sales " +
                "SET quantity = :quantity " +
                "WHERE id = :sales_id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("sales_id", salesId);
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("quantity", quantity);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public List<Person> personSearchFirstName(String firstName) {
        String sql = "SELECT * " +
                "FROM new_schema.person " +
                "WHERE first_name = :first_name";
        SqlParameterSource namedParameters = new MapSqlParameterSource("first_name", firstName);
        return namedParameterJdbcTemplate.queryForList(sql, namedParameters, Person.class);
    }

    public List<Sales> getAllSales() {
        String sql = "SELECT * " +
                "FROM new_schema.sales ";
        return jdbcTemplate.query(
                sql,
                new SalesRowMapper());      //Не через кастомный не работает, почему
    }

    public List<Person> getAllPersons() {
        String sql = "SELECT * " +
                "FROM new_schema.person";
        return jdbcTemplate.query(
                sql,
                new PersonRowMapper());
    }

    public Integer getCount() {
        String sql = "SELECT COUNT(*) " +
                "FROM new_schema.sales";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void editPerson(Person person) {
        String sql = "UPDATE new_schema.person " +
                "SET first_name = :first_name, last_name = :last_name " +
                "WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("first_name", person.getFirstName());
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("last_name", person.getLastName());
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("id", person.getId());
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public void editSale(Sales sale) {
        String sql = "UPDATE new_schema.sales " +
                "SET person_id = :person_id, quantity = :quantity " +
                "WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", sale.getId());
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("quantity", sale.getQuantity());
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("person_id", sale.getPersonId());
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    public Person searchPerson(UUID id) {
        String querySearchPerson = "SELECT * " +
                "FROM new_schema.person " +
                "WHERE id = ?";
        return jdbcTemplate.queryForObject(querySearchPerson, new PersonRowMapper(), id);
    }

    public Sales searchSale(UUID id) {
        String querySearchSale = "SELECT * " +
                "FROM new_schema.sales " +
                "WHERE id = ?";
        return jdbcTemplate.queryForObject(querySearchSale, new SalesRowMapper(), id);
    }

    public List<Person> searchPerson(Person person) {
        String queryPersonNonId = "SELECT * " +
                "FROM new_schema.person " +
                "WHERE CASE " +
                "   WHEN :first_name = '' THEN first_name = first_name " +
                "   ELSE person.first_name = :first_name " +
                "END " +
                "AND " +
                "CASE " +
                "   WHEN :last_name = '' THEN last_name = last_name " +
                "ELSE last_name = :last_name " +
                "END";

        SqlParameterSource namedParameters = new MapSqlParameterSource("first_name", person.getFirstName());
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("last_name", person.getLastName());
        return namedParameterJdbcTemplate.query(queryPersonNonId, namedParameters, new PersonRowMapper());
    }

    public List<Sales> searchSale(Sales sale) {
        String querySalesNonId = "SELECT * " +
                "FROM new_schema.sales " +
                "WHERE person_id = :person_id and quantity = :quantity";
        SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", sale.getPersonId());
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("quantity", sale.getQuantity());
        return namedParameterJdbcTemplate.query(querySalesNonId, namedParameters, new SalesRowMapper());
    }
    public List<Sales> searchSaleQuantity(Integer quantity) {
        String querySalesNonId = "SELECT * " +
                "FROM new_schema.sales " +
                "WHERE quantity > :quantity";
        SqlParameterSource namedParameters = new MapSqlParameterSource("quantity", quantity);
        return namedParameterJdbcTemplate.query(querySalesNonId, namedParameters, new SalesRowMapper());
    }
    public void deleteSalesById(UUID id) {
        String sql = "DELETE FROM new_schema.sales " +
                "WHERE id = :id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }
    public void deleteSalesByPersonId(UUID personId) {
        String sql = "DELETE FROM new_schema.sales " +
                "WHERE person_id = :person_id";
        SqlParameterSource namedParameters = new MapSqlParameterSource("person_id", personId);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }
    public void deletePerson(Person person) {
        String sqlPerson = "DELETE FROM new_schema.person " +
                "WHERE CASE " +
                "WHEN :id = '00000000-0000-0000-0000-000000000000' THEN (id = id) " +
                "AND " +
                "(CASE WHEN :first_name = '' THEN first_name = first_name " +
                "     ELSE first_name = :first_name " +
                "END AND " +
                "CASE WHEN :last_name = '' THEN last_name = last_name " +
                "     ELSE last_name = :last_name " +
                "     END) " +
                "ELSE id = :id " +
                "END";
        SqlParameterSource namedParameters = new MapSqlParameterSource("id", person.getId());
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("first_name", person.getFirstName());
        namedParameters = ((MapSqlParameterSource) namedParameters).addValue("last_name", person.getLastName());
        namedParameterJdbcTemplate.update(sqlPerson, namedParameters);
    }
}
