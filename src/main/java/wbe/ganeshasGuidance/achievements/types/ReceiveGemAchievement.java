package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import wbe.ganeshasGuidance.achievements.BaseAchievement;
import wbe.tartarusRiches.events.PlayerReceiveGemEvent;

import java.util.List;

public class ReceiveGemAchievement extends BaseAchievement {
    public ReceiveGemAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                                 List<String> rewards) {
        super(key, display, parent, maxProgression, rewards);

        if(Bukkit.getPluginManager().getPlugin("TartarusRiches") == null) {
            return;
        }

        registerEvent(PlayerReceiveGemEvent.class, event -> {
            Player player = event.getPlayer();
            if(!isVisible(player)) {
                return;
            }

            incrementProgression(player);
        });
    }
}
