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

- Before using the plugin, you need to create an account with (username and password). This account will be an administrator account, with this account the player will be able to run the plugin commands directly through the game

### Commands

- `/register-admin <Username> <Password>` - This command must be executed only on the console.
- `/add-player <PlayerNickName>` -(Add player from whitelist)
- `/remove-player <PlayerNickName>` - (Remove player from whitelist)
- `/whitelist-start` - This command must be executed only on the console, it activates and deactivates the plugin

### Contribution

Feel free to contribute with suggestions, bug fixes, or improvements through pull requests

### License

This plugin is distributed under the MIT license. See the `LICENSE` file for more details.

---
