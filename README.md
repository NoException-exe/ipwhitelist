**Bukkit IP Whitelist Plugin**

### Features

- Whitelisting IPs for access to the Bukkit/Spigot server.
- Storage using SQLite for data persistence.
- Simplified configuration through commands.
- Additional protection against unauthorized access.

### Installation

1. Download the plugin JAR file.
2. Place the JAR file into the "plugins" folder of your Bukkit/Spigot server.
3. Restart the server.

### Initial Setup

- Before using the plugin, you need to create an account with (username and password). This account will be responsible for adding new players to the whitelist. using the /add-player command in the Player, if you use the console command you don't need to create an account

### Commands

- `/register-admin <Username> <Password>` - This command must be executed only on the console. The username and password will be used to add new players to the whitelist when not on the console (when running by player)

- `/add-player <PlayerNickName> <AdminUsername> <AdminPassword>` -(Add player from whitelist) This command, when executed in the console, does not require the login of the account created with the `/register-admin` command. but if it is executed by a player he will need to use the login

- `/remove-player <PlayerNickName> <AdminUsername> <AdminPassword>` - (Remove player from whitelist) This command, when executed in the console, does not require the login of the account created with the `/register-admin` command. but if it is executed by a player he will need to use the login

- `/whitelist-start` - This command must be executed only on the console, it activates and deactivates the plugin

### Contribution

Feel free to contribute with suggestions, bug fixes, or improvements through pull requests

### License

This plugin is distributed under the MIT license. See the `LICENSE` file for more details.

---
