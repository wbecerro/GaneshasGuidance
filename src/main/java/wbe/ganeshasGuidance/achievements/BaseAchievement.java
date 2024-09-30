package wbe.ganeshasGuidance.achievements;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class BaseAchievement extends BaseAdvancement {

    private List<String> rewards;

    public BaseAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                           List<String> rewards) {
        super(key, display, parent, maxProgression);
        this.rewards = rewards;
    }

    @Override
    public void giveReward(Player player) {
        for(String reward : rewards) {
            String command = reward.replace("%player%", player.getName());
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
        }
    }
}
