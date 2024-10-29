package rental_system;

import java.sql.*;

import rental_system.DB.DbConnection;

public class CustomerDAO {

    public Customer findCustomerByContact(String contact) {
        String sql = "SELECT * FROM Customer WHERE contact = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, contact);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setContact(contact);
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveCustomer(Customer customer) {
        Customer existingCustomer = findCustomerByContact(customer.getcontact());
        if (existingCustomer != null) {
            System.out.println("Customer with this contact already exists: " + customer.getcontact());
            return false; 
        }

        try (Connection connection = DbConnection.getConnection()) {
            String sql = "INSERT INTO customer (name, contact) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getname());
            preparedStatement.setString(2, customer.getcontact());

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1)); 
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

