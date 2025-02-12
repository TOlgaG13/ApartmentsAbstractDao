package homework;

import java.sql.SQLException;
import java.util.List;

public interface ApartmentDao {
    void insert(Apartment apartment) throws SQLException;
    List<Apartment> getAll() throws SQLException;
    List<Apartment> searchByAddress(String address) throws SQLException;
    List<Apartment> searchByRoomCount(int roomCount) throws SQLException;
    void clear() throws SQLException;
}

