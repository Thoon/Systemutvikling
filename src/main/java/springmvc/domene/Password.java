/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;


/**
 * Author: Ian Gallagher <igallagher@securityinnovation.com>
 *
 * This code utilizes jBCrypt, which you need installed to use. jBCrypt:
 * http://www.mindrot.org/projects/jBCrypt/
 */
public class Password {
    // Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value.

    private static int workload = 12;

    /**
     * This method can be used to generate a string representing an account
     * password suitable for storing in a database. It will be an OpenBSD-style
     * crypt(3) formatted hash string of length=60 The bcrypt workload is
     * specified in the above static variable, a value from 10 to 31. A workload
     * of 12 is a very reasonable safe default as of 2013. This automatically
     * handles secure 128-bit salt generation and storage within the hash.
     *
     * @param password_plaintext The account's plaintext password as provided
     * during account creation, or when changing an account's password.
     * @return String - a string of length 60 that is the bcrypt hashed password
     * in crypt(3) format.
     */
    public static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }

    public static String getNewPassword() {
        String passord = "";
        String possible1 = "0123456789";
        String possible2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String possible3 = "abcdefghijklmnopqrstuvwxyz";
        String possible4 = "!&=?+.,:;-_";

        for (int i = 0; i < 3; i++) {
            passord += possible1.charAt((int) Math.floor(Math.random() * possible1.length()));
            passord += possible2.charAt((int) Math.floor(Math.random() * possible2.length()));
            passord += possible3.charAt((int) Math.floor(Math.random() * possible3.length()));
            passord += possible4.charAt((int) Math.floor(Math.random() * possible4.length()));
        }

        return passord;
    }
    
    public static String getNewToken(){
        String token = "";
        String possible1 = "0123456789";
        String possible2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String possible3 = "abcdefghijklmnopqrstuvwxyz";
        
        for (int i = 0; i < 3; i++) {
            token += possible1.charAt((int) Math.floor(Math.random() * possible1.length()));
            token += possible2.charAt((int) Math.floor(Math.random() * possible2.length()));
            token += possible3.charAt((int) Math.floor(Math.random() * possible3.length()));
        }
        return token;
    }

    /**
     * This method can be used to verify a computed hash from a plaintext (e.g.
     * during a login request) with that of a stored hash from a database. The
     * password hash from the database must be passed as the second variable.
     *
     * @param password_plaintext The account's plaintext password, as provided
     * during a login request
     * @param stored_hash The account's stored password hash, retrieved from the
     * authorization database
     * @return boolean - true if the password matches the password of the stored
     * hash, false otherwise
     */
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
}
