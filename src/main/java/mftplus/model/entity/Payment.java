package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import mftplus.model.entity.enums.PaymentType;


import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Payment {
    private int paymentId;
    private double amount;
    private PaymentType paymentType;
    private LocalDateTime paymentTime;



    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
