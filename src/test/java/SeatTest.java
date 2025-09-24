import mftplus.model.entity.Saloon;
import mftplus.model.entity.Seat;
import mftplus.model.repository.SeatRepository;
import mftplus.model.service.SeatService;

public class SeatTest {
    public static void main(String[] args) throws Exception {
        Seat seat = Seat
                .builder()
                .seatId(21)
                .seatNumber("l")
                .isAvailable(true)
                .saloon(Saloon.builder().saloonId(1).build())
                .build();

        SeatService.getService().save(seat);

    }
}
