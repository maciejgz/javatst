package pl.mg.javatst.security;


import at.favre.lib.crypto.bcrypt.BCrypt;

public class BcryptTest {

    public static void main(String[] args) {
        String password = "1234";
        String hash = BCrypt.withDefaults().hashToString(6, password.toCharArray());

        System.out.println(hash);
        BCrypt.Result verify = BCrypt.verifyer().verify(password.getBytes(), hash.getBytes());
        System.out.println(verify.verified);
    }

}
