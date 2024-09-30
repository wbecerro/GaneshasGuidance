package wbe.ganeshasGuidance.config;

import org.bukkit.configuration.file.FileConfiguration;

public class Messages {

    private FileConfiguration config;

    public String noPermission;
    public String reload;

    public Messages(FileConfiguration config) {
        this.config = config;

        noPermission = config.getString("Messages.noPermission").replace("&", "§");
        reload = config.getString("Messages.reload").replace("&", "§");
    }
}
