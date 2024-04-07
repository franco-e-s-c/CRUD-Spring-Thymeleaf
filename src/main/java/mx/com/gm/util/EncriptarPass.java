package mx.com.gm.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPass {
    public static void main(String[] args){

        var password="123";
        System.out.println("pass: "+encriptarPass(password));
    }

    public static String encriptarPass(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
