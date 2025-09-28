import mftplus.model.entity.Customer;
import mftplus.model.service.CustomerService;

public class CustomerTest {
    public static void main(String[] args) throws Exception {
        Customer customer=
                Customer.builder()
                        .customerId(1)
                        .age(20)
                        .phoneNumber("123456789")
                        .name("arian")
                        .family("sadat")
                        .build();
        CustomerService.getService().save(customer);

    }
}
