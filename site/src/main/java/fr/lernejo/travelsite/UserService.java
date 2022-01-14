package fr.lernejo.travelsite;

import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> userList;

    UserService (){
        userList = new ArrayList<User>();
    }

    public void addNewUser(User user) {
        userList.add(user);
    }
    public User userByName (String name){
        User user = new User();
        for (User value : userList) {
            if (value.getUserName().equals(name)) {
                user = value;
            }
        }
        return user;
    }
}
