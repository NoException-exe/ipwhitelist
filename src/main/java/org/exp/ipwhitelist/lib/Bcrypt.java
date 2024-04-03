package org.exp.ipwhitelist.lib;
import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {
    private final String password;

    public Bcrypt(String password) {
        this.password = password;
    }


    public String hashPassword() {
        return BCrypt.hashpw(this.password, BCrypt.gensalt(12)); //return hashed password
    }


    public boolean comparePasswordHash(String HashPassword) {
        return BCrypt.checkpw(this.password, HashPassword);
    }


}


