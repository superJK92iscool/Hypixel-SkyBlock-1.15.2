package net.hypixel.skyblock.tileentity.minion;

import net.hypixel.skyblock.tileentity.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

/**
 * @author MrPineapple070
 * @version 31 December 2020
 * @since 11 July 2019
 */
public class AcaciaMinionTileEntity extends AbstractMinionTileEntity {
	public AcaciaMinionTileEntity(TileEntityType<? extends AbstractMinionTileEntity> typeIn, MinionTier tier) {
		super(typeIn, tier);
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return null;
	}

	@Override
	protected Item[] getCompactor() {
		return null;
	}

	@Override
	public ITextComponent getDisplayName() {
		return null;
	}

	@Override
	protected SoundEvent getSoundEvent() {
		return null;
	}

	@Override
	protected BlockState getState() {
		return null;
	}

	@Override
	protected Item[] getSuperCompactor() {
		return null;
	}

	@Override
	protected BlockPos[][][] initSurround() {
		return null;
	}

	@Override
	protected BlockPos pickBlock() {
		return null;
	}

	@Override
	protected void setSurround() {
	}

	@Override
	protected void setValidSurround() {
	}

	@Override
	public void tick() {
	}
	
	public static class AcaciaMTE1 extends AcaciaMinionTileEntity {
		public AcaciaMTE1() {
			super(ModTileEntityTypes.acacia_minion_1.get(), MinionTier.I);
		}
	}
	
	public static class AcaciaMTE2 extends AcaciaMinionTileEntity {
		public AcaciaMTE2() {
			super(ModTileEntityTypes.acacia_minion_2.get(), MinionTier.II);
		}
	}
	
	public static class AcaciaMTE3 extends AcaciaMinionTileEntity {
		public AcaciaMTE3() {
			super(ModTileEntityTypes.acacia_minion_3.get(), MinionTier.III);
		}
	}
	
	public static class AcaciaMTE4 extends AcaciaMinionTileEntity {
		public AcaciaMTE4() {
			super(ModTileEntityTypes.acacia_minion_4.get(), MinionTier.IV);
		}
	}
	
	public static class AcaciaMTE5 extends AcaciaMinionTileEntity {
		public AcaciaMTE5() {
			super(ModTileEntityTypes.acacia_minion_5.get(), MinionTier.V);
		}
	}
	
	public static class AcaciaMTE6 extends AcaciaMinionTileEntity {
		public AcaciaMTE6() {
			super(ModTileEntityTypes.acacia_minion_6.get(), MinionTier.VI);
		}
	}
	
	public static class AcaciaMTE7 extends AcaciaMinionTileEntity {
		public AcaciaMTE7() {
			super(ModTileEntityTypes.acacia_minion_7.get(), MinionTier.VII);
		}
	}
	
	public static class AcaciaMTE8 extends AcaciaMinionTileEntity {
		public AcaciaMTE8() {
			super(ModTileEntityTypes.acacia_minion_8.get(), MinionTier.VIII);
		}
	}
	
	public static class AcaciaMTE9 extends AcaciaMinionTileEntity {
		public AcaciaMTE9() {
			super(ModTileEntityTypes.acacia_minion_9.get(), MinionTier.IX);
		}
	}
	
	public static class AcaciaMTEa extends AcaciaMinionTileEntity {
		public AcaciaMTEa() {
			super(ModTileEntityTypes.acacia_minion_a.get(), MinionTier.X);
		}
	}
	
	public static class AcaciaMTEb extends AcaciaMinionTileEntity {
		public AcaciaMTEb() {
			super(ModTileEntityTypes.acacia_minion_b.get(), MinionTier.XI);
		}
	}
}