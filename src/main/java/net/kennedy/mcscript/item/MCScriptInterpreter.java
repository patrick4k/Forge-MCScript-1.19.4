package net.kennedy.mcscript.item;

import net.kennedy.mcscript.screen.ScriptEditorMenu;
import net.kennedy.mcscript.screen.ScriptEditorScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

public class MCScriptInterpreter extends Item {

    public MCScriptInterpreter(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && player instanceof ServerPlayer) {
            NetworkHooks.openScreen(
                (ServerPlayer) player,
                new SimpleMenuProvider(
                    (id, inv, plyr) -> new ScriptEditorMenu(id, inv),
                    Component.translatable("")
                )
            );
        }
        return super.use(level, player, hand);
    }


}
