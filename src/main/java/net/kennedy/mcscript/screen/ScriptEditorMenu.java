package net.kennedy.mcscript.screen;

import net.kennedy.mcscript.item.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Function;

public class ScriptEditorMenu extends AbstractContainerMenu {
    private final Level level;
    public ScriptEditorMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv);
    }

    public ScriptEditorMenu(int id, Inventory inv) {
        super(ModMenuTypes.SCRIPT_EDITOR_MENU.get(), id);
        this.level = inv.player.level;
    }

    @Override
    public ItemStack quickMoveStack(Player p_38941_, int p_38942_) {
        // TODO: ?
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    public boolean runScript(String script) {
        System.out.println("RUNNING SCRIPT");
        System.out.println(script);
        return true;
    }

}
