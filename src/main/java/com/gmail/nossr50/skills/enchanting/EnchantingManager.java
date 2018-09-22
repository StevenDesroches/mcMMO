package com.gmail.nossr50.skills.enchanting;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.skills.SecondaryAbility;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.datatypes.skills.XPGainReason;
import com.gmail.nossr50.skills.SkillManager;
import com.gmail.nossr50.util.EnchantmentUtils;
import com.gmail.nossr50.util.Misc;
import com.gmail.nossr50.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import com.gmail.nossr50.config.experience.ExperienceConfig;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import com.gmail.nossr50.util.ItemUtils;

import java.util.Map;
import java.util.logging.Logger;

public class EnchantingManager extends SkillManager {
    private double baseChancePrestidigitation = (Enchanting.prestidigitationMaxBonusLevel / Enchanting.prestidigitationMaxBonus);
    private double baseChanceDouble = (Enchanting.arcaneImbuementMaxBonusLevel / Enchanting.arcaneImbuementMaxBonus);
    private double skillValue;

    private boolean canUseArcaneImbuement() { return Permissions.secondaryAbilityEnabled(getPlayer(), SecondaryAbility.ARCANE_IMBUEMENT) && getSkillLevel() >= Enchanting.arcaneImbumentUnlockLevel; }
    private boolean canUseOverwhelmingMagick() { return Permissions.secondaryAbilityEnabled(getPlayer(), SecondaryAbility.OVERWHELMING_MAGICK) && getSkillLevel() >= Enchanting.overwhelmingMagickUnlockLevel; }
    private boolean canUseConcentration() { return Permissions.secondaryAbilityEnabled(getPlayer(), SecondaryAbility.CONCENTRATION) && getSkillLevel() >= Enchanting.concentrationUnlockLevel; }

    public EnchantingManager(McMMOPlayer mcMMOPlayer) {
        super(mcMMOPlayer, SkillType.ENCHANTING );
        skillValue = mcMMOPlayer.getSkillLevel(SkillType.ENCHANTING);
    }

    public void handleEnchant(int enchantXpCost, Map<Enchantment, Integer> enchantsToAdd, ItemStack item, ItemStack heldItem) {

        int xp = editEnchant(item, enchantsToAdd, heldItem);
        applyXpGain((xp + enchantXpCost) * (int) ExperienceConfig.getInstance().getEnchantingXPBase(), XPGainReason.PVE);
    }

    private int editEnchant(ItemStack item, Map<Enchantment, Integer> enchantsToAdd, ItemStack heldItem) {
        int maxAddedEnchant = 1;
        int numbRoll = 0;
        int xp = 0;

    if (canUseConcentration()){
        maxAddedEnchant = 4;
        xp += 4;
        if (ItemUtils.isArmor(item)){
            armorEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else if (ItemUtils.isSword(heldItem) || ItemUtils.isSword(item)){
            combatEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else if (ItemUtils.isHoe(heldItem) || ItemUtils.isHoe(item) || ItemUtils.isAxe(item) || ItemUtils.isPickaxe(item) || ItemUtils.isShovel(item)) {
            farmingEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else if (ItemUtils.isBow(item)){
            bowEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else {
            defaultEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        }
    } else if (canUseOverwhelmingMagick()) {
        maxAddedEnchant = 3;
        xp += 3;
        if (ItemUtils.isArmor(item)){
            armorEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else if (ItemUtils.isSword(item)){
            combatEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else if (ItemUtils.isHoe(item) || ItemUtils.isAxe(item) || ItemUtils.isPickaxe(item) || ItemUtils.isShovel(item)) {
            farmingEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else if(ItemUtils.isBow(item)) {
            bowEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else {
            defaultEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        }
    } else if (canUseArcaneImbuement()) {
        maxAddedEnchant = 2;
        xp += 1;
        if (ItemUtils.isArmor(item)){
            armorEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else if (ItemUtils.isSword(item)){
            combatEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else if (ItemUtils.isHoe(item) || ItemUtils.isAxe(item) || ItemUtils.isPickaxe(item) || ItemUtils.isShovel(item)) {
            farmingEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else if (ItemUtils.isBow(item)){
            bowEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        } else {
            defaultEnchant(enchantsToAdd, item, numbRoll, maxAddedEnchant);
        }
    }

        for (Enchantment enchant : enchantsToAdd.keySet() ){
            double diceRoll = Misc.getRandom().nextDouble() * 100;
            int levelUp = 0;

            if (diceRoll <= (skillValue / baseChancePrestidigitation )) {
                diceRoll = Misc.getRandom().nextDouble() * 100;
                levelUp++;
                xp++;
                if (diceRoll <= (skillValue / baseChancePrestidigitation ) / 2) {
                    diceRoll = Misc.getRandom().nextDouble() * 100;
                    levelUp++;
                    xp++;
                    if (diceRoll <= (skillValue / baseChancePrestidigitation ) / 5) {
                        diceRoll = Misc.getRandom().nextDouble() * 100;
                        levelUp++;
                        xp++;
                        if (diceRoll <= (skillValue / baseChancePrestidigitation ) / 10) {
                            levelUp++;
                            xp++;
                        }
                    }
                }
            }

            int lvl = enchantsToAdd.get(enchant);
            enchantsToAdd.put(enchant, (lvl + levelUp));

        }

        return xp;
    }

    private void armorEnchant(Map<Enchantment, Integer> enchantsToAdd, ItemStack item, int numbRoll, int maxAddedEnchant) {
        for (Enchantment e : EnchantmentUtils.getListArmor().values()) {
            double diceRoll = Misc.getRandom().nextDouble() * 100;

            if (numbRoll == maxAddedEnchant) {
                return;
            }

            if (enchantsToAdd.containsKey(e)) {
                enchantsToAdd.put(e, enchantsToAdd.get(e) + 1);
                numbRoll++;
            } else if (diceRoll <= (skillValue / baseChanceDouble)) {
                enchantsToAdd.put(e, 1);
                numbRoll++;
            }
        }
    }

    private void combatEnchant(Map<Enchantment, Integer> enchantsToAdd, ItemStack item, int numbRoll, int maxAddedEnchant) {
        for (Enchantment e : EnchantmentUtils.getListCombat().values()) {
            double diceRoll = Misc.getRandom().nextDouble() * 100;

            if (numbRoll == maxAddedEnchant) {
                return;
            }

            if (enchantsToAdd.containsKey(e)) {
                enchantsToAdd.put(e, enchantsToAdd.get(e) + 1);
                numbRoll++;
            } else if (diceRoll <= (skillValue / baseChanceDouble)) {
                enchantsToAdd.put(e, 1);
                numbRoll++;
            }
        }
    }

    private void farmingEnchant( Map<Enchantment, Integer> enchantsToAdd, ItemStack item, int numbRoll, int maxAddedEnchant) {
        for (Enchantment e : EnchantmentUtils.getListFarming().values()) {
            double diceRoll = Misc.getRandom().nextDouble() * 100;

            if (numbRoll == maxAddedEnchant) { return; }

            if (enchantsToAdd.containsKey(e)) {
                enchantsToAdd.put(e, enchantsToAdd.get(e) + 1);
                numbRoll++;
            } else if (diceRoll <= (skillValue / baseChanceDouble)) {
                enchantsToAdd.put(e, 1);
                numbRoll++;
            }
        }
    }

    private void defaultEnchant(Map<Enchantment, Integer> enchantsToAdd, ItemStack item, int numbRoll, int maxAddedEnchant) {
        for (Enchantment e : EnchantmentUtils.getList().values()) {
            double diceRoll = Misc.getRandom().nextDouble() * 100;

            if (numbRoll == maxAddedEnchant) { return; }

            if (enchantsToAdd.containsKey(e)) {
                enchantsToAdd.put(e, enchantsToAdd.get(e) + 1);
                numbRoll++;
            } else if (diceRoll <= (skillValue / baseChanceDouble)) {
                enchantsToAdd.put(e, 1);
                numbRoll++;
            }
        }
    }
    private void bowEnchant(Map<Enchantment, Integer> enchantsToAdd, ItemStack item, int numbRoll, int maxAddedEnchant) {
        for (Enchantment e : EnchantmentUtils.getListBow().values()) {
            double diceRoll = Misc.getRandom().nextDouble() * 100;

            if (numbRoll == maxAddedEnchant) { return; }

            if (enchantsToAdd.containsKey(e)) {
                enchantsToAdd.put(e, enchantsToAdd.get(e) + 1);
                numbRoll++;
            } else if (diceRoll <= (skillValue / baseChanceDouble)) {
                enchantsToAdd.put(e, 1);
                numbRoll++;
            }
        }
    }
}
