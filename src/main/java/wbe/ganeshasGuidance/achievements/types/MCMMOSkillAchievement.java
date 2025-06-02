package wbe.ganeshasGuidance.achievements.types;

import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import wbe.ganeshasGuidance.achievements.BaseAchievement;

import java.util.List;

public class MCMMOSkillAchievement extends BaseAchievement {
    public MCMMOSkillAchievement(String key, AdvancementDisplay display, Advancement parent, List<String> rewards,
                                 String skill, int level) {
        super(key, display, parent, 1, rewards);

        if(Bukkit.getPluginManager().getPlugin("mcMMO") == null) {
            return;
        }

        registerEvent(McMMOPlayerLevelUpEvent.class, event -> {
            Player player = event.getPlayer();

            if(!isVisible(player)) {
                return;
            }

            if(!event.getSkill().equals(PrimarySkillType.valueOf(skill.toUpperCase()))) {
                return;
            }

            if(event.getSkillLevel() >= level) {
                incrementProgression(player);
            }
        });
    }
}
