package mftplus.model.tools;

import mftplus.model.entity.Artist;
import java.sql.ResultSet;

public class ArtistMapper {
    public Artist artistMapper(ResultSet resultSet) throws Exception {
        return Artist
                .builder()
                .artistId(resultSet.getInt("artistId"))
                .name(resultSet.getString("name"))
                .family(resultSet.getString("family"))
                .category(resultSet.getString("category"))
                .genre(resultSet.getString("genre"))
                .build();
    }
}
