package net.hypixel.skyblock.client.gui.screen;

import java.util.Objects;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.systems.RenderSystem;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.blocks.minion.MinionChestBlock.ChestType;
import net.hypixel.skyblock.inventory.container.minion.MinionChestContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * A basis for MinionChest Screen.
 *
 * @author MrPineapple070
 * @version 24 June 2020
 * @since 14 June 2019
 */
public abstract class MinionChestScreen extends ContainerScreen<MinionChestContainer> {
	public static class SmallMCS extends MinionChestScreen {
		public SmallMCS(MinionChestContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
			super(screenContainer, inv, titleIn, ChestType.Small);
		}
	}
	
	public static class MediumMCS extends MinionChestScreen {
		public MediumMCS(MinionChestContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
			super(screenContainer, inv, titleIn, ChestType.Medium);
		}
	}
	
	public static class LargeMCS extends MinionChestScreen {
		public LargeMCS(MinionChestContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
			super(screenContainer, inv, titleIn, ChestType.Large);
		}
	}

	/**
	 * Background Texture for Small Minion Chest
	 */
	@Nonnull
	private static final ResourceLocation small = new ResourceLocation(HypixelSkyBlockMod.MOD_ID,
			"textures/gui/small_minion_chest_screen.png");

	/**
	 * Background Texture for Medium Minion Chest
	 */
	@Nonnull
	private static final ResourceLocation medium = new ResourceLocation(HypixelSkyBlockMod.MOD_ID,
			"textures/gui/medium_minion_chest_screen.png");
	
	/**
	 * Background Texture for Large Minion Chest
	 */
	@Nonnull
	private static final ResourceLocation large = new ResourceLocation(HypixelSkyBlockMod.MOD_ID,
			"textures/gui/large_minion_chest_screen.png");
	
	/**
	 * The {@link ChestType} of this.
	 */
	@Nonnull
	protected final ChestType type;

	protected MinionChestScreen(MinionChestContainer screenContainer, PlayerInventory inv, ITextComponent titleIn,
			ChestType type) {
		super(screenContainer, inv, titleIn);
		this.guiLeft = 0;
		this.guiTop = 0;
		this.xSize = 256;
		this.ySize = 256;
		this.type = Objects.requireNonNull(type, "Type of this MinionChestScreen cannot be null.");
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX,
			int mouseY) {
		// RenderSystem.color4f(1f, 1f, 1f, 1f);
		RenderSystem.blendColor(1f, 1f, 1f, 1f);
		switch (this.type) {
		case Small:
			this.minecraft.getTextureManager().bindTexture(small);
			break;
		case Medium:
			this.minecraft.getTextureManager().bindTexture(medium);
			break;
		case Large:
			this.minecraft.getTextureManager().bindTexture(large);
			break;
		default:
			throw new IllegalStateException("Illegal ChestType " + this.type.name());
		}
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.blit(x, y, 0, 0, this.xSize, this.type.additional + 17);
		this.blit(x, y + this.type.additional + 17, 0, 126, this.xSize, 96);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.font.drawString(this.title.getString(), 48, 50, 0x404040);
		this.font.drawString(this.playerInventory.getDisplayName().getString(), 48, 118, 0x404040);
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}
}