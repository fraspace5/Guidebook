/*
 * Copyright (C) 2016 MCME
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mcmiddleearth.guidebook.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

/**
 *
 * @author Eriol_Eandur
 */
public abstract class InfoArea {
    
    @Getter
    @Setter
    private Location center;
        
    private final Set<UUID> informedPlayers = new HashSet<>();
    
    @Getter
    @Setter
    private String description = "";
    
    @Getter
    private int nearDistance = 10;
    
    public InfoArea(Location center) {
        this.center = center;
    }
    
    public InfoArea(ConfigurationSection config) {
        this.center = deserializeLocation(config.getConfigurationSection("center"));
        this.description = config.getString("description");
    }
    
    public abstract boolean isNear(Location loc);
    
    public abstract boolean isInside(Location loc);
    
    public boolean isInfomed(Player player) {
        return informedPlayers.contains(player.getUniqueId());
    }
    
    public void addInformedPlayer(Player player) {
        informedPlayers.add(player.getUniqueId());
    }
    
    public void removeInformedPlayer(Player player) {
        informedPlayers.remove(player.getUniqueId());
    }
        
    public Map<String, Object> serialize() {
        Map<String,Object> result = new HashMap();
        result.put("center", serializeLocation(this.center));
        result.put("description", description);
        return result;
    }
    
    private static Map<String,Object> serializeLocation(Location loc) {
        Map<String,Object> result = new HashMap<>();
        result.put("x", loc.getX());
        result.put("y", loc.getY());
        result.put("z", loc.getZ());
        result.put("world", loc.getWorld().getName());
        return result;
    }
    
    private static Location deserializeLocation(ConfigurationSection data) {
        if(data == null) {
            return null;
        }
        World world = Bukkit.getWorld(data.getString("world"));
        if(world == null) {
            return null;
        }
        else {
            return new Location(world, (Double) data.get("x"), 
                                       (Double) data.get("y"), 
                                       (Double) data.get("z"));
        }
    }
    
}
