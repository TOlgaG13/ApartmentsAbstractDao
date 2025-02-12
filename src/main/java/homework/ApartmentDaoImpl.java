package homework;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApartmentDaoImpl implements ApartmentDao{
    private final Connection connection;

    public ApartmentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Apartment apartment) throws SQLException {
        String sql = "INSERT INTO apart (district, address, area, rooms, price) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, apartment.getDistrict());
            stmt.setString(2, apartment.getAddress());
            stmt.setDouble(3, apartment.getArea());
            stmt.setInt(4, apartment.getRooms());
            stmt.setDouble(5, apartment.getPrice());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Apartment> getAll() throws SQLException {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apart";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Apartment apartment = new Apartment();
                apartment.setId(rs.getInt("id"));
                apartment.setDistrict(rs.getString("district"));
                apartment.setAddress(rs.getString("address"));
                apartment.setArea(rs.getDouble("area"));
                apartment.setRooms(rs.getInt("rooms"));
                apartment.setPrice(rs.getDouble("price"));
                apartments.add(apartment);
            }
        }
        return apartments;
    }

    @Override
    public List<Apartment> searchByAddress(String address) throws SQLException {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apart WHERE address LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + address + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Apartment apartment = new Apartment();
                    apartment.setId(rs.getInt("id"));
                    apartment.setDistrict(rs.getString("district"));
                    apartment.setAddress(rs.getString("address"));
                    apartment.setArea(rs.getDouble("area"));
                    apartment.setRooms(rs.getInt("rooms"));
                    apartment.setPrice(rs.getDouble("price"));
                    apartments.add(apartment);
                }
            }
        }
        return apartments;
    }

    @Override
    public List<Apartment> searchByRoomCount(int roomCount) throws SQLException {
        List<Apartment> apartments = new ArrayList<>();
        String sql = "SELECT * FROM apart WHERE rooms = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, roomCount);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Apartment apartment = new Apartment();
                    apartment.setId(rs.getInt("id"));
                    apartment.setDistrict(rs.getString("district"));
                    apartment.setAddress(rs.getString("address"));
                    apartment.setArea(rs.getDouble("area"));
                    apartment.setRooms(rs.getInt("rooms"));
                    apartment.setPrice(rs.getDouble("price"));
                    apartments.add(apartment);
                }
            }
        }
        return apartments;
    }

    @Override
    public void clear() throws SQLException {
        String sql = "DELETE FROM apart";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        }
        String resetPrimaryKeySQL = "ALTER TABLE apart AUTO_INCREMENT = 1";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(resetPrimaryKeySQL);
        }
    }
}


