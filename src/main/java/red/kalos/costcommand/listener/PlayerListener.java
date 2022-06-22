package red.kalos.costcommand.listener;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import red.kalos.costcommand.Main;
import red.kalos.costcommand.entity.CostCommand;
import red.kalos.costcommand.manager.CostCommandManager;
import red.kalos.costcommand.util.api.ColorParser;

public class PlayerListener implements Listener {

    @EventHandler
    public void PlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        String command = event.getMessage().toLowerCase();
        if (command.contains(" ")){
            command = command.substring(0, event.getMessage().toLowerCase().indexOf(" "));
        }
        for (CostCommand costCommand: CostCommandManager.getCostCommandList()) {

            if (command.equals("/"+costCommand.getCommand().toLowerCase())){
                if (Main.getEconomy().getBalance(player)>=costCommand.getCost()){
                    if (CostCommandManager.setPlayerData(event.getPlayer(), costCommand)){
                        Main.getEconomy().withdrawPlayer(player,costCommand.getCost());
                        if (!(costCommand.getCost()<=0)){
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),Main.getInstance().getConfig().getString("command")
                                    .replace("%player%",player.getName())
                                    .replace("%cost%",String.valueOf(costCommand.getCost()))
                            );
                        }
                    }else {
                        player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您使用该指令的次数上限了。"));
                        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                        event.setCancelled(true);
                    }
                }else {
                    player.sendMessage(ColorParser.parse("&8[&c&l!&8] &7很抱歉，您余额不足，您还需要 &c"+(costCommand.getCost()-Main.getEconomy().getBalance(player))+" &7金钱才能使用这个指令。"));
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    event.setCancelled(true);
                }

            }
        }
    }

    public static void main(String[] args) {
        System.out.println("2313 223".substring(0, "2313 223".indexOf(" ")));
    }
}
