package software.aditya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.aditya.model.User;
import software.aditya.repository.UserRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class UserService {

    @Autowired
    private UserRepository repo;


    public User registerUser( User user) throws Exception {

        if(repo.existsByEmail(user.getEmail())){
            throw new Exception("User Already Exists");
        }

        user.setPassword(getSHA512Hash(user.getPassword()));
        return repo.save(user);

    }

    public User findByEmail(String email){
        return repo.findByEmail(email);
    }





    public static String getSHA512Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
