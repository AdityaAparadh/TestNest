package software.aditya.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.aditya.model.User;
import software.aditya.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody User user){
        try{
            service.registerUser(user);
            return ResponseEntity.ok("User Registered Successfully");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Server Error");
        }
    }

}

