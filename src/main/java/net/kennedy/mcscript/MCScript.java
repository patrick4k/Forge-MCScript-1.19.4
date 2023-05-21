package net.kennedy.mcscript;

import com.mojang.logging.LogUtils;
import net.kennedy.mcscript.item.ModCreativeModeTab;
import net.kennedy.mcscript.item.ModItems;
import net.kennedy.mcscript.screen.ModMenuTypes;
import net.kennedy.mcscript.screen.ScriptEditorScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MCScript.MOD_ID)
public class MCScript
{
    public static final String MOD_ID = "mcscript";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MCScript()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event)
    {
        // TODO: Add items to creative tab
        if (event.getTab() == ModCreativeModeTab.MCSCRIPT_TAB)
        {
            event.accept(Items.REDSTONE);
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.SCRIPT_EDITOR_MENU.get(), ScriptEditorScreen::new);
        }
    }
}
