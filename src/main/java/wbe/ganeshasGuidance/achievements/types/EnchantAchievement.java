package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class EnchantAchievement extends BaseAchievement {
    public EnchantAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                              List<String> rewards, Material item) {
        super(key, display, parent, maxProgression, rewards);

        registerEvent(EnchantItemEvent.class, event -> {
            Player player = event.getEnchanter();

            if(isVisible(player) && event.getItem().getType().equals(item)) {
                incrementProgression(player);
            }
        });
    }
}
