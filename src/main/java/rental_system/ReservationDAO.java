package rental_system;

import java.sql.*;

import rental_system.DB.DbConnection;

public class ReservationDAO {
    public boolean saveReservation(Reservation reservation) {
    
        try  (Connection connection = DbConnection.getConnection())  {
            String sql = "INSERT INTO Reservation (vehicle_id, customer_id, employee_id, hours_rented, total_cost, model, reservation_status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, reservation.getVehicle().getId());
            pstmt.setInt(2, reservation.getCustomer().getId());
            pstmt.setInt(3, reservation.getAssignedEmployee().getId());
            pstmt.setInt(4, reservation.getHours());
            pstmt.setDouble(5, reservation.getCost());
            pstmt.setString(6, reservation.getModel());
            pstmt.setString(7, "Active");

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

