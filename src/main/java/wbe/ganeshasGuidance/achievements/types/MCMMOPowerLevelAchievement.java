package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import com.gmail.nossr50.util.player.UserManager;
import org.bukkit.entity.Player;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class MCMMOPowerLevelAchievement extends BaseAchievement {
    public MCMMOPowerLevelAchievement(String key, AdvancementDisplay display, Advancement parent, List<String> rewards,
                                      int level) {
        super(key, display, parent, 1, rewards);

        registerEvent(McMMOPlayerLevelUpEvent.class, event -> {
            Player player = event.getPlayer();

            if(!isVisible(player)) {
                return;
            }

            McMMOPlayer mcMMOPlayer = UserManager.getPlayer(event.getPlayer());
            if(mcMMOPlayer.getPowerLevel() >= level) {
                incrementProgression(player);
            }
        });
    }
}
