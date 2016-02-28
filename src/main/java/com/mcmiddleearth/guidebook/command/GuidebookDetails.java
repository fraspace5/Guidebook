/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcmiddleearth.guidebook.command;

import com.mcmiddleearth.guidebook.data.CuboidInfoArea;
import com.mcmiddleearth.guidebook.data.InfoArea;
import com.mcmiddleearth.guidebook.data.PluginData;
import com.mcmiddleearth.guidebook.util.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Eriol_Eandur
 */
public class GuidebookDetails extends GuidebookCommand{
    
    public GuidebookDetails(String... permissionNodes) {
        super(1, true, permissionNodes);
        setShortDescription(": Shows details of a Guidebook area.");
        setUsageDescription(" <AreaName>: Shows details of area <AreaName>.");
    }
    
    @Override
    protected void execute(CommandSender cs, String... args) {
        InfoArea area = PluginData.getInfoArea(args[0]);
        if(area==null) {
            sendNoAreaErrorMessage(cs);
        }
        else {
            MessageUtil.sendInfoMessage(cs, "Details for Guidebook area "+args[0]+".");
            MessageUtil.sendClickableMessage((Player)cs, MessageUtil.getNOPREFIX()+ChatColor.GOLD
                                                   +"Center"+ChatColor.YELLOW
                                                   +": "+ area.getCenter().getWorld().getName()
                                                   +" "+area.getCenter().getBlockX()
                                                   +" "+area.getCenter().getBlockY()
                                                   +" "+area.getCenter().getBlockZ(),
                                              "/guidebook warp "+args[0]);
            if(area instanceof CuboidInfoArea) {
                CuboidInfoArea cuboid = (CuboidInfoArea)area;
                MessageUtil.sendNoPrefixInfoMessage(cs, ChatColor.YELLOW+"Cuboid area with"
                                                       +" dx="+cuboid.getSizeX()
                                                       +" dy="+cuboid.getSizeY()
                                                       +" dz="+cuboid.getSizeZ());
            }
            /*else {
                SphericalTeleportationArea sphere = (SphericalTeleportationArea)area;
                MessageUtil.sendNoPrefixInfoMessage(cs, ChatColor.YELLOW+"Spheric area with "
                                                       +"radius "+sphere.getRadius());
            }*/
            MessageUtil.sendNoPrefixInfoMessage(cs, ChatColor.GOLD+"Description: "
                                                  + ChatColor.YELLOW+area.getDescription());
        }
    }
    
}
