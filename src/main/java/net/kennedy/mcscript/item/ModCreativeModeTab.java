package net.kennedy.mcscript.item;

import net.kennedy.mcscript.MCScript;
import net.kennedy.mcscript.util.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MCScript.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab MCSCRIPT_TAB;

    @SubscribeEvent
    public static void registerCreateModeTabs(CreativeModeTabEvent.Register event)
    {
        MCSCRIPT_TAB = event.registerCreativeModeTab(new ResourceLocation(MCScript.MOD_ID, "mcscript_tab"),
                builder -> builder.icon(() -> Config.CreativeTabItem).title(Component.literal("MCScript")).build());
    }
}
