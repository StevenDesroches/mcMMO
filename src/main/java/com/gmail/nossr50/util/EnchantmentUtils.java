package com.gmail.nossr50.util;

import java.util.HashMap;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentUtils {

    private static final HashMap<String, Enchantment> enchants = new HashMap<String, Enchantment>();

    private static final HashMap<String, Enchantment> enchantsCombat = new HashMap<String, Enchantment>();

    private static final HashMap<String, Enchantment> enchantsArmor = new HashMap<String, Enchantment>();

    private static final HashMap<String, Enchantment> enchantsBow = new HashMap<String, Enchantment>();

    private static final HashMap<String, Enchantment> enchantsFarm = new HashMap<String, Enchantment>();

    static {
        enchants.put("SHARPNESS", Enchantment.DAMAGE_ALL);
        enchants.put("POWER", Enchantment.ARROW_DAMAGE);
        enchants.put("FIRE_PROTECTION", Enchantment.PROTECTION_FIRE);
        enchants.put("FEATHER_FALLING", Enchantment.PROTECTION_FALL);
        enchants.put("PROTECTION", Enchantment.PROTECTION_ENVIRONMENTAL);
        enchants.put("BLAST_PROTECTION", Enchantment.PROTECTION_EXPLOSIONS);
        enchants.put("PROJECTILE_PROTECTION", Enchantment.PROTECTION_PROJECTILE);
        enchants.put("RESPIRATION", Enchantment.OXYGEN);
        enchants.put("INFINITY", Enchantment.ARROW_INFINITE);
        enchants.put("AQUA_AFFINITY", Enchantment.WATER_WORKER);
        enchants.put("UNBREAKING", Enchantment.DURABILITY);
        enchants.put("SMITE", Enchantment.DAMAGE_UNDEAD);
        enchants.put("BANE_OF_ARTHROPODS", Enchantment.DAMAGE_ARTHROPODS);
        enchants.put("EFFICIENCY", Enchantment.DIG_SPEED);
        enchants.put("FIRE_ASPECT", Enchantment.FIRE_ASPECT);
        enchants.put("SILK_TOUCH", Enchantment.SILK_TOUCH);
        enchants.put("FORTUNE", Enchantment.LOOT_BONUS_BLOCKS);
        enchants.put("LOOTING", Enchantment.LOOT_BONUS_MOBS);
        enchants.put("PUNCH", Enchantment.ARROW_KNOCKBACK);
        enchants.put("FLAME", Enchantment.ARROW_FIRE);
        enchants.put("KNOCKBACK", Enchantment.KNOCKBACK);
        enchants.put("THORNS", Enchantment.THORNS);
        enchants.put("MENDING", Enchantment.MENDING);
        enchants.put("DEPTH_STRIDER", Enchantment.DEPTH_STRIDER);
        enchants.put("FROST_WALKER", Enchantment.FROST_WALKER);

        enchantsCombat.put("SHARPNESS", Enchantment.DAMAGE_ALL);
        enchantsCombat.put("SMITE", Enchantment.DAMAGE_UNDEAD);
        enchantsCombat.put("BANE_OF_ARTHROPODS", Enchantment.DAMAGE_ARTHROPODS);
        enchantsCombat.put("FIRE_ASPECT", Enchantment.FIRE_ASPECT);
        enchantsCombat.put("LOOTING", Enchantment.LOOT_BONUS_MOBS);
        enchantsCombat.put("KNOCKBACK", Enchantment.KNOCKBACK);
        //enchantsCombat.put("MENDING", Enchantment.MENDING);
        enchantsCombat.put("UNBREAKING", Enchantment.DURABILITY);

        enchantsArmor.put("FIRE_PROTECTION", Enchantment.PROTECTION_FIRE);
        enchantsArmor.put("FEATHER_FALLING", Enchantment.PROTECTION_FALL);
        enchantsArmor.put("PROTECTION", Enchantment.PROTECTION_ENVIRONMENTAL);
        enchantsArmor.put("BLAST_PROTECTION", Enchantment.PROTECTION_EXPLOSIONS);
        enchantsArmor.put("PROJECTILE_PROTECTION", Enchantment.PROTECTION_PROJECTILE);
        enchantsArmor.put("RESPIRATION", Enchantment.OXYGEN);
        enchantsArmor.put("AQUA_AFFINITY", Enchantment.WATER_WORKER);
        enchantsArmor.put("UNBREAKING", Enchantment.DURABILITY);
        enchantsArmor.put("THORNS", Enchantment.THORNS);
        //enchantsArmor.put("MENDING", Enchantment.MENDING);
        enchantsArmor.put("DEPTH_STRIDER", Enchantment.DEPTH_STRIDER);
        enchantsArmor.put("FROST_WALKER", Enchantment.FROST_WALKER);

        enchantsBow.put("POWER", Enchantment.ARROW_DAMAGE);
        enchantsBow.put("INFINITY", Enchantment.ARROW_INFINITE);
        enchantsBow.put("UNBREAKING", Enchantment.DURABILITY);
        enchantsBow.put("PUNCH", Enchantment.ARROW_KNOCKBACK);
        enchantsBow.put("FLAME", Enchantment.ARROW_FIRE);
        //enchantsBow.put("MENDING", Enchantment.MENDING);

        enchantsFarm.put("UNBREAKING", Enchantment.DURABILITY);
        enchantsFarm.put("EFFICIENCY", Enchantment.DIG_SPEED);
        enchantsFarm.put("SILK_TOUCH", Enchantment.SILK_TOUCH);
        enchantsFarm.put("FORTUNE", Enchantment.LOOT_BONUS_BLOCKS);
        //.put("MENDING", Enchantment.MENDING);
    }

    /**
     * Method to get an {@link Enchantment} using it's Vanilla Minecraft name or Bukkit enum name
     *
     * @param enchantmentName Vanilla or Bukkit name of enchantment
     *
     * @return Enchantment or null if no enchantment was found
     */
    public static Enchantment getByName(String enchantmentName) {
        if (enchants.containsKey(enchantmentName)) {
            return enchants.get(enchantmentName);
        }

        return Enchantment.getByName(enchantmentName);
    }

    public static HashMap<String, Enchantment> getList(){ return enchants; }
    public static HashMap<String, Enchantment> getListCombat(){ return enchantsCombat; }
    public static HashMap<String, Enchantment> getListArmor(){ return enchantsArmor; }
    public static HashMap<String, Enchantment> getListBow(){ return enchantsBow; }
    public static HashMap<String, Enchantment> getListFarming(){ return enchantsFarm; }
}
