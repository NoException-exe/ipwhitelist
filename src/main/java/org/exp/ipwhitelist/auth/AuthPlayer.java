package org.exp.ipwhitelist.auth;

import org.exp.ipwhitelist.lib.Bcrypt;
import org.exp.ipwhitelist.repository.AdminRepository;


public class AuthPlayer {

    private final AdminRepository adminRepository;

    public AuthPlayer(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean login(String username, String adminNickName , String password) {
        if (!adminRepository.exist(username, adminNickName))
            return false;

        Bcrypt bcrypt = new Bcrypt(password);
        String hashPassword = adminRepository.getData(); //get password hashed

        return bcrypt.comparePasswordHash(hashPassword);
    }


    public boolean playerIsAuthenticated(String AdminNickname){
        return adminRepository.IsAuthenticated(AdminNickname);
    }
}
