package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import wbe.artemisCompanions.events.PlayerGetPetEvent;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class PetCountAchievement extends BaseAchievement {
    public PetCountAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                               List<String> rewards) {
        super(key, display, parent, maxProgression, rewards);

        if(Bukkit.getPluginManager().getPlugin("ArtemisCompanions") == null) {
            return;
        }

        registerEvent(PlayerGetPetEvent.class, event -> {
            Player player = event.getPlayer();
            if(!isVisible(player)) {
                return;
            }

            incrementProgression(player);
        });
    }
}
