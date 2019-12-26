package com.dxzo.bukkit.CustomEnchantments;

import org.bukkit.plugin.java.*;
import net.milkbowl.vault.economy.*;
import org.bukkit.event.*;
import com.sk89q.worldguard.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.enchantments.*;
import java.util.*;

public final class Main extends JavaPlugin
{
    public static Plugin plugin;
    public static Plugin worldguard;
    public static Boolean hasWorldguard;
    public static String lorePrefix;
    public static List<String> enchantments;
    public static List<String> items;
    public static Economy econ;
    public static Boolean hasEconomy;
    public static Plugin pl;
    public static Boolean isRestricting;
    public static int maxEnchants;

    static {
        Main.plugin = null;
        Main.worldguard = null;
        Main.hasWorldguard = false;
        Main.lorePrefix = null;
        Main.enchantments = new ArrayList<String>();
        Main.items = new ArrayList<String>();
        Main.econ = null;
        Main.hasEconomy = false;
        Main.isRestricting = false;
        Main.maxEnchants = 0;
    }

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerListener(), (Plugin)this);
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        Main.isRestricting = this.getConfig().getBoolean("restrictEnchantments");
        Main.maxEnchants = this.getConfig().getInt("maximumEnchants");
        Main.pl = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");
        if (Main.pl != null && Main.pl instanceof WorldGuardPlugin) {
            Main.hasWorldguard = true;
        }
        else {
            Main.hasWorldguard = false;
        }
        if (this.getConfig().getBoolean("enchantments.lore.disableItalic")) {
            Main.lorePrefix = new StringBuilder().append(ChatColor.valueOf(this.getConfig().getString("enchantments.lore.color"))).toString();
        }
        else {
            Main.lorePrefix = new StringBuilder().append(ChatColor.valueOf(this.getConfig().getString("enchantments.lore.color"))).append(ChatColor.ITALIC).toString();
        }
        if (this.setupEconomy()) {
            Main.hasEconomy = true;
        }
        this.makeLists();
        Main.plugin = (Plugin)this;
    }

    public void onDisable() {
        this.getServer().getScheduler().cancelAllTasks();
    }

    public ChatColor getNameColor() {
        try {
            ChatColor.valueOf(this.getConfig().getString("color.name").toUpperCase());
            final ChatColor name = ChatColor.valueOf(this.getConfig().getString("color.name").toUpperCase());
            return name;
        }
        catch (IllegalArgumentException e) {
            final ChatColor name2 = ChatColor.AQUA;
            return name2;
        }
    }

    public ChatColor getLoreColor() {
        try {
            ChatColor.valueOf(this.getConfig().getString("color.lore").toUpperCase());
            final ChatColor lore = ChatColor.valueOf(this.getConfig().getString("color.lore").toUpperCase());
            return lore;
        }
        catch (IllegalArgumentException e) {
            final ChatColor lore2 = ChatColor.DARK_RED;
            return lore2;
        }
    }

    public static ItemStack setName(final ItemStack item, final String name, final List<String> lore) {
        final ItemMeta meta = item.getItemMeta();
        if (name != null) {
            meta.setDisplayName(name);
        }
        if (lore != null) {
            meta.setLore((List)lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    private Player fromArgtoPlayer(final String arg) {
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = (Player[])Bukkit.getOnlinePlayers().toArray()).length, i = 0; i < length; ++i) {
            final Player p = onlinePlayers[i];
            if (p.getPlayerListName().equalsIgnoreCase(arg)) {
                return p;
            }
        }
        return null;
    }

    private boolean setupEconomy() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        final RegisteredServiceProvider<Economy> rsp = (RegisteredServiceProvider<Economy>)this.getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (rsp == null) {
            return false;
        }
        Main.econ = (Economy)rsp.getProvider();
        return Main.econ != null;
    }

    public void makeLists() {
        Main.enchantments.add("Lifesteal");
        Main.enchantments.add("Gooey");
        Main.enchantments.add("Deathbringer");
        Main.enchantments.add("Poison");
        Main.enchantments.add("Block");
        Main.enchantments.add("Shockwave");
        Main.enchantments.add("Deepwounds");
        Main.enchantments.add("Thunderingblow");
        Main.enchantments.add("Cripple");
        Main.enchantments.add("Cursed");
        Main.enchantments.add("Ice");
        Main.enchantments.add("Autorepair");
        Main.enchantments.add("Vampire");
        Main.enchantments.add("Blind");
        Main.enchantments.add("Bombardment");
        Main.enchantments.add("Firework");
        Main.enchantments.add("Lightning");
        Main.enchantments.add("Molten");
        Main.enchantments.add("Frozen");
        Main.enchantments.add("Poisoned");
        Main.enchantments.add("Enlighted");
        Main.enchantments.add("Obsidianshield");
        Main.enchantments.add("Hardened");
        Main.enchantments.add("Gears");
        Main.enchantments.add("Springs");
        Main.enchantments.add("Stomp");
        Main.enchantments.add("Glowing");
        Main.enchantments.add("Implants");
        Main.items.add(ChatColor.AQUA + "Minigun");
        Main.items.add(ChatColor.AQUA + "Beastmaster");
        Main.items.add(ChatColor.AQUA + "Hookshot");
        Main.items.add(ChatColor.GOLD + "Hermesboots");
        Main.items.add(ChatColor.DARK_RED + "Livefire");
        Main.items.add(ChatColor.AQUA + "Rocketboots");
        Main.items.add(ChatColor.DARK_GREEN + "Druidboots");
        Main.items.add(ChatColor.DARK_RED + "Flamethrower");
        Main.items.add(ChatColor.AQUA + "Necromancer");
        Main.items.add(ChatColor.BLUE + "Swimsuit");
        Main.items.add(ChatColor.GOLD + "Thoraxe");
        Main.items.add(ChatColor.DARK_RED + "Pyroaxe");
        Main.items.add(ChatColor.AQUA + "Assassin");
        Main.items.add(ChatColor.GREEN + "Healingshovel");
        Main.items.add(ChatColor.GRAY + "Beartrap");
        Main.items.add(ChatColor.GRAY + "Piranhatrap");
        Main.items.add(ChatColor.DARK_GREEN + "Poisonivy");
        Main.items.add(ChatColor.LIGHT_PURPLE + "Prickly");
        Main.items.add(ChatColor.GRAY + "Landmine");
        Main.items.add(ChatColor.GRAY + "Suicidelever");
        Main.items.add(ChatColor.AQUA + "Powergloves");
        Main.items.add(ChatColor.GREEN + "Medikit");
        Main.items.add(ChatColor.GREEN + "Bandage");
        Main.items.add(ChatColor.DARK_GRAY + "Deathscythe");
        Main.items.add(ChatColor.GRAY + "Potionlauncher");
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("cereload") && (sender.isOp() || sender.equals(Bukkit.getConsoleSender()))) {
            this.reloadConfig();
            if (sender instanceof Player) {
                sender.sendMessage(ChatColor.GREEN + "The Custom Enchantments config has successfully been reloaded");
            }
            else {
                Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "The Custom Enchantments config has successfully been reloaded");
            }
        }
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (player.isOp() || (!this.getConfig().getBoolean("commandBypass") && player.hasPermission("customenchantment.canChangeItem"))) {
                if (cmd.getName().equalsIgnoreCase("setname")) {
                    if (args.length >= 2) {
                        final ItemStack item = player.getItemInHand();
                        ChatColor color;
                        try {
                            color = ChatColor.valueOf(args[0].toUpperCase());
                        }
                        catch (Exception e2) {
                            player.sendMessage(ChatColor.RED + "The Color you have entered is unknown.");
                            player.sendMessage(ChatColor.RED + "An example for a Color would be RED and AQUA");
                            return false;
                        }
                        String name = "";
                        for (int i = 1; i < args.length; ++i) {
                            name = String.valueOf(name) + args[i] + " ";
                        }
                        setName(item, color + name, item.getItemMeta().getLore());
                        return true;
                    }
                    player.sendMessage(ChatColor.RED + "Usage: /setname <color> <name>");
                    return false;
                }
                else if (cmd.getName().equalsIgnoreCase("addlore") && player.getItemInHand().hasItemMeta()) {
                    if (args.length < 2) {
                        player.sendMessage(ChatColor.RED + "Usage: /addlore <color> <lore>");
                        return false;
                    }
                    if (player.getItemInHand().getItemMeta().hasLore()) {
                        final ItemStack item = player.getItemInHand();
                        final String name2 = item.getItemMeta().getDisplayName();
                        List<String> lore = new ArrayList<String>();
                        try {
                            lore = (List<String>)item.getItemMeta().getLore();
                        }
                        catch (Exception ex) {}
                        ChatColor color2;
                        try {
                            color2 = ChatColor.valueOf(args[0].toUpperCase());
                        }
                        catch (Exception e3) {
                            player.sendMessage(ChatColor.RED + "The Color you have entered is unknown.");
                            player.sendMessage(ChatColor.RED + "An example for a Color would be RED and AQUA");
                            return false;
                        }
                        String lores = "";
                        for (int j = 1; j < args.length; ++j) {
                            lores = String.valueOf(lores) + color2 + args[j] + " ";
                        }
                        lore.add(lores);
                        setName(item, name2, lore);
                        return true;
                    }
                    player.sendMessage(ChatColor.RED + "Your Item does not have a Lore to add to -> /setlore to create a new Lore");
                    return false;
                }
                else if (cmd.getName().equalsIgnoreCase("setlore") && player.getItemInHand().hasItemMeta()) {
                    if (args.length >= 2) {
                        final ItemStack item = player.getItemInHand();
                        final String name2 = item.getItemMeta().getDisplayName();
                        final List<String> lore = new ArrayList<String>();
                        ChatColor color2;
                        try {
                            color2 = ChatColor.valueOf(args[0].toUpperCase());
                        }
                        catch (Exception e3) {
                            player.sendMessage(ChatColor.RED + "The Color you have entered is unknown.");
                            player.sendMessage(ChatColor.RED + "An example for a Color would be RED and AQUA");
                            return false;
                        }
                        String lores = "";
                        for (int j = 1; j < args.length; ++j) {
                            lores = String.valueOf(lores) + color2 + args[j] + " ";
                        }
                        lore.add(lores);
                        setName(item, name2, lore);
                        return true;
                    }
                    player.sendMessage(ChatColor.RED + "Usage: /setlore <color> <lore>");
                    return false;
                }
                else if (cmd.getName().equalsIgnoreCase("removelore") && player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore()) {
                    final ItemStack item = player.getItemInHand();
                    final String name2 = item.getItemMeta().getDisplayName();
                    final List<String> lore = new ArrayList<String>();
                    lore.clear();
                    setName(item, name2, lore);
                }
            }
            if (cmd.getName().equalsIgnoreCase("ench")) {
                if (player.isOp() || (!this.getConfig().getBoolean("commandBypass") && player.hasPermission("customenchantment.canEnchant"))) {
                    if (args.length > 0) {
                        if (args[0].equalsIgnoreCase("custom") && args.length == 1) {
                            player.sendMessage(ChatColor.GOLD + "--------Custom Enchantments-------");
                            player.sendMessage(ChatColor.RED + " Page 1: Global Enchantments");
                            player.sendMessage(ChatColor.BLUE + " Page 2: Bow Enchantments");
                            player.sendMessage(ChatColor.DARK_GRAY + " Page 3: Armor Enchantments");
                            player.sendMessage(ChatColor.AQUA + " Page 4: Items");
                            player.sendMessage(ChatColor.GREEN + " Page 5: Bandages");
                            player.sendMessage(ChatColor.GOLD + "   /ench custom <page> for more");
                            player.sendMessage(ChatColor.GOLD + "-------------------------------------");
                            return true;
                        }
                        if (args.length > 1) {
                            if (args[0].equalsIgnoreCase("custom") && args[1].equals("1")) {
                                player.sendMessage(ChatColor.GOLD + "--------Global Enchantments-------");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Lifesteal");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Blind");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Gooey");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Deathbringer");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Poison");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Block");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Shockwave");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Deepwounds");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Thunderingblow");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Cripple");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Cursed");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Ice");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Autorepair");
                                player.sendMessage(String.valueOf(Main.lorePrefix) + "    Vampire");
                                player.sendMessage(ChatColor.GOLD + "  /ench custom <page> for more");
                                player.sendMessage(ChatColor.GOLD + "------------- Page 1/5 --------------");
                            }
                            else if (args[0].equalsIgnoreCase("custom") && args[1].equals("2")) {
                                player.sendMessage(ChatColor.GOLD + "---------Bow Enchantments---------");
                                player.sendMessage(ChatColor.BLUE + "    Bombardment");
                                player.sendMessage(ChatColor.BLUE + "    Firework");
                                player.sendMessage(ChatColor.BLUE + "    Lightning");
                                player.sendMessage(ChatColor.BLUE + "    Blind");
                                player.sendMessage(ChatColor.GOLD + "------------- Page 2/5 --------------");
                            }
                            else if (args[0].equalsIgnoreCase("custom") && args[1].equals("3")) {
                                player.sendMessage(ChatColor.GOLD + "---------Armor Enchantments---------");
                                player.sendMessage(ChatColor.DARK_GRAY + "    Molten");
                                player.sendMessage(ChatColor.DARK_GRAY + "    Frozen");
                                player.sendMessage(ChatColor.DARK_GRAY + "    Poisoned");
                                player.sendMessage(ChatColor.DARK_GRAY + "    Enlighted");
                                player.sendMessage(ChatColor.DARK_GRAY + "    Obsidianshield");
                                player.sendMessage(ChatColor.DARK_GRAY + "    Boots: Gears");
                                player.sendMessage(ChatColor.DARK_GRAY + "    Boots: Springs");
                                player.sendMessage(ChatColor.DARK_GRAY + "    Boots: Stomp");
                                player.sendMessage(ChatColor.DARK_GRAY + "    Helmet: Glowing");
                                player.sendMessage(ChatColor.DARK_GRAY + "    Helmet: Implants");
                                player.sendMessage(ChatColor.GOLD + "------------- Page 3/5 --------------");
                            }
                            else if (args[0].equalsIgnoreCase("custom") && args[1].equals("4")) {
                                player.sendMessage(ChatColor.GOLD + "--------------Items--------------");
                                player.sendMessage(ChatColor.AQUA + "    Assassin         " + ChatColor.DARK_GRAY + "(Sword)");
                                player.sendMessage(ChatColor.AQUA + "    Potionlauncher   " + ChatColor.DARK_GRAY + "(Any)");
                                player.sendMessage(ChatColor.AQUA + "    Necromancer      " + ChatColor.DARK_GRAY + "(Stick)");
                                player.sendMessage(ChatColor.AQUA + "    Powergloves      " + ChatColor.DARK_GRAY + "(Nether Quartz)");
                                player.sendMessage(ChatColor.AQUA + "    Flamethrower     " + ChatColor.DARK_GRAY + "(Flint and Steel)");
                                player.sendMessage(ChatColor.AQUA + "    Deathscythe      " + ChatColor.DARK_GRAY + "(Hoe)");
                                player.sendMessage(ChatColor.AQUA + "    Thoraxe       - Ultimate   " + ChatColor.DARK_GRAY + "(Axe)");
                                player.sendMessage(ChatColor.AQUA + "    Pyroaxe          " + ChatColor.DARK_GRAY + "(Axe)");
                                player.sendMessage(ChatColor.AQUA + "    Suicidelever          " + ChatColor.DARK_GRAY + "(Lever)");
                                player.sendMessage(ChatColor.AQUA + "    Healingshovel - Major / Minor " + ChatColor.DARK_GRAY + "(Shovel ... Duh)");
                                player.sendMessage(ChatColor.AQUA + "    Beastmaster      " + ChatColor.DARK_GRAY + "(Bow)");
                                player.sendMessage(ChatColor.AQUA + "    Minigun          " + ChatColor.DARK_GRAY + "(Bow)");
                                player.sendMessage(ChatColor.AQUA + "    Hookshot         " + ChatColor.DARK_GRAY + "(Bow)");
                                player.sendMessage(ChatColor.AQUA + "    Swimsuit         " + ChatColor.DARK_GRAY + "(Armor)");
                                player.sendMessage(ChatColor.AQUA + "    Rocketboots      " + ChatColor.DARK_GRAY + "(Boots)");
                                player.sendMessage(ChatColor.AQUA + "    Druidboots       " + ChatColor.DARK_GRAY + "(Boots)");
                                player.sendMessage(ChatColor.AQUA + "    Hermesboots      " + ChatColor.DARK_GRAY + "(Boots)");
                                player.sendMessage(ChatColor.AQUA + "    Livefire         " + ChatColor.DARK_GRAY + "(Boots)");
                                player.sendMessage(ChatColor.AQUA + "    Beartrap         " + ChatColor.DARK_GRAY + "(Any Block)");
                                player.sendMessage(ChatColor.AQUA + "    Piranhatrap      " + ChatColor.DARK_GRAY + "(Any Block)");
                                player.sendMessage(ChatColor.AQUA + "    Poisonivy        " + ChatColor.DARK_GRAY + "(Any Block)");
                                player.sendMessage(ChatColor.AQUA + "    Prickly          " + ChatColor.DARK_GRAY + "(Any Block)");
                                player.sendMessage(ChatColor.AQUA + "    Landmine         " + ChatColor.DARK_GRAY + "(Any Block)");
                                player.sendMessage(ChatColor.GOLD + "------------- Page 4/5 --------------");
                            }
                            else if (args[0].equalsIgnoreCase("custom") && args[1].equals("5")) {
                                player.sendMessage(ChatColor.GOLD + "--------------Items--------------");
                                player.sendMessage(ChatColor.GREEN + "    Medikit" + ChatColor.DARK_GRAY + "  (Any)");
                                player.sendMessage(ChatColor.GREEN + "    Bandage" + ChatColor.DARK_GRAY + "  (Paper)");
                                player.sendMessage(ChatColor.DARK_PURPLE + "                 -  Minor ");
                                player.sendMessage(ChatColor.DARK_PURPLE + "                 -  Tough ");
                                player.sendMessage(ChatColor.DARK_PURPLE + "                 -  Heavy ");
                                player.sendMessage(ChatColor.RED + "   Example:  /ench Bandage Heavy");
                                player.sendMessage(ChatColor.GOLD + "------------- Page 5/5 --------------");
                            }
                            return true;
                        }
                        if (!player.getItemInHand().getType().equals((Object)Material.AIR)) {
                            int amp = 1;
                            Label_3911: {
                                if (args.length == 2) {
                                    if (args[1].equalsIgnoreCase("Minor") && args[1].equalsIgnoreCase("Major") && args[1].equalsIgnoreCase("Tough") && args[1].equalsIgnoreCase("Heavy")) {
                                        if (args[1].equalsIgnoreCase("Ultimate")) {
                                            break Label_3911;
                                        }
                                    }
                                    try {
                                        amp = Integer.parseInt(args[1]);
                                    }
                                    catch (Exception e4) {
                                        player.sendMessage(ChatColor.RED + "The Strength you entered is invalid!");
                                        return false;
                                    }
                                }
                            }
                            if (args[0].equalsIgnoreCase("Damage")) {
                                if (player.getItemInHand().getType().equals((Object)Material.BOW)) {
                                    player.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, amp);
                                    player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                                }
                                else {
                                    player.getItemInHand().addUnsafeEnchantment(Enchantment.DAMAGE_ALL, amp);
                                    player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                                }
                            }
                            else if (args[0].equalsIgnoreCase("Knockback")) {
                                if (player.getItemInHand().getType().equals((Object)Material.BOW)) {
                                    player.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, amp);
                                    player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                                }
                                else {
                                    player.getItemInHand().addUnsafeEnchantment(Enchantment.KNOCKBACK, amp);
                                    player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                                }
                            }
                            else if (args[0].equalsIgnoreCase("Thorns")) {
                                player.getItemInHand().addUnsafeEnchantment(Enchantment.THORNS, amp);
                                player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                            }
                            else if (args[0].equalsIgnoreCase("Loot")) {
                                player.getItemInHand().addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, amp);
                                player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                            }
                            else if (args[0].equalsIgnoreCase("Protection")) {
                                player.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, amp);
                                player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                            }
                            else if (args[0].equalsIgnoreCase("Durability")) {
                                player.getItemInHand().addUnsafeEnchantment(Enchantment.DURABILITY, amp);
                                player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                            }
                            else if (args[0].equalsIgnoreCase("Featherfalling")) {
                                player.getItemInHand().addUnsafeEnchantment(Enchantment.PROTECTION_FALL, amp);
                                player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                            }
                            else if (args[0].equalsIgnoreCase("Infinity")) {
                                if (player.getItemInHand().getType().equals((Object)Material.BOW)) {
                                    player.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
                                    player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "This Enchantment can only be used on Bows!");
                                }
                            }
                            else if (args[0].equalsIgnoreCase("Fire")) {
                                if (player.getItemInHand().getType().equals((Object)Material.BOW)) {
                                    player.getItemInHand().addUnsafeEnchantment(Enchantment.ARROW_FIRE, amp);
                                    player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                                }
                                else {
                                    player.getItemInHand().addUnsafeEnchantment(Enchantment.FIRE_ASPECT, amp);
                                    player.sendMessage(ChatColor.GREEN + "You have succesfully applied the Enchantment!");
                                }
                            }
                            else {
                                if (player.getItemInHand().hasItemMeta() && player.getItemInHand().getItemMeta().hasLore() && this.getConfig().getBoolean("restrictEnchantments")) {
                                    int e = 0;
                                    final List<String> lore = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                    for (final String l : lore) {
                                        for (final String r : Main.enchantments) {
                                            if (PlayerListener.getEnchantmentName(r).equalsIgnoreCase(l)) {
                                                ++e;
                                            }
                                        }
                                    }
                                    if (e >= this.getConfig().getInt("maximumEnchants")) {
                                        player.sendMessage(ChatColor.RED + "Your Runes cannot be changed!");
                                        return false;
                                    }
                                }
                                if (args[0].equalsIgnoreCase("Lifesteal")) {
                                    List<String> lore2 = null;
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Lifesteal");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Lifesteal");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Vampire")) {
                                    List<String> lore2 = null;
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Vampire");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Vampire");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Blind")) {
                                    List<String> lore2 = null;
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Blind");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Blind");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Deepwounds")) {
                                    List<String> lore2 = null;
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Deep Wounds");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Deep Wounds");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Thunderingblow")) {
                                    List<String> lore2 = null;
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Thundering Blow");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Thundering Blow");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Cursed")) {
                                    List<String> lore2 = null;
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Cursed");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Cursed");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Cripple")) {
                                    List<String> lore2 = null;
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Crippling Strike");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Crippling Strike");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Gooey")) {
                                    List<String> lore2 = null;
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Gooey");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Gooey");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Deathbringer")) {
                                    List<String> lore2 = null;
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Deathbringer");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Deathbringer");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Poison")) {
                                    List<String> lore2 = new ArrayList<String>();
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Poison");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Poison");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Block")) {
                                    List<String> lore2 = new ArrayList<String>();
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Block");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Block");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Shockwave")) {
                                    List<String> lore2 = new ArrayList<String>();
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Shockwave");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Shockwave");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Ice")) {
                                    List<String> lore2 = new ArrayList<String>();
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Ice Aspect");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Ice Aspect");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Assassin")) {
                                    final List<String> lore2 = new ArrayList<String>();
                                    lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Strike from the Shadows").toString());
                                    setName(player.getItemInHand(), ChatColor.AQUA + "Assassin's Blade", lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Bombardment")) {
                                    if (player.getItemInHand().getType().equals((Object)Material.BOW)) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Bombardment");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Bombardment");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on bows.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Firework")) {
                                    if (player.getItemInHand().getType().equals((Object)Material.BOW)) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Firework");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Firework");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on bows.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Lightning")) {
                                    if (player.getItemInHand().getType().equals((Object)Material.BOW)) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Lightning");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Lightning");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on bows.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Minigun")) {
                                    if (player.getItemInHand().getType().equals((Object)Material.BOW)) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Fires a Volley of Arrows").toString());
                                        setName(player.getItemInHand(), ChatColor.AQUA + "Minigun", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "bow's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on bows.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Beastmaster")) {
                                    if (player.getItemInHand().getType().equals((Object)Material.BOW)) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Tame the Wilderness and ").toString());
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("unleash Beasts at your enemy").toString());
                                        setName(player.getItemInHand(), ChatColor.AQUA + "Beastmaster's Bow", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "bow's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on bows.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Landmine")) {
                                    final List<String> lore2 = new ArrayList<String>();
                                    lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Don't step on it!").toString());
                                    setName(player.getItemInHand(), ChatColor.GRAY + "Landmine", lore2);
                                    player.getItemInHand().getItemMeta().setLore((List)lore2);
                                    player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "block's " + ChatColor.GREEN + "shape shifting!");
                                }
                                else if (args[0].equalsIgnoreCase("Poisonivy")) {
                                    final List<String> lore2 = new ArrayList<String>();
                                    lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Poisonous!").toString());
                                    setName(player.getItemInHand(), ChatColor.DARK_GREEN + "Poison Ivy", lore2);
                                    player.getItemInHand().getItemMeta().setLore((List)lore2);
                                    player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "block's " + ChatColor.GREEN + "shape shifting!");
                                }
                                else if (args[0].equalsIgnoreCase("Prickly")) {
                                    final List<String> lore2 = new ArrayList<String>();
                                    lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("You prick!").toString());
                                    setName(player.getItemInHand(), ChatColor.LIGHT_PURPLE + "Prickly Block", lore2);
                                    player.getItemInHand().getItemMeta().setLore((List)lore2);
                                    player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "block's " + ChatColor.GREEN + "shape shifting!");
                                }
                                else if (args[0].equalsIgnoreCase("Beartrap")) {
                                    final List<String> lore2 = new ArrayList<String>();
                                    lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Does this work on Players?").toString());
                                    setName(player.getItemInHand(), ChatColor.GRAY + "Beartrap", lore2);
                                    player.getItemInHand().getItemMeta().setLore((List)lore2);
                                    player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "block's " + ChatColor.GREEN + "shape shifting!");
                                }
                                else if (args[0].equalsIgnoreCase("Suicidelever")) {
                                    if (player.getItemInHand().getType().equals((Object)Material.LEVER)) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("How did I get this?").toString());
                                        setName(player.getItemInHand(), ChatColor.GRAY + "Suicide Lever", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "lever's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Piranhatrap")) {
                                    final List<String> lore2 = new ArrayList<String>();
                                    lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Nom nom nom, nom nom").toString());
                                    setName(player.getItemInHand(), ChatColor.GRAY + "Piranha Trap", lore2);
                                    player.getItemInHand().getItemMeta().setLore((List)lore2);
                                    player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "block's " + ChatColor.GREEN + "shape shifting!");
                                }
                                else if (args[0].equalsIgnoreCase("Flamethrower")) {
                                    final List<String> lore2 = new ArrayList<String>();
                                    lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Caution: Hot!").toString());
                                    setName(player.getItemInHand(), ChatColor.DARK_RED + "Flamethrower", lore2);
                                    player.getItemInHand().getItemMeta().setLore((List)lore2);
                                    player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "item's " + ChatColor.GREEN + "shape shifting!");
                                }
                                else if (args[0].equalsIgnoreCase("Medikit")) {
                                    final List<String> lore2 = new ArrayList<String>();
                                    lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Use to instantly heal to Full HP").toString());
                                    setName(player.getItemInHand(), ChatColor.GREEN + "Medikit", lore2);
                                    player.getItemInHand().getItemMeta().setLore((List)lore2);
                                    player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "item's " + ChatColor.GREEN + "shape shifting!");
                                }
                                else if (args[0].equalsIgnoreCase("Bandage")) {
                                    if (player.getItemInHand().getType().equals((Object)Material.PAPER)) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        if (args.length == 1) {
                                            lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Rightclick to use.").toString());
                                            lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Heals 10 Hearts over 20 Seconds.").toString());
                                            lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Can be applied to other Players by hitting them.").toString());
                                            setName(player.getItemInHand(), ChatColor.GREEN + "Bandage", lore2);
                                        }
                                        else if (args.length > 1) {
                                            if (args[1].equalsIgnoreCase("minor")) {
                                                lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Rightclick to use.").toString());
                                                lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Heals 5 Hearts over 10 Seconds.").toString());
                                                lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Can be applied to other Players by hitting them.").toString());
                                                setName(player.getItemInHand(), ChatColor.GREEN + "Minor Bandage", lore2);
                                            }
                                            else if (args[1].equalsIgnoreCase("tough")) {
                                                lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Rightclick to use.").toString());
                                                lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Heals 15 Hearts over 20 Seconds.").toString());
                                                lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Can be applied to other Players by hitting them.").toString());
                                                setName(player.getItemInHand(), ChatColor.GREEN + "Tough Bandage", lore2);
                                            }
                                            else if (args[1].equalsIgnoreCase("heavy")) {
                                                lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Rightclick to use.").toString());
                                                lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Heals 25 Hearts over 20 Seconds.").toString());
                                                lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Can be applied to other Players by hitting them.").toString());
                                                setName(player.getItemInHand(), ChatColor.GREEN + "Heavy Bandage", lore2);
                                            }
                                        }
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "item's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on paper.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Hookshot")) {
                                    if (player.getItemInHand().getType().equals((Object)Material.BOW)) {
                                        if (player.getItemInHand().getItemMeta().hasLore() && (player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Mode: Pull") || player.getItemInHand().getItemMeta().getLore().contains(ChatColor.DARK_GRAY + "Mode: Push"))) {
                                            player.sendMessage(ChatColor.RED + "This Bow already has this enchantment");
                                            return false;
                                        }
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Fling yourself to the enemy").toString());
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("or fling the enemy to you").toString());
                                        lore2.add(ChatColor.DARK_GRAY + "Mode: Push");
                                        setName(player.getItemInHand(), ChatColor.AQUA + "Hookshot Bow", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "bow's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on bows.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Rocketboots")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS")) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Fly high into the Sky").toString());
                                        lore2.add(ChatColor.DARK_GRAY + "Power: " + ChatColor.RED + ChatColor.ITALIC + "OFFLINE");
                                        setName(player.getItemInHand(), ChatColor.AQUA + "Rocket Boots", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "boot's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Boots.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Hermesboots")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS")) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Run Forrest, run!").toString());
                                        setName(player.getItemInHand(), ChatColor.GOLD + "Hermes Boots", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "boot's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Boots.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Druidboots")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS")) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Greenpeace ain't got shit on me").toString());
                                        setName(player.getItemInHand(), ChatColor.DARK_GREEN + "Druid's Boots", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "boot's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Boots.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Livefire")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS")) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Leave a hot Trail!").toString());
                                        setName(player.getItemInHand(), ChatColor.DARK_RED + "Livefire Boots", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "boot's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Boots.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Deathscythe")) {
                                    if (player.getItemInHand().getType().name().endsWith("HOE")) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Death always finds a way!").toString());
                                        setName(player.getItemInHand(), ChatColor.DARK_GRAY + "Death's Scythe", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "hoe's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on a Hoe.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Thoraxe")) {
                                    if (player.getItemInHand().getType().name().endsWith("AXE")) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Thunder!").toString());
                                        if (args.length == 2 && args[1].equalsIgnoreCase("Ultimate")) {
                                            lore2.add(ChatColor.DARK_PURPLE + "Ultimate");
                                        }
                                        setName(player.getItemInHand(), ChatColor.GOLD + "Thor's Axe", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "axe's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on an Axe.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Healingshovel")) {
                                    if (player.getItemInHand().getType().name().endsWith("SPADE")) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("This won't hurt... that much!").toString());
                                        if (args.length == 2 && args[1].equalsIgnoreCase("Major")) {
                                            setName(player.getItemInHand(), ChatColor.GREEN + "Major Healing Shovel", lore2);
                                        }
                                        else {
                                            setName(player.getItemInHand(), ChatColor.GREEN + "Minor Healing Shovel", lore2);
                                        }
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "shovel's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on a Shovel.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Pyroaxe")) {
                                    if (player.getItemInHand().getType().name().endsWith("AXE")) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.DARK_RED).append("I come from the Iron Hills").toString());
                                        setName(player.getItemInHand(), ChatColor.DARK_RED + "Pyro Axe", lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "axe's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on an Axe.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Molten")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS") || player.getItemInHand().getType().name().endsWith("CHESTPLATE") || player.getItemInHand().getType().name().endsWith("HELMET") || player.getItemInHand().getType().name().endsWith("LEGGINGS")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Molten");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Molten");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Poisoned")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS") || player.getItemInHand().getType().name().endsWith("CHESTPLATE") || player.getItemInHand().getType().name().endsWith("HELMET") || player.getItemInHand().getType().name().endsWith("LEGGINGS")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Poisoned");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Poisoned");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Frozen")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS") || player.getItemInHand().getType().name().endsWith("CHESTPLATE") || player.getItemInHand().getType().name().endsWith("HELMET") || player.getItemInHand().getType().name().endsWith("LEGGINGS")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Frozen");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Frozen");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Enlighted")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS") || player.getItemInHand().getType().name().endsWith("CHESTPLATE") || player.getItemInHand().getType().name().endsWith("HELMET") || player.getItemInHand().getType().name().endsWith("LEGGINGS")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Enlighted");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Enlighted");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Hardened")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS") || player.getItemInHand().getType().name().endsWith("CHESTPLATE") || player.getItemInHand().getType().name().endsWith("HELMET") || player.getItemInHand().getType().name().endsWith("LEGGINGS")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Hardened");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Hardened");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Glowing")) {
                                    if (player.getItemInHand().getType().name().endsWith("HELMET")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Glowing");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Glowing");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Implants")) {
                                    if (player.getItemInHand().getType().name().endsWith("HELMET")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Implants");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Implants");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Gears")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Gears");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Gears");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Springs")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Springs");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Springs");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Stomp")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Stomp");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Stomp");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Obsidianshield")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS") || player.getItemInHand().getType().name().endsWith("CHESTPLATE") || player.getItemInHand().getType().name().endsWith("HELMET") || player.getItemInHand().getType().name().endsWith("LEGGINGS")) {
                                        List<String> lore2 = new ArrayList<String>();
                                        try {
                                            lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Obsidian Shielding");
                                        }
                                        catch (Exception e2) {
                                            lore2 = new ArrayList<String>();
                                            lore2.add(String.valueOf(Main.lorePrefix) + "Obsidian Shielding");
                                        }
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Autorepair")) {
                                    List<String> lore2 = new ArrayList<String>();
                                    try {
                                        lore2 = (List<String>)player.getItemInHand().getItemMeta().getLore();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Automatic Repair");
                                    }
                                    catch (Exception e2) {
                                        lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Automatic Repair");
                                    }
                                    setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                    player.getItemInHand().getItemMeta().setLore((List)lore2);
                                    player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "item's " + ChatColor.GREEN + "shape shifting!");
                                }
                                else if (args[0].equalsIgnoreCase("Cursed")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS") || player.getItemInHand().getType().name().endsWith("CHESTPLATE") || player.getItemInHand().getType().name().endsWith("HELMET") || player.getItemInHand().getType().name().endsWith("LEGGINGS")) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(String.valueOf(Main.lorePrefix) + "Cursed");
                                        setName(player.getItemInHand(), player.getItemInHand().getItemMeta().getDisplayName(), lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Potionlauncher")) {
                                    final List<String> lore2 = new ArrayList<String>();
                                    lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("KA-ZOOOOOM").toString());
                                    setName(player.getItemInHand(), ChatColor.GRAY + "Potion Launcher", lore2);
                                    player.sendMessage(ChatColor.DARK_RED + "Runes " + ChatColor.GREEN + "have appeared on your Item");
                                }
                                else if (args[0].equalsIgnoreCase("Swimsuit")) {
                                    if (player.getItemInHand().getType().name().endsWith("BOOTS") || player.getItemInHand().getType().name().endsWith("CHESTPLATE") || player.getItemInHand().getType().name().endsWith("HELMET") || player.getItemInHand().getType().name().endsWith("LEGGINGS")) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Stay Underwater... Forever!").toString());
                                        String name = "Swimsuit";
                                        if (player.getItemInHand().getType().name().endsWith("HELMET")) {
                                            name = ChatColor.BLUE + "Scuba Mask";
                                        }
                                        if (player.getItemInHand().getType().name().endsWith("CHESTPLATE")) {
                                            name = ChatColor.BLUE + "Upper Swimsuit";
                                        }
                                        if (player.getItemInHand().getType().name().endsWith("LEGGINGS")) {
                                            name = ChatColor.BLUE + "Lower Swimsuit";
                                        }
                                        if (player.getItemInHand().getType().name().endsWith("BOOTS")) {
                                            name = ChatColor.BLUE + "Flippers";
                                        }
                                        setName(player.getItemInHand(), name, lore2);
                                        player.getItemInHand().getItemMeta().setLore((List)lore2);
                                        player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "armor's " + ChatColor.GREEN + "shape shifting!");
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be used on Armor.");
                                    }
                                }
                                else if (args[0].equalsIgnoreCase("Powergloves")) {
                                    final List<String> lore2 = new ArrayList<String>();
                                    lore2.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Power Slam!").toString());
                                    setName(player.getItemInHand(), ChatColor.AQUA + "Power Gloves", lore2);
                                    player.getItemInHand().getItemMeta().setLore((List)lore2);
                                    player.sendMessage(ChatColor.GREEN + "You feel your " + ChatColor.AQUA + "item's " + ChatColor.GREEN + "shape shifting!");
                                }
                                else if (args[0].equalsIgnoreCase("Necromancer")) {
                                    if (player.getItemInHand().getType().equals((Object)Material.STICK)) {
                                        final List<String> lore2 = new ArrayList<String>();
                                        lore2.add(ChatColor.DARK_PURPLE + "Spell: Fireball");
                                        setName(player.getItemInHand(), ChatColor.AQUA + "Necromancer's Staff of Destruction", lore2);
                                    }
                                    else {
                                        player.sendMessage(ChatColor.RED + "This enchantment can only be applied to a Stick!");
                                    }
                                }
                                else {
                                    player.sendMessage(ChatColor.RED + "The specified Enchantment was not found, try /ench custom <page>");
                                }
                            }
                        }
                    }
                    else {
                        player.sendMessage(ChatColor.GOLD + "  For a List of Custom Enchantments, use /ench custom <page>.");
                        player.sendMessage(ChatColor.RED + "  Usage: /ench <enchantment> <strength>");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "You don't have Permission to do that.");
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("enchgive")) {
            if (sender.isOp() || (!this.getConfig().getBoolean("commandBypass") && sender.hasPermission("customenchantment.canEnchant"))) {
                if (args.length == 3) {
                    Player p = null;
                    if (this.fromArgtoPlayer(args[0]) == null) {
                        sender.sendMessage(ChatColor.RED + "Player not found.");
                        return false;
                    }
                    p = this.fromArgtoPlayer(args[0]);
                    if (p == null) {
                        return false;
                    }
                    String message = ChatColor.MAGIC + "++" + ChatColor.GREEN + " You have received an enchanted item! " + ChatColor.RESET + ChatColor.MAGIC + "++";
                    if (sender instanceof Player) {
                        final Player player2 = (Player)sender;
                        message = ChatColor.MAGIC + "++" + ChatColor.GREEN + " You have received an enchanted item from " + ChatColor.DARK_RED + player2.getDisplayName() + ChatColor.WHITE + " " + ChatColor.MAGIC + "++";
                    }
                    Material mat = Material.AIR;
                    try {
                        mat = Material.valueOf(args[1].toUpperCase());
                    }
                    catch (Exception e2) {
                        sender.sendMessage(ChatColor.RED + "Material not found.");
                        return false;
                    }
                    final ItemStack item2 = new ItemStack(mat, 1);
                    if (p.getInventory() == null) {
                        Bukkit.broadcastMessage("Inventory not found");
                        return false;
                    }
                    if (args[2].equalsIgnoreCase("Lifesteal")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Lifesteal");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Blind")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Blind");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Gooey")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Gooey");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Deathbringer")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Deathbringer");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Poison")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Poison");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Block")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Block");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Assassin")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Strike from the Shadows").toString());
                        setName(item2, ChatColor.AQUA + "Assassin's Blade", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + " ++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Bombardment")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Bombardment");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Shockwave")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Shockwave");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Firework")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Firework");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Lightning")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Lightning");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Minigun")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Fires a Volley of Arrows").toString());
                        setName(item2, ChatColor.AQUA + "Minigun", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Beastmaster")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Tame the Wilderness and ").toString());
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("unleash Beasts at your enemy").toString());
                        setName(item2, ChatColor.AQUA + "Beastmaster's Bow", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Hookshot")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Fling yourself to the enemy").toString());
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("or fling the enemy to you").toString());
                        lore3.add(ChatColor.DARK_GRAY + "Mode: Push");
                        setName(item2, ChatColor.AQUA + "Hookshot Bow", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Rocketboots")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Fly high into the Sky").toString());
                        lore3.add(ChatColor.DARK_GRAY + "Power: " + ChatColor.RED + ChatColor.ITALIC + "OFFLINE");
                        setName(item2, ChatColor.AQUA + "Rocket Boots", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Powergloves")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Power Slam!").toString());
                        setName(item2, ChatColor.AQUA + "Power Gloves", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Necromancer")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(ChatColor.DARK_PURPLE + "Spell: Fireball");
                        setName(item2, ChatColor.AQUA + "Necromancer's Staff of Destruction", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Flamethrower")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Caution: Hot!").toString());
                        setName(item2, ChatColor.DARK_RED + "Flamethrower", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++ " + ChatColor.GREEN + "The Item has been sent." + ChatColor.RESET + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Hermesboots")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Run Forrest, run!").toString());
                        setName(item2, ChatColor.GOLD + "Hermes Boots", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Livefire")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Leave a hot Trail!").toString());
                        setName(item2, ChatColor.DARK_RED + "Livefire Boots", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Molten")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Molten");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Poisoned")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Poisoned");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Frozen")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Frozen");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Enlighted")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Enlighted");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Glowing")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Glowing");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Implants")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Implants");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Gears")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Gears");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Springs")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Springs");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Autorepair")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Automatic Repair");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Obsidianshield")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Obsidian Shielding");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Stomp")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(String.valueOf(Main.lorePrefix) + "Stomp");
                        setName(item2, item2.getItemMeta().getDisplayName(), lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Landmine")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Don't step on it!").toString());
                        setName(item2, ChatColor.GRAY + "Landmine", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Thoraxe")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Thunder!").toString());
                        setName(item2, ChatColor.GOLD + "Thor's Axe", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Potionlauncher")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("KA-ZOOOOOM").toString());
                        setName(item2, ChatColor.GRAY + "Potion Launcher", lore3);
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Pyroaxe")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.DARK_RED).append("I come from the Iron Hills").toString());
                        setName(item2, ChatColor.DARK_RED + "Pyro Axe", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Medikit")) {
                        final List<String> lore3 = new ArrayList<String>();
                        lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Use to instantly heal to Full HP").toString());
                        setName(item2, ChatColor.GREEN + "Medikit", lore3);
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    if (args[2].equalsIgnoreCase("Bandage")) {
                        final List<String> lore3 = new ArrayList<String>();
                        if (args.length == 3) {
                            lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Rightclick to use.").toString());
                            lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Heals 10 Hearts over 20 Seconds.").toString());
                            lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Can be applied to other Players by hitting them.").toString());
                            setName(item2, ChatColor.GREEN + "Bandage", lore3);
                        }
                        else if (args.length > 3) {
                            if (args[3].equalsIgnoreCase("minor")) {
                                lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Rightclick to use.").toString());
                                lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Heals 5 Hearts over 10 Seconds.").toString());
                                lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Can be applied to other Players by hitting them.").toString());
                                setName(item2, ChatColor.GREEN + "Minor Bandage", lore3);
                            }
                            else if (args[3].equalsIgnoreCase("tough")) {
                                lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Rightclick to use.").toString());
                                lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Heals 15 Hearts over 20 Seconds.").toString());
                                lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Can be applied to other Players by hitting them.").toString());
                                setName(item2, ChatColor.GREEN + "Tough Bandage", lore3);
                            }
                            else if (args[3].equalsIgnoreCase("heavy")) {
                                lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Rightclick to use.").toString());
                                lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Heals 25 Hearts over 20 Seconds.").toString());
                                lore3.add(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.ITALIC).append("Can be applied to other Players by hitting them.").toString());
                                setName(item2, ChatColor.GREEN + "Heavy Bandage", lore3);
                            }
                        }
                        p.getInventory().addItem(new ItemStack[] { item2 });
                        sender.sendMessage(ChatColor.MAGIC + "++" + ChatColor.GREEN + "The Item has been sent." + ChatColor.WHITE + " " + ChatColor.MAGIC + "++");
                        p.sendMessage(message);
                        return true;
                    }
                    sender.sendMessage(ChatColor.RED + "Enchantment not found");
                }
                else {
                    sender.sendMessage(ChatColor.RED + "Usage: /enchgive <player> <material> <enchantment>");
                }
            }
            else {
                sender.sendMessage(ChatColor.RED + "You don't have the Permission to do that");
            }
        }
        return false;
    }
}
