import mftplus.model.entity.Event;
import mftplus.model.entity.Saloon;
import mftplus.model.service.EventService;

import java.time.LocalDateTime;

public class EventTest {
    public static void main(String[] args) throws Exception {
        Saloon saloon= Saloon.builder()
                .saloonId(1).build();



Event event=Event.builder()
        .eventId(1)
        .title("fast")
        .description("fast")
        .eventStartDate(LocalDateTime.of(2020, 10, 10, 10, 10))
        .eventEndDate(LocalDateTime.of(2020, 10, 15, 10, 10))
        .duration(30)
        .saloon(saloon)
        .build();

    EventService.getService().save(event);


}
}