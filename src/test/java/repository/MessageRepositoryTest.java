package test.java.repository;

import main.java.factory.LocalStatementBuilder;
import main.java.model.Message;
import main.java.repository.MessageRepository;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class MessageRepositoryTest {

    final Statement statement = LocalStatementBuilder.getTestInstance().createStatement();

    final MessageRepository messageRepository =
            new MessageRepository(statement);

    @Test
    public void shouldSaveMessage() throws SQLException {
        //given
        Message message = new Message();
        message.date = "2000-10-10";
        message.text = "hej";
        message.receiverId = 1;
        message.senderId = 2;
        //when
        messageRepository.save(message);
        //then
        Message sentMessage = messageRepository.findByReceiverId(1).get(1);
        message.id = sentMessage.id;
        assertEquals(sentMessage.toString(),message.toString());
    }
}
