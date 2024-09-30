package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class PlaceAchievement extends BaseAchievement {
    public PlaceAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                            List<String> rewards, Material block) {
        super(key, display, parent, maxProgression, rewards);

        registerEvent(BlockPlaceEvent.class, event -> {
            Player player = event.getPlayer();
            if(isVisible(player) && event.getBlock().getType().equals(block)) {
                incrementProgression(player);
            }
        });
    }
}
