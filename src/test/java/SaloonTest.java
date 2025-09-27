import mftplus.model.entity.Saloon;
import mftplus.model.entity.Seat;
import mftplus.model.entity.User;
import mftplus.model.service.SaloonService;

import java.util.ArrayList;

public class SaloonTest {
    public static void main(String[] args) throws Exception {
        User manager = new User();
        manager.setUserId(1);
        manager.setUsername("manager1");
        manager.setPassword("1234");
        manager.setRole("manager");


        ArrayList<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Seat seat = new Seat();
            seat.setSeatId(i);
            seats.add(seat);
        }

        Saloon saloon = new Saloon();
        saloon.setSaloonId(1);
        saloon.setName("VIP Hall");
        saloon.setAddress("Tehran, Valiasr St.");
        saloon.setCapacity(50);
        saloon.setSeatList(seats);
        saloon.setManager(manager);

        SaloonService.getService().save(saloon);


    }
}


