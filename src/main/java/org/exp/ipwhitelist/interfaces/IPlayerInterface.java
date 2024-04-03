package org.exp.ipwhitelist.interfaces;

public interface IPlayerInterface {
   public boolean exist(String playerIp);
   public void create (String playerName);
   public String getData();
   public void update(String playerName, String PlayerIp);
   public void delete(String PlayerName);
}
