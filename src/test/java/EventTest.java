import mftplus.model.entity.Event;
import mftplus.model.service.EventService;

import java.time.LocalDateTime;

public class EventTest {
    public static void main(String[] args) throws Exception {
Event event=Event.builder()
        .eventId(1)
        .title("fast")
        .description("fast")
        .eventStartTime(LocalDateTime.of(2020, 10, 10, 10, 10))
        .eventEndTime(LocalDateTime.of(2020, 10, 10, 10, 10))
        .duration(30)
        .build();

    EventService.getService().save(event);


}
}