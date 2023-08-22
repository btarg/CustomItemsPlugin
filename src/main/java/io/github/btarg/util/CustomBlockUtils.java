package io.github.btarg.util;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import io.github.btarg.PluginMain;
import io.github.btarg.blockdata.CustomBlockDatabase;
import io.github.btarg.definitions.CustomBlockDefinition;
import io.github.btarg.registry.CustomBlockRegistry;
import io.github.btarg.util.items.ItemTagHelper;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class CustomBlockUtils {

    public static CustomBlockDefinition getDefinitionFromBlock(Block block) {
        if (!CustomBlockDatabase.blockIsInDatabase(block.getLocation())) return null;
        Entity linkedFrame = GetLinkedItemFrame(block);
        if (linkedFrame == null) return null;
        NBTCompound nbtCompound = ItemTagHelper.getItemTagFromItemFrame(linkedFrame);
        if (nbtCompound == null) return null;
        String blockId = nbtCompound.getString(PluginMain.customBlockIDKey);
        return CustomBlockRegistry.GetRegisteredBlock(blockId);
    }

    public static Entity GetLinkedItemFrame(Block block) {
        String check_uuid = CustomBlockDatabase.getBlockUUIDFromDatabase(block.getLocation());
        if (check_uuid != null && !check_uuid.isEmpty()) {

            for (Entity ent : block.getWorld().getNearbyEntities(block.getLocation(), 1.0, 1.0, 1.0)) {
                if (ent.getUniqueId().toString().equals(check_uuid)) {
                    return ent;
                }
            }
        }
        return null;
    }
}
