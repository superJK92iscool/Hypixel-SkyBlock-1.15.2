package net.hypixel.skyblock.client.gui.screen;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.systems.RenderSystem;

import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer;
import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer.GreaterBC;
import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer.LargeBC;
import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer.MediumBC;
import net.hypixel.skyblock.inventory.container.AbstractBackpackContainer.SmallBC;
import net.hypixel.skyblock.items.AbstractBackpack.Type;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * Serve as a base for Backpack Screen
 *
 * @author MrPineapple070
 * @version 02 June 2020
 * @since 11 June 2019
 */
public abstract class AbstractBackpackScreen extends ContainerScreen<AbstractBackpackContainer> {
	public static class GreaterBS extends AbstractBackpackScreen {
		public GreaterBS(GreaterBC screenContainer, PlayerInventory inv, ITextComponent titleIn) {
			super(screenContainer, inv, titleIn);
		}
	}

	public static class LargeBS extends AbstractBackpackScreen {
		public LargeBS(LargeBC screenContainer, PlayerInventory inv, ITextComponent titleIn) {
			super(screenContainer, inv, titleIn);
		}
	}

	public static class MediumBS extends AbstractBackpackScreen {
		public MediumBS(MediumBC screenContainer, PlayerInventory inv, ITextComponent titleIn) {
			super(screenContainer, inv, titleIn);
		}
	}

	public static class SmallBS extends AbstractBackpackScreen {
		public SmallBS(SmallBC screenContainer, PlayerInventory inv, ITextComponent titleIn) {
			super(screenContainer, inv, titleIn);
		}
	}

	/**
	 * Background Texture for Greater Backpack
	 */
	@Nonnull
	private static final ResourceLocation greater = new ResourceLocation(
			"textures/gui/container/greater_backpack_screen.png");

	/**
	 * Background Texture for Large Backpack
	 */
	@Nonnull
	private static final ResourceLocation large = new ResourceLocation(
			"textures/gui/container/large_backpack_screen.png");

	/**
	 * Background Texture for Mediium Backpack
	 */
	@Nonnull
	private static final ResourceLocation medium = new ResourceLocation(
			"textures/gui/container/medium_backpack_screen.png");

	/**
	 * Background Texture for Small Backpack
	 */
	@Nonnull
	private static final ResourceLocation small = new ResourceLocation(
			"textures/gui/container/small_backpack_screen.png");

	/**
	 * The {@link Type} of this.
	 */
	@Nonnull
	protected final Type type;

	/**
	 * Construct this
	 *
	 * @param screenContainer the {@link AbstractBackpackContainer}
	 * @param inv             the {@link PlayerInventory}
	 * @param titleIn         the {@link ITextComponent} for the title of this
	 *                        screen.
	 */
	protected AbstractBackpackScreen(AbstractBackpackContainer screenContainer, PlayerInventory inv,
			ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.xSize = 256;
		this.ySize = 256;
		this.guiLeft = 0;
		this.guiTop = 0;
		this.type = screenContainer.getBackpackType();
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1, 1, 1, 1);
		ResourceLocation rl;
		switch (this.type) {
		case Small:
		default:
			rl = small;
			break;
		case Medium:
			rl = medium;
			break;
		case Large:
			rl = large;
			break;
		case Greater:
			rl = greater;
			break;
		}
		this.minecraft.getTextureManager().bindTexture(rl);
		final int startX = (this.width - this.xSize) / 2;
		final int startY = (this.height - this.ySize) / 2;
		this.blit(startX, startY, 0, 0, this.xSize, this.type.size * 2 + 17);
		this.blit(startX, startY + this.type.size * 2 + 17, 0, 126, this.xSize, 96);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.font.drawString(this.title.getFormattedText(), 8f, 6f, 0x404040);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8f, this.ySize - 94, 0x404040);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}