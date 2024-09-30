package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import org.bukkit.entity.Player;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class MMKillAchievement extends BaseAchievement {
    public MMKillAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                             List<String> rewards, String mob) {
        super(key, display, parent, maxProgression, rewards);

        registerEvent(MythicMobDeathEvent.class, event -> {
            Player player = (Player) event.getKiller();

            MythicMob mythicMob = event.getMobType();
            if(isVisible(player) && mythicMob.getInternalName().equals(mob)) {
                incrementProgression(player);
            }
        });
    }
}
