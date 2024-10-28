package rental_system;

import java.sql.*;

import rental_system.DB.DbConnection;

public class ReservationDAO {
    public boolean saveReservation(Reservation reservation) {
        String sql = "INSERT INTO Reservation (vehicle_id, customer_id, employee_id, hours_rented, total_cost, model, reservation_status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DbConnection.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, reservation.getVehicle().getId());
            pstmt.setInt(2, reservation.getCustomer().getId());
            pstmt.setInt(3, reservation.getAssignedEmployee().getId());
            pstmt.setInt(4, reservation.getHours());
            pstmt.setDouble(5, reservation.getCost());
            pstmt.setString(6, reservation.getModel());
            pstmt.setString(7, "Active");

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("SQL error message " + e.getErrorCode());
            System.out.println("SQL state " + e.getSQLState());
            e.printStackTrace();
            return false;
        }
    }
}

