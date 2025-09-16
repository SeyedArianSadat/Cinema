package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Saloon;
import mftplus.model.repository.SaloonRepository;
import java.util.List;

public class SaloonService implements Service<Saloon ,Integer>{

    @Getter
    private static final SaloonService service =  new SaloonService();

    private SaloonService(){
    }


    @Override
    public void save(Saloon saloon) throws Exception {
        try(SaloonRepository saloonRepository = new SaloonRepository()){
            saloonRepository.save(saloon);
        }
    }

    @Override
    public void edit(Saloon saloon) throws Exception {
        try(SaloonRepository saloonRepository = new SaloonRepository()){
            saloonRepository.edit(saloon);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try(SaloonRepository saloonRepository = new SaloonRepository()){
            saloonRepository.delete(id);
        }
    }

    @Override
    public List<Saloon> findAll() throws Exception {
        try(SaloonRepository saloonRepository = new SaloonRepository()){
            return saloonRepository.findAll();
        }
    }

    @Override
    public Saloon findById(Integer id) throws Exception {
        try(SaloonRepository saloonRepository = new SaloonRepository()){
            return saloonRepository.findById(id);
        }
    }
}
