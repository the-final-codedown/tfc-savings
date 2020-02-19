package fr.polytech.al.tfc.savings.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
public class AccountDTO {

    @Setter(AccessLevel.NONE)
    protected Integer amountSlidingWindow = 300;

    @Id
    private String accountId;

    @NonNull
    private Integer money;

    private Integer lastWindow = 0;

    private ProfileDTO owner;

    @NonNull
    private AccountType accountType;

    public AccountDTO(String accountId, @NonNull Integer money, AccountType accountType) {
        this.accountType = accountType;
        this.accountId = accountId;
        this.money = money;
    }

    public AccountDTO(AccountDTO accountDTO) {
        this.accountType = accountDTO.getAccountType();
        this.money = accountDTO.getMoney();
    }

    public Integer getAmountSlidingWindow() {
        return amountSlidingWindow;
    }



}
