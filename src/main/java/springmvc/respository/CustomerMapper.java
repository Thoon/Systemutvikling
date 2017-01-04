package springmvc.respository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domene.Customer;

public class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int i) throws SQLException {
        Customer c = new Customer();
        c.setCustomerId(rs.getInt("customerId"));
        c.setFirstName(rs.getString("firstName"));
        c.setLastName(rs.getString("lastName"));
        c.setAddress(rs.getString("address"));
        return c;
    }
}