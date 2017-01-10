package springmvc.respository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class IntMapper implements RowMapper<Integer> {

    @Override
    public Integer mapRow(ResultSet rs, int i) throws SQLException {
        Integer intete = 0;
        intete = rs.getInt("permissions");
        System.out.println(intete);
        return intete;
    }
}
