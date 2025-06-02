package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.gamingmesh.jobs.api.JobsLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class JobLevelAchievement extends BaseAchievement {
    public JobLevelAchievement(String key, AdvancementDisplay display, Advancement parent, List<String> rewards,
                               String job, int level) {
        super(key, display, parent, 1, rewards);

        if(Bukkit.getPluginManager().getPlugin("Jobs") == null) {
            return;
        }

        registerEvent(JobsLevelUpEvent.class, event -> {
            Player player = event.getPlayer().getPlayer();

            if(!isVisible(player)) {
                return;
            }

            if(!event.getJob().getName().equalsIgnoreCase(job)) {
                return;
            }

            if(event.getLevel() >= level) {
                incrementProgression(player);
            }
        });
    }
}
