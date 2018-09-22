package com.gmail.nossr50.skills.enchanting;

import com.gmail.nossr50.config.AdvancedConfig;
import com.gmail.nossr50.config.Config;
import org.bukkit.Material;

public class Enchanting {
    public static double prestidigitationMaxBonus = AdvancedConfig.getInstance().getPrestidigitationMaxBonus();
    public static int prestidigitationMaxBonusLevel = AdvancedConfig.getInstance().getPrestidigitationMaxBonusLevel();
    public static int arcaneImbumentUnlockLevel = AdvancedConfig.getInstance().getArcaneImbuementUnlockLevel();
    public static double arcaneImbuementMaxBonus = AdvancedConfig.getInstance().getArcaneImbuementMaxBonus();
    public static int arcaneImbuementMaxBonusLevel = AdvancedConfig.getInstance().getArcaneImbuementMaxBonusLevel();
    public static int overwhelmingMagickUnlockLevel = AdvancedConfig.getInstance().getOverwhelmingMagickUnlockLevel();
    public static int concentrationUnlockLevel = AdvancedConfig.getInstance().getConcentrationUnlockLevel();

    //public static Material anvilMaterial  = Config.getInstance().getRepairAnvilMaterial();
}
