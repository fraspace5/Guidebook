/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcmiddleearth.guidebook.command;

import com.mcmiddleearth.guidebook.data.InfoArea;
import com.mcmiddleearth.guidebook.data.PluginData;
import com.mcmiddleearth.guidebook.util.MessageUtil;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Eriol_Eandur
 */
public class GuidebookList extends GuidebookCommand{
    
    public GuidebookList(String... permissionNodes) {
        super(0, true, permissionNodes);
        setShortDescription(": Lists all guidebook areas.");
        setUsageDescription(" [selection]: Lists all guidebook areas which names start with [selection].");
    }
    
    @Override
    protected void execute(CommandSender cs, String... args) {
        MessageUtil.sendInfoMessage(cs, "Guidebook areas:");
        for(String areaName : PluginData.getInfoAreas().keySet()) {
            if(args.length==0 || areaName.startsWith(args[0])) {
                InfoArea area = PluginData.getInfoArea(areaName);
                Map<String,String> message = new LinkedHashMap<>();
                message.put(ChatColor.AQUA+MessageUtil.getNOPREFIX()+"- ",null);
                message.put(ChatColor.BLUE+areaName,"/guidebook warp "+areaName);
                message.put(ChatColor.AQUA+".",null);
                MessageUtil.sendClickableMessage((Player)cs, message);
            }
        }
        
    }
    
}
