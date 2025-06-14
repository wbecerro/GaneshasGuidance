package wbe.ganeshasGuidance.achievements.types;

import com.badbones69.crazycrates.paper.api.events.CrateOpenEvent;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class OpenCrateAchievement extends BaseAchievement {
    public OpenCrateAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                                List<String> rewards, String crate) {
        super(key, display, parent, maxProgression, rewards);

        if(Bukkit.getPluginManager().getPlugin("CrazyCrates") == null) {
            return;
        }

        registerEvent(CrateOpenEvent.class, event -> {
            Player player = event.getPlayer();
            if(!isVisible(player)) {
                return;
            }

            if(event.getCrate().getFileName().equalsIgnoreCase(crate)) {
                incrementProgression(player);
            }
        });
    }
}
