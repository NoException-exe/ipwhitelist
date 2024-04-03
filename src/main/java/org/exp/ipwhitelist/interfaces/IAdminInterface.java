package org.exp.ipwhitelist.interfaces;

public interface IAdminInterface {
    public  boolean exist(String username);
    public String getData();
    public  void create(String username, String password);
}
