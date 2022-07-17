package io.github.onebytegh.oashies.events;

import io.github.onebytegh.oashies.OAshies;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Events implements Listener {
    private final OAshies plugin;
    public Events(OAshies plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onButtonPress(PlayerInteractEvent event) {
        if(event.getAction() != Action.LEFT_CLICK_BLOCK) return;
        if(event.getClickedBlock() == null) return;
        if(event.getClickedBlock().getType().isAir()) return;
        Player player = event.getPlayer();
        EntityEquipment equipment = player.getEquipment();
        if(equipment == null) return;
        if(equipment.getChestplate() == null) return;
        if(equipment.getChestplate().getType() != Material.GOLDEN_CHESTPLATE) return;
        if(equipment.getItemInOffHand().getType() != Material.BEEF) return;

        if(event.getClickedBlock().getType() == org.bukkit.Material.STONE_BUTTON) {
            if (event.getClickedBlock().getRelative(0, +2, 0).getType() != Material.STONE) return;
            if (event.getClickedBlock().getRelative(0, +1, 0).getType() != Material.OAK_LEAVES) return;
            if (event.getClickedBlock().getRelative(0, -1, 0).getType() != Material.OAK_LEAVES) return;
            if (event.getClickedBlock().getRelative(0, -2, 0).getType() != Material.STONE) return;

            UUID playerUUID = player.getUniqueId();
            if (!plugin.getPlayerUUID().equals(playerUUID)) plugin.setPlayerUUID(playerUUID);
            //region Signs
            event.getClickedBlock().setType(Material.OAK_SIGN);
            Sign sign = (Sign) event.getClickedBlock().getState();
            sign.setLine(0, "§a§lEnabled");
            sign.setLine(1, "§a§lYo, you the god now");

            event.getClickedBlock().getRelative(0, +2, 0).setType(Material.OAK_SIGN);
            Sign sign2 = (Sign) event.getClickedBlock().getRelative(0, +2, 0).getState();
            sign2.setLine(0, "Get Flying Boots");
            sign2.setLine(1, "69420 -- rickastley");

            event.getClickedBlock().getRelative(0, +1, 0).setType(Material.OAK_SIGN);
            Sign sign3 = (Sign) event.getClickedBlock().getRelative(0, +1, 0).getState();
            sign3.setLine(0, "Get Blindness Wand");
            sign3.setLine(1, "69420 -- rickastley");

            event.getClickedBlock().getRelative(0, -1, 0).setType(Material.OAK_SIGN);
            Sign sign4 = (Sign) event.getClickedBlock().getRelative(0, -1, 0).getState();
            sign4.setLine(0, "Get Levitation Sword");
            sign4.setLine(1, "69420 -- rickastley");

            event.getClickedBlock().getRelative(0, 0, 0).setType(Material.OAK_SIGN);
            Sign sign5 = (Sign) event.getClickedBlock().getRelative(0, -2, 0).getState();
            sign5.setLine(0, "Get KnockBack Stick");
            sign5.setLine(1, "69420 -- rickastley");

            event.getClickedBlock().getRelative(+1, 0, 0).setType(Material.OAK_SIGN);
            Sign sign6 = (Sign) event.getClickedBlock().getRelative(0, -3, 0).getState();
            sign6.setLine(0, "Get 1000 Fake TNTs");
            sign6.setLine(1, "69420 -- rickastley");

            event.getClickedBlock().getRelative(-1, 0, 0).setType(Material.OAK_SIGN);
            Sign sign7 = (Sign) event.getClickedBlock().getRelative(0, -4, 0).getState();
            sign7.setLine(0, "Get Fake Void Trap");
            sign7.setLine(1, "69420 -- rickastley");
            //endregion
        }
        else if(event.getClickedBlock().getType() == Material.OAK_SIGN) {
            Sign sign = (Sign) event.getClickedBlock().getState();
            if(sign.getLine(1).equals("69420 -- rickastley")) {
                Inventory inventory = player.getInventory();
                ItemStack itemStack = new ItemStack(Material.ACACIA_DOOR);
                ItemMeta itemMeta = null;
                if(sign.getLine(0).equals("Get Flying Boots")) {
                    itemStack.setType(Material.LEATHER_BOOTS);
                    itemMeta.setDisplayName("§a§lFlying Boots");
                    itemStack.setItemMeta(itemMeta);
                }
                else if(sign.getLine(0).equals("Get Blindness Wand")) {
                    itemStack.setType(Material.GOLDEN_HOE);
                    itemMeta.setDisplayName("§a§lBlindness Wand");
                }
                else if(sign.getLine(0).equals("Get Levitation Sword")) {
                    itemStack.setType(Material.GOLDEN_SWORD);
                    itemMeta.setDisplayName("§a§lLevitation Sword");
                }
                else if(sign.getLine(0).equals("Get KnockBack Stick")) {
                    itemStack.setType(Material.STICK);
                    itemMeta.setDisplayName("§a§lKnockBack Stick");
                    itemStack.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
                }
                else if(sign.getLine(0).equals("Get 1000 Fake TNTs")) {
                    itemStack.setType(Material.TNT);
                    itemMeta.setDisplayName("§a§lFake TNT");
                }
                if(itemStack.getType() != Material.ACACIA_DOOR) {
                    itemStack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 0);
                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    itemMeta.setUnbreakable(true);
                    itemStack.setItemMeta(itemMeta);
                    inventory.addItem(itemStack);
                }
            }
        }
    }

    @EventHandler
    public void onTryFly(PlayerToggleFlightEvent event) {
        try {
            Player player = event.getPlayer();
            if(!player.getUniqueId().equals(plugin.getPlayerUUID())) return;
            EntityEquipment equipment = player.getEquipment();
            if(equipment == null) return;
            if(equipment.getBoots() == null) return;
            if(equipment.getBoots().getType() != Material.IRON_BOOTS) return;

            if(equipment.getBoots().getItemMeta().hasDisplayName()) {
                if(equipment.getBoots().getItemMeta().getDisplayName().equals("§a§lFlying Boots")) {
                    event.setCancelled(true);
                    player.setFlying(!event.isFlying());
                }
            }
        } catch (Exception ignored) {}
    }

    @EventHandler
    public void onBlindnessWandAttack(EntityDamageByEntityEvent event) {
        if(event.getDamager().getType() != EntityType.PLAYER) return;
        Player player = (Player) event.getDamager();
        if(!player.getUniqueId().equals(plugin.getPlayerUUID())) return;
        EntityEquipment equipment = player.getEquipment();
        if(equipment == null) return;
        if(equipment.getItemInMainHand().getType() != Material.STICK) return;
        if(equipment.getItemInMainHand().getItemMeta().hasDisplayName()) {
            if(equipment.getItemInMainHand().getItemMeta().getDisplayName().equals("§a§lBlindness Wand")) {
                if(event.getEntity() instanceof LivingEntity) {
                    LivingEntity entity = (LivingEntity) event.getEntity();
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 1));
                }
            }
        }
    }

    @EventHandler
    public void onLevitationSwordAttack(EntityDamageByEntityEvent event) {
        if(event.getDamager().getType() != EntityType.PLAYER) return;
        Player player = (Player) event.getDamager();
        if(!player.getUniqueId().equals(plugin.getPlayerUUID())) return;
        EntityEquipment equipment = player.getEquipment();
        if(equipment == null) return;
        if(equipment.getItemInMainHand().getType() != Material.GOLDEN_SWORD) return;
        if(equipment.getItemInMainHand().getItemMeta().hasDisplayName()) {
            if(equipment.getItemInMainHand().getItemMeta().getDisplayName().equals("§a§lLevitation Sword")) {
                if(event.getEntity() instanceof LivingEntity) {
                    LivingEntity entity = (LivingEntity) event.getEntity();
                    entity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 100, 1));
                }
            }
        }
    }

    @EventHandler
    public void onFakeTNTPlace(BlockPlaceEvent event) {
        if(!event.getPlayer().getUniqueId().equals(plugin.getPlayerUUID())) return;
        if(event.getBlock().getType() != Material.TNT) return;
        event.setCancelled(true);
        Location start = event.getBlock().getLocation().add(5, 0, 5);
        Location end = event.getBlock().getLocation().add(-5, 0, -5);
        for(int x = start.getBlockX(); x <= end.getBlockX(); x++) {
            for(int y = start.getBlockY(); y <= end.getBlockY(); y++) {
                for(int z = start.getBlockZ(); z <= end.getBlockZ(); z++) {
                    Location location = new Location(start.getWorld(), x, y, z);
                    TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
                    plugin.getIds().add(tnt.getEntityId());
                }
            }
        }
    }

    @EventHandler
    public void onBlockExplode(EntityExplodeEvent event) {
        for (int id : plugin.getIds()) {
            if(event.getEntity().getEntityId() == id) {
                event.setCancelled(true);
                event.blockList().clear();
                event.getLocation().getWorld().playSound(event.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
            }
        }
    }
}
