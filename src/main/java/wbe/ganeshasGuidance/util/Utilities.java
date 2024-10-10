package wbe.ganeshasGuidance.util;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import wbe.ganeshasGuidance.GaneshasGuidance;

public class Utilities {

    private GaneshasGuidance plugin = GaneshasGuidance.getInstance();

    public int getCraftedAmount(CraftItemEvent event) {
        int amount = event.getRecipe().getResult().getAmount();
        ClickType click = event.getClick();

        switch(click) {
            case NUMBER_KEY:
                if(event.getWhoClicked().getInventory().getItem(event.getHotbarButton()) != null) {
                    amount = 0;
                }
                break;
            case DROP:
            case CONTROL_DROP:
                ItemStack cursor = event.getCursor();
                if(!isAir(cursor)) {
                    amount = 0;
                }
                break;
            case SHIFT_RIGHT:
            case SHIFT_LEFT:
                if(amount == 0) {
                    break;
                }

                int maxCraftable = getMaxCraftAmount(event.getInventory());
                int capacity = getMaxFitInInventory(event.getRecipe().getResult(), event.getView().getBottomInventory());
                if(capacity < maxCraftable) {
                    maxCraftable = ((capacity + amount - 1) / amount) * amount;
                }

                amount = maxCraftable;
                break;
            default:
        }

        if(amount == 0) {
            return 0;
        }

        return amount;
    }

    private int getMaxFitInInventory(ItemStack item, Inventory inventory) {
        ItemStack[] contents = inventory.getContents();
        int result = 0;

        for(ItemStack itemStack : contents) {
            if(itemStack == null) {
                result += item.getMaxStackSize();
            } else if(itemStack.isSimilar(item)) {
                result += Math.max(item.getMaxStackSize() - item.getAmount(), 0);
            }
        }

        return result;
    }

    private int getMaxCraftAmount(CraftingInventory inventory) {
        if(inventory.getResult() == null) {
            return 0;
        }

        int count = inventory.getResult().getAmount();
        int materialCount = Integer.MAX_VALUE;

        for(ItemStack item : inventory.getMatrix()) {
            if(item != null && item.getAmount() < materialCount) {
                materialCount = item.getAmount();
            }
        }

        return count * materialCount;
    }

    private boolean isAir(ItemStack item) {
        return item == null || item.getType().equals(Material.AIR);
    }
}
