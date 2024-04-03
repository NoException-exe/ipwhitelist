package org.exp.ipwhitelist.auth;

import org.exp.ipwhitelist.lib.Bcrypt;
import org.exp.ipwhitelist.repository.AdminRepository;


public class AuthPlayer {

    private final AdminRepository adminRepository;

    public AuthPlayer(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean login(String username, String password) {
        if (!adminRepository.exist(username))
            return false;

        Bcrypt bcrypt = new Bcrypt(password);
        String hashPassword =  adminRepository.getData();

        return bcrypt.comparePasswordHash(hashPassword);
    }
}
