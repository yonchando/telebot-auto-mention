package com.yonchando.autobot.services;

import com.yonchando.autobot.database.DBConnect;
import com.yonchando.autobot.model.User;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserService {

    private final DBConnect dbConnect = new DBConnect();

    public UserService() {
    }

    public User show(Long userId, Long chatId) throws SQLException {
        Connection connection = dbConnect.initial();

        String sql = "SELECT * FROM users WHERE user_id = ? and chat_id = ? LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, userId);
        preparedStatement.setLong(2, chatId);
        ResultSet rs = preparedStatement.executeQuery();

        User user = new User();

        while (rs.next()) {
            user.setUserId(rs.getLong("user_id"));
            user.setChatId(rs.getLong("chat_id"));
            user.setChatTitle(rs.getString("chat_title"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setUsername(rs.getString("username"));
            user.setMessageId(rs.getInt("message_id"));
            user.setIgnoreMe(rs.getBoolean("ignore_me"));
        }

        preparedStatement.close();
        connection.close();

        return user;
    }

    public void save(User user) throws SQLException {
        Connection connection = dbConnect.initial();

        String sql = "INSERT INTO users " +
                "(user_id, chat_id, first_name, last_name, message_id,username,chat_title) " +
                "VALUES " +
                "(?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, user.getUserId());
        preparedStatement.setLong(2, user.getChatId());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setInt(5, user.getMessageId());
        preparedStatement.setString(6, user.getUsername());
        preparedStatement.setString(7, user.getChatTitle());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public void update(User user, User userExist) throws SQLException {
        Connection connection = dbConnect.initial();

        String sql = "UPDATE users SET first_name = ?, last_name = ?, username = ?, chat_title = ?\n " +
                "WHERE user_id = ? AND chat_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getUsername());
        preparedStatement.setString(4, user.getChatTitle());
        preparedStatement.setLong(5, userExist.getUserId());
        preparedStatement.setLong(6, userExist.getChatId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public List<User> getList(Message message) {
        List<User> users = new LinkedList<>();

        try {
            Connection connection = dbConnect.initial();
            String sql = "SELECT * FROM users WHERE chat_id = ? and ignore_me = false and user_id != ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, message.getChatId());
            preparedStatement.setLong(2, message.getFrom().getId());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getLong("user_id"));
                user.setChatId(rs.getLong("chat_id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setUsername(rs.getString("username"));
                user.setMessageId(rs.getInt("message_id"));
                user.setChatTitle(rs.getString("chat_title"));

                users.add(user);
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void saveIfNotExist(
            org.telegram.telegrambots.meta.api.objects.User msgUser,
            Message message
    ) {
        try {
            User userExist = show(msgUser.getId(), message.getChatId());

            User user = new User();
            user.setUserId(msgUser.getId());
            user.setChatId(message.getChatId());
            user.setChatTitle(message.getChat().getTitle());
            user.setFirstName(msgUser.getFirstName());
            user.setLastName(msgUser.getLastName());
            user.setMessageId(message.getMessageId());
            user.setUsername(msgUser.getUserName());

            if (userExist.getUserId() == null) {
                save(user);
                System.out.println("User has created");
            } else {
                update(user, userExist);
                System.out.println("User has updated");
            }
        } catch (SQLException e) {
            System.out.println("User can't created");
            throw new RuntimeException(e);
        }
    }

    public boolean ignoreMe(Long userId, Long chatId) {
        try {
            User user = show(userId, chatId);

            if (user.getIgnoreMe()) {
                return false;
            }

            Connection connection = dbConnect.initial();
            String sql = "UPDATE users SET ignore_me = true where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            int rs = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            return rs != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean in(Long userId, Long chatId) {
        try {
            User user = show(userId, chatId);

            if (!user.getIgnoreMe()) {
                return false;
            }

            Connection connection = dbConnect.initial();
            String sql = "UPDATE users SET ignore_me = false where user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, userId);
            int rs = preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            return rs != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
