package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Event {
    private int eventId;
    private String title;
    private LocalDate eventDate;
    private Artist artist;
    private Saloon saloon;

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}