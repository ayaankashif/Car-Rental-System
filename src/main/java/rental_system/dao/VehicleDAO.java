package rental_system.dao;

import java.sql.*;
import java.time.LocalDateTime;

import rental_system.db.DbConnection;
import rental_system.models.Vehicle;

public class VehicleDAO {
    public boolean saveVehicle(Vehicle vehicle) {    
        try (Connection connection = DbConnection.getConnection()) {
            String sql = "INSERT INTO vehicle (LicensePlate, is_available, created_date) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vehicle.getLicensePlate());
            preparedStatement.setBoolean(2, vehicle.isAvailable(0));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

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


    public boolean isLicensePlateExists(String licensePlate) {
        String query = "SELECT COUNT(*) FROM Vehicle WHERE LicensePlate = ?";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
             
            statement.setString(1, licensePlate);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; 
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return false; 
    }



    // public Vehicle findVehicleByLicensePlate(String licensePlate) {
    //     String query = "SELECT * FROM vehicle WHERE license_plate = ?";
    //     try (Connection conn = Database.getConnection();
    //          PreparedStatement stmt = conn.prepareStatement(query)) {

    //         stmt.setString(1, licensePlate);
    //         ResultSet rs = stmt.executeQuery();

    //         if (rs.next()) {
    //             return new Vehicle(
    //                     rs.getString("license_plate")
               
    //             );
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return null; // Return null if no vehicle is found
    // }

   
    public boolean returnVehicle(int vehicleId) {
        String sql = "UPDATE Vehicle SET is_available = 1 WHERE LicensePlate = ?";
    
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            
            pstmt.setInt(1, vehicleId);
    
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            System.out.println("SQL error message " + e.getErrorCode());
            System.out.println("SQL state " + e.getSQLState());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateVehicleAvailability(int vehicleId, boolean isAvailable) {
        try (Connection connection = DbConnection.getConnection()) {
            String sql = "UPDATE vehicle SET is_available = ? WHERE LicensePlate = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, isAvailable);
            preparedStatement.setInt(2, vehicleId);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}

