package net.hypixel.skyblock.client.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;

import net.hypixel.skyblock.HypixelSkyBlockMod;
import net.hypixel.skyblock.blocks.TimeSaver;
import net.hypixel.skyblock.inventory.container.TimeSaverContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.AbstractButton;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.play.client.CCloseWindowPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * A {@link ContainerScreen} that allows the {@link PlayerEntity} to interact
 * with {@link TimeSaver}.
 *
 * @author MrPineapple070
 * @version 07 October 2020
 * @since 07 October 2020
 */
public class TimeSaverScreen extends ContainerScreen<TimeSaverContainer> {
	/**
	 * An {@link AbstractButton} that allows the {@link PlayerEntity} to interact
	 * with {@link TimeSaverScreen}.
	 */
	@OnlyIn(Dist.CLIENT)
	abstract static class Button extends AbstractButton {
		/**
		 * A boolean tracking if this button is selected.
		 */
		private boolean selected;

		/**
		 * Construct this
		 *
		 * @param xIn x coordinate
		 * @param yIn y coordinate
		 */
		public Button(int xIn, int yIn) {
			super(xIn, yIn, 22, 22, "");
		}

		/**
		 * Binds and renders a texture over this button.
		 */
		protected abstract void bindTexture();

		/**
		 * @return {@link #selected}
		 */
		public boolean isSelected() {
			return this.selected;
		}

		@Override
		public void renderButton(int mouseX, int mouseY, float partialTicks) {
			Minecraft.getInstance().getTextureManager().bindTexture(TimeSaverScreen.background_texture);
			RenderSystem.color4f(1f, 1f, 1f, 1f);
			final int i = 219;
			int j = 0;
			if (!this.active)
				j += this.width * 2;
			else if (this.selected)
				j += this.width * 1;
			else if (this.isHovered())
				j += this.width * 3;

			this.blit(this.x, this.y, j, i, this.width, this.height);
			this.bindTexture();
		}

		/**
		 * @param selectedIn new {@link #selected} value.
		 */
		public void setSelected(boolean selectedIn) {
			this.selected = selectedIn;
		}
	}

	/**
	 * A {@link SpriteButton} that allows the {@link PlayerEntity} to cancel
	 * interaction with {@link TimeSaverScreen}.
	 */
	@OnlyIn(Dist.CLIENT)
	class CancelButton extends SpriteButton {
		public CancelButton(int xIn, int yIn) {
			super(xIn, yIn, 112, 220);
		}

		/**
		 * Closes {@link TimeSaverScreen} when this button is closed.
		 */
		@Override
		public void onPress() {
			TimeSaverScreen.this.minecraft.player.connection
					.sendPacket(new CCloseWindowPacket(TimeSaverScreen.this.minecraft.player.openContainer.windowId));
			TimeSaverScreen.this.minecraft.displayGuiScreen((Screen) null);
		}

		@Override
		public void renderToolTip(int p_renderToolTip_1_, int p_renderToolTip_2_) {
			TimeSaverScreen.this.renderTooltip(I18n.format("gui.cancel"), p_renderToolTip_1_, p_renderToolTip_2_);
		}
	}

	/**
	 * A {@link SpriteButton} that allows that {@link PlayerEntity} to confirm
	 * interaction with {@link TimeSaverScreen}.
	 */
	@OnlyIn(Dist.CLIENT)
	class ConfirmButton extends SpriteButton {
		public ConfirmButton(int xIn, int yIn) {
			super(xIn, yIn, 90, 220);
		}

		@Override
		public void onPress() {
			TimeSaverScreen.this.minecraft.getConnection(/** CTimeSaverPacket */
			);
			TimeSaverScreen.this.minecraft.player.connection
					.sendPacket(new CCloseWindowPacket(TimeSaverScreen.this.minecraft.player.openContainer.windowId));
			TimeSaverScreen.this.minecraft.displayGuiScreen((Screen) null);
		}

		/**
		 * Render tooltip when hovering over this button.
		 */
		@Override
		public void renderToolTip(int p_renderToolTip_1_, int p_renderToolTip_2_) {
			TimeSaverScreen.this.renderTooltip(I18n.format("gui.done"), p_renderToolTip_1_, p_renderToolTip_2_);
		}
	}

	/**
	 * A {@link Button} that allows the {@link PlayerEntity} to select the time to
	 * keep the world at.
	 */
	@OnlyIn(Dist.CLIENT)
	class PowerButton extends Button {
		/**
		 * {@link TextureAtlasSprite} that will display as the texture for this button.
		 */
		private final TextureAtlasSprite texture_sprite;

		/**
		 * Selected time.
		 */
		private final int time;

		public PowerButton(int xIn, int yIn, int selected) {
			super(xIn, yIn);
			this.texture_sprite = null;
			if (selected < 0 || selected > 23)
				throw new IllegalArgumentException("Selected time cannot be less than 0 or greather than 23.");
			this.time = selected;
		}

		@Override
		protected void bindTexture() {
			Minecraft.getInstance().getTextureManager()
					.bindTexture(this.texture_sprite.getAtlasTexture().getTextureLocation());
			AbstractGui.blit(this.x + 2, this.y + 2, this.getBlitOffset(), 18, 18, this.texture_sprite);
		}

		@Override
		public void onPress() {
			if (!this.isSelected()) {
				TimeSaverScreen.this.buttons.clear();
				TimeSaverScreen.this.children.clear();
				TimeSaverScreen.this.init();
				TimeSaverScreen.this.tick();
			}
		}

		@Override
		public void renderToolTip(int width, int height) {
			final String s = I18n.format(String.valueOf(this.time));
			TimeSaverScreen.this.renderTooltip(s, width, height);
		}
	}

	/**
	 * A {@link Button} that allows textures to render over this.
	 */
	@OnlyIn(Dist.CLIENT)
	abstract static class SpriteButton extends Button {
		private final int height;
		private final int width;

		protected SpriteButton(int xIn, int yIn, int width, int height) {
			super(xIn, yIn);
			this.width = width;
			this.height = height;
		}

		@Override
		protected void bindTexture() {
			this.blit(this.x + 2, this.y + 2, this.width, this.height, 18, 18);
		}
	}

	/**
	 * The texture for this screen.
	 */
	public static final ResourceLocation background_texture = new ResourceLocation(HypixelSkyBlockMod.MOD_ID,
			"textures/gui/time_saver_screen.png");

	/**
	 * @param screenContainer {@link TimeSaverContainer} containing this.
	 * @param inv             {@link PlayerInventory} of {@link PlayerEntity}
	 *                        interacting with this
	 * @param titleIn         {@link ITextComponent} of the title for this screen
	 */
	public TimeSaverScreen(TimeSaverContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
		this.guiLeft = 0;
		this.guiTop = 0;
		this.xSize = 256;
		this.ySize = 256;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(background_texture);
		this.blit((this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0, this.xSize, this.ySize);
		this.itemRenderer.zLevel = 100f;
		for (int t = 0; t < 9; t++)
			this.itemRenderer.renderItemAndEffectIntoGUI(new ItemStack(Items.DAYLIGHT_DETECTOR), 48 + 18 * t, 51);
		for (int t = 0; t < 7; t++)
			this.itemRenderer.renderItemAndEffectIntoGUI(new ItemStack(Items.DAYLIGHT_DETECTOR), 66 + 18 * t, 69);
		this.itemRenderer.zLevel = 0f;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		for (final Widget widget : this.buttons)
			if (widget.isHovered()) {
				widget.renderToolTip(mouseX - this.guiLeft, mouseY - this.guiTop);
				break;
			}
	}

	@Override
	protected void init() {
		super.init();
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
	}
}