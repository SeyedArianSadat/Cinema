package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Event {
    private int eventId;
    private String title;
    private String description;
    private LocalDateTime eventStartDate;
    private LocalDateTime eventEndDate;
    private float duration;
    private List<Artist> artistList;
    private Saloon saloon;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}