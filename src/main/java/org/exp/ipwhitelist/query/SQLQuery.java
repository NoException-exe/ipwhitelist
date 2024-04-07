package org.exp.ipwhitelist.query;

public abstract class SQLQuery{
    public static final String FIND_PLAYER = "SELECT * FROM ipwhitelist WHERE nickname = ?";
    public static final String CREATE_NEW_PLAYER = "INSERT INTO ipwhitelist (nickname, ip) VALUES (?, NULL)";
    public static final String UPDATE_PLAYER_IP = "UPDATE ipwhitelist SET ip = ? WHERE nickname = ?";
    public static  final String DELETE_PLAYER_WHITELIST = "DELETE FROM ipwhitelist WHERE nickname = ?";
    public static final String FIND_ADMIN = "SELECT * FROM admins WHERE username = ? AND nickname = ? LIMIT 1;";
    public static final String CREATE_NEW_ADMIN = "INSERT INTO admins (username, password, nickname) VALUES (?, ?, ?)";
    public static final String UPDATE_ADMIN_AUTH = "UPDATE admins SET authenticated = ? WHERE username = ? OR nickname = ?";
    public static final String ADMIN_IS_AUTHENTICATED = "SELECT * FROM admins WHERE nickname = ? AND authenticated = 1 LIMIT 1;";

}
