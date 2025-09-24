import mftplus.model.entity.Payment;
import mftplus.model.entity.enums.PaymentType;
import mftplus.model.repository.PaymentRepository;
import mftplus.model.service.PaymentService;

import java.time.LocalDateTime;

import static mftplus.model.entity.enums.PaymentType.Card;


public class PaymentTest {
    public static void main(String[] args) throws Exception {
        Payment payment =Payment
                .builder()
                .paymentId(1)
                .amount(0.7)
                .paymentTime(LocalDateTime.now())
                .paymentType(PaymentType.valueOf(String.valueOf(Card)))
                .build();


        PaymentService.getService().save(payment);

    }
}
