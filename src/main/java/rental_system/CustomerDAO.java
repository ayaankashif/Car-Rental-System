package rental_system;

import java.sql.*;

import rental_system.DB.DbConnection;

public class CustomerDAO {
    public boolean saveCustomer(Customer customer) {
        try (Connection connection = DbConnection.getConnection()) {
            String sql = "INSERT INTO customer (name, contact) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getname());
            preparedStatement.setString(2, customer.getcontact());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1)); // Setting generated ID
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

