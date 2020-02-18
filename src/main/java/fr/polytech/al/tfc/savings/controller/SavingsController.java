package fr.polytech.al.tfc.savings.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/savings")
public class SavingsController {

    private final SavingsObserver savingsObserver;

    @Autowired
    public SavingsController(SavingsObserver savingsObserver) {
        this.savingsObserver = savingsObserver;
    }

    //604800s => 7 jours
    @PostMapping
    @Scheduled(fixedDelay = 604800)
    public ResponseEntity<String> startComputingSavings() throws URISyntaxException, JsonProcessingException {
        savingsObserver.computeSavings();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
