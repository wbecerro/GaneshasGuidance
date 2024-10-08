package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.entity.Player;
import wbe.ganeshasGuidance.achievements.BaseAchievement;
import wbe.laboursOfHercules.events.CompleteLabourEvent;

import java.util.List;

public class CompleteLabourAchievement extends BaseAchievement {
    public CompleteLabourAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                                     List<String> rewards, String rarity) {
        super(key, display, parent, maxProgression, rewards);

        registerEvent(CompleteLabourEvent.class, event -> {
            Player player = event.getPlayer();
            if(!isVisible(player)) {
                return;
            }

            if(event.getType().getId().equalsIgnoreCase(rarity)) {
                incrementProgression(player);
            }
        });
    }
}
