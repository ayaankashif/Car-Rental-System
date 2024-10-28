package rental_system;

import java.sql.*;

import rental_system.DB.DbConnection;

public class VehicleDAO {
    public boolean saveVehicle(Vehicle vehicle) {
        try (Connection connection = DbConnection.getConnection()) {
            String sql = "INSERT INTO vehicle (type, model, is_available) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vehicle.getType());
            preparedStatement.setString(2, vehicle.getModels(0));
            preparedStatement.setBoolean(3, vehicle.isAvailable(0));

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    vehicle.setId(generatedKeys.getInt(1)); // Setting the generated ID in the object
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

