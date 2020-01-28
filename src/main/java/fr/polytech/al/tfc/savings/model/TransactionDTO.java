package fr.polytech.al.tfc.savings.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class TransactionDTO {
    @NonNull
    private String source;
    @NonNull
    private String receiver;
    @NonNull
    private int amount;
    @NonNull
    private LocalDateTime date;
}

/*
type TransactionDTO struct {
	Source   string    `json:"source"`
	Receiver string    `json:"receiver"`
	Amount   int32       `json:"amount"`
	Date     time.Time `json:"date"`
}
 */
