package io.github.onebytegh.oashies.events;

import io.github.onebytegh.oashies.OAshies;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class EnableEvent implements Listener {
    private final OAshies plugin;
    public EnableEvent(OAshies plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onButtonPress(PlayerInteractEvent event) {
        if(event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        if(event.getClickedBlock() == null) return;
        if(event.getClickedBlock().getType().isAir()) return;
        if(event.getClickedBlock().getType() != org.bukkit.Material.STONE_BUTTON) return;
        if(event.getClickedBlock().getRelative(0, +2, 0).getType() != Material.STONE) return;
        if(event.getClickedBlock().getRelative(0, +1, 0).getType() != Material.OAK_LEAVES) return;
        if(event.getClickedBlock().getRelative(0, -1, 0).getType() != Material.OAK_LEAVES) return;
        if(event.getClickedBlock().getRelative(0, -2, 0).getType() != Material.STONE) return;

        UUID playerUUID = event.getPlayer().getUniqueId();
        if(!plugin.getPlayerUUID().equals(playerUUID)) plugin.setPlayerUUID(playerUUID);
        event.getClickedBlock().setType(Material.OAK_SIGN);
        Sign sign = (Sign) event.getClickedBlock().getState();
        sign.setLine(0, "§a§lEnabled");
        sign.setLine(1, "§a§lYo, you the god now");

        event.getClickedBlock().getRelative(0, +2, 0).setType(Material.OAK_SIGN);
        Sign sign2 = (Sign) event.getClickedBlock().getRelative(0, +2, 0).getState();
        sign2.setLine(0, "§a§lGet Flying Boots");

        event.getClickedBlock().getRelative(0, +1, 0).setType(Material.OAK_SIGN);
        Sign sign3 = (Sign) event.getClickedBlock().getRelative(0, +1, 0).getState();
        sign3.setLine(0, "§a§lGet Blindness Wand");

        event.getClickedBlock().getRelative(0, -1, 0).setType(Material.OAK_SIGN);
        Sign sign4 = (Sign) event.getClickedBlock().getRelative(0, -1, 0).getState();
        sign4.setLine(0, "§a§lGet Levitation Sword");

        event.getClickedBlock().getRelative(0, 0, 0).setType(Material.OAK_SIGN);
        Sign sign5 = (Sign) event.getClickedBlock().getRelative(0, -2, 0).getState();
        sign5.setLine(0, "§a§lGet KnockBack Stick");

        event.getClickedBlock().getRelative(+1, 0, 0).setType(Material.OAK_SIGN);
        Sign sign6 = (Sign) event.getClickedBlock().getRelative(0, -3, 0).getState();
        sign6.setLine(0, "§a§lGet 1000 Fake TNTs");

        event.getClickedBlock().getRelative(-1, 0, 0).setType(Material.OAK_SIGN);
        Sign sign7 = (Sign) event.getClickedBlock().getRelative(0, -4, 0).getState();
        sign7.setLine(0, "§a§lGet Fake Void Trap");
    }
}
