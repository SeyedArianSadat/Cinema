package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.User;
import mftplus.model.repository.UserRepository;
import java.util.List;

public class UserService implements Service<User , Integer> {
    @Getter
    private final static UserService Service = new UserService();
    private  UserService(){

    }
    @Override
    public void save(User user) throws Exception {
        try (UserRepository userRepository =new UserRepository()){
            userRepository.save(user);
        }
    }
    @Override
    public void edit(User user) throws Exception {
        try (UserRepository userRepository =new UserRepository()){
            userRepository.edit(user);
        }
    }
    @Override
    public void delete(Integer id) throws Exception {
        try (UserRepository userRepository =new UserRepository()){
            userRepository.delete(id);
        }
    }
    @Override
    public List<User> findAll() throws Exception {
        try (UserRepository userRepository =new UserRepository()){
            return userRepository.findAll();
        }
    }
    @Override
    public User findById(Integer id) throws Exception {
        try (UserRepository userRepository =new UserRepository()){
            return userRepository.findById(id);
        }
    }
    public User findByUsernameAndPassword(String username, String password) throws Exception {
        try (UserRepository userRepository =new UserRepository()){
            User user = userRepository.findByUsernameAndPassword(username, password);
            if(user != null){
                return user;
            }else{
                throw new Exception();

            }
        }
    }
}
