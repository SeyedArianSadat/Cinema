package mftplus.model.repository;

import mftplus.model.entity.Artist;
import mftplus.model.tools.ArtistMapper;
import mftplus.model.tools.ConnectionProvider;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository implements Repository<Artist,Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final ArtistMapper artistMapper=new ArtistMapper();

    public ArtistRepository() throws SQLException {
        connection= ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Artist artist) throws Exception {
        artist.setArtistId(ConnectionProvider.getProvider().getNextId("artist_seq"));
        preparedStatement=connection.prepareStatement(
                "insert into artists (artist_id,name,family,category,genre)"+
                        " values (ARTIST_SEQ.nextval,?,?,?,?)"
        );

        preparedStatement.setString(1, artist.getName());
        preparedStatement.setString(2, artist.getFamily());
        preparedStatement.setString(3, artist.getCategory());
        preparedStatement.setString(4, artist.getGenre());
        preparedStatement.executeUpdate();

    }

    @Override
    public void edit(Artist artist) throws Exception {
        preparedStatement=connection.prepareStatement(
                "update artists set name=?,family=?,category=?,genre=? where artist_id=?"
        );
        preparedStatement.setString(1, artist.getName());
        preparedStatement.setString(2, artist.getFamily());
        preparedStatement.setString(3, artist.getCategory());
        preparedStatement.setString(4, artist.getGenre());
        preparedStatement.setInt(5, artist.getArtistId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement(
                "delete from artists where artist_id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    @Override
    public List<Artist> findAll() throws Exception {
        List<Artist> artistList=new ArrayList<>();

        preparedStatement=connection.prepareStatement("select * from artists order by artist_id");
        ResultSet resultSet=preparedStatement.executeQuery();

        while (resultSet.next()) {
            Artist artist=artistMapper.artistMapper(resultSet);
            artistList.add(artist);
        }
        return artistList;

    }

    @Override
    public Artist findById(Integer id) throws Exception {
        Artist artist=null;

        preparedStatement=connection.prepareStatement("select * from artists where artist_id=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet=preparedStatement.executeQuery();
        if (resultSet.next()) {
            artist=artistMapper.artistMapper(resultSet);
        }
        return artist;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();

    }
}
