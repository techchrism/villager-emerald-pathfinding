package me.techchrism.villageremeraldpathfinding;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;

public class VillagerEmeraldPathfinding extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        // Every 5 ticks, check if a player has an emerald block in their hand
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () ->
        {
            for(Player player : getServer().getOnlinePlayers())
            {
                if(player.getInventory().getItemInMainHand().getType() == Material.EMERALD_BLOCK ||
                    player.getInventory().getItemInOffHand().getType() == Material.EMERALD_BLOCK)
                {
                    // Get all nearby villagers
                    for(Entity entity : player.getNearbyEntities(15, 15, 15))
                    {
                        if(entity instanceof Villager)
                        {
                            ((Villager) entity).getPathfinder().moveTo(player);
                        }
                    }
                }
            }
        }, 5L, 5L);
    }
}
