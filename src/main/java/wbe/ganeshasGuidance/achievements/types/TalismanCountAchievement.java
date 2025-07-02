package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import wbe.ganeshasGuidance.achievements.BaseAchievement;
import wbe.voodooTalismans.events.PlayerGetTalismanEvent;

import java.util.List;

public class TalismanCountAchievement extends BaseAchievement {
    public TalismanCountAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                                    List<String> rewards) {
        super(key, display, parent, maxProgression, rewards);

        if(Bukkit.getPluginManager().getPlugin("VoodooTalismans") == null) {
            return;
        }

        registerEvent(PlayerGetTalismanEvent.class, event -> {
            Player player = event.getPlayer();
            if(!isVisible(player)) {
                return;
            }

            incrementProgression(player);
        });
    }
}
