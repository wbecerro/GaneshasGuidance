package wbe.ganeshasGuidance.config;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.advancement.Advancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.BaseAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementFrameType;
import com.fren_gor.ultimateAdvancementAPI.events.PlayerLoadingCompletedEvent;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import wbe.ganeshasGuidance.GaneshasGuidance;
import wbe.ganeshasGuidance.achievements.BaseAchievement;
import wbe.ganeshasGuidance.achievements.RootAchievement;
import wbe.ganeshasGuidance.achievements.types.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Config {

    private FileConfiguration config;

    public Config(FileConfiguration config) {
        this.config = config;

        loadTabs();
    }

    private void loadTabs() {
        Set<String> configTabs = config.getConfigurationSection("Tabs").getKeys(false);
        for(String tab : configTabs) {
            AdvancementTab advancementTab = GaneshasGuidance.advancementAPI.createAdvancementTab(tab);
            RootAdvancement root = loadRootAchievement(tab, advancementTab);
            Set<BaseAdvancement> achievements = loadAchievements(tab, root);
            advancementTab.registerAdvancements(root, achievements);

            advancementTab.getEventManager().register(tab, PlayerLoadingCompletedEvent.class, event -> {
                advancementTab.showTab(event.getPlayer());
                advancementTab.grantRootAdvancement(event.getPlayer());
            });
        }
    }

    private RootAdvancement loadRootAchievement(String tab, AdvancementTab advancementTab) {
        String displayMaterial = config.getString("Tabs." + tab + ".achievements.root.display.material");
        String displayName = config.getString("Tabs." + tab + ".achievements.root.display.name");
        String displayFrame = config.getString("Tabs." + tab + ".achievements.root.display.frame");
        boolean displayToast = config.getBoolean("Tabs." + tab + ".achievements.root.display.toast");
        boolean displayAnnounce = config.getBoolean("Tabs." + tab + ".achievements.root.display.announce");
        int displayX = config.getInt("Tabs." + tab + ".achievements.root.display.x");
        int displayY = config.getInt("Tabs." + tab + ".achievements.root.display.y");
        String displayDescription = config.getString("Tabs." + tab + ".achievements.root.display.description");
        String background = config.getString("Tabs." + tab + ".achievements.root.background");
        AdvancementDisplay display = new AdvancementDisplay(Material.valueOf(displayMaterial.toUpperCase()), displayName,
                AdvancementFrameType.valueOf(displayFrame), displayToast, displayAnnounce, displayX, displayY, displayDescription);
        RootAchievement rootAchievement = new RootAchievement(tab, "root", display, background);
        return rootAchievement.createAdvancement(advancementTab);
    }

    private Set<BaseAdvancement> loadAchievements(String tab, RootAdvancement root) {
        Set<String> configAchievements = config.getConfigurationSection("Tabs." + tab + ".achievements.base").getKeys(false);
        Set<BaseAdvancement> achievements = new HashSet<>();
        for(String achievement : configAchievements) {
            String displayMaterial = config.getString("Tabs." + tab + ".achievements.base." + achievement + ".display.material");
            String displayName = config.getString("Tabs." + tab + ".achievements.base." + achievement + ".display.name");
            String displayFrame = config.getString("Tabs." + tab + ".achievements.base." + achievement + ".display.frame");
            boolean displayToast = config.getBoolean("Tabs." + tab + ".achievements.base." + achievement + ".display.toast");
            boolean displayAnnounce = config.getBoolean("Tabs." + tab + ".achievements.base." + achievement + ".display.announce");
            int displayX = config.getInt("Tabs." + tab + ".achievements.base." + achievement + ".display.x");
            int displayY = config.getInt("Tabs." + tab + ".achievements.base." + achievement + ".display.y");
            String displayDescription = config.getString("Tabs." + tab + ".achievements.base." + achievement + ".display.description");
            AdvancementDisplay display = new AdvancementDisplay(Material.valueOf(displayMaterial.toUpperCase()), displayName,
                    AdvancementFrameType.valueOf(displayFrame), displayToast, displayAnnounce, displayX, displayY, displayDescription);

            String parentName = config.getString("Tabs." + tab + ".achievements.base." + achievement + ".parent");
            int maxProgression = 1;
            if(config.contains("Tabs." + tab + ".achievements.base." + achievement + ".maxProgression")) {
                maxProgression = config.getInt("Tabs." + tab + ".achievements.base." + achievement + ".maxProgression");
            }
            List<String> rewards = config.getStringList("Tabs." + tab + ".achievements.base." + achievement + ".rewards");
            String type = config.getString("Tabs." + tab + ".achievements.base." + achievement + ".type.id");

            achievements.add(createAchievement(type, achievement, display, parentName, maxProgression, rewards,
                    achievements, tab, root));
        }

        return achievements;
    }

    private BaseAchievement createAchievement(String type, String key, AdvancementDisplay display, String parentName, int maxProgression,
                                              List<String> rewards, Set<BaseAdvancement> achievements, String tab, RootAdvancement root) {
        Advancement parent = getParent(parentName, root, achievements);
        switch(type) {
            case "BREAK":
                Material block = Material.valueOf(config.getString("Tabs." + tab + ".achievements.base." + key + ".type.block")
                        .toUpperCase());
                return new BreakAchievement(key, display, parent, maxProgression, rewards, block);
            case "CRAFT":
                Material item = Material.valueOf(config.getString("Tabs." + tab + ".achievements.base." + key + ".type.item")
                        .toUpperCase());
                return new CraftAchievement(key, display, parent, maxProgression, rewards, item);
            case "ENCHANT":
                item = Material.valueOf(config.getString("Tabs." + tab + ".achievements.base." + key + ".type.item")
                        .toUpperCase());
                return new EnchantAchievement(key, display, parent, maxProgression, rewards, item);
            case "JOBLEVEL":
                String job = config.getString("Tabs." + tab + ".achievements.base." + key + ".type.job");
                int level = config.getInt("Tabs." + tab + ".achievements.base." + key + ".type.level");
                return new JobLevelAchievement(key, display, parent, rewards, job, level);
            case "KILL":
                EntityType entityType = EntityType.valueOf(config.getString("Tabs." + tab + ".achievements.base." + key + ".type.entity")
                        .toUpperCase());
                return new KillAchievement(key, display, parent, maxProgression, rewards, entityType);
            case "MCMMOPOWERLEVEL":
                level = config.getInt("Tabs." + tab + ".achievements.base." + key + ".type.level");
                return new MCMMOPowerLevelAchievement(key, display, parent, rewards, level);
            case "MCMMOSKILL":
                String skill = config.getString("Tabs." + tab + ".achievements.base." + key + ".type.skill");
                level = config.getInt("Tabs." + tab + ".achievements.base." + key + ".type.level");
                return new MCMMOSkillAchievement(key, display, parent, rewards, skill, level);
            case "MMKILL":
                String mob = config.getString("Tabs." + tab + ".achievements.base." + key + ".type.mob");
                return new MMKillAchievement(key, display, parent, maxProgression, rewards, mob);
            case "PLACE":
                block = Material.valueOf(config.getString("Tabs." + tab + ".achievements.base." + key + ".type.block")
                        .toUpperCase());
                return new PlaceAchievement(key, display, parent, maxProgression, rewards, block);
            case "CRATE":
                String crate = config.getString("Tabs." + tab + ".achievements.base." + key + ".type.crate");
                return new OpenCrateAchievement(key, display, parent, maxProgression, rewards, crate);
            case "TRADE":
                return new TradeAchievement(key, display, parent, maxProgression, rewards);
            case "FISHINGRARITY":
                String rarity = config.getString("Tabs." + tab + ".achievements.base." + key + ".type.rarity");
                return new FishingRewardAchievement(key, display, parent, maxProgression, rewards, rarity);
            case "WOODCUTTINGRARITY":
                rarity = config.getString("Tabs." + tab + ".achievements.base." + key + ".type.rarity");
                return new WoodcuttingRewardAchievement(key, display, parent, maxProgression, rewards, rarity);
            case "LABOUR":
                rarity = config.getString("Tabs." + tab + ".achievements.base." + key + ".type.rarity");
                return new CompleteLabourAchievement(key, display, parent, maxProgression, rewards, rarity);
            case "CRYSTAL":
                rarity = config.getString("Tabs." + tab + ".achievements.base." + key + ".type.rarity");
                return new UseCrystalAchievement(key, display, parent, maxProgression, rewards, rarity);
        }

        return null;
    }

    private Advancement getParent(String parentName, RootAdvancement root, Set<BaseAdvancement> achievements) {
        for(BaseAdvancement achievement : achievements) {
            if(achievement.getKey().getKey().equals(parentName)) {
                return achievement;
            }
        }

        return root;
    }
}
