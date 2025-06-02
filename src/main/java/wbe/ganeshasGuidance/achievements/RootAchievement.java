package wbe.ganeshasGuidance.achievements;

import com.fren_gor.ultimateAdvancementAPI.AdvancementTab;
import com.fren_gor.ultimateAdvancementAPI.advancement.RootAdvancement;
import com.fren_gor.ultimateAdvancementAPI.advancement.display.AdvancementDisplay;

public class RootAchievement {

    private String page;

    private String key;

    private AdvancementDisplay display;

    private String backgroundTexture;

    public RootAchievement(String page, String key, AdvancementDisplay display, String backgroundTexture) {
        this.page = page;
        this.key = key;
        this.display = display;
        this.backgroundTexture = backgroundTexture;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AdvancementDisplay getDisplay() {
        return display;
    }

    public void setDisplay(AdvancementDisplay display) {
        this.display = display;
    }

    public String getBackgroundTexture() {
        return backgroundTexture;
    }

    public void setBackgroundTexture(String backgroundTexture) {
        this.backgroundTexture = backgroundTexture;
    }

    public RootAdvancement createAdvancement(AdvancementTab tab) {
        RootAdvancement root = new RootAdvancement(tab, key, display, backgroundTexture);
        return root;
    }
}
