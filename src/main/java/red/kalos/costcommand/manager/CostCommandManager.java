package red.kalos.costcommand.manager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import red.kalos.costcommand.Main;
import red.kalos.costcommand.entity.CostCommand;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CostCommandManager {

    public static List<CostCommand> getCostCommandList(){
        List<CostCommand> costCommands = new ArrayList<>();
        for (String key:Main.getInstance().getConfig().getConfigurationSection("costcommand").getKeys(false)) {
            costCommands.add(new CostCommand(
                    key,
                    Main.getInstance().getConfig().getInt("costcommand."+key+".coolDown"),
                    Main.getInstance().getConfig().getInt("costcommand."+key+".number"),
                    Main.getInstance().getConfig().getInt("costcommand."+key+".cost")
            ));
        }
        return costCommands;
    }

    public static boolean setPlayerData(Player player,CostCommand command){
        File file = new File(Main.getInstance().getDataFolder(),"data/"+player.getUniqueId()+".yml");
        YamlConfiguration data = YamlConfiguration.loadConfiguration(file);

        if (data.get(command.getCommand())==null){
            data.set(command.getCommand(),1);
        }else {
            if (command.getNumber()!=-1&&data.getInt(command.getCommand())>=command.getNumber()){
                return false;
            }else {
                data.set(command.getCommand(),data.getInt(command.getCommand())+1);
            }
        }




        //数据保存
        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}
