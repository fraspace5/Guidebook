/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcmiddleearth.guidebook.command;

import com.mcmiddleearth.guidebook.GuidebookPlugin;
import com.mcmiddleearth.guidebook.conversation.ConfirmationFactory;
import com.mcmiddleearth.guidebook.conversation.Confirmationable;
import com.mcmiddleearth.guidebook.data.CuboidInfoArea;
import com.mcmiddleearth.guidebook.data.InfoArea;
import com.mcmiddleearth.guidebook.data.PluginData;
import com.mcmiddleearth.guidebook.util.MessageUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Eriol_Eandur
 */
public class GuidebookSet extends GuidebookCommand implements Confirmationable{
    
    private InfoArea area;
    
    private String areaName;
    
    private Location center;
    
    private boolean cuboid;
    
    private boolean newDynamic;
    
    public GuidebookSet(String... permissionNodes) {
        super(1, true, permissionNodes);
        setShortDescription(": Defines a Guidebook area.");
        setUsageDescription(" <AreaName>: Location of command sender becomes center of Guidebook area with name <AreaName>.");
    }
    
    @Override
    protected void execute(CommandSender cs, String... args) {
        areaName = args[0];
        area = PluginData.getInfoArea(args[0]);
        center = ((Player)cs).getLocation().clone();
        cuboid = true;
        /*if(args.length>1) {
            if(args[1].equalsIgnoreCase("sphere")) {
                cuboid = false;
            }
            else if(!args[1].equalsIgnoreCase("cuboid")) {
                sentInvalidArgumentMessage(cs);
            }
        }*/
        if(area==null) {
            if(cuboid) {
                area = new CuboidInfoArea(center);
            }
            /*else {
                area = new SphericalInfoArea(center);
            }*/
            PluginData.addInfoArea(areaName, area);
            saveData(cs);
            sendNewAreaMessage(cs);
        }
        else {
            new ConfirmationFactory(GuidebookPlugin.getPluginInstance()).start((Player) cs, 
                    "An area with that name already exists. Do you want to move it to your location?", this);
        }
    }

    private void saveData(CommandSender cs){
        try {
            PluginData.saveData();
        } catch (IOException ex) {
            sendIOErrorMessage(cs);
            Logger.getLogger(GuidebookSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void confirmed(Player player) {
        area.setCenter(player.getLocation());
        saveData(player);
        sendCenterSetMessage(player);
    }

    @Override
    public void cancelled(Player player) {
        MessageUtil.sendErrorMessage(player, "You cancelled setting of area. No changes were made.");
    }

    private void sendCenterSetMessage(CommandSender cs) {
        MessageUtil.sendInfoMessage(cs, "Center of guidebook area was moved to your location.");
    }

    private void sendNewAreaMessage(CommandSender cs) {
        MessageUtil.sendInfoMessage(cs, "New guidebook area created.");
    }

}
