package org.example.mappers;

import org.example.models.Sales;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesRowMapper implements RowMapper<Sales> {
    @Override
    public Sales mapRow(ResultSet rs, int rowNum) throws SQLException {
        Sales sale = new Sales();
        sale.setId((java.util.UUID) rs.getObject("id"));
        sale.setPersonId((java.util.UUID) rs.getObject("person_id"));
        sale.setQuantity(rs.getInt("quantity"));

        return sale;
    }
}
