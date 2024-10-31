package rental_system.dao;

import java.sql.*;
import java.time.LocalDateTime;

import rental_system.db.DbConnection;
import rental_system.models.Employee;

public class EmployeeDAO {

    public Employee findEmployeeById(int employeeId) {
        Employee employee = null;
        try (Connection connection = DbConnection.getConnection()) {
            String sql = "SELECT * FROM employees WHERE employee_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("employee_id"));
                employee.setName(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public boolean saveEmployee(Employee employee) {
        try (Connection connection = DbConnection.getConnection()) {
            String sql = "INSERT INTO employee (name, role, rental_date) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getname());
            preparedStatement.setString(2, employee.getrole());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now())); 

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

