package net.kennedy.mcscript.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sun.jna.platform.win32.WinDef;
import net.kennedy.mcscript.MCScript;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.client.gui.components.MultiLineEditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.player.Input;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ScriptEditorScreen extends AbstractContainerScreen<ScriptEditorMenu> {
    private static final int CHAR_WIDTH = 34;
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MCScript.MOD_ID, "textures/gui/script_editor_gui.png");

    private MultiLineEditBox textField;

    public ScriptEditorScreen(ScriptEditorMenu menu, Inventory inv, Component component)
    {
        super(menu, inv, component);
    }

    @Override
    protected void init() {
        super.init();

        assert this.minecraft != null;
        textField = new MultiLineEditBox(
                this.minecraft.font,
                0, 0,
                this.width, this.height,
                Component.translatable(""),
                Component.translatable(""));

        textField.setValue("print(\"Hello World!\");");
        this.addRenderableWidget(textField);
        this.setFocused(textField);
    }

    @Override
    public boolean mouseScrolled(double p_94686_, double p_94687_, double p_94688_) {
        return this.textField.mouseScrolled(p_94686_, p_94687_, p_94688_);
    }

    @Override
    public boolean keyPressed(int keyCode, int p_97766_, int p_97767_) {
        InputConstants.Key mouseKey = InputConstants.getKey(keyCode, p_97766_);

        assert this.minecraft != null;

        String invKey = this.minecraft.options.keyInventory.getKey().getName();
        if (invKey.equals(mouseKey.getName())) {
            textField.keyPressed(keyCode, p_97766_, p_97767_);
            return true;
        }

        switch (mouseKey.getName()) {
            case "key.keyboard.tab" -> {
                for (int i = 0; i < 5; i++) {
                    textField.charTyped(' ', 0);
                }
                return true;
            }
            case "key.keyboard.f5" -> {
                return this.menu.runScript(textField.getValue());
            }
            case "key.keyboard.space" -> {
                textField.keyPressed(keyCode, p_97766_, p_97767_);
                return true;
            }
            case "key.keyboard.s" -> {
                if (InputConstants.isKeyDown(this.minecraft.getWindow().getWindow(), InputConstants.KEY_LCONTROL) ||
                        InputConstants.isKeyDown(this.minecraft.getWindow().getWindow(), InputConstants.KEY_RCONTROL)) {
                    System.out.println("Save script!");
                    return true;
                }
            }
        }

        return super.keyPressed(keyCode, p_97766_, p_97767_);
    }


    @Override
    protected void renderBg(PoseStack pPoseStack, float particleTick, int mouseX, int mouseY)
    {
        this.renderBackground(pPoseStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (width - this.getXSize()) / 2;
        int y = (height - this.getXSize()) / 2;
        blit(pPoseStack, x, y, 0, 0, width, height);
        this.setFocused(textField);
    }

}
