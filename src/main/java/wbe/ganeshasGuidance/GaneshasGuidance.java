package wbe.ganeshasGuidance;

import com.fren_gor.ultimateAdvancementAPI.UltimateAdvancementAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import wbe.ganeshasGuidance.commands.CommandListener;
import wbe.ganeshasGuidance.config.Config;
import wbe.ganeshasGuidance.config.Messages;

import java.io.File;

public final class GaneshasGuidance extends JavaPlugin {

    private FileConfiguration configuration;

    private CommandListener commandListener;

    public static UltimateAdvancementAPI advancementAPI;

    public static Config config;

    public static Messages messages;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getLogger().info("GaneshasGuidance enabled correctly.");
        advancementAPI = UltimateAdvancementAPI.getInstance(getInstance());
        reloadConfiguration();

        commandListener = new CommandListener();
        getCommand("ganeshasguidance").setExecutor(commandListener);
    }

    @Override
    public void onDisable() {
        reloadConfig();
        getLogger().info("GaneshasGuidance disabled correctly.");
    }

    public static GaneshasGuidance getInstance() {
        return getPlugin(GaneshasGuidance.class);
    }

    public void reloadConfiguration() {
        if(!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        reloadConfig();
        configuration = getConfig();
        config = new Config(configuration);
        messages = new Messages(configuration);
    }
}
