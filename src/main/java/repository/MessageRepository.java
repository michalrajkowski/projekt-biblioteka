package main.java.repository;

import main.java.model.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository extends Repository{

    public MessageRepository(Statement statement) {
        super(statement);
    }

    public void save(Message message) throws SQLException {

        String sql = "INSERT INTO Message(date,text,sender_id,receiver_id) VALUES("+
                "\"" + message.date + "\",\"" + message.text + "\"," + message.senderId + "," + message.receiverId + ");";

        statement.execute(sql);

    }

    public List<Message> findBySenderId(int senderId) throws SQLException {

        String sql = "SELECT * FROM Message WHERE sender_id = " + senderId + ";";

        ResultSet rs = statement.executeQuery(sql);
        List<Message> messages = getMessages(rs);

        return messages;
    }

    public List<Message> findByReceiverId(int receiverId) throws SQLException {

        String sql = "SELECT * FROM Message WHERE receiver_id = " + receiverId + ";";

        ResultSet rs = statement.executeQuery(sql);
        List<Message> messages = getMessages(rs);

        return messages;
    }

    private List<Message> getMessages(ResultSet rs) throws SQLException {
        List<Message> messages = new ArrayList<>();

        while(rs.next()){
            Message message = new Message();
            message.id = rs.getInt("id");
            message.date = rs.getDate("date").toString();
            message.text = rs.getString("text");
            message.senderId = rs.getInt("sender_id");
            message.receiverId = rs.getInt("receiver_id");
            messages.add(message);
        }
        return messages;
    }

}
