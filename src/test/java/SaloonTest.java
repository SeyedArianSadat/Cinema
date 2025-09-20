import mftplus.model.entity.Saloon;
import mftplus.model.entity.User;

public class SaloonTest {
    public static void main(String[] args) throws Exception {
        Saloon saloon = Saloon
                .builder()
                .saloonId(3)
                .name("romi")
                .address("tehran")
                .capacity(12)
                .manager(User.builder().userId(3).build())
                .build();

    }
}
