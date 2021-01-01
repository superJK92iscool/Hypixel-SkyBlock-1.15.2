package net.hypixel.skyblock.client.gui.screen;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.systems.RenderSystem;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.blocks.minion.AbstractMinionBlock;
import net.hypixel.skyblock.blocks.minion.MinionChestBlock;
import net.hypixel.skyblock.inventory.container.minion.AbstractMinionContainer;
import net.hypixel.skyblock.tileentity.minion.AbstractMinionTileEntity.MinionTier;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Serve as a basis for all Minion Screens
 *
 * @author MrPineapple070
 * @version 6 June 2020
 * @since 6 June 2020
 */
@OnlyIn(Dist.CLIENT)
public final class AbstractMinionScreen extends ContainerScreen<AbstractMinionContainer> {
	/**
	 * The texture for this screen.
	 */
	@Nonnull
	public static final ResourceLocation background_texture = new ResourceLocation(HypixelSkyBlockMod.MOD_ID,
			"textures/gui/abstract_minion_screen.png");

	/**
	 * Determine if {@link #upgrade} and {@link #empty} are drawn to the screen.
	 */
	private boolean buttonsNotDrawn;

	/**
	 * {@link UpgradeButton} to display on this screen.
	 */
	private UpgradeButton upgrade;

	/**
	 * {@link EmptyButton} to display on this screen.
	 */
	private EmptyButton empty;

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
		screenContainer.addListener(new IContainerListener() {
			@Override
			public void sendAllContents(Container containerToSend, NonNullList<ItemStack> itemsList) {
				return;
			}

			@Override
			public void sendSlotContents(Container containerToSend, int slotInd, ItemStack stack) {
				return;
			}

			@Override
			public void sendWindowProperty(Container containerIn, int varToUpdate, int newValue) {
				AbstractMinionScreen.this.buttonsNotDrawn = true;
			}
		});
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// RenderSystem.color4f(1f, 1f, 1f, 1f);
		RenderSystem.blendColor(1f, 1f, 1f, 1f);
		this.minecraft.getTextureManager().bindTexture(background_texture);
		this.blit((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.font.drawString(this.title.getString(), 48, 44, 0x404040);
		this.font.drawString("Fuel", 48, 56, 0x404040);
		this.font.drawString("Seller", 48, 73, 0x404040);
		this.font.drawString("Boost0", 48, 91, 0x404040);
		this.font.drawString("Boost1", 48, 109, 0x404040);
		this.font.drawString(this.playerInventory.getDisplayName().getString(), 48, 126, 0x404040);
	}

	@Override
	protected void init() {
		super.init();
		this.upgrade = this.addButton(new UpgradeButton(this.guiLeft, this.guiTop));
		this.empty = this.addButton(new EmptyButton(this.guiLeft, this.guiTop));
		this.buttonsNotDrawn = true;
		this.upgrade.active = true;
		this.empty.active = true;
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	public void tick() {
		super.tick();
		if (this.buttonsNotDrawn)
			this.buttonsNotDrawn = false;
	}

	/**
	 * Serve as a base for buttons to be displayed on this screen.
	 */
	@OnlyIn(Dist.CLIENT)
	abstract static class Button extends AbstractButton {
		/**
		 * Construct this.
		 *
		 * @param xIn  x coordinate
		 * @param yIn  y coordinate
		 * @param name {@link String} to display on the button.
		 */
		public Button(int xIn, int yIn, String name) {
			super(xIn, yIn, 18, 18, name);
		}
	}

	/**
	 * When this {@link Button} is pressed, automatically upgrade this
	 * {@link AbstractMinionBlock} to the next {@link MinionTier}.<br>
	 * This {@link Button} will not do anything if the {@link AbstractMinionBlock} is at the
	 * maximum {@link MinionTier}<br>
	 * or if the {@link PlayerEntity} does not have the required materials.
	 */
	class UpgradeButton extends Button {
		public UpgradeButton(int x, int y) {
			super(x + 119, y + 109, "\u2191");
		}

		@Override
		public void onPress() {
			HypixelSkyBlockMod.LOGGER.info(UpgradeButton.class.getSimpleName() + " pressed.");
			// AbstractMinionScreen.this.minecraft.getConnection().sendPacket(new
			// CEmptyMinionPacket());
		}

		@Override
		public void renderToolTip(int x, int y) {
			AbstractMinionScreen.this.renderTooltip("Upgrade to next Tier", x, y);
		}
	}

	/**
	 * When this {@link Button} is pressed, automatically empty all {@link Item}
	 * from this {@link AbstractMinionBlock}.<br>
	 * Also empties {@link MinionChestBlock} if the {@link AbstractMinionBlock} has one.
	 * This {@link Button} will not do anything is the {@link AbstractMinionBlock} is empty.
	 */
	class EmptyButton extends Button {
		public EmptyButton(int x, int y) {
			super(x + 138, y + 109, "\u2193");
		}

		@Override
		public void onPress() {
			HypixelSkyBlockMod.LOGGER.info(EmptyButton.class.getSimpleName() + " pressed.");
			// AbstractMinionScreen.this.minecraft.getConnection().sendPacket(null);
		}

		@Override
		public void renderToolTip(int x, int y) {
			AbstractMinionScreen.this.renderTooltip("Empty the Minion of contents", x, y);
		}
	}
}