package fr.polytech.al.tfc.savings.controller;


import fr.polytech.al.tfc.savings.model.AccountDTO;
import fr.polytech.al.tfc.savings.model.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class SavingsObserver {

    private final double interest = 1.1;

    public void computeSavings() throws URISyntaxException {
        //todo host in global variable
        String host = "http://localhost:8083/account/"+AccountType.SAVINGS+"/accounts";
        RestTemplate restTemplate = new RestTemplate();
        URI uri = new URI(host);
        //todo list accountDTO
        ResponseEntity<AccountDTO> result = restTemplate.getForEntity(uri, AccountDTO.class);
        AccountDTO accounts = result.getBody();
        for (AccountDTO account : ) {
            account.setMoney((int) Math.round(account.getMoney() * interest));
            accountRepository.save(account);
        }
    }
}
