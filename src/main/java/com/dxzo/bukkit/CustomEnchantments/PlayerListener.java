package com.dxzo.bukkit.CustomEnchantments;

import org.bukkit.scheduler.*;
import org.bukkit.plugin.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;
import java.util.*;
import org.bukkit.event.enchantment.*;
import org.bukkit.metadata.*;
import com.sk89q.worldguard.bukkit.*;
import com.sk89q.worldguard.protection.flags.*;
import org.bukkit.event.entity.*;
import org.bukkit.potion.*;
import org.bukkit.util.Vector;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.block.*;
import org.bukkit.block.*;
import org.bukkit.entity.*;
import net.milkbowl.vault.economy.*;
import org.bukkit.event.player.*;

public class PlayerListener implements Listener
{
    List<Player> stopSpam;
    List<Player> isShootingMinigun;
    List<Player> isUsingBandage;
    List<Player> hasCooldownStaff;
    List<Player> hasAllowFlight;
    List<Player> hasCooldownBlock;
    List<Player> hasCooldownAssassin;
    List<Player> hasCooldownAssassinHit;
    List<Player> hasCooldownPowergloves;
    List<Player> hasCooldownBandage;
    List<Player> hasCooldownShockwave;
    List<Player> hasCooldownDisarmMine;
    List<Player> hasCooldownScythe;
    List<Player> hasCooldownThor;
    List<Player> hasCooldownMedikit;
    List<Player> hasCooldownBleed;
    List<Player> hasCooldownVampire;
    List<Player> hasBeasts;
    List<Player> hasWolves;
    List<Player> hasGolem;
    List<Player> hasCrypt;
    List<Player> hasHermesBoots;
    List<Player> hasSwimSuit;
    List<Player> CarryingPlayer;
    List<Player> isCarryingPlayer;
    List<Player> isReloadingFlamethrower;
    List<Player> isReloadingRocketboots;
    List<Player> isBleeding;
    List<Player> AssassinInvisible;

    public PlayerListener() {
        this.stopSpam = new ArrayList<Player>();
        this.isShootingMinigun = new ArrayList<Player>();
        this.isUsingBandage = new ArrayList<Player>();
        this.hasCooldownStaff = new ArrayList<Player>();
        this.hasAllowFlight = new ArrayList<Player>();
        this.hasCooldownBlock = new ArrayList<Player>();
        this.hasCooldownAssassin = new ArrayList<Player>();
        this.hasCooldownAssassinHit = new ArrayList<Player>();
        this.hasCooldownPowergloves = new ArrayList<Player>();
        this.hasCooldownBandage = new ArrayList<Player>();
        this.hasCooldownShockwave = new ArrayList<Player>();
        this.hasCooldownDisarmMine = new ArrayList<Player>();
        this.hasCooldownScythe = new ArrayList<Player>();
        this.hasCooldownThor = new ArrayList<Player>();
        this.hasCooldownMedikit = new ArrayList<Player>();
        this.hasCooldownBleed = new ArrayList<Player>();
        this.hasCooldownVampire = new ArrayList<Player>();
        this.hasBeasts = new ArrayList<Player>();
        this.hasWolves = new ArrayList<Player>();
        this.hasGolem = new ArrayList<Player>();
        this.hasCrypt = new ArrayList<Player>();
        this.hasHermesBoots = new ArrayList<Player>();
        this.hasSwimSuit = new ArrayList<Player>();
        this.CarryingPlayer = new ArrayList<Player>();
        this.isCarryingPlayer = new ArrayList<Player>();
        this.isReloadingFlamethrower = new ArrayList<Player>();
        this.isReloadingRocketboots = new ArrayList<Player>();
        this.isBleeding = new ArrayList<Player>();
        this.AssassinInvisible = new ArrayList<Player>();
    }

    public void Cooldown(final Player player, final List<Player> list, final int duration) {
        final Plugin main = Main.plugin;
        list.add(player);
        new BukkitRunnable() {
            public void run() {
                if (PlayerListener.this.stopSpam.contains(player)) {
                    PlayerListener.this.stopSpam.remove(player);
                }
                list.remove(player);
                this.cancel();
            }
        }.runTaskTimer(main, (long)duration, 0L);
    }

    public void allowFlight(final Player player) {
        if (!player.getGameMode().equals((Object)GameMode.SURVIVAL)) {
            return;
        }
        if (this.hasAllowFlight.contains(player)) {
            return;
        }
        this.hasAllowFlight.add(player);
        final Plugin main = Main.plugin;
        new BukkitRunnable() {
            public void run() {
                final Location loc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1.0, player.getLocation().getZ());
                if (player.isFlying()) {
                    player.setFlying(false);
                }
                if (loc.getBlock().getType().equals((Object)Material.AIR)) {
                    player.setAllowFlight(true);
                }
                else {
                    player.setAllowFlight(false);
                    PlayerListener.this.hasAllowFlight.remove(player);
                    this.cancel();
                }
            }
        }.runTaskTimer(main, 0L, 1L);
    }

    public String getItemName(final String item, final ItemStack i) {
        String name = ChatColor.stripColor(item);
        if (item.equals("Hookshot")) {
            name = ChatColor.AQUA + "Hookshot Bow";
        }
        else if (item.equals("Rocketboots")) {
            name = ChatColor.AQUA + "Rocket Boots";
        }
        else if (item.equals("Necromancer")) {
            name = ChatColor.AQUA + "Necromancer's Staff of Destruction";
        }
        else if (item.equals("Healingshovel")) {
            name = ChatColor.GREEN + "Minor Healing Shovel";
        }
        else if (item.equals("Livefire")) {
            name = ChatColor.DARK_RED + "Livefire Boots";
        }
        else if (item.equals("Pyroaxe")) {
            name = ChatColor.DARK_RED + "Pyro Axe";
        }
        else if (item.equals("Thoraxe")) {
            name = ChatColor.GOLD + "Thor's Axe";
        }
        else if (item.equals("Deathscythe")) {
            name = ChatColor.DARK_GRAY + "Death's Scythe";
        }
        else if (item.equals("Suicidelever")) {
            name = ChatColor.GRAY + "Suicide Lever";
        }
        else if (item.equals("Powergloves")) {
            name = ChatColor.AQUA + "Power Gloves";
        }
        else if (item.equals("Potionlauncher")) {
            name = ChatColor.GRAY + "Potion Launcher";
        }
        else if (item.equals("Druidboots")) {
            name = ChatColor.DARK_GREEN + "Druid's Boots";
        }
        else if (item.equals("Hermesboots")) {
            name = ChatColor.GOLD + "Hermes Boots";
        }
        else if (item.equals("Rocketboots")) {
            name = ChatColor.AQUA + "Rocket Boots";
        }
        else if (item.equals("Piranhatrap")) {
            name = ChatColor.GRAY + "Piranha Trap";
        }
        else if (item.equals("Prickly")) {
            name = ChatColor.LIGHT_PURPLE + "Prickly Block";
        }
        else if (item.equals("Prickly")) {
            name = ChatColor.DARK_GREEN + "Poison Ivy";
        }
        else if (item.equals("Beastmaster")) {
            name = ChatColor.AQUA + "Beastmaster's Bow";
        }
        else if (item.equals("Assassin")) {
            name = ChatColor.AQUA + "Assassin's Blade";
        }
        else if (item.equals("Swimsuit")) {
            if (i.getType().name().endsWith("HELMET")) {
                name = ChatColor.BLUE + "Scuba Mask";
            }
            if (i.getType().name().endsWith("CHESTPLATE")) {
                name = ChatColor.BLUE + "Upper Swimsuit";
            }
            if (i.getType().name().endsWith("LEGGINGS")) {
                name = ChatColor.BLUE + "Lower Swimsuit";
            }
            if (i.getType().name().endsWith("BOOTS")) {
                name = ChatColor.BLUE + "Flippers";
            }
        }
        return name;
    }

    public static String getEnchantmentName(final String i) {
        final String item = ChatColor.stripColor(i);
        String loreToAdd = i;
        if (item.equalsIgnoreCase("Obsidianshield")) {
            loreToAdd = String.valueOf(Main.lorePrefix) + "Obsidian Shielding";
        }
        else if (item.equalsIgnoreCase("Autorepair")) {
            loreToAdd = String.valueOf(Main.lorePrefix) + "Automatic Repair";
        }
        else if (item.equalsIgnoreCase("Ice")) {
            loreToAdd = String.valueOf(Main.lorePrefix) + "Ice Aspect";
        }
        else if (item.equalsIgnoreCase("Cripple")) {
            loreToAdd = String.valueOf(Main.lorePrefix) + "Crippling Strike";
        }
        else if (item.equalsIgnoreCase("Thunderingblow")) {
            loreToAdd = String.valueOf(Main.lorePrefix) + "Thundering Blow";
        }
        else if (item.equalsIgnoreCase("Deepwounds")) {
            loreToAdd = String.valueOf(Main.lorePrefix) + "Deep Wounds";
        }
        else {
            loreToAdd = String.valueOf(Main.lorePrefix) + loreToAdd;
        }
        return loreToAdd;
    }

    public List<String> addItemLore(final String item, final List<String> lore) {
        final List<String> b = lore;
        if (item.equals("Hookshot")) {
            b.add(ChatColor.DARK_GRAY + "Mode: Push");
        }
        else if (item.equals("Rocketboots")) {
            b.add(ChatColor.DARK_GRAY + "Power: " + ChatColor.RED + ChatColor.ITALIC + "OFFLINE");
        }
        else if (item.equals("Necromancer")) {
            b.add(ChatColor.DARK_PURPLE + "Spell: Fireball");
        }
        return lore;
    }

    public int Positive(final int i) {
        if (i < 0) {
            final int b = i * -1;
            return b;
        }
        return i;
    }

    public static String getPlayerDirection(final Player player) {
        double rotation = (player.getLocation().getYaw() - 90.0f) % 360.0f;
        if (rotation < 0.0) {
            rotation += 360.0;
        }
        if (0.0 <= rotation && rotation < 22.5) {
            return "W";
        }
        if (22.5 <= rotation && rotation < 67.5) {
            return "NW";
        }
        if (67.5 <= rotation && rotation < 112.5) {
            return "N";
        }
        if (112.5 <= rotation && rotation < 157.5) {
            return "NE";
        }
        if (157.5 <= rotation && rotation < 202.5) {
            return "E";
        }
        if (202.5 <= rotation && rotation < 247.5) {
            return "SE";
        }
        if (247.5 <= rotation && rotation < 292.5) {
            return "S";
        }
        if (292.5 <= rotation && rotation < 337.5) {
            return "SW";
        }
        if (337.5 <= rotation && rotation < 360.0) {
            return "W";
        }
        return null;
    }

    public boolean isItem(final String s) {
        for (final String item : Main.items) {
            if (ChatColor.stripColor(item).equalsIgnoreCase(ChatColor.stripColor(s))) {
                return true;
            }
        }
        return false;
    }

    public int getItemIndex(final String s) {
        for (int i = 0; i < Main.items.size(); ++i) {
            if (ChatColor.stripColor((String)Main.items.get(i)).equalsIgnoreCase(ChatColor.stripColor(s))) {
                return i;
            }
        }
        return 0;
    }

    public List<Location> getLinePlayer(final Player player, final int length) {
        final List<Location> list = new ArrayList<Location>();
        for (int amount = length; amount > 0; --amount) {
            list.add(player.getTargetBlock((HashSet<Byte>)null, amount).getLocation());
        }
        return list;
    }

    public List<Location> getCone(final Location loc, final String direction) {
        final List<Location> locs = new ArrayList<Location>();
        final Location loc2 = loc.clone();
        final Location loc3 = loc.clone();
        Location loc4 = loc.clone();
        if (direction.equals("N")) {
            loc2.setZ(loc.getZ() - 1.0);
            locs.add(loc2);
            loc3.setZ(loc.getZ() - 2.0);
            locs.add(loc3);
            loc4.setZ(loc.getZ() - 3.0);
            locs.add(loc4);
            final Location loc5 = loc3.clone();
            final Location loc6 = loc3.clone();
            final Location loc7 = loc4.clone();
            final Location loc8 = loc4.clone();
            final Location loc9 = loc4.clone();
            final Location loc10 = loc4.clone();
            loc5.setX(loc3.getX() - 1.0);
            locs.add(loc5);
            loc6.setX(loc3.getX() + 1.0);
            locs.add(loc6);
            loc7.setX(loc4.getX() + 2.0);
            locs.add(loc7);
            loc8.setX(loc4.getX() + 1.0);
            locs.add(loc8);
            loc9.setX(loc4.getX() - 1.0);
            locs.add(loc9);
            loc10.setX(loc4.getX() - 2.0);
            locs.add(loc10);
        }
        else if (direction.equals("S")) {
            loc2.setZ(loc.getZ() + 1.0);
            locs.add(loc2);
            loc3.setZ(loc.getZ() + 2.0);
            locs.add(loc3);
            loc4.setZ(loc.getZ() + 3.0);
            locs.add(loc4);
            final Location loc5 = loc3.clone();
            final Location loc6 = loc3.clone();
            final Location loc7 = loc4.clone();
            final Location loc8 = loc4.clone();
            final Location loc9 = loc4.clone();
            final Location loc10 = loc4.clone();
            loc5.setX(loc3.getX() - 1.0);
            locs.add(loc5);
            loc6.setX(loc3.getX() + 1.0);
            locs.add(loc6);
            loc7.setX(loc4.getX() + 2.0);
            locs.add(loc7);
            loc8.setX(loc4.getX() + 1.0);
            locs.add(loc8);
            loc9.setX(loc4.getX() - 1.0);
            locs.add(loc9);
            loc10.setX(loc4.getX() - 2.0);
            locs.add(loc10);
        }
        else if (direction.equals("E")) {
            loc2.setX(loc.getX() + 1.0);
            locs.add(loc2);
            loc3.setX(loc2.getX() + 1.0);
            locs.add(loc3);
            loc4.setX(loc3.getX() + 1.0);
            locs.add(loc4);
            final Location loc5 = loc3.clone();
            final Location loc6 = loc3.clone();
            final Location loc7 = loc4.clone();
            final Location loc8 = loc4.clone();
            final Location loc9 = loc4.clone();
            final Location loc10 = loc4.clone();
            loc5.setZ(loc3.getZ() - 1.0);
            locs.add(loc5);
            loc6.setZ(loc3.getZ() + 1.0);
            locs.add(loc6);
            loc7.setZ(loc4.getZ() + 2.0);
            locs.add(loc7);
            loc8.setZ(loc4.getZ() + 1.0);
            locs.add(loc8);
            loc9.setZ(loc4.getZ() - 1.0);
            locs.add(loc9);
            loc10.setZ(loc4.getZ() - 2.0);
            locs.add(loc10);
        }
        else if (direction.equals("W")) {
            loc2.setX(loc.getX() - 1.0);
            locs.add(loc2);
            loc3.setX(loc2.getX() - 1.0);
            locs.add(loc3);
            loc4.setX(loc3.getX() - 1.0);
            locs.add(loc4);
            final Location loc5 = loc3.clone();
            final Location loc6 = loc3.clone();
            final Location loc7 = loc4.clone();
            final Location loc8 = loc4.clone();
            final Location loc9 = loc4.clone();
            final Location loc10 = loc4.clone();
            loc5.setZ(loc3.getZ() - 1.0);
            locs.add(loc5);
            loc6.setZ(loc3.getZ() + 1.0);
            locs.add(loc6);
            loc7.setZ(loc4.getZ() + 2.0);
            locs.add(loc7);
            loc8.setZ(loc4.getZ() + 1.0);
            locs.add(loc8);
            loc9.setZ(loc4.getZ() - 1.0);
            locs.add(loc9);
            loc10.setZ(loc4.getZ() - 2.0);
            locs.add(loc10);
        }
        else if (direction.equals("NW")) {
            loc2.setZ(loc.getZ() - 1.0);
            loc2.setX(loc.getX() - 1.0);
            locs.add(loc2);
            loc3.setZ(loc.getZ() - 2.0);
            loc3.setX(loc.getX() - 2.0);
            locs.add(loc3);
            loc4 = loc2.clone();
            loc4.setZ(loc2.getZ() - 1.0);
            locs.add(loc4);
            final Location loc5 = loc2.clone();
            final Location loc6 = loc2.clone();
            final Location loc7 = loc2.clone();
            loc5.setZ(loc2.getZ() - 2.0);
            locs.add(loc5);
            loc6.setX(loc2.getX() - 1.0);
            locs.add(loc6);
            loc7.setX(loc2.getX() - 2.0);
            locs.add(loc7);
        }
        else if (direction.equals("NE")) {
            loc2.setZ(loc.getZ() - 1.0);
            loc2.setX(loc.getX() + 1.0);
            locs.add(loc2);
            loc3.setZ(loc.getZ() - 2.0);
            loc3.setX(loc.getX() + 2.0);
            locs.add(loc3);
            loc4 = loc2.clone();
            loc4.setZ(loc2.getZ() - 1.0);
            locs.add(loc4);
            final Location loc5 = loc2.clone();
            final Location loc6 = loc2.clone();
            final Location loc7 = loc2.clone();
            loc5.setZ(loc2.getZ() - 2.0);
            locs.add(loc5);
            loc6.setX(loc2.getX() + 1.0);
            locs.add(loc6);
            loc7.setX(loc2.getX() + 2.0);
            locs.add(loc7);
        }
        else if (direction.equals("SW")) {
            loc2.setZ(loc.getZ() + 1.0);
            loc2.setX(loc.getX() - 1.0);
            locs.add(loc2);
            loc3.setZ(loc.getZ() + 2.0);
            loc3.setX(loc.getX() - 2.0);
            locs.add(loc3);
            loc4 = loc2.clone();
            final Location loc5 = loc2.clone();
            final Location loc6 = loc2.clone();
            final Location loc7 = loc2.clone();
            loc4.setZ(loc2.getZ() + 1.0);
            locs.add(loc4);
            loc5.setZ(loc2.getZ() + 2.0);
            locs.add(loc5);
            loc6.setX(loc2.getX() - 1.0);
            locs.add(loc6);
            loc7.setX(loc2.getX() - 2.0);
            locs.add(loc7);
        }
        else if (direction.equals("SE")) {
            loc2.setZ(loc.getZ() + 1.0);
            loc2.setX(loc.getX() + 1.0);
            locs.add(loc2);
            loc3.setZ(loc.getZ() + 2.0);
            loc3.setX(loc.getX() + 2.0);
            locs.add(loc3);
            loc4 = loc2.clone();
            loc4.setZ(loc2.getZ() + 1.0);
            locs.add(loc4);
            final Location loc5 = loc2.clone();
            final Location loc6 = loc2.clone();
            final Location loc7 = loc2.clone();
            loc5.setZ(loc2.getZ() + 2.0);
            locs.add(loc5);
            loc6.setX(loc2.getX() + 1.0);
            locs.add(loc6);
            loc7.setX(loc2.getX() + 2.0);
            locs.add(loc7);
        }
        return locs;
    }

    @EventHandler
    public void antiArrowSpam(final ProjectileHitEvent event) {
        if (event.getEntityType().equals((Object)EntityType.ARROW)) {
            final Arrow arrow = (Arrow)event.getEntity();
            if (arrow.getShooter() instanceof Player && event.getEntity().hasMetadata("minigunarrow") && ((Player)arrow.getShooter()).getGameMode().equals((Object)GameMode.CREATIVE)) {
                arrow.remove();
            }
        }
    }

    public Color fireworkColor(final int i) {
        switch (i) {
            default: {
                return Color.SILVER;
            }
            case 2: {
                return Color.AQUA;
            }
            case 3: {
                return Color.BLACK;
            }
            case 4: {
                return Color.BLUE;
            }
            case 5: {
                return Color.FUCHSIA;
            }
            case 6: {
                return Color.GRAY;
            }
            case 7: {
                return Color.GREEN;
            }
            case 8: {
                return Color.LIME;
            }
            case 9: {
                return Color.MAROON;
            }
            case 10: {
                return Color.NAVY;
            }
            case 11: {
                return Color.OLIVE;
            }
            case 12: {
                return Color.ORANGE;
            }
            case 13: {
                return Color.PURPLE;
            }
            case 14: {
                return Color.RED;
            }
            case 15: {
                return Color.YELLOW;
            }
            case 16: {
                return Color.TEAL;
            }
        }
    }

    public Firework shootFirework(final Location loc, final Random rand) {
        final int type = rand.nextInt(5) + 1;
        final Firework firework = (Firework)loc.getWorld().spawn(loc, (Class)Firework.class);
        final FireworkMeta meta = firework.getFireworkMeta();
        FireworkEffect.Type ft = null;
        switch (type) {
            case 1: {
                ft = FireworkEffect.Type.BALL;
                break;
            }
            case 2: {
                ft = FireworkEffect.Type.BALL_LARGE;
                break;
            }
            case 3: {
                ft = FireworkEffect.Type.BURST;
                break;
            }
            case 4: {
                ft = FireworkEffect.Type.CREEPER;
                break;
            }
            case 5: {
                ft = FireworkEffect.Type.STAR;
                break;
            }
        }
        final Random rand2 = new Random();
        final FireworkEffect effect = FireworkEffect.builder().flicker(rand.nextBoolean()).withColor(this.fireworkColor(rand.nextInt(16) + 1)).withFade(this.fireworkColor(rand.nextInt(16) + 1)).trail(rand.nextBoolean()).with(ft).trail(rand.nextBoolean()).build();
        meta.addEffect(effect);
        firework.setFireworkMeta(meta);
        return firework;
    }

    @EventHandler
    public void onEnchant(final EnchantItemEvent event) {
        final String lorePrefix = Main.lorePrefix;
        if (event.getExpLevelCost() == 30) {
            final Plugin main = Main.plugin;
            if (!main.getConfig().get("enchantmentTable").equals("enabled") || !main.getConfig().contains("enchantmentTable")) {
                return;
            }
            final Random r = new Random();
            final int prob = r.nextInt(100) + 1;
            if (prob >= main.getConfig().getInt("enchantmentTableProbability")) {
                final Random r2 = new Random();
                final int cProb = r2.nextInt(100) + 1;
                final Player player = event.getEnchanter();
                final int a = main.getConfig().getInt("enchantments.Blind.enchantProbability");
                final int b = main.getConfig().getInt("enchantments.Lifesteal.enchantProbability");
                final int c = main.getConfig().getInt("enchantments.Deathbringer.enchantProbability");
                final int d = main.getConfig().getInt("enchantments.Poison.enchantProbability");
                final int es = main.getConfig().getInt("enchantments.Gooey.enchantProbability");
                final int f = main.getConfig().getInt("enchantments.Block.enchantProbability");
                final int g = main.getConfig().getInt("enchantments.Ice.enchantProbability");
                final int h = main.getConfig().getInt("enchantments.Shockwave.enchantProbability");
                final int i = main.getConfig().getInt("enchantments.BombardmentBow.enchantProbability");
                final int j = main.getConfig().getInt("enchantments.LightningBow.enchantProbability");
                final int k = main.getConfig().getInt("enchantments.PoisonBow.enchantProbability");
                final int l = main.getConfig().getInt("enchantments.FireworkBow.enchantProbability");
                final int o = main.getConfig().getInt("enchantments.Poisoned.enchantProbability");
                final int p = main.getConfig().getInt("enchantments.Frozen.enchantProbability");
                final int q = main.getConfig().getInt("enchantments.Enlighted.enchantProbability");
                final int rs = main.getConfig().getInt("enchantments.Hardened.enchantProbability");
                final int s = main.getConfig().getInt("enchantments.Molten.enchantProbability");
                final int t = main.getConfig().getInt("enchantments.Obsidianshielding.enchantProbability");
                final int u = main.getConfig().getInt("enchantments.Glowing.enchantProbability");
                final int v = main.getConfig().getInt("enchantments.Implants.enchantProbability");
                final int w = main.getConfig().getInt("enchantments.Gears.enchantProbability");
                final int x = main.getConfig().getInt("enchantments.Springs.enchantProbability");
                final int y = main.getConfig().getInt("enchantments.Stomp.enchantProbability");
                final int z = main.getConfig().getInt("enchantments.Hooks.enchantProbability");
                final int a2 = main.getConfig().getInt("enchantments.Autorepair.enchantProbability");
                if ((event.getItem().getType().name().endsWith("SWORD") || event.getItem().getType().equals((Object)Material.BOW) || event.getItem().getType().name().endsWith("CHESTPLATE") || event.getItem().getType().name().endsWith("BOOTS") || event.getItem().getType().name().endsWith("LEGGINGS") || event.getItem().getType().name().endsWith("HELMET")) && cProb <= a2) {
                    List<String> lore = null;
                    try {
                        lore = (List<String>)event.getItem().getItemMeta().getLore();
                        lore.add(String.valueOf(lorePrefix) + "Automatic Repair");
                    }
                    catch (Exception e) {
                        lore = new ArrayList<String>();
                        lore.add(String.valueOf(lorePrefix) + "Automatic");
                    }
                    Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                }
                if (event.getItem().getType().name().endsWith("SWORD")) {
                    if (cProb <= a) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Blind");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Blind");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb <= a + b && cProb > a) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Lifesteal");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Lifesteal");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb <= a + b + c && cProb > a + b) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Deathbringer");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Deathbringer");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb <= a + b + c + d && cProb > a + b + c) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Poison");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Poison");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb <= a + b + c + d + es && cProb > a + b + c + d) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Gooey");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Gooey");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb <= a + b + c + d + es + f && cProb > a + b + c + d + es) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Block");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Block");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb <= a + b + c + d + es + f + g && cProb > a + b + c + d + es + f) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Ice Aspect");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Ice aspect");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb <= a + b + c + d + es + f + g + h && cProb > a + b + c + d + es + f + h) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Shockwave");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Shockwave");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                }
                else if (event.getItem().getType().equals((Object)Material.BOW)) {
                    if (cProb <= l) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Firework");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Firework");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    if (cProb <= l + i && cProb > l) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Bombardment");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Bombardment");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    if (cProb <= l + i + j && cProb > l + i) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Lightning");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Lightning");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    if (cProb <= l + i + j + k && cProb > l + i + j) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Poison");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Poison");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                }
                else if (event.getItem().getType().name().endsWith("CHESTPLATE") || event.getItem().getType().name().endsWith("BOOTS") || event.getItem().getType().name().endsWith("LEGGINGS") || event.getItem().getType().name().endsWith("HELMET")) {
                    if (cProb <= o) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Poisoned");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Poisoned");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb <= o + p && cProb > o) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Frozen");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Frozen");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb < o + p + q && cProb > o + p) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Enlighted");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Enlighted");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb < o + p + q + rs && cProb > o + p + q) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Hardened");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Hardened");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb < o + p + q + rs + s && cProb >= o + p + q + rs) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Molten");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Molten");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    else if (cProb < o + p + q + rs + s + t && cProb >= o + p + q + rs + s) {
                        List<String> lore = null;
                        try {
                            lore = (List<String>)event.getItem().getItemMeta().getLore();
                            lore.add(String.valueOf(lorePrefix) + "Obsidian Shielding");
                        }
                        catch (Exception e) {
                            lore = new ArrayList<String>();
                            lore.add(String.valueOf(lorePrefix) + "Obsidian Shielding");
                        }
                        Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                    }
                    if (event.getItem().getType().name().endsWith("HELMET")) {
                        if (cProb <= u) {
                            List<String> lore = null;
                            try {
                                lore = (List<String>)event.getItem().getItemMeta().getLore();
                                lore.add(String.valueOf(lorePrefix) + "Glowing");
                            }
                            catch (Exception e) {
                                lore = new ArrayList<String>();
                                lore.add(String.valueOf(lorePrefix) + "Glowing");
                            }
                            Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                            player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                        }
                        else if (cProb <= u + v && cProb > u) {
                            List<String> lore = null;
                            try {
                                lore = (List<String>)event.getItem().getItemMeta().getLore();
                                lore.add(String.valueOf(lorePrefix) + "Implants");
                            }
                            catch (Exception e) {
                                lore = new ArrayList<String>();
                                lore.add(String.valueOf(lorePrefix) + "Implants");
                            }
                            Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                            player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                        }
                    }
                    if (event.getItem().getType().name().endsWith("BOOTS")) {
                        if (cProb <= w) {
                            List<String> lore = null;
                            try {
                                lore = (List<String>)event.getItem().getItemMeta().getLore();
                                lore.add(String.valueOf(lorePrefix) + "Gears");
                            }
                            catch (Exception e) {
                                lore = new ArrayList<String>();
                                lore.add(String.valueOf(lorePrefix) + "Gears");
                            }
                            Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                            player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                        }
                        else if (cProb <= w + x && cProb > w) {
                            List<String> lore = null;
                            try {
                                lore = (List<String>)event.getItem().getItemMeta().getLore();
                                lore.add(String.valueOf(lorePrefix) + "Springs");
                            }
                            catch (Exception e) {
                                lore = new ArrayList<String>();
                                lore.add(String.valueOf(lorePrefix) + "Springs");
                            }
                            Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                            player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                        }
                        else if (cProb <= w + x + y && cProb > w + x) {
                            List<String> lore = null;
                            try {
                                lore = (List<String>)event.getItem().getItemMeta().getLore();
                                lore.add(String.valueOf(lorePrefix) + "Stomp");
                            }
                            catch (Exception e) {
                                lore = new ArrayList<String>();
                                lore.add(String.valueOf(lorePrefix) + "Stomp");
                            }
                            Main.setName(event.getItem(), event.getItem().getItemMeta().getDisplayName(), lore);
                            player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                        }
                    }
                }
            }
        }
    }

    public List<Location> getSurroundingBlocks(final Location l) {
        final List<Location> ls = new ArrayList<Location>();
        ls.add(l.getBlock().getRelative(0, 1, 0).getLocation());
        ls.add(l.getBlock().getRelative(1, 0, 0).getLocation());
        ls.add(l.getBlock().getRelative(0, 0, 1).getLocation());
        ls.add(l.getBlock().getRelative(0, 0, -1).getLocation());
        ls.add(l.getBlock().getRelative(-1, 0, 0).getLocation());
        return ls;
    }

    @EventHandler
    public void onArrowShot(final EntityShootBowEvent event) {
        final String lorePrefix = Main.lorePrefix;
        if (event.getEntity() instanceof Player) {
            final Player shooter = (Player)event.getEntity();
            if (event.getBow().getItemMeta().hasDisplayName() && event.getBow().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Minigun")) {
                final Plugin main = Main.plugin;
                if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Minigun")) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.Minigun.enabled")) {
                    return;
                }
                final Random random = new Random();
                event.setCancelled(true);
                if (!this.isShootingMinigun.contains(shooter)) {
                    this.isShootingMinigun.add(shooter);
                    new BukkitRunnable() {
                        int arrowcount = Main.plugin.getConfig().getInt("items.Minigun.shotsFired");

                        public void run() {
                            if (this.arrowcount > 0) {
                                if (shooter.getItemInHand().hasItemMeta() && shooter.getItemInHand().getItemMeta().hasDisplayName() && shooter.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Minigun")) {
                                    if (shooter.getInventory().contains(Material.ARROW, 1)) {
                                        if (!shooter.getGameMode().equals((Object)GameMode.CREATIVE)) {
                                            if (event.getBow().getDurability() < 380) {
                                                event.getBow().setDurability((short)(event.getBow().getDurability() + 1));
                                            }
                                            else {
                                                final ItemStack brokenItem = new ItemStack(Material.AIR);
                                                shooter.setItemInHand(brokenItem);
                                                shooter.getWorld().playEffect(shooter.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
                                                shooter.getWorld().playSound(shooter.getLocation(), Sound.ITEM_BREAK, 10.0f, 0.0f);
                                                PlayerListener.this.isShootingMinigun.remove(shooter);
                                                this.cancel();
                                            }
                                            final ItemStack arrows = new ItemStack(Material.ARROW, 1);
                                            shooter.getInventory().removeItem(new ItemStack[] { arrows });
                                        }
                                        final Arrow arrow = (Arrow)event.getEntity().launchProjectile((Class)Arrow.class);
                                        arrow.setBounce(false);
                                        arrow.setVelocity(event.getEntity().getLocation().getDirection().multiply(5));
                                        arrow.setShooter(event.getEntity());
                                        event.getEntity().getWorld().playEffect(event.getEntity().getLocation(), Effect.BOW_FIRE, 20);
                                        final MetadataValue minigun = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                                        arrow.setMetadata("minigunarrow", minigun);
                                        --this.arrowcount;
                                    }
                                    else {
                                        shooter.sendMessage(ChatColor.RED + "Out of ammo!");
                                        event.getEntity().getWorld().playEffect(event.getEntity().getLocation(), Effect.CLICK2, 80);
                                        PlayerListener.this.isShootingMinigun.remove(shooter);
                                        this.cancel();
                                    }
                                }
                                else {
                                    PlayerListener.this.isShootingMinigun.remove(shooter);
                                    this.cancel();
                                }
                            }
                            else {
                                PlayerListener.this.isShootingMinigun.remove(shooter);
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(main, 0L, 1L);
                }
            }
            if (event.getBow().getItemMeta().hasLore()) {
                // if (event.getBow().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Firework") || event.getBow().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Fireworks")) {
                //     final Plugin main = Main.plugin;
                //     if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Firework")) {
                //         return;
                //     }
                //     if (!main.getConfig().getBoolean("enchantments.FireworkBow.enabled")) {
                //         return;
                //     }
                //     final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                //     event.getProjectile().setMetadata("fireworkarrow", meta);
                //     new BukkitRunnable(main) {
                //         int fireworkarrowaliveTime = plugin.getConfig().getInt("enchantments.FireworkBow.Duration") * 20;

                //         public void run() {
                //             if (this.fireworkarrowaliveTime > 0) {
                //                 final Location loc = event.getProjectile().getLocation();
                //                 if (!event.getProjectile().isDead()) {
                //                     PlayerListener.this.shootFirework(loc, new Random());
                //                     --this.fireworkarrowaliveTime;
                //                 }
                //             }
                //             else {
                //                 this.cancel();
                //             }
                //         }
                //     }.runTaskTimer(main, 0L, main.getConfig().getLong("enchantments.FireworkBow.Delay"));
                // }
                if (shooter.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Lightning")) {
                    final Plugin main = Main.plugin;
                    if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Lightning")) {
                        return;
                    }
                    if (!main.getConfig().getBoolean("enchantments.LightningBow.enabled")) {
                        return;
                    }
                    final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                    event.getProjectile().setMetadata("lightningarrow", meta);
                }
                if (shooter.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Bombardment")) {
                    final Plugin main = Main.plugin;
                    if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Bombardment")) {
                        return;
                    }
                    if (!main.getConfig().getBoolean("enchantments.BombardmentBow.enabled")) {
                        return;
                    }
                    final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                    event.getProjectile().setMetadata("bombardmentarrow", meta);
                }
                if (shooter.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Blind")) {
                    final Plugin main = Main.plugin;
                    if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Blind")) {
                        return;
                    }
                    if (!main.getConfig().getBoolean("enchantments.BlindBow.enabled")) {
                        return;
                    }
                    final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                    event.getProjectile().setMetadata("blindarrow", meta);
                }
                if (shooter.getItemInHand().getItemMeta().hasDisplayName()) {
                    if (shooter.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "Bow of the Crypt")) {
                        final Plugin main = Main.plugin;
                        if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Crypt")) {
                            return;
                        }
                        final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                        event.getProjectile().setMetadata("cryptarrow", meta);
                    }
                    if (shooter.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Savage Fang")) {
                        final Plugin main = Main.plugin;
                        if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Crypt")) {
                            return;
                        }
                        final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                        event.getProjectile().setMetadata("savagearrow", meta);
                    }
                    if (shooter.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Iron Fury")) {
                        final Plugin main = Main.plugin;
                        if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.IronFury")) {
                            return;
                        }
                        final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                        event.getProjectile().setMetadata("ironfuryarrow", meta);
                    }
                    if (shooter.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Beastmaster's Bow")) {
                        final Plugin main = Main.plugin;
                        if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Beastmaster")) {
                            return;
                        }
                        if (!main.getConfig().getBoolean("items.BeastmastersBow.enabled")) {
                            return;
                        }
                        final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                        event.getProjectile().setMetadata("beastmasterarrow", meta);
                    }
                    if (shooter.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Hookshot Bow")) {
                        final Plugin main = Main.plugin;
                        if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Hookshot")) {
                            return;
                        }
                        if (!main.getConfig().getBoolean("items.HookshotBow.enabled")) {
                            return;
                        }
                        final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                        event.getProjectile().setMetadata("hookshotarrow", meta);
                    }
                    if (shooter.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Spider's Bite")) {
                        final Plugin main = Main.plugin;
                        if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Spider")) {
                            return;
                        }
                        final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                        event.getProjectile().setMetadata("poisonarrow", meta);
                    }
                    if (shooter.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Serpent's Sting")) {
                        final Plugin main = Main.plugin;
                        if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Serpent")) {
                            return;
                        }
                        final MetadataValue meta = (MetadataValue)new FixedMetadataValue(main, (Object)true);
                        event.getProjectile().setMetadata("serpentarrow", meta);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamaged(final EntityDamageEvent event) {
        if (Main.hasWorldguard && ((WorldGuardPlugin)Main.pl).getRegionManager(event.getEntity().getWorld()).getApplicableRegions(event.getEntity().getLocation()) != null && !((WorldGuardPlugin)Main.pl).getRegionManager(event.getEntity().getWorld()).getApplicableRegions(event.getEntity().getLocation()).allows(DefaultFlag.PVP)) {
            return;
        }
        if (event.getEntity() instanceof Player) {
            final Player player = (Player)event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL && player.getEquipment().getBoots() != null && player.getEquipment().getBoots().hasItemMeta() && player.getEquipment().getBoots().getItemMeta().hasLore()) {
                if (player.getEquipment().getBoots().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Stomp")) {
                    if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !player.hasPermission("customenchantment.Stomp")) {
                        return;
                    }
                    if (!Main.plugin.getConfig().getBoolean("enchantments.Stomp.enabled")) {
                        return;
                    }
                    if (event.getDamage() > 0.0) {
                        final List<Entity> entities = (List<Entity>)player.getNearbyEntities(0.0, 0.0, 0.0);
                        if (!entities.isEmpty()) {
                            for (final Entity ent : entities) {
                                if (ent instanceof LivingEntity) {
                                    ((LivingEntity)ent).damage((double)(int)(event.getDamage() / 2.0), (Entity)player);
                                }
                            }
                            player.getWorld().playEffect(player.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
                            player.getWorld().playSound(player.getLocation(), Sound.EXPLODE, 1.0f, 4.0f);
                            player.damage((double)(int)(event.getDamage() / 4.0));
                            event.setCancelled(true);
                        }
                    }
                }
                else if (player.getEquipment().getBoots().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Springs")) {
                    if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !player.hasPermission("customenchantment.Springs")) {
                        return;
                    }
                    if (!Main.plugin.getConfig().getBoolean("enchantments.Springs.enabled")) {
                        return;
                    }
                    if (event.getDamage() > 0.0) {
                        if (event.getDamage() - 10.0 <= 0.0) {
                            event.setDamage(0.0);
                        }
                        else {
                            event.setDamage(event.getDamage() - 10.0);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onHit(final EntityDamageByEntityEvent event) {
        final String lorePrefix = Main.lorePrefix;
        final Plugin main = Main.plugin;
        if (!event.isCancelled()) {
            if (event.getEntity() instanceof Player && Main.hasWorldguard && ((WorldGuardPlugin)Main.pl).getRegionManager(event.getEntity().getWorld()).getApplicableRegions(event.getEntity().getLocation()) != null && !((WorldGuardPlugin)Main.pl).getRegionManager(event.getEntity().getWorld()).getApplicableRegions(event.getEntity().getLocation()).allows(DefaultFlag.PVP)) {
                return;
            }
            if (event.getDamager() instanceof Player) {
                final Player damager = (Player)event.getDamager();
                if (damager.getItemInHand().hasItemMeta()) {
                    if (damager.getItemInHand().getItemMeta().hasLore()) {
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Blind")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Blind")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Blind.enabled")) {
                                return;
                            }
                            if (event.getEntity() instanceof Player && event.getDamage() > 0.0) {
                                final Player damaged = (Player)event.getEntity();
                                damaged.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * main.getConfig().getInt("enchantments.Blind.duration"), main.getConfig().getInt("enchantments.Blind.strength")));
                                damaged.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * main.getConfig().getInt("enchantments.Blind.duration"), main.getConfig().getInt("enchantments.Blind.strength")));
                            }
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Crippling Strike")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Cripple")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Cripple.enabled")) {
                                return;
                            }
                            final LivingEntity live = (LivingEntity)event.getEntity();
                            if (!live.hasPotionEffect(PotionEffectType.CONFUSION)) {
                                live.getWorld().playSound(live.getLocation(), Sound.HURT_FLESH, 1.0f, 0.0f);
                                live.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 0));
                                live.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
                            }
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Lifesteal")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Lifesteal")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Lifesteal.enabled")) {
                                return;
                            }
                            final double heal = main.getConfig().getDouble("enchantments.Lifesteal.heal");
                            if (damager.getHealth() + heal <= damager.getMaxHealth()) {
                                damager.setHealth(damager.getHealth() + heal);
                            }
                            else {
                                damager.setHealth(damager.getMaxHealth());
                            }
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Thundering Blow")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Thunderingblow")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Thunderingblow.enabled")) {
                                return;
                            }
                            final Random random = new Random();
                            final int i = random.nextInt(100);
                            final int rand = main.getConfig().getInt("enchantments.Thunderingblow.lightningChance");
                            if (i <= rand) {
                                damager.getWorld().strikeLightning(event.getEntity().getLocation());
                            }
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Cursed")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Cursed")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Cursed.enabled")) {
                                return;
                            }
                            ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 1));
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Deep Wounds")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Deepwounds")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Deepwounds.enabled")) {
                                return;
                            }
                            final Random random = new Random();
                            final int i = random.nextInt(100);
                            final int rand = main.getConfig().getInt("enchantments.Deepwounds.bleedChance");
                            if (i <= rand && !this.hasCooldownBleed.contains(damager) && event.getEntity() instanceof Player && !this.isBleeding.contains(event.getEntity())) {
                                this.Cooldown(damager, this.hasCooldownBleed, 140);
                                final Player player = (Player)event.getEntity();
                                this.isBleeding.add(player);
                                new BukkitRunnable() {
                                    int seconds = 5;

                                    public void run() {
                                        if (this.seconds >= 0) {
                                            if (PlayerListener.this.isBleeding.contains(player)) {
                                                player.damage((double)(int)(player.getHealth() / 15.0));
                                                --this.seconds;
                                            }
                                            else {
                                                this.cancel();
                                            }
                                        }
                                        else {
                                            PlayerListener.this.isBleeding.remove(player);
                                            player.sendMessage(ChatColor.GREEN + "You have stopped Bleeding!");
                                            this.cancel();
                                        }
                                    }
                                }.runTaskTimer(Main.plugin, 0L, 20L);
                            }
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Vampire")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Vampire")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Vampire.enabled")) {
                                return;
                            }
                            if (!this.hasCooldownVampire.contains(damager)) {
                                if (damager.getHealth() + event.getDamage() / 2.0 <= damager.getMaxHealth()) {
                                    damager.setHealth(damager.getHealth() + event.getDamage() / 2.0);
                                }
                                else {
                                    damager.setHealth(damager.getMaxHealth());
                                }
                                this.Cooldown(damager, this.hasCooldownVampire, 100);
                            }
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Deathbringer")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Deathbringer")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Deathbringer.enabled")) {
                                return;
                            }
                            event.setDamage(event.getDamage() * main.getConfig().getInt("enchantments.Deathbringer.damageAmplifier"));
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Gooey")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Gooey")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Gooey.rightclick")) {
                                if (!main.getConfig().getBoolean("enchantments.Gooey.enabled")) {
                                    return;
                                }
                                new BukkitRunnable() {
                                    public void run() {
                                        event.getEntity().setVelocity(event.getEntity().getVelocity().setY(main.getConfig().getInt("enchantments.Gooey.heightMultiplier")));
                                        event.getEntity().getWorld().playEffect(event.getEntity().getLocation(), Effect.SMOKE, 60);
                                    }
                                }.runTaskLater(main, 1L);
                            }
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Poison")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Poison")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Poison.enabled")) {
                                return;
                            }
                            if (!event.getEntity().isDead()) {
                                final LivingEntity live = (LivingEntity)event.getEntity();
                                live.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * main.getConfig().getInt("enchantments.Poison.duration"), main.getConfig().getInt("enchantments.Poison.strength")));
                            }
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Shockwave")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Shockwave")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Shockwave.enabled")) {
                                return;
                            }
                            if (!this.hasCooldownShockwave.contains(damager)) {
                                new BukkitRunnable() {
                                    public void run() {
                                        event.getEntity().setVelocity(new Vector(0, 1, 0));
                                    }
                                }.runTaskLater(main, 1L);
                                final String string = getPlayerDirection(damager);
                                final Location loc = damager.getLocation();
                                loc.setY(damager.getLocation().getY() - 1.0);
                                final List<Location> list = this.getCone(loc, string);
                                this.Cooldown(damager, this.hasCooldownShockwave, main.getConfig().getInt("enchantments.Shockwave.cooldown") * 20);
                                damager.getWorld().playEffect(damager.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
                                for (final Location l : list) {
                                    if (!l.getBlock().getType().equals((Object)Material.AIR) && !l.getBlock().getType().equals((Object)Material.BEDROCK) && !l.getBlock().getType().equals((Object)Material.WATER) && !l.getBlock().getType().equals((Object)Material.LAVA) && !l.getBlock().getType().equals((Object)Material.CACTUS) && !l.getBlock().getType().equals((Object)Material.CAKE_BLOCK) && !l.getBlock().getType().equals((Object)Material.CROPS) && !l.getBlock().getType().equals((Object)Material.TORCH) && !l.getBlock().getType().equals((Object)Material.ENDER_PORTAL) && !l.getBlock().getType().equals((Object)Material.MOB_SPAWNER) && !l.getBlock().getType().equals((Object)Material.NETHER_WARTS) && !l.getBlock().getType().equals((Object)Material.MELON_STEM) && !l.getBlock().getType().equals((Object)Material.PISTON_EXTENSION) && !l.getBlock().getType().equals((Object)Material.PISTON_MOVING_PIECE) && !l.getBlock().getType().equals((Object)Material.STATIONARY_LAVA) && !l.getBlock().getType().equals((Object)Material.STATIONARY_WATER)) {
                                        final Material mat = l.getBlock().getType();
                                        final FallingBlock b = l.getWorld().spawnFallingBlock(l, l.getBlock().getTypeId(), l.getBlock().getData());
                                        b.setVelocity(new Vector(0.0, 0.5, 0.0));
                                        l.getBlock().setType(Material.AIR);
                                        new BukkitRunnable() {
                                            public void run() {
                                                if (!b.isDead()) {
                                                    b.remove();
                                                }
                                                l.getBlock().setType(mat);
                                            }
                                        }.runTaskLater(main, 20L);
                                    }
                                }
                            }
                        }
                        if (damager.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Ice Aspect")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Ice")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Ice.enabled")) {
                                return;
                            }
                            final Random rand2 = new Random();
                            final int i = rand2.nextInt(100);
                            if (i < 80) {
                                ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 5));
                            }
                            else if (i > 90 && event.getEntity() instanceof Player) {
                                ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 10));
                                if (main.getConfig().getBoolean("enchantments.Ice.specialFreeze")) {
                                    final Location loc2 = event.getEntity().getLocation();
                                    loc2.setY(loc2.getY() + 1.0);
                                    final Location loc3 = event.getEntity().getLocation();
                                    final Location loc4 = event.getEntity().getLocation();
                                    final Location loc5 = event.getEntity().getLocation();
                                    final Location loc6 = event.getEntity().getLocation();
                                    final Location loc7 = event.getEntity().getLocation();
                                    final Location loc8 = event.getEntity().getLocation();
                                    Location loc9 = event.getEntity().getLocation();
                                    Location loc10 = event.getEntity().getLocation();
                                    Location loc11 = event.getEntity().getLocation();
                                    Location loc12 = event.getEntity().getLocation();
                                    loc3.setY(loc3.getY() - 1.0);
                                    loc4.setX(loc4.getX() - 1.0);
                                    loc5.setX(loc5.getX() + 1.0);
                                    loc6.setY(loc6.getY() + 2.0);
                                    loc7.setZ(loc7.getZ() - 1.0);
                                    loc8.setZ(loc8.getZ() + 1.0);
                                    final Material mat2 = loc3.getBlock().getType();
                                    final Material mat3 = loc4.getBlock().getType();
                                    final Material mat4 = loc5.getBlock().getType();
                                    final Material mat5 = loc6.getBlock().getType();
                                    final Material mat6 = loc7.getBlock().getType();
                                    final Material mat7 = loc8.getBlock().getType();
                                    loc3.getBlock().setType(Material.ICE);
                                    loc4.getBlock().setType(Material.ICE);
                                    loc5.getBlock().setType(Material.ICE);
                                    loc6.getBlock().setType(Material.ICE);
                                    loc7.getBlock().setType(Material.ICE);
                                    loc8.getBlock().setType(Material.ICE);
                                    loc9 = loc4.clone();
                                    loc9.setY(loc9.getY() + 1.0);
                                    loc10 = loc5.clone();
                                    loc10.setY(loc10.getY() + 1.0);
                                    loc11 = loc7.clone();
                                    loc11.setY(loc11.getY() + 1.0);
                                    loc12 = loc8.clone();
                                    loc12.setY(loc12.getY() + 1.0);
                                    final Material mat8 = loc9.getBlock().getType();
                                    final Material mat9 = loc10.getBlock().getType();
                                    final Material mat10 = loc11.getBlock().getType();
                                    final Material mat11 = loc12.getBlock().getType();
                                    loc9.getBlock().setType(Material.ICE);
                                    loc10.getBlock().setType(Material.ICE);
                                    loc11.getBlock().setType(Material.ICE);
                                    loc12.getBlock().setType(Material.ICE);
                                    event.getEntity().getWorld().playSound(loc2, Sound.ITEM_BREAK, 1000.0f, 1.0f);
                                    final Location loc13 = loc3.clone();
                                    final Location loc14 = loc4.clone();
                                    final Location loc15 = loc5.clone();
                                    final Location loc16 = loc6.clone();
                                    final Location loc17 = loc7.clone();
                                    final Location loc18 = loc8.clone();
                                    final Location loc19 = loc9.clone();
                                    final Location loc20 = loc10.clone();
                                    final Location loc21 = loc11.clone();
                                    final Location loc22 = loc12.clone();
                                    new BukkitRunnable() {
                                        public void run() {
                                            if (mat2 != Material.ICE) {
                                                loc13.getBlock().setType(mat2);
                                            }
                                            if (mat3 != Material.ICE) {
                                                loc14.getBlock().setType(mat3);
                                            }
                                            if (mat4 != Material.ICE) {
                                                loc15.getBlock().setType(mat4);
                                            }
                                            if (mat5 != Material.ICE) {
                                                loc16.getBlock().setType(mat5);
                                            }
                                            if (mat6 != Material.ICE) {
                                                loc17.getBlock().setType(mat6);
                                            }
                                            if (mat7 != Material.ICE) {
                                                loc18.getBlock().setType(mat7);
                                            }
                                            if (mat8 != Material.ICE) {
                                                loc19.getBlock().setType(mat8);
                                            }
                                            if (mat9 != Material.ICE) {
                                                loc20.getBlock().setType(mat9);
                                            }
                                            if (mat10 != Material.ICE) {
                                                loc21.getBlock().setType(mat10);
                                            }
                                            if (mat11 != Material.ICE) {
                                                loc22.getBlock().setType(mat11);
                                            }
                                            this.cancel();
                                        }
                                    }.runTaskLater(main, 40L);
                                }
                            }
                        }
                        if (damager.getItemInHand().getItemMeta().hasDisplayName()) {
                            if (event.getEntity() instanceof Player && damager.getItemInHand().getType().name().endsWith("SPADE") && damager.getItemInHand().getItemMeta().getDisplayName().endsWith("Healing Shovel")) {
                                event.setDamage(0.0);
                                final String[] shovel = damager.getItemInHand().getItemMeta().getDisplayName().split(" ");
                                final String prefix = shovel[0];
                                final Player damaged2 = (Player)event.getEntity();
                                int durability = 1;
                                int heal2 = 5;
                                if (prefix.equals(ChatColor.GREEN + "Major")) {
                                    durability = 5;
                                    heal2 = 12;
                                }
                                damaged2.getWorld().playSound(damaged2.getLocation(), Sound.ORB_PICKUP, 0.5f, 1.0f);
                                final short currentDur = damager.getItemInHand().getDurability();
                                if (damaged2.getHealth() + heal2 <= 20.0) {
                                    damaged2.setHealth(damaged2.getHealth() + heal2);
                                }
                                else {
                                    damaged2.setHealth(20.0);
                                }
                                if (currentDur + durability < 33) {
                                    damager.getItemInHand().setDurability((short)(currentDur + durability));
                                }
                                else {
                                    damager.setItemInHand(new ItemStack(Material.AIR, 1));
                                    damager.getWorld().playSound(damager.getLocation(), Sound.ITEM_BREAK, 0.1f, 0.0f);
                                }
                            }
                            if (damager.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Pyro Axe")) {
                                if (!main.getConfig().getBoolean("items.PyroAxe.enabled")) {
                                    return;
                                }
                                if (event.getEntity().getFireTicks() > 0) {
                                    event.setDamage(main.getConfig().getInt("items.PyroAxe.damageMultiplier") * event.getDamage());
                                    event.getEntity().getWorld().playEffect(event.getEntity().getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
                                    event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ANVIL_LAND, 1.0f, 0.001f);
                                }
                            }
                            if (damager.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Assassin's Blade")) {
                                if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Assassin")) {
                                    return;
                                }
                                if (!main.getConfig().getBoolean("items.Assassin.enabled")) {
                                    return;
                                }
                                if (main.getConfig().getInt("items.Assassin.Length(Attack)") <= 0) {
                                    return;
                                }
                                if (!this.hasCooldownAssassinHit.contains(damager)) {
                                    if (this.AssassinInvisible.contains(damager)) {
                                        Player[] onlinePlayers;
                                        for (int length = (onlinePlayers = (Player[])Bukkit.getOnlinePlayers().toArray()).length, n = 0; n < length; ++n) {
                                            final Player p = onlinePlayers[n];
                                            p.showPlayer(damager);
                                        }
                                        damager.removePotionEffect(PotionEffectType.INVISIBILITY);
                                        this.AssassinInvisible.remove(damager);
                                        damager.playEffect(damager.getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1);
                                        event.setDamage(event.getDamage() * 2.0);
                                        damager.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, main.getConfig().getInt("items.Assassin.WeaknessLength") * 20, 5));
                                        this.Cooldown(damager, this.hasCooldownAssassinHit, main.getConfig().getInt("items.Assassin.Cooldown(Attack)"));
                                        return;
                                    }
                                    this.AssassinInvisible.add(damager);
                                    Player[] onlinePlayers2;
                                    for (int length2 = (onlinePlayers2 = (Player[])Bukkit.getOnlinePlayers().toArray()).length, n2 = 0; n2 < length2; ++n2) {
                                        final Player p = onlinePlayers2[n2];
                                        p.hidePlayer(damager);
                                    }
                                    damager.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, main.getConfig().getInt("items.Assassin.Length(Attack)") * 20, 5));
                                    new BukkitRunnable() {
                                        public void run() {
                                            PlayerListener.this.AssassinInvisible.remove(damager);
                                            Player[] onlinePlayers;
                                            for (int length = (onlinePlayers = (Player[])Bukkit.getOnlinePlayers().toArray()).length, i = 0; i < length; ++i) {
                                                final Player p = onlinePlayers[i];
                                                p.showPlayer(damager);
                                            }
                                            damager.removePotionEffect(PotionEffectType.INVISIBILITY);
                                            this.cancel();
                                        }
                                    }.runTaskTimer(main, (long)(main.getConfig().getInt("items.Assassin.Length(Attack)") * 20), 0L);
                                }
                            }
                        }
                    }
                    // if (damager.getItemInHand().getType().equals((Object)Material.PAPER) && damager.getItemInHand().hasItemMeta() && damager.getItemInHand().getItemMeta().hasDisplayName() && damager.getItemInHand().getItemMeta().getDisplayName().endsWith("Bandage")) {
                    //     if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damager.hasPermission("customenchantment.Bandage")) {
                    //         return;
                    //     }
                    //     if (!main.getConfig().getBoolean("items.Bandage.enabled")) {
                    //         return;
                    //     }
                    //     if (event.getEntity() instanceof Player) {
                    //         event.setCancelled(true);
                    //         final Player beingHealed = (Player)event.getEntity();
                    //         if (!this.hasCooldownBandage.contains(beingHealed)) {
                    //             if (!this.hasCooldownBandage.contains(damager)) {
                    //                 if (beingHealed.getHealth() < 20.0 && beingHealed.getHealth() > 0.0) {
                    //                     damager.sendMessage(ChatColor.GREEN + "You succesfully applied the bandage on " + beingHealed.getDisplayName() + "!");
                    //                     beingHealed.sendMessage(ChatColor.GREEN + damager.getDisplayName() + " has applied a bandage on you!");
                    //                     long period = 0L;
                    //                     int maxHeals = 0;
                    //                     if (damager.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Bandage")) {
                    //                         maxHeals = 20;
                    //                         period = 400 / maxHeals;
                    //                     }
                    //                     else {
                    //                         final String[] bandage = damager.getItemInHand().getItemMeta().getDisplayName().split(" ");
                    //                         final String prefix2 = bandage[0];
                    //                         if (prefix2.equals(ChatColor.GREEN + "Minor")) {
                    //                             maxHeals = 10;
                    //                             period = 200 / maxHeals;
                    //                         }
                    //                         else if (prefix2.equals(ChatColor.GREEN + "Tough")) {
                    //                             maxHeals = 30;
                    //                             period = 400 / maxHeals;
                    //                         }
                    //                         else if (prefix2.equals(ChatColor.GREEN + "Heavy")) {
                    //                             maxHeals = 50;
                    //                             period = 400 / maxHeals;
                    //                         }
                    //                     }
                    //                     final long maxHeals2 = maxHeals;
                    //                     final ItemStack j = new ItemStack(Material.PAPER, damager.getItemInHand().getAmount() - 1);
                    //                     j.setItemMeta(damager.getItemInHand().getItemMeta());
                    //                     damager.getInventory().setItemInHand(j);
                    //                     this.Cooldown(damager, this.hasCooldownBandage, main.getConfig().getInt("items.Bandage.Cooldown(Others)") * 20);
                    //                     this.isUsingBandage.add(beingHealed);
                    //                     new BukkitRunnable(maxHeals2) {
                    //                         long maxTime3 = maxTime3;

                    //                         public void run() {
                    //                             if (!beingHealed.isDead()) {
                    //                                 if (this.maxTime3 >= 0L) {
                    //                                     if (beingHealed.getHealth() + 1.0 <= 20.0) {
                    //                                         beingHealed.setHealth(beingHealed.getHealth() + 1.0);
                    //                                         --this.maxTime3;
                    //                                     }
                    //                                 }
                    //                                 else {
                    //                                     PlayerListener.this.isUsingBandage.remove(beingHealed);
                    //                                     PlayerListener.this.Cooldown(beingHealed, PlayerListener.this.hasCooldownBandage, main.getConfig().getInt("items.Bandage.Cooldown(Others)") * 20);
                    //                                     this.cancel();
                    //                                 }
                    //                             }
                    //                             else {
                    //                                 PlayerListener.this.isUsingBandage.remove(beingHealed);
                    //                                 this.cancel();
                    //                             }
                    //                         }
                    //                     }.runTaskTimer(main, 0L, period);
                    //                 }
                    //                 else {
                    //                     damager.sendMessage(ChatColor.RED + "The Player either has Full HP or is Dead.");
                    //                 }
                    //             }
                    //             else {
                    //                 damager.sendMessage(ChatColor.RED + "You cannot use another Bandage at the moment");
                    //             }
                    //         }
                    //         else {
                    //             damager.sendMessage(ChatColor.RED + "This Player has been bandaged recently!");
                    //         }
                    //     }
                    // }
                }
            }
            if (event.getDamager() instanceof Arrow) {
                final Arrow arrow = (Arrow)event.getDamager();
                if (arrow.getShooter() instanceof Player) {
                    final Player shooter = (Player)arrow.getShooter();
                    if (shooter.getItemInHand().hasItemMeta()) {
                        if (shooter.getItemInHand().getItemMeta().hasLore()) {
                            // if (shooter.getItemInHand().getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Bombardment")) {
                            //     if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Bombardment")) {
                            //         return;
                            //     }
                            //     if (!main.getConfig().getBoolean("enchantments.BombardmentBow.enabled")) {
                            //         return;
                            //     }
                            //     final Entity target = event.getEntity();
                            //     final World world = target.getWorld();
                            //     final double highest = 255.0 - target.getLocation().getY();
                            //     final double spawnY = target.getLocation().getY() + highest;
                            //     final Vector vec = new Vector(0, -5, 0);
                            //     final Location spawnLocation = new Location(world, target.getLocation().getX(), spawnY, target.getLocation().getZ());
                            //     final FallingBlock b2 = world.spawnFallingBlock(spawnLocation, 46, (byte)0);
                            //     b2.setVelocity(vec);
                            //     new BukkitRunnable() {
                            //         Location l = val$b.getLocation();

                            //         public void run() {
                            //             if (!b2.isDead()) {
                            //                 this.l = b2.getLocation();
                            //                 world.playSound(b2.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 5.0f);
                            //             }
                            //             else {
                            //                 b2.getLocation().getBlock().setType(Material.AIR);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 ((TNTPrimed)world.spawn(this.l, (Class)TNTPrimed.class)).setFuseTicks(0);
                            //                 this.cancel();
                            //             }
                            //         }
                            //     }.runTaskTimer(main, 0L, 1L);
                            // }
                            if (event.getDamager().hasMetadata("lightningarrow")) {
                                if (event.getEntity() instanceof Player && Main.hasWorldguard && ((WorldGuardPlugin)Main.pl).getRegionManager(event.getDamager().getWorld()).getApplicableRegions(event.getDamager().getLocation()) != null && !((WorldGuardPlugin)Main.pl).getRegionManager(event.getDamager().getWorld()).getApplicableRegions(event.getDamager().getLocation()).allows(DefaultFlag.PVP)) {
                                    return;
                                }
                                if (!main.getConfig().getBoolean("enchantments.LightningBow.customLightning")) {
                                    event.getEntity().getWorld().strikeLightning(event.getEntity().getLocation());
                                }
                                else {
                                    event.getEntity().getWorld().strikeLightningEffect(event.getEntity().getLocation());
                                    event.getEntity().setFireTicks(60);
                                    if (event.getEntity() instanceof LivingEntity) {
                                        ((LivingEntity)event.getEntity()).damage(2.0);
                                    }
                                }
                            }
                        }
                        if (event.getDamager().hasMetadata("poisonarrow")) {
                            ((LivingEntity)event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * main.getConfig().getInt("enchantments.PoisonBow.Duration"), main.getConfig().getInt("enchantments.PoisonBow.Strength")));
                        }
                    }
                    if (shooter.getItemInHand().getItemMeta().hasDisplayName()) {
                        if (event.getDamager().hasMetadata("beastmasterarrow")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Beastmaster")) {
                                return;
                            }
                            if (event.getEntity() instanceof Player) {
                                for (int i = main.getConfig().getInt("items.BeastmastersBow.mobAmount"); i >= 0; --i) {
                                    event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.valueOf(main.getConfig().getString("items.BeastmastersBow.toSpawn")));
                                }
                                event.getEntity().getWorld().playEffect(event.getEntity().getLocation(), Effect.EXTINGUISH, 20);
                                event.getEntity().getWorld().playEffect(event.getEntity().getLocation(), Effect.MOBSPAWNER_FLAMES, 20);
                                event.getEntity().getWorld().playEffect(event.getEntity().getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 20);
                            }
                            else {
                                event.setDamage(event.getDamage() * 2.0);
                            }
                        }
                        if (shooter.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Hookshot Bow")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !shooter.hasPermission("customenchantment.Hookshot")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("items.HookshotBow.enabled")) {
                                return;
                            }
                            final Location e1 = event.getEntity().getLocation();
                            final Location e2 = shooter.getLocation();
                            if (shooter.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Mode: Push")) {
                                final int x = e1.getBlockX() - e2.getBlockX();
                                final int z = e1.getBlockZ() - e2.getBlockZ();
                                final int y = e1.getBlockY() - e2.getBlockY();
                                final int multiplier = (this.Positive(x) + this.Positive(y) + this.Positive(z)) / 6;
                                final int ymultiplier = this.Positive(y) - (this.Positive(x) + this.Positive(z)) / 30;
                                final Vector vec2 = new Vector(x, this.Positive(y) + ymultiplier, z).normalize().multiply(multiplier);
                                if ((this.Positive(y) + ymultiplier) * multiplier > 3) {
                                    vec2.setY(vec2.getY() / 2.0);
                                }
                                shooter.getWorld().playSound(shooter.getLocation(), Sound.MAGMACUBE_JUMP, 1.0f, 9999.0f);
                                shooter.setVelocity(vec2);
                            }
                            else if (shooter.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Mode: Pull")) {
                                final int x = e2.getBlockX() - e1.getBlockX();
                                final int z = e2.getBlockZ() - e1.getBlockZ();
                                final int y = e2.getBlockY() - e1.getBlockY();
                                final int multiplier = (this.Positive(x) + this.Positive(y) + this.Positive(z)) / 2;
                                final int ymultiplier = this.Positive(x) + this.Positive(z);
                                event.getEntity().setVelocity(new Vector(x, this.Positive(y) * (2 * ymultiplier), z).normalize().multiply(multiplier));
                            }
                        }
                    }
                }
            }
            if (event.getEntity() instanceof Player) {
                final Player damaged3 = (Player)event.getEntity();
                if (Main.hasWorldguard && ((WorldGuardPlugin)Main.pl).getRegionManager(event.getEntity().getWorld()).getApplicableRegions(event.getEntity().getLocation()) != null && !((WorldGuardPlugin)Main.pl).getRegionManager(event.getEntity().getWorld()).getApplicableRegions(event.getEntity().getLocation()).allows(DefaultFlag.PVP)) {
                    return;
                }
                if ((event.getDamager() instanceof Arrow || event.getDamager() instanceof LivingEntity) && damaged3.getEquipment().getArmorContents() != null) {
                    ItemStack[] armorContents;
                    for (int length3 = (armorContents = damaged3.getEquipment().getArmorContents()).length, n3 = 0; n3 < length3; ++n3) {
                        final ItemStack k = armorContents[n3];
                        if (k.hasItemMeta() && k.getItemMeta().hasLore() && k.getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Molten")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damaged3.hasPermission("customenchantment.Molten")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Molten.enabled")) {
                                return;
                            }
                            if (event.getDamager() instanceof Arrow) {
                                // ((Arrow)event.getDamager()).getShooter().setFireTicks(main.getConfig().getInt("enchantments.Molten.Duration") * 20);
                            }
                            else {
                                event.getDamager().setFireTicks(main.getConfig().getInt("enchantments.Molten.Duration") * 20);
                            }
                        }
                        if (k.hasItemMeta() && k.getItemMeta().hasLore() && k.getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Frozen")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damaged3.hasPermission("customenchantment.Frozen")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Frozen.enabled")) {
                                return;
                            }
                            if (event.getDamager() instanceof Arrow) {
                                // ((Arrow)event.getDamager()).getShooter().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, main.getConfig().getInt("enchantments.Frozen.Duration") * 20, main.getConfig().getInt("enchantments.Frozen.Strength")));
                            }
                            else {
                                ((LivingEntity)event.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, main.getConfig().getInt("enchantments.Frozen.Duration") * 20, main.getConfig().getInt("enchantments.Frozen.Strength")));
                            }
                        }
                        if (k.hasItemMeta() && k.getItemMeta().hasLore() && k.getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Poisoned")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damaged3.hasPermission("customenchantment.Poisoned")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Poisoned.enabled")) {
                                return;
                            }
                            if (event.getDamager() instanceof Arrow) {
                                // ((Arrow)event.getDamager()).getShooter().addPotionEffect(new PotionEffect(PotionEffectType.POISON, main.getConfig().getInt("enchantments.Poisoned.Duration") * 20, main.getConfig().getInt("enchantments.Poisoned.Strength")));
                            }
                            else {
                                ((LivingEntity)event.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, main.getConfig().getInt("enchantments.Poisoned.Duration") * 20, main.getConfig().getInt("enchantments.Poisoned.Strength")));
                            }
                        }
                        if (k.hasItemMeta() && k.getItemMeta().hasLore() && k.getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Hardened")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damaged3.hasPermission("customenchantment.Hardened")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Hardened.enabled")) {
                                return;
                            }
                            // if (event.getDamager() instanceof Arrow) {
                            //     ((Arrow)event.getDamager()).getShooter().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, main.getConfig().getInt("enchantments.Hardened.Duration") * 20, main.getConfig().getInt("enchantments.Hardened.Strength")));
                            // }
                            else {
                                ((LivingEntity)event.getDamager()).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, main.getConfig().getInt("enchantments.Hardened.Duration") * 20, main.getConfig().getInt("enchantments.Hardened.Strength")));
                            }
                        }
                        if (k.hasItemMeta() && k.getItemMeta().hasLore() && k.getItemMeta().getLore().contains(String.valueOf(lorePrefix) + "Enlighted")) {
                            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !damaged3.hasPermission("customenchantment.Enlighted")) {
                                return;
                            }
                            if (!main.getConfig().getBoolean("enchantments.Enlighted.enabled")) {
                                return;
                            }
                            damaged3.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, main.getConfig().getInt("enchantments.Enlighted.Duration") * 20, main.getConfig().getInt("enchantments.Enlighted.Strength")));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Plugin main = Main.plugin;
        final Player player = event.getPlayer();
        final Location loc = player.getLocation();
        final Location locAbove = player.getLocation().getBlock().getRelative(0, 1, 0).getLocation();
        final Location locBelow = player.getLocation().getBlock().getRelative(0, -1, 0).getLocation();
        final ItemStack a = player.getItemInHand();
        final ItemMeta im = a.getItemMeta();
        if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore() && Main.isRestricting && event.getFrom().distance(event.getTo()) > 0.1 && !player.hasMetadata("CE-CHECK")) {
            player.setMetadata("CE-CHECK", (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)""));
            int e = 0;
            final List<String> lore = (List<String>)player.getItemInHand().getItemMeta().getLore();
            final List<String> toRemove = new ArrayList<String>();
            for (final String r : Main.enchantments) {
                for (final String l : lore) {
                    if (getEnchantmentName(r).equalsIgnoreCase(l)) {
                        ++e;
                        toRemove.add(l);
                    }
                }
            }
            if (e > Main.maxEnchants) {
                for (final String r : toRemove) {
                    lore.remove(r);
                }
            }
            im.setLore((List)lore);
            player.getItemInHand().setItemMeta(im);
            new BukkitRunnable() {
                public void run() {
                    player.removeMetadata("CE-CHECK", Main.plugin);
                }
            }.runTaskLater(Main.plugin, 100L);
        }
        if (Main.plugin.getConfig().getBoolean("enchantments.Autorepair.repairOnMoving")) {
            if (player.getEquipment() != null && player.getEquipment().getArmorContents() != null) {
                ItemStack[] armorContents;
                for (int length = (armorContents = player.getEquipment().getArmorContents()).length, n = 0; n < length; ++n) {
                    final ItemStack b = armorContents[n];
                    if (b.getType() != Material.AIR && b.hasItemMeta() && b.getItemMeta().hasLore() && b.getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Automatic Repair")) {
                        if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !player.hasPermission("customenchantment.Autorepair")) {
                            return;
                        }
                        if (!Main.plugin.getConfig().getBoolean("enchantments.Autorepair.enabled")) {
                            return;
                        }
                        final int healAmount = Main.plugin.getConfig().getInt("enchantments.Autorepair.healAmount");
                        if (player != null && player.isOnline() && !player.isDead()) {
                            if (b.getDurability() - healAmount >= 0) {
                                b.setDurability((short)(b.getDurability() - healAmount));
                            }
                            else {
                                b.setDurability((short)0);
                            }
                        }
                    }
                }
            }
            if (a != null && a.hasItemMeta() && a.getItemMeta().hasLore() && a.getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Automatic Repair")) {
                if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !player.hasPermission("customenchantment.Autorepair")) {
                    return;
                }
                if (!Main.plugin.getConfig().getBoolean("enchantments.Autorepair.enabled")) {
                    return;
                }
                final int healAmount2 = Main.plugin.getConfig().getInt("enchantments.Autorepair.healAmount");
                if (player != null && player.isOnline() && !player.isDead() && player.getInventory().contains(a)) {
                    if (a.getDurability() - healAmount2 >= 0) {
                        a.setDurability((short)(a.getDurability() - healAmount2));
                    }
                    else {
                        a.setDurability((short)0);
                    }
                }
            }
        }
        if (event.getPlayer().getEquipment().getHelmet() != null && event.getPlayer().getEquipment().getHelmet().hasItemMeta() && event.getPlayer().getEquipment().getHelmet().getItemMeta().hasLore() && event.getPlayer().getEquipment().getHelmet().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Implants")) {
            if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Implants")) {
                return;
            }
            if (!main.getConfig().getBoolean("enchantments.Implants.enabled")) {
                return;
            }
            final int hunger = event.getPlayer().getFoodLevel();
            final int air = event.getPlayer().getRemainingAir();
            if (hunger < 20) {
                event.getPlayer().setFoodLevel(hunger + 1);
            }
            if (air < event.getPlayer().getMaximumAir()) {
                event.getPlayer().setRemainingAir(air + 1);
            }
        }
        final Location loc2 = event.getTo().getBlock().getRelative(0, -1, 0).getLocation();
        if (event.getPlayer().getEquipment().getBoots() != null && event.getPlayer().getEquipment().getBoots().hasItemMeta() && event.getPlayer().getEquipment().getBoots().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getBoots().getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "Icewalker's Boots")) {
            final Location loc3 = loc2.getBlock().getRelative(1, 0, 1).getLocation();
            final Location loc4 = loc2.getBlock().getRelative(-1, 0, -1).getLocation();
            final Location loc5 = loc2.getBlock().getRelative(0, 0, 1).getLocation();
            final Location loc6 = loc2.getBlock().getRelative(1, 0, 0).getLocation();
            final Location loc7 = loc2.getBlock().getRelative(-1, 0, 1).getLocation();
            final Location loc8 = loc2.getBlock().getRelative(1, 0, -1).getLocation();
            final Location loc9 = loc2.getBlock().getRelative(0, 0, -1).getLocation();
            final Location loc10 = loc2.getBlock().getRelative(-1, 0, 0).getLocation();
            final List<Location> locs = new ArrayList<Location>();
            locs.add(loc2);
            locs.add(loc3);
            locs.add(loc4);
            locs.add(loc5);
            locs.add(loc6);
            locs.add(loc7);
            locs.add(loc8);
            locs.add(loc9);
            locs.add(loc10);
            for (final Location i : locs) {
                if ((i.getBlock().getType().equals((Object)Material.WATER) || i.getBlock().getType().equals((Object)Material.STATIONARY_WATER)) && event.getTo().getBlock().getType().equals((Object)Material.AIR)) {
                    i.getBlock().setType(Material.ICE);
                    final Player p = event.getPlayer();
                    new BukkitRunnable() {
                        public void run() {
                            if (i.getBlock().getType().equals((Object)Material.ICE)) {
                                if ((long)p.getLocation().getY() != (int)i.getBlock().getRelative(0, 1, 0).getLocation().getY() || PlayerListener.this.Positive((int)p.getLocation().getX() - (int)i.getBlock().getRelative(0, 1, 0).getLocation().getX()) >= 2 || PlayerListener.this.Positive((int)p.getLocation().getZ() - (int)i.getBlock().getRelative(0, 1, 0).getLocation().getZ()) >= 2) {
                                    i.getBlock().setType(Material.WATER);
                                    this.cancel();
                                }
                            }
                            else {
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(Main.plugin, 20L, 1L);
                }
            }
        }
        if (event.getPlayer().getEquipment().getBoots() != null && event.getPlayer().getEquipment().getBoots().hasItemMeta() && event.getPlayer().getEquipment().getBoots().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getBoots().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Druid's Boots")) {
            final MetadataValue meta = (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true);
            if (loc2.getBlock().getType().equals((Object)Material.GRASS) || loc2.getBlock().getType().equals((Object)Material.DIRT) || loc2.getBlock().getType().equals((Object)Material.LEAVES)) {
                if (event.getPlayer().hasMetadata("druidboots")) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600000, 4));
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600000, 1));
                }
                else {
                    event.getPlayer().setMetadata("druidboots", meta);
                }
            }
            else if (event.getPlayer().hasMetadata("druidboots") && !loc2.getBlock().getType().equals((Object)Material.AIR)) {
                event.getPlayer().removeMetadata("druidboots", Main.plugin);
                event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
                event.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
            }
        }
        else if (event.getPlayer().hasMetadata("druidboots")) {
            event.getPlayer().removeMetadata("druidboots", Main.plugin);
            event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
            event.getPlayer().removePotionEffect(PotionEffectType.REGENERATION);
        }
        for (final Location locatione : this.getSurroundingBlocks(loc)) {
            if (locatione.getBlock().hasMetadata("pricklytits")) {
                if (locatione.getBlock().getType().equals((Object)Material.AIR)) {
                    locatione.getBlock().removeMetadata("pricklytits", Main.plugin);
                    return;
                }
                if (event.getPlayer().hasPotionEffect(PotionEffectType.CONFUSION)) {
                    continue;
                }
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 5, 20));
                event.getPlayer().damage(1.0);
            }
        }
        if (player.getLocation().getBlock().getRelative(0, -1, 0).hasMetadata("pricklytits")) {
            if (player.getLocation().getBlock().getRelative(0, -1, 0).getType().equals((Object)Material.AIR)) {
                player.getLocation().getBlock().getRelative(0, -1, 0).removeMetadata("pricklytits", Main.plugin);
                return;
            }
            if (!event.getPlayer().hasPotionEffect(PotionEffectType.CONFUSION)) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 5, 20));
                event.getPlayer().damage(1.0);
            }
        }
        if (locBelow.getBlock().hasMetadata("beartrap")) {
            if (locBelow.getBlock().getType().equals((Object)Material.AIR)) {
                locBelow.getBlock().removeMetadata("beartrap", Main.plugin);
                return;
            }
            if (!this.isBleeding.contains(event.getPlayer())) {
                this.isBleeding.add(player);
                new BukkitRunnable() {
                    int seconds = 5;

                    public void run() {
                        if (this.seconds >= 0) {
                            if (PlayerListener.this.isBleeding.contains(player)) {
                                player.damage(3.0);
                                --this.seconds;
                            }
                            else {
                                this.cancel();
                            }
                        }
                        else {
                            PlayerListener.this.isBleeding.remove(player);
                            player.sendMessage(ChatColor.GREEN + "You have stopped Bleeding!");
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Main.plugin, 0L, 20L);
            }
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 5));
            locBelow.getBlock().setType(Material.AIR);
            locBelow.getBlock().removeMetadata("beartrap", Main.plugin);
        }
        if (event.getPlayer().getLocation().getBlock().hasMetadata("beartrap")) {
            if (event.getPlayer().getLocation().getBlock().getType().equals((Object)Material.AIR)) {
                event.getPlayer().getLocation().getBlock().removeMetadata("beartrap", Main.plugin);
                return;
            }
            if (!this.isBleeding.contains(event.getPlayer()) && !event.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
                this.isBleeding.add(player);
                new BukkitRunnable() {
                    int seconds = 5;

                    public void run() {
                        if (this.seconds >= 0) {
                            if (PlayerListener.this.isBleeding.contains(player)) {
                                player.damage(3.0);
                                --this.seconds;
                            }
                            else {
                                this.cancel();
                            }
                        }
                        else {
                            PlayerListener.this.isBleeding.remove(player);
                            player.sendMessage(ChatColor.GREEN + "You have stopped Bleeding!");
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Main.plugin, 0L, 20L);
            }
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 5));
            event.getPlayer().getLocation().getBlock().setType(Material.AIR);
            event.getPlayer().getLocation().getBlock().removeMetadata("beartrap", Main.plugin);
        }
        if (locBelow.getBlock().hasMetadata("piranhatrap")) {
            if (locBelow.getBlock().getType().equals((Object)Material.AIR)) {
                locBelow.getBlock().removeMetadata("piranhatrap", Main.plugin);
                return;
            }
            for (final Entity ent : event.getPlayer().getNearbyEntities(3.0, 2.0, 3.0)) {
                if (ent instanceof Player) {
                    final Player player2 = (Player)ent;
                    if (this.isBleeding.contains(player2) || player2.getGameMode().equals((Object)GameMode.CREATIVE)) {
                        continue;
                    }
                    this.isBleeding.add(player2);
                    new BukkitRunnable() {
                        int seconds = 5;

                        public void run() {
                            if (this.seconds >= 0) {
                                if (PlayerListener.this.isBleeding.contains(player2)) {
                                    player2.damage(3.0);
                                    --this.seconds;
                                }
                                else {
                                    this.cancel();
                                }
                            }
                            else {
                                PlayerListener.this.isBleeding.remove(player2);
                                player2.sendMessage(ChatColor.GREEN + "You have stopped Bleeding!");
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(Main.plugin, 0L, 20L);
                }
            }
            if (!this.isBleeding.contains(player) && !event.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
                this.isBleeding.add(player);
                new BukkitRunnable() {
                    int seconds = 5;

                    public void run() {
                        if (this.seconds >= 0) {
                            if (PlayerListener.this.isBleeding.contains(player)) {
                                player.damage(3.0);
                                --this.seconds;
                            }
                            else {
                                this.cancel();
                            }
                        }
                        else {
                            PlayerListener.this.isBleeding.remove(player);
                            player.sendMessage(ChatColor.GREEN + "You have stopped Bleeding!");
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Main.plugin, 0L, 20L);
            }
            final ItemStack is = new ItemStack(Material.RAW_FISH, 1);
            is.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
            final Item j = locAbove.getWorld().dropItem(locAbove, is);
            j.setPickupDelay(500);
            j.setVelocity(new Vector(0.0, 0.4, 0.01));
            final ItemStack is2 = new ItemStack(Material.RAW_FISH, 1);
            is2.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
            final Item i2 = locAbove.getWorld().dropItem(locAbove, is2);
            i2.setPickupDelay(500);
            i2.setVelocity(new Vector(-0.01, 0.4, 0.01));
            final ItemStack is3 = new ItemStack(Material.RAW_FISH, 1);
            is3.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 3);
            final Item i3 = locAbove.getWorld().dropItem(locAbove, is3);
            i3.setPickupDelay(500);
            i3.setVelocity(new Vector(0.01, 0.4, -0.01));
            final ItemStack is4 = new ItemStack(Material.RAW_FISH, 1);
            is4.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 4);
            final Item i4 = locAbove.getWorld().dropItem(locAbove, is4);
            i4.setPickupDelay(500);
            i4.setVelocity(new Vector(-0.01, 0.4, -0.01));
            final ItemStack is5 = new ItemStack(Material.RAW_FISH, 1);
            is5.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
            final Item i5 = locAbove.getWorld().dropItem(locAbove, is5);
            i5.setPickupDelay(500);
            i5.setVelocity(new Vector(0.01, 0.4, 0.0));
            final ItemStack is6 = new ItemStack(Material.RAW_FISH, 1);
            is6.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 3);
            final Item i6 = locAbove.getWorld().dropItem(locAbove, is6);
            i6.setPickupDelay(500);
            i6.setVelocity(new Vector(-0.01, 0.4, 0.0));
            final ItemStack is7 = new ItemStack(Material.RAW_FISH, 1);
            is7.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 4);
            final Item i7 = locAbove.getWorld().dropItem(locAbove, is7);
            i7.setPickupDelay(500);
            i7.setVelocity(new Vector(0.0, 0.4, -0.01));
            final ItemStack is8 = new ItemStack(Material.RAW_FISH, 1);
            is8.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
            final Item i8 = locAbove.getWorld().dropItem(locAbove, is8);
            i8.setPickupDelay(500);
            i8.setVelocity(new Vector(0.0, 0.4, 0.01));
            new BukkitRunnable() {
                int maxTime = 5;

                public void run() {
                    if (this.maxTime >= 0) {
                        j.setVelocity(j.getVelocity().multiply(-2));
                        i2.setVelocity(i2.getVelocity().multiply(-2));
                        i3.setVelocity(i3.getVelocity().multiply(-2));
                        i4.setVelocity(i4.getVelocity().multiply(-2));
                        i5.setVelocity(i5.getVelocity().multiply(-2));
                        i6.setVelocity(i6.getVelocity().multiply(-2));
                        i7.setVelocity(i7.getVelocity().multiply(-2));
                        i8.setVelocity(i8.getVelocity().multiply(-2));
                        j.setVelocity(j.getVelocity().setY(0.4));
                        i2.setVelocity(i2.getVelocity().setY(0.4));
                        i3.setVelocity(i3.getVelocity().setY(0.4));
                        i4.setVelocity(i4.getVelocity().setY(0.4));
                        i5.setVelocity(i5.getVelocity().setY(0.4));
                        i6.setVelocity(i6.getVelocity().setY(0.4));
                        i7.setVelocity(i7.getVelocity().setY(0.4));
                        i8.setVelocity(i8.getVelocity().setY(0.4));
                        --this.maxTime;
                    }
                    else {
                        j.remove();
                        i2.remove();
                        i3.remove();
                        i4.remove();
                        i5.remove();
                        i6.remove();
                        i7.remove();
                        i8.remove();
                        this.cancel();
                    }
                }
            }.runTaskTimer(Main.plugin, 0L, 20L);
            locBelow.getBlock().setType(Material.AIR);
            locBelow.getBlock().removeMetadata("piranhatrap", Main.plugin);
        }
        if (event.getPlayer().getLocation().getBlock().hasMetadata("piranhatrap")) {
            if (event.getPlayer().getLocation().getBlock().getType().equals((Object)Material.AIR)) {
                event.getPlayer().getLocation().getBlock().removeMetadata("piranhatrap", Main.plugin);
                return;
            }
            for (final Entity ent : event.getPlayer().getNearbyEntities(3.0, 2.0, 3.0)) {
                if (ent instanceof Player) {
                    final Player player2 = (Player)ent;
                    if (this.isBleeding.contains(player2) || event.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
                        continue;
                    }
                    this.isBleeding.add(player2);
                    new BukkitRunnable() {
                        int seconds = 5;

                        public void run() {
                            if (this.seconds >= 0) {
                                if (PlayerListener.this.isBleeding.contains(player2)) {
                                    player2.damage(3.0);
                                    --this.seconds;
                                }
                                else {
                                    this.cancel();
                                }
                            }
                            else {
                                PlayerListener.this.isBleeding.remove(player2);
                                player2.sendMessage(ChatColor.GREEN + "You have stopped Bleeding!");
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(Main.plugin, 0L, 20L);
                }
            }
            if (!this.isBleeding.contains(player)) {
                this.isBleeding.add(player);
                new BukkitRunnable() {
                    int seconds = 5;

                    public void run() {
                        if (this.seconds >= 0) {
                            if (PlayerListener.this.isBleeding.contains(player)) {
                                player.damage(3.0);
                                --this.seconds;
                            }
                            else {
                                this.cancel();
                            }
                        }
                        else {
                            PlayerListener.this.isBleeding.remove(player);
                            player.sendMessage(ChatColor.GREEN + "You have stopped Bleeding!");
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Main.plugin, 0L, 20L);
            }
            final ItemStack is = new ItemStack(Material.RAW_FISH, 1);
            is.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
            final Item j = loc.getWorld().dropItem(event.getPlayer().getLocation(), is);
            j.setPickupDelay(500);
            j.setVelocity(new Vector(0.01, 0.4, 0.01));
            final ItemStack is2 = new ItemStack(Material.RAW_FISH, 1);
            is2.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
            final Item i2 = loc.getWorld().dropItem(event.getPlayer().getLocation(), is2);
            i2.setPickupDelay(500);
            i2.setVelocity(new Vector(-0.01, 0.4, 0.01));
            final ItemStack is3 = new ItemStack(Material.RAW_FISH, 1);
            is3.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 3);
            final Item i3 = loc.getWorld().dropItem(event.getPlayer().getLocation(), is3);
            i3.setPickupDelay(500);
            i3.setVelocity(new Vector(0.01, 0.4, -0.01));
            final ItemStack is4 = new ItemStack(Material.RAW_FISH, 1);
            is4.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 4);
            final Item i4 = loc.getWorld().dropItem(event.getPlayer().getLocation(), is4);
            i4.setPickupDelay(500);
            i4.setVelocity(new Vector(-0.01, 0.4, -0.01));
            final ItemStack is5 = new ItemStack(Material.RAW_FISH, 1);
            is5.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 2);
            final Item i5 = loc.getWorld().dropItem(event.getPlayer().getLocation(), is5);
            i5.setPickupDelay(500);
            i5.setVelocity(new Vector(0.01, 0.4, 0.0));
            final ItemStack is6 = new ItemStack(Material.RAW_FISH, 1);
            is6.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 3);
            final Item i6 = loc.getWorld().dropItem(event.getPlayer().getLocation(), is6);
            i6.setPickupDelay(500);
            i6.setVelocity(new Vector(-0.01, 0.4, 0.0));
            final ItemStack is7 = new ItemStack(Material.RAW_FISH, 1);
            is7.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 4);
            final Item i7 = loc.getWorld().dropItem(event.getPlayer().getLocation(), is7);
            i7.setPickupDelay(500);
            i7.setVelocity(new Vector(0.0, 0.4, -0.01));
            final ItemStack is8 = new ItemStack(Material.RAW_FISH, 1);
            is8.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
            final Item i8 = loc.getWorld().dropItem(event.getPlayer().getLocation(), is8);
            i8.setPickupDelay(500);
            i8.setVelocity(new Vector(0.0, 0.4, 0.01));
            new BukkitRunnable() {
                int maxTime = 5;

                public void run() {
                    if (this.maxTime >= 0) {
                        j.setVelocity(j.getVelocity().multiply(-2));
                        i2.setVelocity(i2.getVelocity().multiply(-2));
                        i3.setVelocity(i3.getVelocity().multiply(-2));
                        i4.setVelocity(i4.getVelocity().multiply(-2));
                        i5.setVelocity(i5.getVelocity().multiply(-2));
                        i6.setVelocity(i6.getVelocity().multiply(-2));
                        i7.setVelocity(i7.getVelocity().multiply(-2));
                        i8.setVelocity(i8.getVelocity().multiply(-2));
                        j.setVelocity(j.getVelocity().setY(0.4));
                        i2.setVelocity(i2.getVelocity().setY(0.4));
                        i3.setVelocity(i3.getVelocity().setY(0.4));
                        i4.setVelocity(i4.getVelocity().setY(0.4));
                        i5.setVelocity(i5.getVelocity().setY(0.4));
                        i6.setVelocity(i6.getVelocity().setY(0.4));
                        i7.setVelocity(i7.getVelocity().setY(0.4));
                        i8.setVelocity(i8.getVelocity().setY(0.4));
                        --this.maxTime;
                    }
                    else {
                        j.remove();
                        i2.remove();
                        i3.remove();
                        i4.remove();
                        i5.remove();
                        i6.remove();
                        i7.remove();
                        i8.remove();
                        this.cancel();
                    }
                }
            }.runTaskTimer(Main.plugin, 0L, 20L);
            event.getPlayer().getLocation().getBlock().setType(Material.AIR);
            event.getPlayer().getLocation().getBlock().removeMetadata("piranhatrap", Main.plugin);
        }
        if (event.getPlayer().getLocation().getBlock().hasMetadata("poisonivy")) {
            if (event.getPlayer().getLocation().getBlock().getType().equals((Object)Material.AIR)) {
                event.getPlayer().getLocation().getBlock().removeMetadata("poisonivy", Main.plugin);
                return;
            }
            if (event.getPlayer().getEquipment().getArmorContents() != null) {
                ItemStack[] armorContents2;
                for (int length2 = (armorContents2 = event.getPlayer().getEquipment().getArmorContents()).length, n2 = 0; n2 < length2; ++n2) {
                    final ItemStack k = armorContents2[n2];
                    if (k.hasItemMeta() && k.getItemMeta().hasLore() && k.getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Poisoned")) {
                        return;
                    }
                }
            }
            event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.POISON, 120, 1));
        }
        if (event.getPlayer().getEquipment().getHelmet() != null && event.getPlayer().getEquipment().getHelmet().hasItemMeta() && event.getPlayer().getEquipment().getHelmet().getItemMeta().hasLore() && event.getPlayer().getEquipment().getHelmet().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Glowing")) {
            if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Glowing")) {
                return;
            }
            if (!main.getConfig().getBoolean("enchantments.Glowing.enabled")) {
                return;
            }
            final MetadataValue meta = (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true);
            if (event.getPlayer().hasMetadata("glowing")) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 600000, 0));
            }
            else {
                event.getPlayer().setMetadata("glowing", meta);
            }
        }
        else if (event.getPlayer().hasMetadata("glowing")) {
            event.getPlayer().removeMetadata("glowing", Main.plugin);
            event.getPlayer().removePotionEffect(PotionEffectType.NIGHT_VISION);
        }
        if ((event.getPlayer().getEquipment().getBoots() != null && event.getPlayer().getEquipment().getBoots().hasItemMeta() && event.getPlayer().getEquipment().getBoots().getItemMeta().hasLore() && event.getPlayer().getEquipment().getBoots().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Obsidian Shielding")) || (event.getPlayer().getEquipment().getHelmet() != null && event.getPlayer().getEquipment().getHelmet().hasItemMeta() && event.getPlayer().getEquipment().getHelmet().getItemMeta().hasLore() && event.getPlayer().getEquipment().getHelmet().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Obsidian Shielding")) || (event.getPlayer().getEquipment().getChestplate() != null && event.getPlayer().getEquipment().getChestplate().hasItemMeta() && event.getPlayer().getEquipment().getChestplate().getItemMeta().hasLore() && event.getPlayer().getEquipment().getChestplate().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Obsidian Shielding")) || (event.getPlayer().getEquipment().getLeggings() != null && event.getPlayer().getEquipment().getLeggings().hasItemMeta() && event.getPlayer().getEquipment().getLeggings().getItemMeta().hasLore() && event.getPlayer().getEquipment().getLeggings().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Obsidian Shielding"))) {
            if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Obsidianshielding")) {
                return;
            }
            if (!main.getConfig().getBoolean("enchantments.Obsidianshielding.enabled")) {
                return;
            }
            final MetadataValue meta = (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true);
            if (event.getPlayer().hasMetadata("fireproof")) {
                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600000, 10));
            }
            else {
                event.getPlayer().setMetadata("fireproof", meta);
            }
        }
        else if (event.getPlayer().hasMetadata("fireproof")) {
            event.getPlayer().removeMetadata("fireproof", Main.plugin);
            event.getPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
        }
        if ((event.getPlayer().getLocation().getBlock().getType().equals((Object)Material.WATER) || event.getPlayer().getLocation().getBlock().getType().equals((Object)Material.STATIONARY_WATER)) && ((event.getPlayer().getEquipment().getHelmet() != null && event.getPlayer().getEquipment().getHelmet().hasItemMeta() && event.getPlayer().getEquipment().getHelmet().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Scuba Mask")) || (event.getPlayer().getEquipment().getChestplate() != null && event.getPlayer().getEquipment().getChestplate().hasItemMeta() && event.getPlayer().getEquipment().getChestplate().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getChestplate().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Upper Swimsuit")) || (event.getPlayer().getEquipment().getLeggings() != null && event.getPlayer().getEquipment().getLeggings().hasItemMeta() && event.getPlayer().getEquipment().getLeggings().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getLeggings().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Lower Swimsuit")) || (event.getPlayer().getEquipment().getBoots() != null && event.getPlayer().getEquipment().getBoots().hasItemMeta() && event.getPlayer().getEquipment().getBoots().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getBoots().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Flippers")))) {
            if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Swimsuit")) {
                return;
            }
            if (!main.getConfig().getBoolean("items.Swimsuit.enabled")) {
                return;
            }
            if (!this.hasSwimSuit.contains(event.getPlayer())) {
                this.hasSwimSuit.add(event.getPlayer());
                new BukkitRunnable() {
                    public void run() {
                        if (!PlayerListener.this.hasSwimSuit.contains(event.getPlayer())) {
                            if (event.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) {
                                event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
                            }
                            if (event.getPlayer().hasPotionEffect(PotionEffectType.INCREASE_DAMAGE)) {
                                event.getPlayer().removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
                            }
                            this.cancel();
                        }
                        if (event.getPlayer().getLocation().getBlock().getType().equals((Object)Material.WATER) || event.getPlayer().getLocation().getBlock().getType().equals((Object)Material.STATIONARY_WATER)) {
                            if ((event.getPlayer().getEquipment().getHelmet() != null && event.getPlayer().getEquipment().getHelmet().hasItemMeta() && event.getPlayer().getEquipment().getHelmet().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getHelmet().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Scuba Mask")) || (event.getPlayer().getEquipment().getChestplate() != null && event.getPlayer().getEquipment().getChestplate().hasItemMeta() && event.getPlayer().getEquipment().getChestplate().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getChestplate().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Upper Swimsuit")) || (event.getPlayer().getEquipment().getLeggings() != null && event.getPlayer().getEquipment().getLeggings().hasItemMeta() && event.getPlayer().getEquipment().getLeggings().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getLeggings().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Lower Swimsuit")) || (event.getPlayer().getEquipment().getBoots() != null && event.getPlayer().getEquipment().getBoots().hasItemMeta() && event.getPlayer().getEquipment().getBoots().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getBoots().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Flippers"))) {
                                event.getPlayer().setRemainingAir(event.getPlayer().getMaximumAir());
                                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 3600000, Main.plugin.getConfig().getInt("items.Swimsuit.speedBoostUnderwater") - 1), true);
                                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3600000, Main.plugin.getConfig().getInt("items.Swimsuit.damageBoostUnderwater") - 1), true);
                            }
                            else {
                                PlayerListener.this.hasSwimSuit.remove(event.getPlayer());
                            }
                        }
                        else {
                            PlayerListener.this.hasSwimSuit.remove(event.getPlayer());
                        }
                    }
                }.runTaskTimer(Main.plugin, 0L, 10L);
            }
        }
        if (event.getPlayer().getEquipment().getBoots() != null) {
            if (event.getPlayer().getEquipment().getBoots().hasItemMeta() && event.getPlayer().getEquipment().getBoots().getItemMeta().hasLore() && event.getPlayer().getEquipment().getBoots().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Springs")) {
                if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Springs")) {
                    return;
                }
                if (!main.getConfig().getBoolean("enchantments.Springs.enabled")) {
                    return;
                }
                final MetadataValue meta = (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true);
                if (event.getPlayer().hasMetadata("springs")) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 600000, main.getConfig().getInt("enchantments.Springs.jumpBoost") - 1));
                }
                else {
                    event.getPlayer().setMetadata("springs", meta);
                }
            }
            else if (event.getPlayer().hasMetadata("springs")) {
                event.getPlayer().removeMetadata("springs", Main.plugin);
                event.getPlayer().removePotionEffect(PotionEffectType.JUMP);
            }
        }
        else if (event.getPlayer().hasMetadata("springs")) {
            event.getPlayer().removeMetadata("springs", Main.plugin);
            event.getPlayer().removePotionEffect(PotionEffectType.JUMP);
        }
        if (event.getPlayer().getEquipment().getBoots() != null) {
            if (event.getPlayer().getEquipment().getBoots().hasItemMeta() && event.getPlayer().getEquipment().getBoots().getItemMeta().hasLore() && event.getPlayer().getEquipment().getBoots().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Gears")) {
                if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Gears")) {
                    return;
                }
                if (!main.getConfig().getBoolean("enchantments.Gears.enabled")) {
                    return;
                }
                final MetadataValue meta = (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true);
                if (event.getPlayer().hasMetadata("gears")) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600000, main.getConfig().getInt("enchantments.Gears.speedBoost") - 1));
                }
                else {
                    event.getPlayer().setMetadata("gears", meta);
                }
            }
            else if (event.getPlayer().hasMetadata("gears")) {
                event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
                event.getPlayer().removeMetadata("gears", Main.plugin);
            }
        }
        else if (event.getPlayer().hasMetadata("gears")) {
            event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
            event.getPlayer().removeMetadata("gears", Main.plugin);
        }
        if (event.getPlayer().getEquipment().getBoots() != null) {
            if (event.getPlayer().getEquipment().getBoots().hasItemMeta() && event.getPlayer().getEquipment().getBoots().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getBoots().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Hermes Boots")) {
                if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Hermesboots")) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.HermesBoots.enabled")) {
                    return;
                }
                final MetadataValue meta = (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true);
                if (event.getPlayer().hasMetadata("hermesboots")) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600000, main.getConfig().getInt("items.HermesBoots.speedStrength") - 1));
                }
                else {
                    event.getPlayer().setMetadata("hermesboots", meta);
                }
            }
            else if (event.getPlayer().hasMetadata("hermesboots")) {
                event.getPlayer().removeMetadata("hermesboots", Main.plugin);
                event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
            }
        }
        else if (event.getPlayer().hasMetadata("hermesboots")) {
            event.getPlayer().removeMetadata("hermesboots", Main.plugin);
            event.getPlayer().removePotionEffect(PotionEffectType.SPEED);
        }
        if (event.getPlayer().getEquipment().getBoots() != null) {
            if (event.getPlayer().getEquipment().getBoots().hasItemMeta() && event.getPlayer().getEquipment().getBoots().getItemMeta().hasDisplayName() && event.getPlayer().getEquipment().getBoots().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Livefire Boots")) {
                if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Livefire")) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.LivefireBoots.enabled")) {
                    return;
                }
                final Location loc3 = loc2.getBlock().getRelative(1, 0, 1).getLocation();
                final MetadataValue location = (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)event.getPlayer().getLocation());
                if (event.getPlayer().hasMetadata("livefire")) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600000, 10));
                    final Location locXY = (Location)event.getPlayer().getMetadata("livefire").get(0).value();
                    final int x = (int)locXY.getX();
                    final int y = (int)locXY.getY();
                    final int z = (int)locXY.getZ();
                    final int xs = (int)event.getPlayer().getLocation().getX();
                    final int ys = (int)event.getPlayer().getLocation().getY();
                    final int zs = (int)event.getPlayer().getLocation().getZ();
                    if (x != xs || z != zs) {
                        if (loc3.getBlock().getType().equals((Object)Material.AIR)) {
                            loc3.getBlock().setType(Material.FIRE);
                            final Location i = loc3;
                            new BukkitRunnable() {
                                public void run() {
                                    if (i.getBlock().getType().equals((Object)Material.FIRE)) {
                                        i.getWorld().playEffect(i, Effect.EXTINGUISH, 60);
                                        i.getBlock().setType(Material.AIR);
                                    }
                                }
                            }.runTaskLater(Main.plugin, (long)(main.getConfig().getInt("items.LivefireBoots.flameDuration") * 20));
                        }
                        event.getPlayer().setMetadata("livefire", location);
                    }
                }
                else {
                    event.getPlayer().setMetadata("livefire", location);
                }
            }
            else if (event.getPlayer().hasMetadata("livefire")) {
                event.getPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                event.getPlayer().removeMetadata("livefire", Main.plugin);
            }
        }
        else if (event.getPlayer().hasMetadata("livefire")) {
            event.getPlayer().removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            event.getPlayer().removeMetadata("livefire", Main.plugin);
        }
        final Location locY = event.getPlayer().getLocation().getBlock().getRelative(0, -1, 0).getLocation();
        if (locY.getBlock().hasMetadata("mine")) {
            if (locY.getBlock().getType().equals((Object)Material.AIR)) {
                locY.getBlock().removeMetadata("mine", Main.plugin);
                return;
            }
            ((TNTPrimed)event.getPlayer().getWorld().spawn(locY, (Class)TNTPrimed.class)).setFuseTicks(0);
            ((TNTPrimed)event.getPlayer().getWorld().spawn(locY, (Class)TNTPrimed.class)).setFuseTicks(0);
            ((TNTPrimed)event.getPlayer().getWorld().spawn(locY, (Class)TNTPrimed.class)).setFuseTicks(0);
            locY.getBlock().setType(Material.AIR);
            locY.getBlock().removeMetadata("mine", Main.plugin);
        }
        if (event.getPlayer().getLocation().getBlock().hasMetadata("mine")) {
            if (event.getPlayer().getLocation().getBlock().getType().equals((Object)Material.AIR)) {
                event.getPlayer().getLocation().getBlock().removeMetadata("mine", Main.plugin);
                return;
            }
            ((TNTPrimed)event.getPlayer().getWorld().spawn(locY, (Class)TNTPrimed.class)).setFuseTicks(0);
            ((TNTPrimed)event.getPlayer().getWorld().spawn(locY, (Class)TNTPrimed.class)).setFuseTicks(0);
            ((TNTPrimed)event.getPlayer().getWorld().spawn(locY, (Class)TNTPrimed.class)).setFuseTicks(0);
            event.getPlayer().getLocation().getBlock().setType(Material.AIR);
            event.getPlayer().getLocation().getBlock().removeMetadata("mine", Main.plugin);
        }
    }

    @EventHandler
    public void signPlaced(final SignChangeEvent e) {
        if (e.getLine(0).equals("[CustomEnchant]")) {
            if (Main.hasEconomy) {
                if (e.getPlayer().hasPermission("customenchantment.canMakeShop")) {
                    e.setLine(1, String.valueOf(e.getLine(1).substring(0, 1).toUpperCase()) + e.getLine(1).substring(1).toLowerCase());
                    if (Main.enchantments.contains(e.getLine(1))) {
                        e.setLine(1, String.valueOf(Main.lorePrefix) + e.getLine(1));
                        if (e.getLine(2).split(" ").length == 2) {
                            try {
                                Integer.parseInt(e.getLine(2).split(" ")[1]);
                            }
                            catch (Exception ex) {
                                e.getPlayer().sendMessage(ChatColor.RED + "The Price you entered is not a number");
                                return;
                            }
                            e.getPlayer().sendMessage(ChatColor.GREEN + "CE-Shop successfully set up!");
                        }
                        else {
                            e.getPlayer().sendMessage(ChatColor.RED + "Line 3 has to follow the format");
                            e.getPlayer().sendMessage(ChatColor.GOLD + "Price: [Price]");
                            e.setCancelled(true);
                        }
                    }
                    else if (e.getLine(1).equalsIgnoreCase("CLEAR-ENCHANTS")) {
                        e.setLine(1, ChatColor.DARK_RED + e.getLine(1));
                        e.getPlayer().sendMessage(ChatColor.GREEN + "Enchantment clearing sign successfully set up!");
                    }
                    else {
                        if (this.isItem(e.getLine(1))) {
                            e.setLine(1, ChatColor.AQUA + e.getLine(1));
                            if (e.getLine(2).split(" ").length == 2) {
                                try {
                                    Integer.parseInt(e.getLine(2).split(" ")[1]);
                                }
                                catch (Exception ex) {
                                    e.getPlayer().sendMessage(ChatColor.RED + "The Price you entered is not a number");
                                    return;
                                }
                                e.getPlayer().sendMessage(ChatColor.GREEN + "CE-Shop successfully set up!");
                            }
                            else {
                                e.getPlayer().sendMessage(ChatColor.RED + "Line 3 has to follow the format");
                                e.getPlayer().sendMessage(ChatColor.GOLD + "Price: [Price]");
                                e.setCancelled(true);
                            }
                            return;
                        }
                        e.getPlayer().sendMessage(ChatColor.RED + "Enchantment not found.");
                        e.getPlayer().sendMessage(ChatColor.RED + "Possible Enchantments: ");
                        e.getPlayer().sendMessage(ChatColor.GOLD + "-----------------------");
                        for (int i = 0; i < Main.enchantments.size(); ++i) {
                            e.getPlayer().sendMessage(ChatColor.GOLD + Main.enchantments.get(i));
                        }
                        e.getPlayer().sendMessage(ChatColor.GOLD + "-----------------------");
                        e.getPlayer().sendMessage(ChatColor.RED + "Or use CLEAR-ENCHANTS instead");
                        e.getPlayer().sendMessage(ChatColor.RED + "to make an enchantment clearing sign");
                        e.setCancelled(true);
                    }
                }
                else {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.RED + "You do not have permission to do that.");
                }
            }
            else {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.RED + "Vault has not been found on this Server.");
            }
        }
    }

    @EventHandler
    public void onBlockPlaced(final BlockPlaceEvent event) {
        final Plugin main = Main.plugin;
        if (event.getItemInHand().hasItemMeta()) {
            if (event.getItemInHand().getItemMeta().hasDisplayName()) {
                if (Main.hasWorldguard && ((WorldGuardPlugin)Main.pl).getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()) != null && !((WorldGuardPlugin)Main.pl).getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()).allows(DefaultFlag.PVP)) {
                    return;
                }
                if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Suicide Lever")) {
                    if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Suicidelever")) {
                        return;
                    }
                    if (!main.getConfig().getBoolean("items.Suicidelever.enabled")) {
                        return;
                    }
                    final Location l = event.getBlockPlaced().getLocation();
                    final Player p = event.getPlayer();
                    new BukkitRunnable() {
                        public void run() {
                            if (l.getBlock().getType().equals((Object)Material.LEVER)) {
                                for (int i = 0; i < Main.plugin.getConfig().getInt("items.Suicidelever.tntAmount"); ++i) {
                                    final TNTPrimed tnt = (TNTPrimed)l.getWorld().spawnEntity(l, EntityType.PRIMED_TNT);
                                    tnt.setYield((float)Main.plugin.getConfig().getInt("items.Suicidelever.strength"));
                                    tnt.setFuseTicks(0);
                                }
                            }
                        }
                    }.runTaskLater(Main.plugin, 5L);
                }
                if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Landmine")) {
                    if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Landmine")) {
                        return;
                    }
                    if (!main.getConfig().getBoolean("items.Landmine.enabled")) {
                        return;
                    }
                    event.getBlock().setMetadata("mine", (MetadataValue)new FixedMetadataValue(main, (Object)true));
                }
            }
            if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Beartrap")) {
                event.getBlock().setMetadata("beartrap", (MetadataValue)new FixedMetadataValue(main, (Object)true));
            }
            if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Prickly Block")) {
                event.getBlock().setMetadata("pricklytits", (MetadataValue)new FixedMetadataValue(main, (Object)true));
            }
            if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Piranha Trap")) {
                event.getBlock().setMetadata("piranhatrap", (MetadataValue)new FixedMetadataValue(main, (Object)true));
            }
            if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GREEN + "Poison Ivy")) {
                event.getBlock().setMetadata("poisonivy", (MetadataValue)new FixedMetadataValue(main, (Object)true));
            }
        }
    }

    @EventHandler
    public void onBlockDestroyed(final BlockBreakEvent event) {
        if (event.getBlock().hasMetadata("poisonivy")) {
            event.getBlock().removeMetadata("piranhatrap", Main.plugin);
        }
        if (event.getBlock().hasMetadata("beartrap") && !event.getPlayer().isOp()) {
            event.setCancelled(true);
            if (!this.hasCooldownDisarmMine.contains(event.getPlayer())) {
                final Random rand = new Random();
                final int i = rand.nextInt(100);
                if (i > 0 && i < 50) {
                    event.getPlayer().sendMessage(ChatColor.GREEN + "Trap disarmed!");
                    event.getBlock().removeMetadata("beartrap", Main.plugin);
                    event.getBlock().setType(Material.AIR);
                }
                else if (i > 50) {
                    event.getPlayer().sendMessage(ChatColor.RED + "Disarming failed!");
                    this.Cooldown(event.getPlayer(), this.hasCooldownDisarmMine, 200);
                }
            }
        }
        if (event.getBlock().hasMetadata("piranhatrap") && !event.getPlayer().isOp()) {
            event.setCancelled(true);
            if (!this.hasCooldownDisarmMine.contains(event.getPlayer())) {
                final Random rand = new Random();
                final int i = rand.nextInt(100);
                if (i > 0 && i < 50) {
                    event.getPlayer().sendMessage(ChatColor.GREEN + "Trap disarmed!");
                    event.getBlock().removeMetadata("piranhatrap", Main.plugin);
                    event.getBlock().setType(Material.AIR);
                }
                else if (i > 50) {
                    event.getPlayer().sendMessage(ChatColor.RED + "Disarming failed!");
                    this.Cooldown(event.getPlayer(), this.hasCooldownDisarmMine, 200);
                }
            }
        }
        if (event.getBlock().hasMetadata("mine") && !event.getPlayer().isOp()) {
            event.setCancelled(true);
            if (!this.hasCooldownDisarmMine.contains(event.getPlayer())) {
                final Random rand = new Random();
                final int i = rand.nextInt(100);
                if (i > 0 && i < 50) {
                    event.getPlayer().sendMessage(ChatColor.GREEN + "Mine disarmed!");
                    event.getBlock().removeMetadata("mine", Main.plugin);
                    event.getBlock().setType(Material.AIR);
                }
                else if (i > 50) {
                    event.getPlayer().sendMessage(ChatColor.RED + "Disarming failed!");
                    this.Cooldown(event.getPlayer(), this.hasCooldownDisarmMine, Main.plugin.getConfig().getInt("items.Landmine.disarmCooldown") * 20);
                }
            }
        }
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final Plugin main = Main.plugin;
        if (event.getPlayer().getItemInHand() != null) {
            if (event.getPlayer().getInventory().getBoots() != null && event.getPlayer().getInventory().getBoots().hasItemMeta() && event.getPlayer().getInventory().getBoots().getItemMeta().hasDisplayName() && event.getPlayer().getInventory().getBoots().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Rocket Boots")) {
                if (main.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.RocketBoots")) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.RocketBoots.enabled")) {
                    return;
                }
                if (event.getPlayer().getInventory().getBoots().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Power: " + ChatColor.GREEN + ChatColor.ITALIC + "ONLINE") && event.getPlayer().isSneaking() && (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK))) {
                    if (!event.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
                        int maxDurability = 0;
                        if (event.getPlayer().getInventory().getBoots().getType().equals((Object)Material.LEATHER_BOOTS)) {
                            maxDurability = 65;
                        }
                        if (event.getPlayer().getInventory().getBoots().getType().equals((Object)Material.GOLD_BOOTS)) {
                            maxDurability = 91;
                        }
                        if (event.getPlayer().getInventory().getBoots().getType().equals((Object)Material.DIAMOND_BOOTS)) {
                            maxDurability = 429;
                        }
                        if (event.getPlayer().getInventory().getBoots().getType().equals((Object)Material.IRON_BOOTS) || event.getPlayer().getInventory().getBoots().getType().equals((Object)Material.CHAINMAIL_BOOTS)) {
                            maxDurability = 195;
                        }
                        if (event.getPlayer().getInventory().getBoots().getDurability() == maxDurability * 0.75) {
                            event.getPlayer().sendMessage(ChatColor.RED + "Fuel at 25%");
                        }
                        if (event.getPlayer().getInventory().getBoots().getDurability() == maxDurability * 0.9) {
                            event.getPlayer().sendMessage(ChatColor.RED + "Fuel at 10%");
                        }
                        if (event.getPlayer().getInventory().getBoots().getDurability() == maxDurability * 0.95) {
                            event.getPlayer().sendMessage(ChatColor.RED + "Fuel at 5%");
                        }
                        if (event.getPlayer().getInventory().getBoots().getDurability() == maxDurability) {
                            final List<String> lore = (List<String>)event.getPlayer().getInventory().getBoots().getItemMeta().getLore();
                            if (lore.size() < 3) {
                                lore.add(2, ChatColor.RED + "Out of Fuel");
                            }
                            Main.setName(event.getPlayer().getInventory().getBoots(), event.getPlayer().getInventory().getBoots().getItemMeta().getDisplayName(), lore);
                            event.getPlayer().sendMessage(ChatColor.GRAY + "Out of Fuel");
                            event.getPlayer().updateInventory();
                            event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.BAT_TAKEOFF, 1.0f, 0.0f);
                            return;
                        }
                        event.getPlayer().getInventory().getBoots().setDurability((short)(event.getPlayer().getInventory().getBoots().getDurability() + 1));
                        event.getPlayer().updateInventory();
                    }
                    event.getPlayer().setFallDistance(0.0f);
                    event.getPlayer().setVelocity(event.getPlayer().getLocation().getDirection().setY(0.5));
                    event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 10, 10);
                    event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.SMOKE, 40, 40);
                    event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.FIRE, 5.0f, 5.0f);
                    this.allowFlight(event.getPlayer());
                }
            }
            final Player player = event.getPlayer();
            if ((player.getItemInHand().getType().equals((Object)Material.CHAINMAIL_BOOTS) || player.getItemInHand().getType().equals((Object)Material.IRON_BOOTS) || player.getItemInHand().getType().equals((Object)Material.DIAMOND_BOOTS) || player.getItemInHand().getType().equals((Object)Material.LEATHER_BOOTS) || player.getItemInHand().getType().equals((Object)Material.GOLD_BOOTS)) && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore() && player.getItemInHand().getItemMeta().hasDisplayName() && player.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Rocket Boots") && (event.getAction().equals((Object)Action.LEFT_CLICK_AIR) || event.getAction().equals((Object)Action.LEFT_CLICK_BLOCK))) {
                final List<String> lore = (List<String>)event.getPlayer().getItemInHand().getItemMeta().getLore();
                if (lore.contains(ChatColor.DARK_GRAY + "Power: " + ChatColor.RED + ChatColor.ITALIC + "OFFLINE")) {
                    if (lore.contains(ChatColor.RED + "Out of Fuel")) {
                        if (!this.isReloadingRocketboots.contains(event.getPlayer())) {
                            this.isReloadingRocketboots.add(event.getPlayer());
                            event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 1);
                            event.getPlayer().sendMessage(ChatColor.RED + "Reloading...");
                            lore.remove(2);
                            new BukkitRunnable() {
                                public void run() {
                                    if (event.getPlayer().getItemInHand().getDurability() == 0) {
                                        PlayerListener.this.isReloadingRocketboots.remove(event.getPlayer());
                                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK2, 1000);
                                        Main.setName(event.getPlayer().getItemInHand(), event.getPlayer().getItemInHand().getItemMeta().getDisplayName(), lore);
                                        this.cancel();
                                    }
                                    else {
                                        event.getPlayer().getItemInHand().setDurability((short)(event.getPlayer().getItemInHand().getDurability() - 1));
                                    }
                                }
                            }.runTaskTimer(main, 0L, 2L);
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.GREEN + "Rocket Boots activated.");
                        player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 10);
                        lore.set(1, ChatColor.DARK_GRAY + "Power: " + ChatColor.GREEN + ChatColor.ITALIC + "ONLINE");
                        Main.setName(event.getPlayer().getItemInHand(), event.getPlayer().getItemInHand().getItemMeta().getDisplayName(), lore);
                    }
                }
                else if (lore.contains(ChatColor.DARK_GRAY + "Power: " + ChatColor.GREEN + ChatColor.ITALIC + "ONLINE")) {
                    player.sendMessage(ChatColor.GREEN + "Rocket Boots " + ChatColor.RED + "deactivated.");
                    player.getWorld().playEffect(player.getLocation(), Effect.CLICK1, 10);
                    lore.set(1, ChatColor.DARK_GRAY + "Power: " + ChatColor.RED + ChatColor.ITALIC + "OFFLINE");
                    Main.setName(event.getPlayer().getItemInHand(), event.getPlayer().getItemInHand().getItemMeta().getDisplayName(), lore);
                }
            }
            if (event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals((Object)Material.IRON_BLOCK) && Main.plugin.getConfig().getBoolean("AntiMcMMOrepair") && event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasLore()) {
                final ItemMeta im = event.getPlayer().getItemInHand().getItemMeta();
                final List<String> lore2 = (List<String>)event.getPlayer().getItemInHand().getItemMeta().getLore();
                final int i = lore2.size();
                for (final String s : lore2) {
                    for (final String m : Main.enchantments) {
                        if (getEnchantmentName(m).equals(s)) {
                            lore2.remove(s);
                            event.getPlayer().sendMessage(ChatColor.MAGIC + "YourRunesaredisappearing!");
                        }
                    }
                }
                if (i != lore2.size()) {
                    event.getPlayer().sendMessage(ChatColor.GRAY + "Your Runes have disappeared!");
                    event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.GHAST_SCREAM, 0.5f, 0.5f);
                }
                im.setLore((List)lore2);
                event.getPlayer().getItemInHand().setItemMeta(im);
            }
            if ((event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) && event.getPlayer().isSneaking()) {
                final ItemStack a = player.getItemInHand();
                final ItemMeta im2 = a.getItemMeta();
                if (!Main.plugin.getConfig().getBoolean("enchantments.Autorepair.repairOnMoving") && a != null && a.hasItemMeta() && a.getItemMeta().hasLore() && a.getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Automatic Repair")) {
                    if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !player.hasPermission("customenchantment.Autorepair")) {
                        return;
                    }
                    if (!Main.plugin.getConfig().getBoolean("enchantments.Autorepair.enabled")) {
                        return;
                    }
                    final List<String> lore3 = (List<String>)a.getItemMeta().getLore();
                    final String message = new StringBuilder().append(ChatColor.GRAY).append(ChatColor.ITALIC).append("Repairing").toString();
                    if (lore3.contains(message)) {
                        player.sendMessage(ChatColor.GRAY + "AUTOREPAIR: " + ChatColor.RED + "DEACTIVATED.");
                        lore3.remove(message);
                        im2.setLore((List)lore3);
                        a.setItemMeta(im2);
                        return;
                    }
                    lore3.add(message);
                    im2.setLore((List)lore3);
                    a.setItemMeta(im2);
                    final int healAmount = Main.plugin.getConfig().getInt("enchantments.Autorepair.healAmount");
                    player.sendMessage(ChatColor.GRAY + "AUTOREPAIR: " + ChatColor.GREEN + "ACTIVATED.");
                    new BukkitRunnable() {
                        public void run() {
                            if (player != null && player.isOnline() && !player.isDead() && player.getInventory().contains(a) && lore3.contains(message)) {
                                if (a.getDurability() - healAmount >= 0) {
                                    a.setDurability((short)(a.getDurability() - healAmount));
                                }
                                else if (Main.plugin.getConfig().getBoolean("enchantments.Autorepair.stopAtFullDur")) {
                                    a.setDurability((short)0);
                                    this.cancel();
                                }
                            }
                            else {
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(Main.plugin, 0L, 20L);
                    if (a.getDurability() > 0) {
                        a.setDurability((short)(a.getDurability() - 1));
                    }
                }
            }
            if (event.getAction().equals((Object)Action.LEFT_CLICK_BLOCK) && event.getClickedBlock().hasMetadata("pricklytits")) {
                event.getPlayer().damage(2.0);
            }
            if (event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock().getType().equals((Object)Material.WALL_SIGN) || event.getClickedBlock().getType().equals((Object)Material.SIGN_POST) || event.getClickedBlock().getType().equals((Object)Material.SIGN))) {
                final Sign sign = (Sign)event.getClickedBlock().getState();
                if (sign.getLine(0).equals("[CustomEnchant]")) {
                    event.setCancelled(true);
                    String line = sign.getLine(1);
                    if (sign.getLine(1).equals("CLEAR-ENCHANTS")) {
                        final ItemMeta im3 = event.getPlayer().getItemInHand().getItemMeta();
                        im3.setLore((List)new ArrayList());
                        event.getPlayer().getItemInHand().setItemMeta(im3);
                        return;
                    }
                    line = ChatColor.stripColor(line);
                    String loreToAdds = String.valueOf(Main.lorePrefix) + line;
                    loreToAdds = getEnchantmentName(loreToAdds);
                    if (!event.getPlayer().hasPermission("customenchantment." + line)) {
                        event.getPlayer().sendMessage(ChatColor.RED + "You don't have permission to buy that!");
                        return;
                    }
                    final Economy ec = Main.econ;
                    final Double price = Double.parseDouble(sign.getLine(2).split(" ")[1]);
                    if (ec.getBalance(event.getPlayer().getName()) < price) {
                        event.getPlayer().sendMessage(ChatColor.RED + "You don't have enough money to buy that!");
                        event.getPlayer().sendMessage(ChatColor.RED + "You need " + price + " and only have " + ec.getBalance(event.getPlayer().getName()) + "!");
                        return;
                    }
                    if (player.getItemInHand().getType() == Material.AIR) {
                        event.getPlayer().sendMessage(ChatColor.RED + "You don't have an item selected.");
                        return;
                    }
                    List<String> lore4 = new ArrayList<String>();
                    if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore()) {
                        lore4 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                    }
                    final Material mat = player.getItemInHand().getType();
                    if (this.isItem(line)) {
                        final String item = line;
                        if (item.equals("Swimsuit") && !mat.toString().endsWith("HELMET") && !mat.toString().endsWith("CHESTPLATE") && !mat.toString().endsWith("LEGGINGS") && !mat.toString().endsWith("BOOTS")) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Armor");
                            return;
                        }
                        if ((item.equals("Powergloves") || item.equals("Medikit") || item.equals("Bandage") || item.equals("Deathscythe")) && (mat.toString().endsWith("HELMET") || mat.toString().endsWith("CHESTPLATE") || mat.toString().endsWith("LEGGINGS") || mat.toString().endsWith("BOOTS"))) {
                            player.sendMessage(ChatColor.RED + "This Item is not compatible with Armor");
                            return;
                        }
                        if ((sign.getLine(1).equals("Minigun") || sign.getLine(1).equals("Beastmaster") || sign.getLine(1).equals("Hookshot")) && !mat.equals((Object)Material.BOW)) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Bows");
                            return;
                        }
                        if ((sign.getLine(1).equals("Hermesboots") || sign.getLine(1).equals("Livefire") || sign.getLine(1).equals("Rocketboots") || sign.getLine(1).equals("Druidboots")) && !mat.toString().endsWith("BOOTS")) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Boots");
                            return;
                        }
                        if ((sign.getLine(1).equals("Beartrap") || sign.getLine(1).equals("Piranhatrap") || sign.getLine(1).equals("Poisonivy") || sign.getLine(1).equals("Prickly") || sign.getLine(1).equals("Landmine")) && !mat.isBlock()) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Blocks");
                            return;
                        }
                        if ((sign.getLine(1).equals("Thoraxe") || sign.getLine(1).equals("Pyroaxe")) && !mat.toString().endsWith("AXE")) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Axes");
                            return;
                        }
                        if (sign.getLine(1).equals("Healingshovel") && !mat.toString().endsWith("SPADE")) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Shovels");
                            return;
                        }
                        if (sign.getLine(1).equals("Assassin") && !mat.toString().endsWith("SWORD")) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Swords");
                            return;
                        }
                        if (sign.getLine(1).equals("Flamethrower") && !mat.equals((Object)Material.FLINT_AND_STEEL)) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Flint and Steel");
                            return;
                        }
                        if (sign.getLine(1).equals("Suicidelever") && !mat.equals((Object)Material.LEVER)) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with a Lever");
                            return;
                        }
                        if (sign.getLine(1).equals("Necromancer") && !mat.equals((Object)Material.BLAZE_ROD) && !mat.equals((Object)Material.STICK)) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with a Stick or a Blaze Rod");
                            return;
                        }
                        final String name = this.getItemName(line, player.getItemInHand());
                        lore4 = this.addItemLore(name, lore4);
                        if (player.getItemInHand().getItemMeta().hasDisplayName() && player.getItemInHand().getItemMeta().getDisplayName().equals(name)) {
                            player.sendMessage(ChatColor.RED + "You are already using this Item.");
                            return;
                        }
                        final EconomyResponse ecr = ec.withdrawPlayer(player.getName(), (double)price);
                        if (ecr.transactionSuccess()) {
                            Main.setName(player.getItemInHand(), name, lore4);
                            player.sendMessage(ChatColor.GREEN + "Successfully enchanted your item with " + sign.getLine(1) + ChatColor.GREEN + "!");
                            return;
                        }
                        player.sendMessage(ChatColor.RED + "Economy Error!");
                        return;
                    }
                    else {
                        if (line.equals("Blind") && (mat.toString().endsWith("HELMET") || mat.toString().endsWith("CHESTPLATE") || mat.toString().endsWith("LEGGINGS") || mat.toString().endsWith("BOOTS"))) {
                            player.sendMessage(ChatColor.RED + "This Item is not compatible with Armor");
                            return;
                        }
                        if ((line.equals("Bombardment") || sign.getLine(1).equals("Firework") || sign.getLine(1).equals("Lightning")) && !mat.equals((Object)Material.BOW)) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Bows");
                            return;
                        }
                        if ((line.equals("Hardened") || sign.getLine(1).equals("Molten") || sign.getLine(1).equals("Frozen") || sign.getLine(1).equals("Enlighted") || sign.getLine(1).equals("Obsidianshield") || sign.getLine(1).equals("Poisoned")) && !mat.toString().endsWith("HELMET") && !mat.toString().endsWith("CHESTPLATE") && !mat.toString().endsWith("LEGGINGS") && !mat.toString().endsWith("BOOTS")) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Armor");
                            return;
                        }
                        if ((line.equals("Gears") || sign.getLine(1).equals("Springs") || sign.getLine(1).equals("Stomp")) && !mat.toString().endsWith("BOOTS")) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Boots");
                            return;
                        }
                        if ((line.equals("Glowing") || sign.getLine(1).equals("Implants")) && !mat.toString().endsWith("HELMET")) {
                            player.sendMessage(ChatColor.RED + "This Item is only compatible with Helmets");
                            return;
                        }
                        String loreToAdd = String.valueOf(Main.lorePrefix) + line;
                        loreToAdd = getEnchantmentName(loreToAdd);
                        if (lore4.contains(loreToAdd)) {
                            player.sendMessage(ChatColor.RED + "You already have this Enchantment.");
                            return;
                        }
                        if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore() && Main.plugin.getConfig().getBoolean("restrictEnchantments")) {
                            int e = 0;
                            for (final String l : player.getItemInHand().getItemMeta().getLore()) {
                                for (final String r : Main.enchantments) {
                                    if (getEnchantmentName(r).equalsIgnoreCase(l)) {
                                        ++e;
                                    }
                                }
                            }
                            if (e >= Main.plugin.getConfig().getInt("maximumEnchants")) {
                                player.sendMessage(ChatColor.RED + "You already have the maximum number of Enchantments!");
                                return;
                            }
                        }
                        final EconomyResponse ecr2 = ec.withdrawPlayer(player.getName(), (double)price);
                        if (ecr2.transactionSuccess()) {
                            lore4.add(loreToAdd);
                            final ItemMeta im4 = player.getItemInHand().getItemMeta();
                            im4.setLore((List)lore4);
                            player.getItemInHand().setItemMeta(im4);
                            player.sendMessage(ChatColor.GREEN + "Successfully enchanted your item with " + sign.getLine(1) + "for " + price + "!");
                            player.sendMessage(new StringBuilder().append(ChatColor.GRAY).append(ChatColor.ITALIC).append("You now have: ").append(ChatColor.RESET).append(ChatColor.GREEN).append(ec.getBalance(event.getPlayer().getName())).append(ChatColor.GRAY).append(".").toString());
                            return;
                        }
                        player.sendMessage(ChatColor.RED + "Economy Error!");
                        return;
                    }
                }
            }
            // if (event.getPlayer().getItemInHand().getType().equals((Object)Material.PAPER) && event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().endsWith("Bandage") && (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK))) {
            //     if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Bandage")) {
            //         return;
            //     }
            //     if (!main.getConfig().getBoolean("items.Bandage.enabled")) {
            //         return;
            //     }
            //     if (!this.hasCooldownBandage.contains(event.getPlayer())) {
            //         if (!this.isUsingBandage.contains(event.getPlayer())) {
            //             if (!event.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
            //                 if (event.getPlayer().getHealth() < 20.0) {
            //                     int maxHeals = 0;
            //                     long period = 0L;
            //                     player.sendMessage(ChatColor.GREEN + "You succesfully applied a Bandage on yourself!");
            //                     if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Bandage")) {
            //                         maxHeals = 20;
            //                         period = 400 / maxHeals;
            //                     }
            //                     else {
            //                         final String[] bandage = event.getPlayer().getItemInHand().getItemMeta().getDisplayName().split(" ");
            //                         final String prefix = bandage[0];
            //                         if (prefix.equals(ChatColor.GREEN + "Minor")) {
            //                             maxHeals = 10;
            //                             period = 200 / maxHeals;
            //                         }
            //                         else if (prefix.equals(ChatColor.GREEN + "Tough")) {
            //                             maxHeals = 30;
            //                             period = 400 / maxHeals;
            //                         }
            //                         else if (prefix.equals(ChatColor.GREEN + "Heavy")) {
            //                             maxHeals = 50;
            //                             period = 400 / maxHeals;
            //                         }
            //                     }
            //                     final long maxHeals2 = maxHeals;
            //                     final ItemStack j = new ItemStack(Material.PAPER, event.getPlayer().getItemInHand().getAmount() - 1);
            //                     j.setItemMeta(event.getPlayer().getItemInHand().getItemMeta());
            //                     player.getInventory().setItemInHand(j);
            //                     this.isUsingBandage.add(event.getPlayer());
            //                     new BukkitRunnable(maxHeals2) {
            //                         long maxTime3 = maxTime3;

            //                         public void run() {
            //                             if (!event.getPlayer().isDead()) {
            //                                 if (this.maxTime3 >= 0L) {
            //                                     if (event.getPlayer().getHealth() + 1.0 <= event.getPlayer().getMaxHealth()) {
            //                                         event.getPlayer().setHealth(event.getPlayer().getHealth() + 1.0);
            //                                         --this.maxTime3;
            //                                     }
            //                                 }
            //                                 else {
            //                                     PlayerListener.this.isUsingBandage.remove(event.getPlayer());
            //                                     PlayerListener.this.Cooldown(event.getPlayer(), PlayerListener.this.hasCooldownBandage, main.getConfig().getInt("items.Bandage.Cooldown(self)") * 20);
            //                                     this.cancel();
            //                                 }
            //                             }
            //                             else {
            //                                 PlayerListener.this.isUsingBandage.remove(event.getPlayer());
            //                                 this.cancel();
            //                             }
            //                         }
            //                     }.runTaskTimer(main, 0L, period);
            //                 }
            //                 else {
            //                     event.getPlayer().sendMessage(ChatColor.RED + "You already have full HP!");
            //                 }
            //             }
            //         }
            //         else {
            //             event.getPlayer().sendMessage(ChatColor.RED + "You are already using a Bandage!");
            //         }
            //     }
            //     else {
            //         event.getPlayer().sendMessage(ChatColor.RED + "You cannot use a Bandage right now.");
            //     }
            // }
            if (event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Medikit")) {
                if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Medikit")) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.Medikit.enabled")) {
                    return;
                }
                if (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) {
                    if (!this.hasCooldownMedikit.contains(event.getPlayer())) {
                        if (event.getPlayer().getHealth() < 20.0) {
                            if (!event.getPlayer().isDead()) {
                                event.getPlayer().sendMessage(ChatColor.GREEN + "You have used a Medikit!");
                                event.getPlayer().setHealth(20.0);
                                final ItemStack k = new ItemStack(event.getPlayer().getItemInHand().getType(), event.getPlayer().getItemInHand().getAmount() - 1);
                                k.setItemMeta(event.getPlayer().getItemInHand().getItemMeta());
                                event.getPlayer().getInventory().setItemInHand(k);
                                this.Cooldown(event.getPlayer(), this.hasCooldownMedikit, main.getConfig().getInt("items.Medikit.Cooldown") * 20);
                            }
                        }
                        else {
                            event.getPlayer().sendMessage(ChatColor.RED + "You already have full HP!");
                        }
                    }
                    else {
                        event.getPlayer().sendMessage(ChatColor.RED + "You have recently used a Medikit!");
                    }
                }
            }
            if (Main.hasWorldguard && ((WorldGuardPlugin)Main.pl).getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()) != null && !((WorldGuardPlugin)Main.pl).getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()).allows(DefaultFlag.PVP)) {
                return;
            }
            if (event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Potion Launcher")) {
                if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Potionlauncher")) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.Potionlauncher.enabled")) {
                    return;
                }
                if (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) {
                    final int i2 = event.getPlayer().getInventory().first(event.getPlayer().getItemInHand());
                    final ItemStack potion = event.getPlayer().getInventory().getItem(i2 + 1);
                    if (potion != null && potion.getType().equals((Object)Material.POTION)) {
                        final ThrownPotion tp = (ThrownPotion)event.getPlayer().launchProjectile((Class)ThrownPotion.class);
                        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.EXPLODE, 1.0f, 10.0f);
                        tp.setItem(potion);
                        tp.setBounce(false);
                        tp.setVelocity(event.getPlayer().getLocation().getDirection().multiply(4));
                        if (!event.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
                            potion.setAmount(potion.getAmount() - 1);
                            event.getPlayer().getInventory().setItem(i2 + 1, potion);
                            event.getPlayer().updateInventory();
                        }
                    }
                    else {
                        event.getPlayer().sendMessage(ChatColor.RED + "You need a Potion in the Slot to the right of the Launcher!");
                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 1);
                    }
                }
            }
            if (event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Flamethrower")) {
                if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Flamethrower")) {
                    return;
                }
                if (Main.hasWorldguard && ((WorldGuardPlugin)Main.pl).getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()) != null && !((WorldGuardPlugin)Main.pl).getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()).allows(DefaultFlag.PVP)) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.Flamethrower.enabled")) {
                    return;
                }
                if (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) {
                    event.setCancelled(true);
                    if (!this.isReloadingFlamethrower.contains(event.getPlayer())) {
                        if (event.getPlayer().getItemInHand().getDurability() >= 64) {
                            if (main.getConfig().getBoolean("items.Flamethrower.isReloadable")) {
                                this.isReloadingFlamethrower.add(event.getPlayer());
                                event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 1);
                                event.getPlayer().sendMessage(ChatColor.RED + "Reloading...");
                                new BukkitRunnable() {
                                    public void run() {
                                        if (event.getPlayer().getItemInHand().getDurability() == 0) {
                                            PlayerListener.this.isReloadingFlamethrower.remove(event.getPlayer());
                                            event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK2, 1000);
                                            this.cancel();
                                        }
                                        else {
                                            event.getPlayer().getItemInHand().setDurability((short)(event.getPlayer().getItemInHand().getDurability() - 1));
                                        }
                                    }
                                }.runTaskTimer(main, 0L, 2L);
                            }
                            else {
                                event.getPlayer().getItemInHand().setType(Material.AIR);
                            }
                        }
                        else {
                            final List<Location> list = this.getLinePlayer(event.getPlayer(), 6);
                            for (final Location l2 : list) {
                                if (l2.getBlock().getType().equals((Object)Material.AIR)) {
                                    l2.getBlock().setType(Material.FIRE);
                                }
                                l2.getWorld().playEffect(l2, Effect.SMOKE, 20);
                                final FallingBlock fire = l2.getWorld().spawnFallingBlock(l2, Material.FIRE.getId(), (byte)0);
                                fire.setDropItem(false);
                                fire.setVelocity(event.getPlayer().getLocation().getDirection());
                                new BukkitRunnable() {
                                    public void run() {
                                        if (fire.isDead()) {
                                            list.add(fire.getLocation());
                                            this.cancel();
                                        }
                                        else {
                                            if (fire.getLocation().getBlock().getType().equals((Object)Material.WATER) || fire.getLocation().getBlock().getType().equals((Object)Material.STATIONARY_WATER)) {
                                                fire.getWorld().playEffect(fire.getLocation(), Effect.EXTINGUISH, 60);
                                                fire.remove();
                                                this.cancel();
                                            }
                                            for (final Entity ent : fire.getNearbyEntities(0.0, 0.0, 0.0)) {
                                                if (ent != event.getPlayer()) {
                                                    ent.setFireTicks(main.getConfig().getInt("items.Flamethrower.fireDuration") * 20);
                                                }
                                            }
                                        }
                                    }
                                }.runTaskTimer(main, 0L, 1L);
                            }
                            new BukkitRunnable() {
                                public void run() {
                                    for (final Location ls : list) {
                                        if (ls.getBlock().getType().equals((Object)Material.FIRE)) {
                                            ls.getWorld().playEffect(ls, Effect.EXTINGUISH, 60);
                                            ls.getBlock().setType(Material.AIR);
                                        }
                                    }
                                }
                            }.runTaskLater(main, 200L);
                            event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.BLAZE_SHOOT, 1);
                            if (!event.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
                                event.getPlayer().getItemInHand().setDurability((short)(event.getPlayer().getItemInHand().getDurability() + 1));
                            }
                        }
                    }
                }
            }
            if (event.getPlayer().getItemInHand().getType().name().endsWith("AXE") && event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Thor's Axe") && !this.hasCooldownThor.contains(event.getPlayer()) && (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK))) {
                if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Thoraxe")) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.ThorsAxe.enabled")) {
                    return;
                }
                if (event.getClickedBlock() != null) {
                    final Location loc = event.getClickedBlock().getLocation();
                    loc.setY(loc.getY() + 1.0);
                    if (loc.getBlock().getType().equals((Object)Material.AIR)) {
                        loc.getBlock().setType(Material.FIRE);
                        loc.clone();
                    }
                    loc.setX(loc.getX() + 1.0);
                    if (loc.getBlock().getType().equals((Object)Material.AIR)) {
                        loc.getBlock().setType(Material.FIRE);
                        loc.clone();
                    }
                    loc.setX(loc.getX() - 2.0);
                    if (loc.getBlock().getType().equals((Object)Material.AIR)) {
                        loc.getBlock().setType(Material.FIRE);
                        loc.clone();
                    }
                    loc.setX(loc.getX() + 1.0);
                    loc.setZ(loc.getZ() + 1.0);
                    if (loc.getBlock().getType().equals((Object)Material.AIR)) {
                        loc.getBlock().setType(Material.FIRE);
                        loc.clone();
                    }
                    loc.setZ(loc.getZ() - 2.0);
                    if (loc.getBlock().getType().equals((Object)Material.AIR)) {
                        loc.getBlock().setType(Material.FIRE);
                        loc.clone();
                    }
                    event.getPlayer().getWorld().strikeLightning(event.getClickedBlock().getLocation());
                    event.getPlayer().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.ENDERDRAGON_GROWL, 10.0f, 1.0f);
                    if (!event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_PURPLE + "Ultimate")) {
                        event.getPlayer().getItemInHand().setDurability((short)(event.getPlayer().getItemInHand().getDurability() + 1));
                        this.Cooldown(event.getPlayer(), this.hasCooldownThor, main.getConfig().getInt("items.ThorsAxe.Cooldown") * 20);
                    }
                    new BukkitRunnable() {
                        public void run() {
                            final Location loc = event.getClickedBlock().getLocation();
                            loc.setY(loc.getY() + 1.0);
                            if (loc.getBlock().getType().equals((Object)Material.FIRE)) {
                                loc.getBlock().setType(Material.AIR);
                            }
                            loc.setX(loc.getX() + 1.0);
                            if (loc.getBlock().getType().equals((Object)Material.FIRE)) {
                                loc.getBlock().setType(Material.AIR);
                            }
                            loc.setX(loc.getX() - 2.0);
                            if (loc.getBlock().getType().equals((Object)Material.FIRE)) {
                                loc.getBlock().setType(Material.AIR);
                            }
                            loc.setX(loc.getX() + 1.0);
                            loc.setZ(loc.getZ() + 1.0);
                            if (loc.getBlock().getType().equals((Object)Material.FIRE)) {
                                loc.getBlock().setType(Material.AIR);
                            }
                            loc.setZ(loc.getZ() - 2.0);
                            if (loc.getBlock().getType().equals((Object)Material.FIRE)) {
                                loc.getBlock().setType(Material.AIR);
                            }
                            this.cancel();
                        }
                    }.runTaskLater(Main.plugin, (long)(main.getConfig().getInt("items.ThorsAxe.fireDuration") * 20));
                }
            }
            if (event.getPlayer().getItemInHand().getType().name().endsWith("HOE") && event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.DARK_GRAY + "Death's Scythe")) {
                if (!main.getConfig().getBoolean("items.Deathscythe.enabled")) {
                    return;
                }
                if (!this.hasCooldownScythe.contains(event.getPlayer())) {
                    if (event.getAction().equals((Object)Action.LEFT_CLICK_AIR)) {
                        for (final Location l3 : this.getLinePlayer(event.getPlayer(), main.getConfig().getInt("items.Deathscythe.range"))) {
                            for (final Entity ent : event.getPlayer().getNearbyEntities((double)main.getConfig().getInt("items.Deathscythe.range"), (double)main.getConfig().getInt("items.Deathscythe.range"), (double)main.getConfig().getInt("items.Deathscythe.range"))) {
                                final Location ls = ent.getLocation();
                                ls.setY(ent.getLocation().getY() - 3.0);
                                final Location lss = ent.getLocation();
                                lss.setY(ent.getLocation().getY() - 1.0);
                                final Location lsss = ent.getLocation();
                                lsss.setY(ent.getLocation().getY() - 2.0);
                                if (this.Positive((int)(l3.getX() - ent.getLocation().getX())) < 1.5 && this.Positive((int)(l3.getY() - ent.getLocation().getY())) < 3 && this.Positive((int)(l3.getZ() - ent.getLocation().getZ())) < 1.5) {
                                    final int x = event.getPlayer().getLocation().getBlockX() - ent.getLocation().getBlockX();
                                    final int z = event.getPlayer().getLocation().getBlockZ() - ent.getLocation().getBlockZ();
                                    final int y = event.getPlayer().getLocation().getBlockY() - ent.getLocation().getBlockY();
                                    if (ent instanceof Player && Main.hasWorldguard && ((WorldGuardPlugin)Main.pl).getRegionManager(ent.getWorld()).getApplicableRegions(ent.getLocation()) != null && !((WorldGuardPlugin)Main.pl).getRegionManager(ent.getWorld()).getApplicableRegions(ent.getLocation()).allows(DefaultFlag.PVP)) {
                                        return;
                                    }
                                    ent.setVelocity(new Vector(x / 4, this.Positive(y / 6), z / 4));
                                    try {
                                        ((LivingEntity)ent).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, main.getConfig().getInt("items.Deathscythe.debuffDuration"), main.getConfig().getInt("items.Deathscythe.debuffStrength")));
                                    }
                                    catch (ClassCastException ex) {}
                                }
                            }
                        }
                        this.Cooldown(event.getPlayer(), this.hasCooldownScythe, main.getConfig().getInt("items.Deathscythe.cooldown") * 20);
                    }
                }
                else if (!this.stopSpam.contains(event.getPlayer())) {
                    event.getPlayer().sendMessage(ChatColor.RED + "The Scythe is not ready yet...");
                    this.stopSpam.add(event.getPlayer());
                }
            }
            if (event.getPlayer().getItemInHand().getType().equals((Object)Material.QUARTZ) && event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Power Gloves")) {
                if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Powergloves")) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.Powergloves.enabled")) {
                    return;
                }
                if (this.CarryingPlayer.contains(event.getPlayer()) && (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK))) {
                    if (event.getPlayer().getPassenger() != null) {
                        final Entity passenger = event.getPlayer().getPassenger();
                        event.getPlayer().getPassenger().leaveVehicle();
                        passenger.setVelocity(event.getPlayer().getLocation().getDirection().multiply(Main.plugin.getConfig().getInt("items.Powergloves.ThrowStrength")));
                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.ZOMBIE_DESTROY_DOOR, 1000, 1000);
                        this.Cooldown(event.getPlayer(), this.hasCooldownPowergloves, main.getConfig().getInt("items.Powergloves.Cooldown") * 20);
                    }
                    this.CarryingPlayer.remove(event.getPlayer());
                    this.isCarryingPlayer.remove(event.getPlayer());
                }
            }
            if (event.getPlayer().getItemInHand().getType().equals((Object)Material.STICK) && event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Necromancer's Staff of Destruction")) {
                if (main.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Necromancer")) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.NecromancerStaff.enabled")) {
                    return;
                }
                final List<String> lore = (List<String>)event.getPlayer().getItemInHand().getItemMeta().getLore();
                if (event.getAction().equals((Object)Action.LEFT_CLICK_AIR) || event.getAction().equals((Object)Action.LEFT_CLICK_BLOCK)) {
                    if (event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_PURPLE + "Spell: Wither's Apprentice")) {
                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 10);
                        lore.set(0, ChatColor.DARK_PURPLE + "Spell: Fireball");
                        Main.setName(event.getPlayer().getItemInHand(), event.getPlayer().getItemInHand().getItemMeta().getDisplayName(), lore);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Changed Spell to " + ChatColor.DARK_RED + "Fireball");
                    }
                    else if (event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_PURPLE + "Spell: Fireball")) {
                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 10);
                        lore.set(0, ChatColor.DARK_PURPLE + "Spell: Lightning Strike");
                        Main.setName(event.getPlayer().getItemInHand(), event.getPlayer().getItemInHand().getItemMeta().getDisplayName(), lore);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Changed Spell to " + ChatColor.DARK_BLUE + "Lightning Strike");
                    }
                    else if (event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_PURPLE + "Spell: Lightning Strike")) {
                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 10);
                        lore.set(0, ChatColor.DARK_PURPLE + "Spell: Wither's Apprentice");
                        Main.setName(event.getPlayer().getItemInHand(), event.getPlayer().getItemInHand().getItemMeta().getDisplayName(), lore);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Changed Spell to " + ChatColor.DARK_GRAY + "Wither's Apprentice");
                    }
                    else if (!event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_PURPLE + "Spell: Lightning Strike") || event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_PURPLE + "Spell: Fireball") || event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_PURPLE + "Spell: Wither's Apprentice")) {
                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 10);
                        lore.set(0, ChatColor.DARK_PURPLE + "Spell: Fireball");
                        Main.setName(event.getPlayer().getItemInHand(), event.getPlayer().getItemInHand().getItemMeta().getDisplayName(), lore);
                    }
                }
                else if (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) {
                    if (event.getPlayer().getItemInHand().getItemMeta().hasLore()) {
                        if (!this.hasCooldownStaff.contains(event.getPlayer())) {
                            final int WitherCost = main.getConfig().getInt("items.NecromancerStaff.Cost(Wither)");
                            final int FireballCost = main.getConfig().getInt("items.NecromancerStaff.Cost(Fireball)");
                            final int LightningCost = main.getConfig().getInt("items.NecromancerStaff.Cost(Lightning)");
                            final int WitherCooldown = main.getConfig().getInt("items.NecromancerStaff.Cooldown(Wither)") * 20;
                            final int FireballCooldown = main.getConfig().getInt("items.NecromancerStaff.Cooldown(Fireball)") * 20;
                            final int LightningCooldown = main.getConfig().getInt("items.NecromancerStaff.Cooldown(Lightning)") * 20;
                            if (event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_PURPLE + "Spell: Wither's Apprentice")) {
                                if (event.getPlayer().getInventory().contains(Material.REDSTONE, WitherCost)) {
                                    if (!event.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
                                        final ItemStack mana = new ItemStack(Material.REDSTONE, WitherCost);
                                        event.getPlayer().getInventory().removeItem(new ItemStack[] { mana });
                                        event.getPlayer().updateInventory();
                                    }
                                    ((WitherSkull)event.getPlayer().launchProjectile((Class)WitherSkull.class)).setVelocity(event.getPlayer().getLocation().getDirection().multiply(2));
                                    event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.WITHER_IDLE, 10.0f, 10.0f);
                                    this.Cooldown(event.getPlayer(), this.hasCooldownStaff, WitherCooldown);
                                }
                                else {
                                    event.getPlayer().sendMessage(ChatColor.RED + "You don't have enough Redstone, you need " + WitherCost + " to cast this spell.");
                                }
                            }
                            if (event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_PURPLE + "Spell: Fireball")) {
                                if (event.getPlayer().getInventory().contains(Material.REDSTONE, FireballCost)) {
                                    if (!event.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
                                        final ItemStack mana = new ItemStack(Material.REDSTONE, FireballCost);
                                        event.getPlayer().getInventory().removeItem(new ItemStack[] { mana });
                                        event.getPlayer().updateInventory();
                                    }
                                    ((SmallFireball)event.getPlayer().launchProjectile((Class)SmallFireball.class)).setVelocity(event.getPlayer().getLocation().getDirection().multiply(2));
                                    event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.BLAZE_HIT, 10.0f, 0.0f);
                                    this.Cooldown(event.getPlayer(), this.hasCooldownStaff, FireballCooldown);
                                }
                                else {
                                    event.getPlayer().sendMessage(ChatColor.RED + "You don't have enough Redstone, you need " + FireballCost + " to cast this spell.");
                                }
                            }
                            if (event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_PURPLE + "Spell: Lightning Strike")) {
                                if (event.getPlayer().getInventory().contains(Material.REDSTONE, LightningCost)) {
                                    if (!event.getPlayer().getTargetBlock((HashSet<Byte>)null, 20).getType().equals((Object)Material.AIR)) {
                                        if (!event.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE)) {
                                            final ItemStack mana = new ItemStack(Material.REDSTONE, LightningCost);
                                            event.getPlayer().getInventory().removeItem(new ItemStack[] { mana });
                                            event.getPlayer().updateInventory();
                                        }
                                        event.getPlayer().getWorld().strikeLightning(event.getPlayer().getTargetBlock((HashSet<Byte>)null, 20).getLocation());
                                        event.getPlayer().getWorld().createExplosion(event.getPlayer().getTargetBlock((HashSet<Byte>)null, 20).getLocation(), 1.0f);
                                        event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENDERDRAGON_GROWL, 10.0f, 9999.0f);
                                        this.Cooldown(event.getPlayer(), this.hasCooldownStaff, LightningCooldown);
                                    }
                                    else {
                                        event.getPlayer().sendMessage(ChatColor.RED + "Nothing in Range!");
                                    }
                                }
                                else {
                                    event.getPlayer().sendMessage(ChatColor.RED + "You don't have enough Redstone, you need 20 to cast this spell.");
                                }
                            }
                        }
                        else if (!this.stopSpam.contains(event.getPlayer())) {
                            event.getPlayer().sendMessage(ChatColor.RED + "You are not strong enough to do this yet!");
                            this.stopSpam.add(event.getPlayer());
                        }
                    }
                }
                else {
                    event.getPlayer().getItemInHand().getItemMeta().setLore((List)lore);
                }
            }
            if (event.getPlayer().getItemInHand().getType().equals((Object)Material.BOW) && event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Hookshot Bow")) {
                if (main.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Hookshot")) {
                    return;
                }
                if ((event.getAction().equals((Object)Action.LEFT_CLICK_AIR) || event.getAction().equals((Object)Action.LEFT_CLICK_BLOCK)) && event.getPlayer().getItemInHand().getItemMeta().hasLore()) {
                    final List<String> lore = (List<String>)event.getPlayer().getItemInHand().getItemMeta().getLore();
                    if (event.getPlayer().getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Mode: Push")) {
                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 10);
                        lore.set(2, ChatColor.DARK_GRAY + "Mode: Pull");
                        Main.setName(event.getPlayer().getItemInHand(), event.getPlayer().getItemInHand().getItemMeta().getDisplayName(), lore);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Changed Hookshot Mode to " + ChatColor.DARK_GRAY + "Pull");
                    }
                    else {
                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 10);
                        lore.set(2, ChatColor.DARK_GRAY + "Mode: Push");
                        Main.setName(event.getPlayer().getItemInHand(), event.getPlayer().getItemInHand().getItemMeta().getDisplayName(), lore);
                        event.getPlayer().sendMessage(ChatColor.GREEN + "Changed Hookshot Mode to " + ChatColor.DARK_GRAY + "Push");
                    }
                }
            }
        }
        if (event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasLore() && event.getPlayer().getItemInHand().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Block")) {
            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Block")) {
                return;
            }
            if (!main.getConfig().getBoolean("enchantments.Block.enabled")) {
                return;
            }
            if ((event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) && !this.hasCooldownBlock.contains(event.getPlayer())) {
                if (main.getConfig().getInt("enchantments.Block.duration") <= 0) {
                    return;
                }
                if (!this.hasCooldownBlock.contains(event.getPlayer())) {
                    event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.ANVIL_LAND, 10.0f, 10.0f);
                    new BukkitRunnable() {
                        PotionEffect resistance = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20 * Main.plugin.getConfig().getInt("enchantments.Block.duration"), Main.plugin.getConfig().getInt("enchantments.Block.strength"));

                        public void run() {
                            if (event.getPlayer().isBlocking()) {
                                event.getPlayer().addPotionEffect(this.resistance);
                            }
                            else {
                                PlayerListener.this.Cooldown(event.getPlayer(), PlayerListener.this.hasCooldownBlock, main.getConfig().getInt("enchantments.Block.cooldown") * 20);
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(main, 0L, 0L);
                }
            }
            if (event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Assassin's Blade") && (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK))) {
                if (main.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Assassin")) {
                    return;
                }
                if (!main.getConfig().getBoolean("items.Assassin.enabled")) {
                    return;
                }
                if (main.getConfig().getInt("items.Assassin.Length(Use)") <= 0) {
                    return;
                }
                if (!this.hasCooldownAssassin.contains(event.getPlayer()) && !this.hasCooldownAssassinHit.contains(event.getPlayer())) {
                    event.getPlayer().sendMessage(ChatColor.DARK_GRAY + "You enter the Shadows");
                    if (this.AssassinInvisible.contains(event.getPlayer())) {
                        Player[] onlinePlayers;
                        for (int length = (onlinePlayers = (Player[])Bukkit.getOnlinePlayers().toArray()).length, n = 0; n < length; ++n) {
                            final Player p = onlinePlayers[n];
                            p.showPlayer(event.getPlayer());
                        }
                        event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
                        this.AssassinInvisible.remove(event.getPlayer());
                        return;
                    }
                    this.AssassinInvisible.add(event.getPlayer());
                    Player[] onlinePlayers2;
                    for (int length2 = (onlinePlayers2 = (Player[])Bukkit.getOnlinePlayers().toArray()).length, n2 = 0; n2 < length2; ++n2) {
                        final Player p = onlinePlayers2[n2];
                        p.hidePlayer(event.getPlayer());
                    }
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, main.getConfig().getInt("items.Assassin.Length(Use)") * 20, 5));
                    this.Cooldown(event.getPlayer(), this.hasCooldownAssassin, main.getConfig().getInt("items.Assassin.Cooldown(Use)") * 20);
                    new BukkitRunnable() {
                        public void run() {
                            Player[] onlinePlayers;
                            for (int length = (onlinePlayers = (Player[])Bukkit.getOnlinePlayers().toArray()).length, i = 0; i < length; ++i) {
                                final Player p = onlinePlayers[i];
                                p.showPlayer(event.getPlayer());
                            }
                            event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
                            PlayerListener.this.AssassinInvisible.remove(event.getPlayer());
                            this.cancel();
                        }
                    }.runTaskTimer(main, (long)(main.getConfig().getInt("items.Assassin.Length(Use)") * 20), 0L);
                }
            }
        }
    }

    @EventHandler
    public void playerInteractwithEntity(final PlayerInteractEntityEvent event) {
        final Plugin main = Main.plugin;
        if (main.getConfig().getBoolean("enchantments.Gooey.rightclick")) {
            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Gooey")) {
                return;
            }
            if (!main.getConfig().getBoolean("enchantments.Gooey.enabled")) {
                return;
            }
            if (Main.hasWorldguard && ((WorldGuardPlugin)Main.pl).getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()) != null && !((WorldGuardPlugin)Main.pl).getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()).allows(DefaultFlag.PVP)) {
                return;
            }
            if (event.getPlayer().isSneaking() && event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasLore() && event.getPlayer().getItemInHand().getItemMeta().getLore().contains(String.valueOf(Main.lorePrefix) + "Gooey")) {
                try {
                    final LivingEntity clicked = (LivingEntity)event.getRightClicked();
                    clicked.setVelocity(new Vector(0, main.getConfig().getInt("enchantments.Gooey.heightMultiplier"), 0));
                    clicked.getWorld().playEffect(clicked.getLocation(), Effect.SMOKE, 60);
                    clicked.getWorld().playSound(clicked.getLocation(), Sound.MAGMACUBE_JUMP, 10.0f, 10.0f);
                }
                catch (ClassCastException ex) {}
            }
        }
        // if (event.getPlayer().getItemInHand().getType().equals((Object)Material.PAPER) && event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().endsWith("Bandage")) {
        //     if (Main.plugin.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Bandage")) {
        //         return;
        //     }
        //     if (!main.getConfig().getBoolean("items.Bandage.enabled")) {
        //         return;
        //     }
        //     if (event.getRightClicked() instanceof Player) {
        //         final Player clicked2 = (Player)event.getRightClicked();
        //         if (!this.hasCooldownBandage.contains(clicked2)) {
        //             if (!this.isUsingBandage.contains(clicked2)) {
        //                 if (!clicked2.getGameMode().equals((Object)GameMode.CREATIVE)) {
        //                     if (clicked2.getHealth() < 20.0) {
        //                         int maxTime = 0;
        //                         long healed = 0L;
        //                         final Player player = event.getPlayer();
        //                         if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Bandage")) {
        //                             maxTime = 20;
        //                             healed = 40L;
        //                         }
        //                         else {
        //                             final String[] bandage = event.getPlayer().getItemInHand().getItemMeta().getDisplayName().split(" ");
        //                             final String prefix = bandage[0];
        //                             if (prefix.equals(ChatColor.GREEN + "Minor")) {
        //                                 maxTime = 10;
        //                                 healed = 20L;
        //                             }
        //                             else if (prefix.equals(ChatColor.GREEN + "Tough")) {
        //                                 maxTime = 20;
        //                                 healed = 26L;
        //                             }
        //                             else if (prefix.equals(ChatColor.GREEN + "Heavy")) {
        //                                 maxTime = 20;
        //                                 healed = 16L;
        //                             }
        //                         }
        //                         final long maxTime2 = maxTime;
        //                         final long healed2 = healed;
        //                         final ItemStack i = new ItemStack(Material.PAPER, event.getPlayer().getItemInHand().getAmount() - 1);
        //                         i.setItemMeta(event.getPlayer().getItemInHand().getItemMeta());
        //                         player.getInventory().setItemInHand(i);
        //                         this.isUsingBandage.add(clicked2);
        //                         new BukkitRunnable(maxTime2, healed2) {
        //                             long maxTime3 = maxTime3;
        //                             long healed2 = healed2;

        //                             public void run() {
        //                                 if (this.maxTime3 >= 0L) {
        //                                     if (clicked2.getHealth() + 1.0 <= 20.0) {
        //                                         clicked2.setHealth(event.getPlayer().getHealth() + 1.0);
        //                                         this.maxTime3 -= this.healed2 / 20L;
        //                                     }
        //                                 }
        //                                 else {
        //                                     PlayerListener.this.isUsingBandage.remove(event.getRightClicked());
        //                                     PlayerListener.this.Cooldown(clicked2, PlayerListener.this.hasCooldownBandage, main.getConfig().getInt("items.Bandage.Cooldown(Others)") * 20);
        //                                     this.cancel();
        //                                 }
        //                             }
        //                         }.runTaskTimer(main, 0L, healed);
        //                     }
        //                     else {
        //                         event.getPlayer().sendMessage(ChatColor.RED + "The Player is already at full HP!");
        //                     }
        //                 }
        //             }
        //             else {
        //                 event.getPlayer().sendMessage(ChatColor.RED + "The Player is already using a Bandage!");
        //             }
        //         }
        //         else {
        //             event.getPlayer().sendMessage(ChatColor.RED + "That Player cannot use a Bandage right now.");
        //         }
        //     }
        // }
        if (event.getPlayer().getItemInHand().getType().equals((Object)Material.QUARTZ) && event.getPlayer().getItemInHand().getItemMeta().hasDisplayName() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Power Gloves")) {
            if (main.getConfig().getBoolean("enchantments.requirePermissions") && !event.getPlayer().hasPermission("customenchantment.Powergloves")) {
                return;
            }
            event.setCancelled(true);
            if (Main.hasWorldguard && ((WorldGuardPlugin)Main.pl).getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()) != null && !((WorldGuardPlugin)Main.pl).getRegionManager(event.getPlayer().getWorld()).getApplicableRegions(event.getPlayer().getLocation()).allows(DefaultFlag.PVP)) {
                return;
            }
            if (!main.getConfig().getBoolean("items.Powergloves.enabled")) {
                return;
            }
            if (!event.getRightClicked().getType().equals((Object)EntityType.PAINTING) && !event.getRightClicked().getType().equals((Object)EntityType.ITEM_FRAME) && event.getRightClicked().getPassenger() == null && event.getPlayer().getPassenger() == null) {
                if (!this.hasCooldownPowergloves.contains(event.getPlayer())) {
                    if (!this.isCarryingPlayer.contains(event.getPlayer())) {
                        if (event.getPlayer().getPassenger() instanceof Player && this.isCarryingPlayer.contains(event.getPlayer().getPassenger())) {
                            return;
                        }
                        this.isCarryingPlayer.add(event.getPlayer());
                        new BukkitRunnable() {
                            public void run() {
                                if (event.getRightClicked().getPassenger() != null) {
                                    event.getRightClicked().getPassenger().eject();
                                }
                                if (event.getPlayer().getPassenger() != null) {
                                    event.getPlayer().getPassenger().eject();
                                }
                            }
                        }.runTaskLater(Main.plugin, 1L);
                        event.getPlayer().setPassenger(event.getRightClicked());
                        event.getRightClicked().getLocation().setY(event.getPlayer().getLocation().getY() + 2.0);
                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.ZOMBIE_CHEW_IRON_DOOR, 1000, 1000);
                        new BukkitRunnable() {
                            public void run() {
                                event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK2, 1000, 1000);
                                PlayerListener.this.CarryingPlayer.add(event.getPlayer());
                                this.cancel();
                            }
                        }.runTaskTimer(main, 10L, 0L);
                        new BukkitRunnable() {
                            int GrabTime = Main.plugin.getConfig().getInt("items.Powergloves.MaxGrabtime") * 20;

                            public void run() {
                                if (this.GrabTime > 0) {
                                    if (!PlayerListener.this.isCarryingPlayer.contains(event.getPlayer())) {
                                        this.cancel();
                                    }
                                    --this.GrabTime;
                                }
                                else if (this.GrabTime <= 0) {
                                    if (PlayerListener.this.CarryingPlayer.contains(event.getPlayer())) {
                                        event.getPlayer().getWorld().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 1000, 1000);
                                        PlayerListener.this.CarryingPlayer.remove(event.getPlayer());
                                        PlayerListener.this.isCarryingPlayer.remove(event.getPlayer());
                                        PlayerListener.this.Cooldown(event.getPlayer(), PlayerListener.this.hasCooldownPowergloves, main.getConfig().getInt("items.Powergloves.Cooldown") * 20);
                                    }
                                    event.getRightClicked().leaveVehicle();
                                    this.cancel();
                                }
                            }
                        }.runTaskTimer(main, 0L, 1L);
                    }
                }
                else if (!this.stopSpam.contains(event.getPlayer())) {
                    event.getPlayer().sendMessage(ChatColor.RED + "The Gloves are not fully charged!");
                    this.stopSpam.add(event.getPlayer());
                }
            }
        }
    }
}
