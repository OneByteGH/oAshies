package io.github.onebytegh.oashies.events;

import io.github.onebytegh.oashies.OAshies;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class EnableEvent implements Listener {
    private final OAshies plugin;
    public EnableEvent(OAshies plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onButtonPress(PlayerInteractEvent event) {
        if(event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        if(event.getClickedBlock().getType() != org.bukkit.Material.STONE_BUTTON) return;

    }
}
