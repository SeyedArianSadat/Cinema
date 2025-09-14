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

public class User {
    private  int userId;
    private String username;
    private String password;
    private  String role;
    private Customer customer;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
