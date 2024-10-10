package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.ItemStack;
import wbe.ganeshasGuidance.achievements.BaseAchievement;
import wbe.ganeshasGuidance.util.Utilities;

import java.util.List;

public class CraftAchievement extends BaseAchievement {

    private Utilities utilities = new Utilities();

    public CraftAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                            List<String> rewards, Material item) {
        super(key, display, parent, maxProgression, rewards);

        registerEvent(CraftItemEvent.class, event -> {
            Player player = (Player) event.getWhoClicked();
            if(!isVisible(player)) {
                return;
            }

            if(event.getSlot() != 0) {
                return;
            }

            ItemStack resultItem = event.getRecipe().getResult();
            if(!resultItem.getType().equals(item)) {
                return;
            }

            int amount = utilities.getCraftedAmount(event);
            incrementProgression(player, amount);
        });
    }
}
