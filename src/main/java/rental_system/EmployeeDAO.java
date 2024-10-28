package rental_system;

import java.sql.*;

import rental_system.DB.DbConnection;


public class EmployeeDAO {
    public boolean saveEmployee(Employee employee) {
        try (Connection connection = DbConnection.getConnection()) {
            String sql = "INSERT INTO employee (name, role) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getname());
            preparedStatement.setString(2, employee.getrole());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    employee.setId(generatedKeys.getInt(1)); // Setting generated ID
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

