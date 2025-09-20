import mftplus.model.entity.Saloon;
import mftplus.model.entity.User;
import mftplus.model.repository.SaloonRepository;
import mftplus.model.service.SaloonService;

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


        try(SaloonRepository saloonRepository = new SaloonRepository()) {
            saloonRepository.save(saloon);
        }

        SaloonService.getService().save(saloon);

    }
}
