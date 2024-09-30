package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class KillAchievement extends BaseAchievement {
    public KillAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                           List<String> rewards, EntityType entity) {
        super(key, display, parent, maxProgression, rewards);

        registerEvent(EntityDeathEvent.class, event -> {
            Entity damager = event.getDamageSource().getCausingEntity();
            if(!(damager instanceof Player)) {
                return;
            }
            Player player = (Player) damager;

            if(isVisible(player) && event.getEntity().getType().equals(entity)) {
                incrementProgression(player);
            }
        });
    }
}
