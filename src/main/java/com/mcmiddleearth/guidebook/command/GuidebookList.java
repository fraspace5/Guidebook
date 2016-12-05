/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcmiddleearth.guidebook.command;

import com.mcmiddleearth.guidebook.data.InfoArea;
import com.mcmiddleearth.guidebook.data.PluginData;
import com.mcmiddleearth.pluginutil.message.FancyMessage;
import org.bukkit.ChatColor;
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
        PluginData.getMessageUtil().sendInfoMessage(cs, "Guidebook areas (click for details):");
        for(String areaName : PluginData.getInfoAreas().keySet()) {
            if(args.length==0 || areaName.startsWith(args[0])) {
                InfoArea area = PluginData.getInfoArea(areaName);
                FancyMessage message = new FancyMessage(PluginData.getMessageUtil());
                message.addSimple(ChatColor.AQUA+PluginData.getMessageUtil().getNOPREFIX()+"- ");
                message.addClickable(ChatColor.BLUE+areaName,"/guidebook details "+areaName);
                message.addSimple(ChatColor.AQUA+".");
                message.send((Player)cs);
            }
        }
        
    }
    
}
