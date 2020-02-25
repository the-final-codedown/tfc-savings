package fr.polytech.al.tfc.savings.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.polytech.al.tfc.savings.model.TransactionDTO;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;


@Component
public class SavingsProducer {

    private final Producer<String, String> producer;
    private String topic = "kafka-transaction";

    public SavingsProducer(Producer<String, String> producer) {
        this.producer = producer;
    }

    public void sendTransaction(TransactionDTO transaction) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            producer.send(new ProducerRecord<>(String.format("%s", topic),  objectMapper.writeValueAsString(transaction))).get();
            System.out.println("Sent " + transaction.toString());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        producer.flush();
    }
}
