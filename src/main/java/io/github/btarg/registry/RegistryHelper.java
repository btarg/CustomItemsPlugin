package io.github.btarg.registry;

import io.github.btarg.OrigamiMain;
import io.github.btarg.definitions.CustomBlockDefinition;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class RegistryHelper {


    public static ItemStack CreateCustomBlockItemStack(CustomBlockDefinition customBlockDefinition, int count) {

        if (customBlockDefinition == null) return null;

        ItemStack stone = new ItemStack(Material.ITEM_FRAME, count);

        // new item meta (name and lore)
        ItemMeta meta = stone.getItemMeta();
        Component name = customBlockDefinition.getDisplayName();
        meta.displayName(name);
        meta.lore(customBlockDefinition.getLore());
        meta.setCustomModelData(customBlockDefinition.blockModelData);

        // add custom block id to the item so that we can tell it's a custom item
        meta.getPersistentDataContainer().set(OrigamiMain.customItemTag, PersistentDataType.STRING, customBlockDefinition.id);
        stone.setItemMeta(meta);

        return stone;


    }

    public static String getRegistryPrefix() {
        return Objects.requireNonNullElse(OrigamiMain.config.get("custom-items.prefix"), "origami") + ":";
    }

    public static boolean isInRegistry(String check) {
        // TODO: also check item registry
        return CustomBlockRegistry.GetRegisteredBlock(check) != null;
    }
}
