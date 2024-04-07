package org.exp.ipwhitelist.query;

public class SQLQuery{
    public static String playerQuery = "SELECT * FROM ipwhitelist WHERE PlayerName = ?";
    public  static String createNewPlayerQuery = "INSERT INTO ipwhitelist (PlayerName, ip) VALUES (?, NULL)";
    public  static  String updatePlayerIpQuery = "UPDATE ipwhitelist SET ip = ? WHERE PlayerName = ?";
    public static  String deletePlayerQuery = "DELETE FROM ipwhitelist WHERE PlayerName = ?";
    public  static String findAdminQuery = "SELECT * FROM admins WHERE username = ? AND nickname = ? LIMIT 1;";
    public  static String crateNewAdminQuery = "INSERT INTO admins (username, password, nickname) VALUES (?, ?, ?)";
    public static String updateAdminAuthQuery = "UPDATE admins SET authenticated = ? WHERE username = ? OR nickname = ?";
    public  static String adminIsAuthenticatedQuery = "SELECT * FROM admins WHERE nickname = ? AND authenticated = 1 LIMIT 1;";

}
