import mftplus.model.entity.Event;

import java.time.LocalDateTime;

public class EventTest {
    public static void main(String[] args) {
Event event=Event.builder().eventId(1).title("fast").description("fast").eventStartTime(LocalDateTime.of(2020, 10, 10, 10, 10))
.eventEndTime(LocalDateTime.of(2020, 10, 10, 10, 10)).build();

        System.out.println("success");


}
}