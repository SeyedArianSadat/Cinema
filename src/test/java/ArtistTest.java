import mftplus.model.entity.Artist;
import mftplus.model.service.ArtistService;


public class ArtistTest {
    public static void main(String[] args) throws Exception {
        Artist artist = Artist.builder()
                .artistId(1)
                .name("ali")
                .genre("rock")
                .category("90")
                .family("abbas")
                .build();

        ArtistService.getService().save(artist);


    }
    }

