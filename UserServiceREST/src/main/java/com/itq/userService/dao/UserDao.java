package com.itq.userService.dao;

import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.itq.userService.dto.Address;
import com.itq.userService.dto.User;
import com.itq.userService.service.CustomUserException;

@Repository
public class UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private int auxiliarAddressId;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean deleteAddress(int addressId) {
        StringBuffer sql = new StringBuffer("");
        sql.append("DELETE FROM address WHERE addressId = ?");
        final String query = sql.toString();
        try {
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(query);
                    ps.setInt(1, addressId);
                    return ps;
                }
            });
            //LOGGER.info("Address with ID {" + addressId + "} deleted successfully");
            return true;
        } catch (Exception e) {
            //LOGGER.error("Error deleting address with ID {" + addressId + "} from the database. Message: " + e.getMessage());
        }
        return false;
    }

    public class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            Address address = new Address();
            user.setUserID(rs.getInt("userId"));
            user.setName(rs.getString("name"));
            user.setLastname(rs.getString("lastname"));
            user.setPhone(rs.getString("phone"));
            user.setMail(rs.getString("email"));
            user.setRfc(rs.getString("rfc"));
            user.setPassword(rs.getString("password"));
            user.setType(rs.getString("type"));
            address.setAddressID(rs.getInt("addressId"));
            address.setStreet(rs.getString("street"));
            address.setExtNumber(rs.getInt("exteriorNumber"));
            address.setIntNumber(rs.getInt("interiorNumber"));
            address.setLocality(rs.getString("suburb"));
            address.setCity(rs.getString("city"));
            address.setState(rs.getString("state"));
            address.setCountry(rs.getString("country"));
            address.setZipCode(Integer.toString(rs.getInt("zipCode")));
            user.setAddress(address);
            return user;
        }
    }

    public boolean createUser(final User user) {

        //Check if the user ID is specified, if so, throw an exception
        if(user.getUserID() != 0) {
            String errorMessage = "ERROR 400. User ID must not be specified";
            LOGGER.error(errorMessage);
            throw new CustomUserException(errorMessage);
        }

        //Check if the address ID is specified, if so, throw an exception
        if(user.getAddress().getAddressID() != 0) {
            String errorMessage = "ERROR 400. Address ID must not be specified";
            LOGGER.error(errorMessage);
            throw new CustomUserException(errorMessage);
        }

        // Step 1: Insert data into the 'address' table
        Address address = user.getAddress();
        StringBuffer addressSql = new StringBuffer("");
        addressSql.append("INSERT INTO address (addressId, street, exteriorNumber, interiorNumber, suburb, city, state, country, zipCode) ");
        addressSql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        final String addressQuery = addressSql.toString();
        final int addressId;

        //Check if the email already exists in the database, if so, throw an exception
        if(!jdbcTemplate.queryForList("SELECT email FROM users WHERE email = ?", user.getMail()).isEmpty()) {
            String errorMessage = "ERROR 409. Email already exists in the database";
            LOGGER.error(errorMessage);
            throw new CustomUserException(errorMessage);
        }

        try {
            GeneratedKeyHolder addressKeyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(addressQuery, java.sql.Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, address.getAddressID());
                    ps.setString(2, address.getStreet());
                    ps.setInt(3, address.getExtNumber());
                    ps.setInt(4, address.getIntNumber());
                    ps.setString(5, address.getLocality());
                    ps.setString(6, address.getCity());
                    ps.setString(7, address.getState());
                    ps.setString(8, address.getCountry());
                    int zipCode = Integer.parseInt(address.getZipCode());
                    ps.setInt(9, zipCode);
                    return ps;
                }
            }, addressKeyHolder);
            addressId = addressKeyHolder.getKey().intValue();
            auxiliarAddressId = addressId;

        // Step 2: Insert data into the 'user' table
        StringBuffer userSql = new StringBuffer("");
        userSql.append("INSERT INTO users (userId, name, lastname, phone, email, rfc, password, type, addressId) ");
        userSql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        final String userQuery = userSql.toString();

        GeneratedKeyHolder userKeyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(userQuery, java.sql.Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, user.getUserID());
                    ps.setString(2, user.getName());
                    ps.setString(3, user.getLastname());
                    ps.setString(4, user.getPhone());
                    ps.setString(5, user.getMail());
                    ps.setString(6, user.getRfc());
                    ps.setString(7, user.getPassword());
                    ps.setString(8, user.getType());
                    ps.setInt(9, addressId);
                    return ps;
                }
            }, userKeyHolder);
            LOGGER.info("User and Address created successfully with IDs: " + userKeyHolder.getKey() + ", " + addressId);
            return true;
        } catch (Exception e) {
            //Delete address if user creation fails, avoiding duplicate addresses, not the best solution but it works
            deleteAddress(auxiliarAddressId);
            LOGGER.error("Error creating user and address in the database. Message: " + e.getMessage());
        }
        return false;
    }

    public User getUserById(int userId) {

        //Check if the user exists in the database, if not, throw an exception
        if(jdbcTemplate.queryForList("SELECT userId FROM users WHERE userId = ?", userId).isEmpty()) {
            String errorMessage = "ERROR 404. User with ID {" + userId + "} not found in the database";
            LOGGER.error(errorMessage);
            throw new CustomUserException(errorMessage);
        }

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT * FROM users INNER JOIN address ON users.addressId = address.addressId WHERE userId = ?");
        final String query = sql.toString();
        try {
            @SuppressWarnings("deprecation")
			User user = jdbcTemplate.queryForObject(query, new Object[]{userId}, new UserRowMapper());
            LOGGER.info("User with ID {" + userId + "} retrieved successfully");
            return user;
        } catch (Exception e) {
            LOGGER.error("Error retrieving user with ID {" + userId + "} from the database. Message: " + e.getMessage());
        }
        return null;
    }

    public boolean updateUser(int userId, User user) {

    //if the user ID is specified in the JSON, throw an exception because it can't be modified
    if(user.getUserID() != 0) {
        String errorMessage = "ERROR 400. User ID must not be specified";
        LOGGER.error(errorMessage);
        throw new CustomUserException(errorMessage);
    }

    //if the address ID is specified in the JSON, throw an exception because it can't be modified
    if(user.getAddress().getAddressID() != 0) {
        String errorMessage = "ERROR 400. Address ID must not be specified";
        LOGGER.error(errorMessage);
        throw new CustomUserException(errorMessage);
    }


    // Step 1: Update data in the 'user' table
    String userSql = "UPDATE users SET name = ?, lastname = ?, phone = ?, email = ?, rfc = ?, password = ?, type = ? WHERE userId = ?";
    
    // Step 2: Update data in the 'address' table
    String addressSql = "UPDATE address SET street = ?, exteriorNumber = ?, interiorNumber = ?, suburb = ?, city = ?, state = ?, country = ?, zipCode = ? WHERE addressId = ?";

    try {

        User existingUser = getUserById(userId);

        if (existingUser == null) {
            String errorMessage = "ERROR 404. User with ID {" + userId + "} not found in the database";
            LOGGER.error(errorMessage);
            throw new CustomUserException(errorMessage);
        }
        // Step 1: Update user information
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(userSql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getMail());
            ps.setString(5, user.getRfc());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getType());
            ps.setInt(8, userId);
            return ps;
        });

        int addressId = existingUser.getAddress().getAddressID();

        // Step 2: Update address information
        Address address = user.getAddress();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(addressSql);
            ps.setString(1, address.getStreet());
            ps.setInt(2, address.getExtNumber());
            ps.setInt(3, address.getIntNumber());
            ps.setString(4, address.getLocality());
            ps.setString(5, address.getCity());
            ps.setString(6, address.getState());
            ps.setString(7, address.getCountry());
            int zipCode = Integer.parseInt(address.getZipCode());
            ps.setInt(8, zipCode);
            ps.setInt(9, addressId);
            return ps;
        });

        LOGGER.info("User with ID {} and associated address updated successfully", userId);
        return true;
        } catch (DataAccessException e) {
            LOGGER.error("Error updating user with ID {} and associated address. Message: {}", user.getUserID(), e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean deleteUser(int userId) {
        try {
            User userToDelete = getUserById(userId);
            if (userToDelete == null) {
                String errorMessage = "ERROR 404. User with ID {" + userId + "} not found in the database";
                LOGGER.error(errorMessage);
                throw new CustomUserException(errorMessage);
            }
            int addressId = userToDelete.getAddress().getAddressID();
            // Delete the user
            String userSql = "DELETE FROM users WHERE userId = ?";
            jdbcTemplate.update(userSql, userId);
            LOGGER.info("User with ID {} deleted successfully", userId);

            // Delete the associated address
            String addressSql = "DELETE FROM address WHERE addressId = ?";
            jdbcTemplate.update(addressSql, addressId);
            LOGGER.info("Address with ID {} deleted successfully", addressId);

            return true;
        } catch (DataAccessException e) {
            LOGGER.error("Error deleting user with ID {} and associated address. Message: {}", userId, e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List <User> getAllUsers() {

        // Check if there are users in the database, if not, throw an exception
        if(jdbcTemplate.queryForList("SELECT userId FROM users").isEmpty()) {
            String errorMessage = "ERROR 404. There are no users in the database";
            LOGGER.error(errorMessage);
            throw new CustomUserException(errorMessage);
        }

        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT * FROM users INNER JOIN address ON users.addressId = address.addressId");
        final String query = sql.toString();
        try {
            List <User> users = jdbcTemplate.query(query, new UserRowMapper());
            LOGGER.info("All users retrieved successfully");
            return users;
        } catch (Exception e) {
            LOGGER.error("Error retrieving all users from the database. Message: " + e.getMessage());
        }
        return null;
    }

    public List<User> getUsersbyCountry(String country) {

        // Check if there are users in the database, if not, throw an exception
        if(jdbcTemplate.queryForList("SELECT userId FROM users").isEmpty()) {
            String errorMessage = "ERROR 404. There are no users in the database";
            LOGGER.error(errorMessage);
            throw new CustomUserException(errorMessage);
        }

        // Check if there are users from the specified country, if not, throw an exception
        if(jdbcTemplate.queryForList("SELECT userId FROM users INNER JOIN address ON users.addressId = address.addressId WHERE country = ?", country).isEmpty()) {
            String errorMessage = "ERROR 404. There are no users from country {" + country + "} in the database";
            LOGGER.error(errorMessage);
            throw new CustomUserException(errorMessage);
        }
        
        StringBuffer sql = new StringBuffer("");
        sql.append("SELECT * FROM users INNER JOIN address ON users.addressId = address.addressId WHERE country = ?");
        final String query = sql.toString();
        try {
            @SuppressWarnings("deprecation")
			List <User> users = jdbcTemplate.query(query, new Object[]{country}, new UserRowMapper());
            LOGGER.info("All users from country {" + country + "} retrieved successfully");
            return users;
        } catch (Exception e) {
            LOGGER.error("Error retrieving all users from country {" + country + "} from the database. Message: " + e.getMessage());
        }
        return null;
    }

}
