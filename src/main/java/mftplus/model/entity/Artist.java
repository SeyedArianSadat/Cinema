package mftplus.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Artist {
    private int artistId;
    private String name;
    private String family;
    private String category;
    private String genre;



    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
