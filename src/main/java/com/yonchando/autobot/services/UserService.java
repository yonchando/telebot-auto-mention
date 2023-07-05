package com.yonchando.autobot.services;

import com.yonchando.autobot.database.DBConnect;
import com.yonchando.autobot.model.User;
import org.telegram.telegrambots.meta.api.objects.Update;

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
        System.out.println(rs);

        User user = new User();

        while (rs.next()) {
            user.setUserId(rs.getLong("user_id"));
            user.setChatId(rs.getLong("chat_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setMessageId(rs.getInt("message_id"));
        }

        preparedStatement.close();
        connection.close();

        return user;
    }

    public void save(User user) throws SQLException {
        Connection connection = dbConnect.initial();

        String sql = "INSERT INTO users (user_id, chat_id, first_name, last_name, message_id,username) VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, user.getUserId());
        preparedStatement.setLong(2, user.getChatId());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setInt(5, user.getMessageId());
        preparedStatement.setString(6, user.getUsername());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public List<User> getList(Update update) throws SQLException {

        System.out.println("ChatId: " + update.getMessage().getChatId());

        Connection connection = dbConnect.initial();

        String sql = "SELECT * FROM users WHERE chat_id = ? and ignore_me = false and user_id != ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, update.getMessage().getChatId());
        preparedStatement.setLong(2, update.getMessage().getFrom().getId());
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println(rs);

        List<User> users = new LinkedList<>();

        System.out.println(rs);

        while (rs.next()) {
            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setChatId(rs.getLong("chat_id"));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setMessageId(rs.getInt("message_id"));
            user.setUsername(rs.getString("username"));

            users.add(user);
        }

        preparedStatement.close();
        connection.close();

        return users;
    }
}
