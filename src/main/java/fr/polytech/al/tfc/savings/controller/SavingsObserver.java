package fr.polytech.al.tfc.savings.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import fr.polytech.al.tfc.savings.model.AccountDTO;
import fr.polytech.al.tfc.savings.model.AccountType;
import fr.polytech.al.tfc.savings.model.TransactionDTO;
import fr.polytech.al.tfc.savings.producer.SavingsProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class SavingsObserver {

    @Value("${account.host}")
    private String accountHost;


    private SavingsProducer savingsProducer;
    private final double interest = 0.1;

    public SavingsObserver(SavingsProducer savingsProducer) {
        this.savingsProducer = savingsProducer;
    }

    public void computeSavings() throws URISyntaxException, JsonProcessingException {
        String host = "http://"+accountHost+"/accounts/"+AccountType.SAVINGS.toString()+"/accounts";
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(host);
        ResponseEntity<AccountDTO[]> result = restTemplate.getForEntity(uri, AccountDTO[].class);
        AccountDTO[] accounts = result.getBody();
        for (AccountDTO account : accounts) {
            int amount = (int) Math.round(account.getMoney() * interest);
            TransactionDTO transaction = new TransactionDTO("bank",account.getAccountId(),amount,"");
            this.savingsProducer.sendTransaction(transaction);
        }
    }
}
