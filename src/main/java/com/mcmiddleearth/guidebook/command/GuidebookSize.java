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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Eriol_Eandur
 */
public class GuidebookSize extends GuidebookCommand{
    
    public GuidebookSize(String... permissionNodes) {
        super(2, true, permissionNodes);
        setShortDescription(": Defines the size of a Guidebook area.");
        setUsageDescription(" <AreaName> <size>: Defines the size of <AreaName>. Size must be a three whole numbers separated with whitespaces for cuboid areas.");
    }
    
    @Override
    protected void execute(CommandSender cs, String... args) {
        InfoArea area = PluginData.getInfoArea(args[0]);
        if(area==null) {
            sendNoAreaErrorMessage(cs);
        }
        else {
            /*if(area instanceof SphericalTeleportationArea) {
                int radius = parseInt(cs, args[1]);
                if(radius==-1) {
                    return;
                }
                ((SphericalTeleportationArea)area).setRadius(radius);
            }
            else {*/
                if(args.length<4) {
                    sendMissingArgumentErrorMessage(cs);
                    return;
                }
                Integer xSize = parseInt(cs, args[1]);
                if(xSize==-1) {
                    return;
                }
                Integer ySize = parseInt(cs, args[2]);
                if(ySize==-1) {
                    return;
                }
                Integer zSize = parseInt(cs, args[3]);
                if(zSize==-1) {
                    return;
                }
                ((CuboidInfoArea)area).setSize(xSize,ySize,zSize);
            //}
            try {
                PluginData.saveData();
            } catch (IOException ex) {
                sendIOErrorMessage(cs);
                Logger.getLogger(GuidebookSize.class.getName()).log(Level.SEVERE, null, ex);
            }
            sendSizeSetMessage(cs);
        }
    }

    private int parseInt(CommandSender cs, String arg) {
        try {
            return Integer.parseInt(arg);
        }
        catch(NumberFormatException e) {
            sendNotANumberMessage(cs);
            return -1;
        }
    }

    private void sendSizeSetMessage(CommandSender cs) {
        MessageUtil.sendInfoMessage(cs, "Size of Guidebook area set.");
    }

    private void sendNotANumberMessage(CommandSender cs) {
        MessageUtil.sendInfoMessage(cs, "Invalid argument. Not a whole number.");
    }
}
