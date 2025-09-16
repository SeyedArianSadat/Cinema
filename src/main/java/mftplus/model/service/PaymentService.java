package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Payment;
import mftplus.model.repository.PaymentRepository;

import java.util.List;

public class PaymentService implements Service<Payment, Integer> {
    @Getter
    private static final PaymentService service = new PaymentService();

    private PaymentService(){
    }


    @Override
    public void save(Payment payment) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.save(payment);
        }
    }

    @Override
    public void edit(Payment payment) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.edit(payment);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            paymentRepository.delete(id);
        }
    }

    @Override
    public List<Payment> findAll() throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findAll();
        }
    }

    @Override
    public Payment findById(Integer id) throws Exception {
        try (PaymentRepository paymentRepository = new PaymentRepository()) {
            return paymentRepository.findById(id);
        }
    }
}
