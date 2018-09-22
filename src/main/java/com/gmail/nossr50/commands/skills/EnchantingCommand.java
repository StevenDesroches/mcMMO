package com.gmail.nossr50.commands.skills;

import com.gmail.nossr50.datatypes.skills.SecondaryAbility;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.locale.LocaleLoader;
import com.gmail.nossr50.skills.enchanting.Enchanting;
import com.gmail.nossr50.util.Permissions;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class EnchantingCommand extends SkillCommand {
    double prestidigitationChance;
    double arcaneImbuementChance;

    private boolean canPrestidigitation;
    private boolean canArcaneImbuement;
    private boolean canOverwhelmingMagick;
    private boolean canConcentration;

    public EnchantingCommand() { super(SkillType.ENCHANTING); }

    @Override
    protected void dataCalculations(Player player, float skillValue, boolean isLucky) {
        if (canPrestidigitation){
            prestidigitationChance = Math.min(skillValue / (Enchanting.prestidigitationMaxBonusLevel / Enchanting.prestidigitationMaxBonus), Enchanting.prestidigitationMaxBonus);
        }
        if (canArcaneImbuement){
            arcaneImbuementChance = Math.min(skillValue / (Enchanting.arcaneImbuementMaxBonusLevel / Enchanting.arcaneImbuementMaxBonus), Enchanting.arcaneImbuementMaxBonus);
        }
    }

    @Override
    protected void permissionsCheck(Player player) {
        canPrestidigitation = Permissions.prestidigitation(player);
        canArcaneImbuement = Permissions.secondaryAbilityEnabled(player, SecondaryAbility.ARCANE_IMBUEMENT);
        canOverwhelmingMagick = Permissions.secondaryAbilityEnabled(player, SecondaryAbility.OVERWHELMING_MAGICK);
        canConcentration = Permissions.secondaryAbilityEnabled(player, SecondaryAbility.CONCENTRATION);
    }

    @Override
    protected List<String> effectsDisplay() {
        List<String> messages = new ArrayList<String>();

        if (canPrestidigitation) {
            messages.add(LocaleLoader.getString("Effects.Template", LocaleLoader.getString("Enchanting.Effect.0"), LocaleLoader.getString("Enchanting.Effect.1")));
        }
        if (canArcaneImbuement) {
            messages.add(LocaleLoader.getString("Effects.Template", LocaleLoader.getString("Enchanting.Effect.2"), LocaleLoader.getString("Enchanting.Effect.3")));
        }
        if (canOverwhelmingMagick) {
            messages.add(LocaleLoader.getString("Effects.Template", LocaleLoader.getString("Enchanting.Effect.4"), LocaleLoader.getString("Enchanting.Effect.5")));
        }
        if (canConcentration) {
            messages.add(LocaleLoader.getString("Effects.Template", LocaleLoader.getString("Enchanting.Effect.6"), LocaleLoader.getString("Enchanting.Effect.7")));
        }

        return messages;
    }

    @Override
    protected List<String> statsDisplay(Player player, float skillValue, boolean hasEndurance, boolean isLucky) {
        List<String> messages = new ArrayList<String>();

        if (canPrestidigitation) {
            messages.add(LocaleLoader.getString("Ability.Generic.Template", LocaleLoader.getString("Enchanting.Ability.Bonus.0"), LocaleLoader.getString("Enchanting.Ability.Bonus.1", prestidigitationChance)));
        }
        if (canArcaneImbuement){
            if(skillValue < Enchanting.arcaneImbumentUnlockLevel) {
                messages.add(LocaleLoader.getString("Ability.Generic.Template.Lock", LocaleLoader.getString("Enchanting.Ability.Locked.0", Enchanting.arcaneImbumentUnlockLevel)));
            } else {
                messages.add(LocaleLoader.getString("Ability.Generic.Template", LocaleLoader.getString("Enchanting.Ability.Bonus.2"), LocaleLoader.getString("Enchanting.Ability.Bonus.3", arcaneImbuementChance)));
            }
        }
        if (canOverwhelmingMagick){
            if(skillValue < Enchanting.overwhelmingMagickUnlockLevel) {
                messages.add(LocaleLoader.getString("Ability.Generic.Template.Lock", LocaleLoader.getString("Enchanting.Ability.Locked.1", Enchanting.overwhelmingMagickUnlockLevel)));
            } else {
                messages.add(LocaleLoader.getString("Ability.Generic.Template", LocaleLoader.getString("Enchanting.Ability.Bonus.4"), LocaleLoader.getString("Enchanting.Ability.Bonus.5")));
            }
        }
        if (canConcentration){
            if (skillValue < Enchanting.concentrationUnlockLevel) {
                messages.add(LocaleLoader.getString("Ability.Generic.Template.Lock", LocaleLoader.getString("Enchanting.Ability.Locked.2", Enchanting.concentrationUnlockLevel)));
            } else {
                messages.add(LocaleLoader.getString("Ability.Generic.Template", LocaleLoader.getString("Enchanting.Ability.Bonus.6"), LocaleLoader.getString("Enchanting.Ability.Bonus.7")));
            }
        }
        return messages;
    }
}
