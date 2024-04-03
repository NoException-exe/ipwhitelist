package org.exp.ipwhitelist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.exp.ipwhitelist.lib.Bcrypt;
import org.exp.ipwhitelist.repository.AdminRepository;

import java.sql.Connection;


public class RegisterAdmin implements CommandExecutor {

    private final AdminRepository adminRepository;

    public RegisterAdmin(Connection connection){
        this.adminRepository = new AdminRepository(connection);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String str, String[] args) {
        if(commandSender instanceof ConsoleCommandSender){
            if (args.length < 2) {
                commandSender.sendMessage("Use register-admin <username> <password>");
                return true;
            }

            //userdata
            String username = args[0].toLowerCase();
            String password = args[1];


            //checking if username exist;
            if (adminRepository.exist(username))
            {
                commandSender.sendMessage("User admin already exist.");
                return true;
            }


            if (password.length() < 8){
                commandSender.sendMessage("Weak password, the admin password needs at least 8 characters");
                return true;
            }


            //encrypt password
            Bcrypt bcrypt = new Bcrypt(password);
            String hashedPassword = bcrypt.hashPassword();

            //creating admin
            adminRepository.create(username, hashedPassword);

            return true;
        }
        else  {
            Player player = (Player) commandSender;
            player.sendMessage("This command can only be used in the console.");
            return true;
        }
    }

}
