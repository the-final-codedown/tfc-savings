package fr.polytech.al.tfc.savings.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import fr.polytech.al.tfc.savings.model.AccountDTO;
import fr.polytech.al.tfc.savings.model.AccountType;
import fr.polytech.al.tfc.savings.model.Cap;
import fr.polytech.al.tfc.savings.model.TransactionDTO;
import fr.polytech.al.tfc.savings.producer.SavingsProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Component
public class SavingsObserver {

    private SavingsProducer savingsProducer;
    private final double interest = 0.1;

    public SavingsObserver(SavingsProducer savingsProducer) {
        this.savingsProducer = savingsProducer;
    }

    public void computeSavings() throws URISyntaxException, JsonProcessingException {
        //todo host in global variable
        String host = "http://localhost:8083/account/"+AccountType.SAVINGS+"/accounts";
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(host);
        //todo list accountDTO
        ResponseEntity<AccountDTO[]> result = restTemplate.getForEntity(uri, AccountDTO[].class);
        AccountDTO[] accounts = result.getBody();
        for (AccountDTO account : accounts) {
            int amount = (int) Math.round(account.getMoney() * interest);
            TransactionDTO transaction = new TransactionDTO("source",account.getOwner(),amount, LocalDateTime.now());
            this.savingsProducer.sendTransaction(transaction);
        }
    }
}
