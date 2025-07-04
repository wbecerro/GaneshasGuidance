package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import wbe.acuaticLostWealth.events.PlayerReceiveRewardEvent;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class FishingRewardAchievement extends BaseAchievement {
    public FishingRewardAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                                    List<String> rewards, String rarity) {
        super(key, display, parent, maxProgression, rewards);

        if(Bukkit.getPluginManager().getPlugin("AcuaticLostWealth") == null) {
            return;
        }

        registerEvent(PlayerReceiveRewardEvent.class, event -> {
            Player player = event.getPlayer();
            if(!isVisible(player)) {
                return;
            }

            if(event.getRarity().getInternalName().equalsIgnoreCase(rarity)) {
                incrementProgression(player);
            }
        });
    }
}
