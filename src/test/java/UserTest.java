import mftplus.model.entity.Customer;
import mftplus.model.entity.User;
import mftplus.model.service.UserService;

public class UserTest {
    public static void main(String[] args) throws Exception {
        Customer customer=Customer.builder()
                .customerId(1).build();


        User user =
                User.builder()
                        .userId(1)
                        .role("customer")
                        .username("hedie")
                        .password("khodaei")
                        .customer(customer)
                        .build();

        UserService.getService().save(user);

    }
}
