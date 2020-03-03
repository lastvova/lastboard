package dao;

import entity.Advertisement;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementDAO {

    private static Logger logger = Logger.getLogger(AdvertisementDAO.class);
    private List<Advertisement> advertisementList;

    private static final String INSERT = "INSERT INTO advertisements( tag, subject, body, user_id, status) VALUES (?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM advertisements WHERE id=?";
    private static final String FIND_BY_AUTHOR = "SELECT * FROM advertisements WHERE user_id in (SELECT id from users where name=?)";
    private static final String FIND_BY_ID = "SELECT * FROM advertisements WHERE id=?";
    private static final String FIND_BY_SUBJECT = "SELECT * FROM advertisements WHERE subject=?";
    private static final String FIND_BY_TAG = "SELECT * FROM advertisements WHERE tag=?";
    private static final String FIND_ALL = "SELECT * FROM advertisements";
    private static final String UPDATE = "UPDATE advertisements SET data=?, status=?, tag=?, subject=?, body=? WHERE id=?";

    private static Advertisement extractAdvertisementFromResultSet(ResultSet resultSet) throws SQLException {
        Advertisement advertisement = new Advertisement();
        advertisement.setId(resultSet.getLong("id"));
        advertisement.setDate(resultSet.getTimestamp("data").toLocalDateTime());
        advertisement.setAdvertisementStatus(resultSet.getString("status"));
        advertisement.setTag(resultSet.getString("tag"));
        advertisement.setSubject(resultSet.getString("subject"));
        advertisement.setBody(resultSet.getString("body"));
        advertisement.setUser(new UserDAO().findById(resultSet.getLong("user_id")));
        return advertisement;
    }

    public boolean create(Advertisement advertisement) {
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, advertisement.getTag());
            statement.setString(2, advertisement.getSubject());
            statement.setString(3, advertisement.getBody());
            statement.setLong(4, advertisement.getUser().getId());
            statement.setString(5, advertisement.getAdvertisementStatus().toString());
            int i = statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    advertisement.setId(resultSet.getLong("id"));
                }
            }
            logger.info("advertisement with id =" + advertisement.getId() + " was created");
            return i == 1;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    public boolean delete(long id) {
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(DELETE)) {
            statement.setLong(1, id);
            int i = statement.executeUpdate();
            logger.info("advertisement with id=" + id + " was deleted");
            return i == 1;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    public List<Advertisement> findByAuthor(String author) {
        advertisementList = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_BY_AUTHOR)) {
            statement.setString(1, author);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    advertisementList.add(extractAdvertisementFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return advertisementList;
    }

    public Advertisement findById(long id) {
        Advertisement advertisement = null;
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    advertisement = extractAdvertisementFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return advertisement;
    }

    public List<Advertisement> findBySubject(String subject) {
        advertisementList = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_BY_SUBJECT)) {
            statement.setString(1, subject);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    advertisementList.add(extractAdvertisementFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }

        return advertisementList;
    }

    public List<Advertisement> findByTag(String tag) {
        advertisementList = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_BY_TAG)) {
            statement.setString(1, tag);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    advertisementList.add(extractAdvertisementFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }

        return advertisementList;
    }

    public boolean update(Advertisement advertisement) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setTimestamp(1, Timestamp.valueOf(advertisement.getDate()));
            statement.setString(2, advertisement.getAdvertisementStatus().toString());
            statement.setString(3, advertisement.getTag());
            statement.setString(4, advertisement.getSubject());
            statement.setString(5, advertisement.getBody());
            statement.setLong(6, advertisement.getId());
            int i = statement.executeUpdate();
            logger.info("advertisement with id=" + advertisement.getId() + " was updated");
            return i == 1;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    public List<Advertisement> findAll() {
        advertisementList = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    advertisementList.add(extractAdvertisementFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return advertisementList;
    }
}
