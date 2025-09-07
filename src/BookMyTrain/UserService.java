package BookMyTrain;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private Map<String ,User> userMap=new HashMap<>();
    private User curerntUser=null;

    public boolean registerUser(String userName,String password,String contact,String fullName){

        if(userMap.containsKey(userName)){
            System.out.println("UserName already taken , use another Username");
            return false;
        }
        User user=new User(userName,fullName,contact,password);
        userMap.put(userName,user);
        System.out.println("Registration Successfull");

        return true;
    }

    public boolean loginUser (String userName,String password){

        if(!userMap.containsKey(userName)){
            System.out.println("UserName not found.");
            return false;
        }

        User user=userMap.get(userName);

        if(!user.getPassword().equals(password)){
            System.out.println("Password is inCorrect");
            return false;
        }

        curerntUser=user;
        System.out.println("Welcome :" +user.getFullName()+"!");
        return true;
    }

    public void logOutUser(){

        if(curerntUser!=null){
            System.out.println("Logged Out "+curerntUser.getFullName());
        }
        curerntUser=null;
    }

    public User getCurerntUser(){
        return curerntUser;
    }

    public boolean isLoggedIn(){
        return curerntUser!=null;
    }

}
