/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcmiddleearth.guidebook.command;

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
public class GuidebookDescription extends GuidebookCommand{
    
    public GuidebookDescription(String... permissionNodes) {
        super(2, true, permissionNodes);
        setShortDescription(": Defines the description of a Guidebook area.");
        setUsageDescription(" <AreaName> <description>: The <description> text will be send to a player moving into the Guidebook area with Name <AreaName>.");
    }
    
    @Override
    protected void execute(CommandSender cs, String... args) {
Logger.getGlobal().info(args[0]);
        InfoArea area = PluginData.getInfoArea(args[0]);
        if(area!=null) {
            String description = args[1];
            for(int i = 2; i<args.length;i++) {
                description = description + " "+args[i];
            }
            area.setDescription(description);
            saveData(cs);
            sendDescriptionSetMessage(cs);
        }
        else {
            sendAreaNotFoundMessage(cs);
        }
    }

    private void saveData(CommandSender cs){
        try {
            PluginData.saveData();
        } catch (IOException ex) {
            sendIOErrorMessage(cs);
            Logger.getLogger(GuidebookDescription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendDescriptionSetMessage(CommandSender cs) {
        MessageUtil.sendInfoMessage(cs, "Descriptoin of guidebook area was saved.");
    }

    private void sendAreaNotFoundMessage(CommandSender cs) {
        MessageUtil.sendInfoMessage(cs, "No Guidebook area with that name found.");
    }

}
