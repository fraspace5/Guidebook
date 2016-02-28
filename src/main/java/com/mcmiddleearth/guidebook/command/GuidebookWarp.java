/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcmiddleearth.guidebook.command;

import com.mcmiddleearth.guidebook.data.InfoArea;
import com.mcmiddleearth.guidebook.data.PluginData;
import com.mcmiddleearth.guidebook.util.MessageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Eriol_Eandur
 */
public class GuidebookWarp extends GuidebookCommand{
    
    public GuidebookWarp(String... permissionNodes) {
        super(1, true, permissionNodes);
        setShortDescription(": Warp to a Guidebook area.");
        setUsageDescription(" <AreaName>: Warps the player who issues this command to Guidebook area <AreaName>.");
    }
    
    @Override
    protected void execute(CommandSender cs, String... args) {
        InfoArea area = PluginData.getInfoArea(args[0]);
        if(area==null) {
            sendNoAreaErrorMessage(cs);
        }
        else {
            ((Player)cs).teleport(area.getCenter());
            sendWelcomeToCenter(cs, args[0]);
        }
    }

    private void sendWelcomeToCenter(CommandSender cs, String arg) {
        MessageUtil.sendInfoMessage(cs, "You are now at center of Guidebook area "+arg+".");
    }
    
}
