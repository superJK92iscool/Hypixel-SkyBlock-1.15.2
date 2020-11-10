package net.hypixel.skyblock.client.gui.screen;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.systems.RenderSystem;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.inventory.container.minion.AbstractMinionContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * Serve as a basis for all Minion Screens
 *
 * @author MrPineapple070
 * @version 6 June 2020
 * @since 6 June 2020
 */
public class AbstractMinionScreen extends ContainerScreen<AbstractMinionContainer> {
	abstract static class Button extends AbstractButton {
		private boolean isSelected;

		/**
		 * Construct this.
		 *
		 * @param xIn x coordinate
		 * @param yIn y coordinate
		 */
		public Button(int xIn, int yIn) {
			super(xIn, yIn, 22, 22, "");
		}

		/**
		 * @return {@link #isSelected}
		 */
		public boolean isSelected() {
			return this.isSelected;
		}
	}

	/**
	 * The texture for this screen.
	 */
	@Nonnull
	public static final ResourceLocation background_texture = new ResourceLocation(HypixelSkyBlockMod.MOD_ID,
			"textures/gui/abstract_minion_screen.png");

	/**
	 * Construct this.
	 *
	 * @param screenContainer {@link AbstractMinionContainer} to holds this.
	 * @param inv             {@link PlayerInventory} of the player viewing this.
	 * @param titleIn         {@link ITextComponent} for the title.
	 */
	public AbstractMinionScreen(AbstractMinionContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.guiLeft = 0;
		this.guiTop = 0;
		this.xSize = 256;
		this.ySize = 256;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.getTextureManager().bindTexture(background_texture);
		this.blit((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.font.drawString(this.title.getFormattedText(), 48, 44, 0x404040);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 48, 126, 0x404040);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}