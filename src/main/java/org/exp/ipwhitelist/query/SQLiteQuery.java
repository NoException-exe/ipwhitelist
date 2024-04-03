package org.exp.ipwhitelist.query;

public class SQLiteQuery {
    public static String playerQuery = "SELECT * FROM ipwhitelist WHERE PlayerName = ?";
    public static String createWhitelistTableQuery = "CREATE TABLE IF NOT EXISTS ipwhitelist (id INTEGER PRIMARY KEY AUTOINCREMENT, PlayerName VARCHAR(255) NOT NULL, ip VARCHAR(255), create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";

    public  static String createNewPlayerQuery = "INSERT INTO ipwhitelist (PlayerName, ip) VALUES (?, NULL)";

    public  static  String updatePlayerIpQuery = "UPDATE ipwhitelist SET ip = ? WHERE PlayerName = ?";

    public static  String deletePlayerQuery = "DELETE FROM ipwhitelist WHERE PlayerName = ?";

    //admin

    public  static String findAdminQuery = "SELECT * FROM admins WHERE username = ? LIMIT 1;";
    public static String createAdminTableQuery = "CREATE TABLE IF NOT EXISTS admins (id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(255) NOT NULL, password VARCHAR(255) NOT NULL, create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";

    public  static String crateNewAdminQuery = "INSERT INTO admins (username, password) VALUES (?, ?)";

}
