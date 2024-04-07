package org.exp.ipwhitelist.interfaces;

import java.util.ArrayList;

public interface IAdminInterface {
    public  boolean exist(String username, String adminNickName);
    public String getData();
    public  void create(String username, String password, String adminNickname);
    public void update(String username, boolean authenticated);
    public boolean IsAuthenticated(String adminNickName);
}
