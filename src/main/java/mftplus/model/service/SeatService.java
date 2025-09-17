package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Seat;
import mftplus.model.repository.SeatRepository;

import java.util.List;

public class SeatService implements Service<Seat, Integer>{

    @Getter
    private static final SeatService service = new SeatService();


    private SeatService(){
    }


    @Override
    public void save(Seat seat) throws Exception {
        try(SeatRepository seatRepository = new SeatRepository()) {
            seatRepository.save(seat);
        }
    }

    @Override
    public void edit(Seat seat) throws Exception {
        try(SeatRepository seatRepository = new SeatRepository()) {
            seatRepository.edit(seat);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try(SeatRepository seatRepository = new SeatRepository()) {
            seatRepository.delete(id);
        }
    }

    @Override
    public List<Seat> findAll() throws Exception {
        try(SeatRepository seatRepository = new SeatRepository()) {
           return seatRepository.findAll();
        }
    }

    @Override
    public Seat findById(Integer id) throws Exception {
        try(SeatRepository seatRepository = new SeatRepository()) {
            return seatRepository.findById(id);
        }
    }
}
