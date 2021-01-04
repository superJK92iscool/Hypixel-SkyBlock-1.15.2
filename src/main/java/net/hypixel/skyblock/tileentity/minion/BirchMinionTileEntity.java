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

public class BirchMinionTileEntity extends AbstractMinionTileEntity {
	public BirchMinionTileEntity(TileEntityType<? extends AbstractMinionTileEntity> typeIn, MinionTier tier) {
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
	
	public static class BirchMTE1 extends BirchMinionTileEntity {
		public BirchMTE1() {
			super(ModTileEntityTypes.birch_minion_1.get(), MinionTier.I);
		}
	}
	
	public static class BirchMTE2 extends BirchMinionTileEntity {
		public BirchMTE2() {
			super(ModTileEntityTypes.birch_minion_2.get(), MinionTier.II);
		}
	}
	
	public static class BirchMTE3 extends BirchMinionTileEntity {
		public BirchMTE3() {
			super(ModTileEntityTypes.birch_minion_3.get(), MinionTier.III);
		}
	}
	
	public static class BirchMTE4 extends BirchMinionTileEntity {
		public BirchMTE4() {
			super(ModTileEntityTypes.birch_minion_4.get(), MinionTier.IV);
		}
	}
	
	public static class BirchMTE5 extends BirchMinionTileEntity {
		public BirchMTE5() {
			super(ModTileEntityTypes.birch_minion_5.get(), MinionTier.V);
		}
	}
	
	public static class BirchMTE6 extends BirchMinionTileEntity {
		public BirchMTE6() {
			super(ModTileEntityTypes.birch_minion_6.get(), MinionTier.VI);
		}
	}
	
	public static class BirchMTE7 extends BirchMinionTileEntity {
		public BirchMTE7() {
			super(ModTileEntityTypes.birch_minion_7.get(), MinionTier.VII);
		}
	}
	
	public static class BirchMTE8 extends BirchMinionTileEntity {
		public BirchMTE8() {
			super(ModTileEntityTypes.birch_minion_8.get(), MinionTier.VIII);
		}
	}
	
	public static class BirchMTE9 extends BirchMinionTileEntity {
		public BirchMTE9() {
			super(ModTileEntityTypes.birch_minion_9.get(), MinionTier.IX);
		}
	}
	
	public static class BirchMTEa extends BirchMinionTileEntity {
		public BirchMTEa() {
			super(ModTileEntityTypes.birch_minion_a.get(), MinionTier.X);
		}
	}
	
	public static class BirchMTEb extends BirchMinionTileEntity {
		public BirchMTEb() {
			super(ModTileEntityTypes.birch_minion_b.get(), MinionTier.XI);
		}
	}
}