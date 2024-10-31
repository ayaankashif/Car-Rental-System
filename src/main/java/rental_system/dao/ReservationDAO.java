package rental_system.dao;

import java.sql.*;
import java.time.LocalDateTime;

import rental_system.db.DbConnection;
import rental_system.models.Reservation;

public class ReservationDAO {
    public boolean saveReservation(Reservation reservation) {
        try  (Connection connection = DbConnection.getConnection())  {
            String sql = "INSERT INTO Reservation (customer_id, employee_id, hours_rented, total_cost, model, reservation_status, rental_date, LicensePlate) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, reservation.getCustomer().getId());
            pstmt.setInt(2, reservation.getAssignedEmployee().getId());
            pstmt.setInt(3, reservation.getHours());
            pstmt.setDouble(4, reservation.getCost());
            pstmt.setString(5, reservation.getVehicle().getModel(0));
            pstmt.setString(6, "Active");
            pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now())); 
            pstmt.setString(8, reservation.getVehicle().getLicensePlate());
            
           int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    reservation.setId(generatedKeys.getInt(1)); 
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQL error message " + e.getErrorCode());
            System.out.println("SQL state " + e.getSQLState());
            e.printStackTrace();
        }
        return false;
    }
}

