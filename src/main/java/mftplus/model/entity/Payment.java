package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Payment {
    private int paymentId;
    private int ticketId;
    private double amount;
    private LocalDate paymentDate;



    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
