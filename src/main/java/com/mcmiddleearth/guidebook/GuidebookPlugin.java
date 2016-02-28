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
package com.mcmiddleearth.guidebook;

import com.mcmiddleearth.guidebook.command.GuidebookCommandExecutor;
import com.mcmiddleearth.guidebook.data.PluginData;
import com.mcmiddleearth.guidebook.listener.PlayerListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Eriol_Eandur
 */
public class GuidebookPlugin extends JavaPlugin{
 
    @Getter
    private static GuidebookPlugin pluginInstance;

    @Override
    public void onEnable() {
        pluginInstance = this;
        PluginData.loadData();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("guidebook").setExecutor(new GuidebookCommandExecutor());
        getLogger().info("Enabled!");
    }
}
