import mftplus.model.entity.Payment;
import mftplus.model.repository.PaymentRepository;
import mftplus.model.service.PaymentService;

import java.time.LocalDateTime;


public class PaymentTest {
    public static void main(String[] args) throws Exception {
        Payment payment =Payment
                .builder()
                .paymentId(1)
                .amount(0.7)
                .paymentTime(LocalDateTime.now())
                //.paymentType()
                .build();
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.save(payment);

        }

        PaymentService.getService().save(payment);

    }
}
