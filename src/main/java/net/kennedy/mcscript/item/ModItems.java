package net.kennedy.mcscript.item;

import net.kennedy.mcscript.MCScript;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MCScript.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


    /* Register items here ------------------------------------------------------ */

    public static final RegistryObject<Item> SCRIPT_INTERPRETER = ITEMS.register("script_interpreter",
            () -> new MCScriptInterpreter(new Item.Properties().stacksTo(1)));

}
