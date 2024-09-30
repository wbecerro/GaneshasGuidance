package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import org.bukkit.Statistic;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class TradeAchievement extends BaseAchievement {
    public TradeAchievement(String key, AdvancementDisplay display, Advancement parent, int maxProgression,
                            List<String> rewards) {
        super(key, display, parent, maxProgression, rewards);

        registerEvent(PlayerStatisticIncrementEvent.class, event -> {
            Player player = event.getPlayer();
            if(!isVisible(player)) {
                return;
            }

            if(event.getStatistic().equals(Statistic.TRADED_WITH_VILLAGER)) {
                incrementProgression(player);
            }
        });
    }
}
