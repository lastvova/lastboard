package dao;

import entity.Advertisement;
import entity.User;
import enums.UserRole;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static Logger logger = Logger.getLogger(UserDAO.class);

    private static final String FIND_USER = "SELECT * FROM users WHERE login=? and password=?";
    private static final String INSERT = "INSERT INTO users (login, password, email, name, role, status) VALUES (?,?,?,?,?,?)";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id =?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM users WHERE email =?";
    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id =?";
    private static final String UPDATE = "UPDATE users SET login=?, password=?, email=?, name=?, role=?, status=? WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM users ";
    private static final String FIND_ADVERTISEMENTS = "SELECT * FROM advertisements WHERE user_id=?";

    private static User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setName(resultSet.getString("name"));
        user.setUserRole(resultSet.getString("role"));
        user.setAccountStatus(resultSet.getString("status"));
        user.setAdvertisementList(getAdvertisements(resultSet.getLong("id")));
        return user;
    }

    private static List<Advertisement> getAdvertisements(long id) {
        List<Advertisement> list = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_ADVERTISEMENTS)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Advertisement advertisement = new Advertisement();
                    advertisement.setId(resultSet.getLong("id"));
                    advertisement.setDate(resultSet.getTimestamp("data").toLocalDateTime());
                    advertisement.setAdvertisementStatus(resultSet.getString("status"));
                    advertisement.setTag(resultSet.getString("tag"));
                    advertisement.setSubject(resultSet.getString("subject"));
                    advertisement.setBody(resultSet.getString("body"));
                    list.add(advertisement);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public boolean isRegistered(String login, String password) {
        boolean result = false;
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next() && resultSet.getRow() == 0) {
                    logger.info("User was unregistered");
                    result = false;
                } else {
                    result = true;
                    logger.info("User was registered");
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return result;
    }

    public boolean create(User user) {
        if (isRegistered(user.getLogin(), user.getPassword())) {
            return false;
        } else {
            try (Connection connection = ConnectionFactory.getConnection();
                 PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getName());
                if (user.getUserRole() == null) {
                    user.setUserRole("USER");
                }
                if (user.getAccountStatus() == null) {
                    user.setAccountStatus("NONACTIVE");
                }
                statement.setString(5, user.getUserRole().toString());
                statement.setString(6, user.getAccountStatus().toString());
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        user.setId(resultSet.getLong("id"));
                    }
                }
                int i = statement.executeUpdate();
                logger.info("New user was created");
                return i == 1;
            } catch (SQLException e) {
                logger.error(e);
                return false;
            }
        }
    }

    public User findByLoginPassword(String login, String password) {
        User user = new User();
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    public User findById(long id) {
        User user = null;
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    public User findByEmail(String email) {
        User user = null;
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_BY_EMAIL)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    public boolean delete(long id) {
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(DELETE_BY_ID)) {
            statement.setLong(1, id);
            int i = statement.executeUpdate();
            logger.info("User with id=" + id + " was deleted");
            return i == 1;
        } catch (SQLException e) {
            logger.error(e);
            return false;
        }
    }

    public void updateUser(User user) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getName());
            statement.setString(5, user.getUserRole().toString());
            statement.setString(6, user.getAccountStatus().toString());
            statement.setLong(7, user.getId());
            statement.executeUpdate();
            logger.info("User with id=" + user.getId() + " was updated");
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(extractUserFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public UserRole findRoleByLoginPassword(String login, String password) {
        User user;
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(FIND_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = extractUserFromResultSet(resultSet);
                    return user.getUserRole();
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return null;
    }
}
