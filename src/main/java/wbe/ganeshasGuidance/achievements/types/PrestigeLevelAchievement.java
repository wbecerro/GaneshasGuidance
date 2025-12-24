package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import wbe.ganeshasGuidance.achievements.BaseAchievement;
import wbe.nikesBlessing.events.PlayerPrestigeEvent;

import java.util.List;

public class PrestigeLevelAchievement extends BaseAchievement {
    public PrestigeLevelAchievement(String key, AdvancementDisplay display, Advancement parent, List<String> rewards,
                                    String skill, int level) {
        super(key, display, parent, level, rewards);

        if(Bukkit.getPluginManager().getPlugin("NikesBlessing") == null) {
            return;
        }

        registerEvent(PlayerPrestigeEvent.class, event -> {
            Player player = event.getPlayer();

            if(!isVisible(player)) {
                return;
            }

            if(!event.getPrestige().getId().equalsIgnoreCase(skill)) {
                return;
            }

            incrementProgression(player, event.getChangeAmount());
        });
    }
}
