package fr.polytech.al.tfc.savings.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {

    private Integer money;

    private String owner;

}
