package net.hypixel.skyblock.client.render.tileentity;

import java.util.Calendar;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.hypixel.skyblock.blocks.minion.MinionChestBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.model.Material;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityMerger.ICallbackWrapper;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MinionChestTileEntityRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<T> {
	private final ModelRenderer singleLid;
	private final ModelRenderer singleBottom;
	private final ModelRenderer singleLatch;
	private final ModelRenderer rightLid;
	private final ModelRenderer rightBottom;
	private final ModelRenderer rightLatch;
	private final ModelRenderer leftLid;
	private final ModelRenderer leftBottom;
	private final ModelRenderer leftLatch;
	private boolean isChristmas;

	public MinionChestTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
		Calendar calendar = Calendar.getInstance();
		this.isChristmas = calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26;

		this.singleBottom = new ModelRenderer(64, 64, 0, 19);
		this.singleBottom.addBox(1f, 0f, 1f, 14f, 10f, 14f, 0f);
		this.singleLid = new ModelRenderer(64, 64, 0, 0);
		this.singleLid.addBox(1f, 0f, 0f, 14f, 5f, 14f, 0f);
		this.singleLid.rotationPointY = 9f;
		this.singleLid.rotationPointZ = 1f;
		this.singleLatch = new ModelRenderer(64, 64, 0, 0);
		this.singleLatch.addBox(7f, -1f, 15f, 2f, 4f, 1f, 0f);
		this.singleLatch.rotationPointY = 8f;
		this.rightBottom = new ModelRenderer(64, 64, 0, 19);
		this.rightBottom.addBox(1f, 0f, 1f, 15f, 10f, 14f, 0f);
		this.rightLid = new ModelRenderer(64, 64, 0, 0);
		this.rightLid.addBox(1f, 0f, 0f, 15f, 5f, 14f, 0f);
		this.rightLid.rotationPointY = 9f;
		this.rightLid.rotationPointZ = 1f;
		this.rightLatch = new ModelRenderer(64, 64, 0, 0);
		this.rightLatch.addBox(15f, -1f, 15f, 1f, 4f, 1f, 0f);
		this.rightLatch.rotationPointY = 8f;
		this.leftBottom = new ModelRenderer(64, 64, 0, 19);
		this.leftBottom.addBox(0f, 0f, 1f, 15f, 10f, 14f, 0f);
		this.leftLid = new ModelRenderer(64, 64, 0, 0);
		this.leftLid.addBox(0f, 0f, 0f, 15f, 5f, 14f, 0f);
		this.leftLid.rotationPointY = 9f;
		this.leftLid.rotationPointZ = 1f;
		this.leftLatch = new ModelRenderer(64, 64, 0, 0);
		this.leftLatch.addBox(0f, -1f, 15f, 1f, 4f, 1f, 0f);
		this.leftLatch.rotationPointY = 8f;
	}

	public void render(T tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		World world = tileEntityIn.getWorld();
		boolean world_null = world != null;
		BlockState block_state = world_null ? tileEntityIn.getBlockState() : Blocks.CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
		ChestType type = block_state.has(ChestBlock.TYPE) ? block_state.get(ChestBlock.TYPE) : ChestType.SINGLE;
		Block block = block_state.getBlock();
		if (block instanceof MinionChestBlock) {
			MinionChestBlock mcb = (MinionChestBlock)block;
			boolean is_single = type != ChestType.SINGLE;
			matrixStackIn.push();
			float angle = block_state.get(ChestBlock.FACING).getHorizontalAngle();
			matrixStackIn.translate(0.5d, 0.5d, 0.5d);
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-angle));
			matrixStackIn.translate(-0.5d, -0.5d, -0.5d);
			ICallbackWrapper<? extends ChestTileEntity> wrapper;
			if (world_null)
				wrapper = mcb.func_225536_a_(block_state, world, tileEntityIn.getPos(), true);
			else
				wrapper = TileEntityMerger.ICallback::func_225537_b_;
			
			float f1 = wrapper.apply(ChestBlock.func_226917_a_((IChestLid)tileEntityIn)).get(partialTicks);
			f1 = 1f - f1;
			f1 = 1f - f1 * f1 * f1;
			int i = wrapper.apply(new DualBrightnessCallback<>()).applyAsInt(combinedLightIn);
			Material material = this.getMaterial(tileEntityIn, type);
			IVertexBuilder vertex_builder = material.getBuffer(bufferIn, RenderType::getEntityCutout);
			if (is_single)
				if (type == ChestType.LEFT)
					this.renderModels(matrixStackIn, vertex_builder, this.leftLid, this.leftLatch, this.leftBottom, f1, i, combinedOverlayIn);
				else
					this.renderModels(matrixStackIn, vertex_builder, this.rightLid, this.rightLatch, this.rightBottom, f1, i, combinedOverlayIn);
			else
				this.renderModels(matrixStackIn, vertex_builder, this.singleLid, this.singleLatch, this.singleBottom, f1, i, combinedOverlayIn);

			matrixStackIn.pop();
		}
	}

	private void renderModels(MatrixStack matrixStackIn, IVertexBuilder bufferIn, ModelRenderer chestLid, ModelRenderer chestLatch, ModelRenderer chestBottom, float lidAngle, int combinedLightIn, int combinedOverlayIn) {
		chestLid.rotateAngleX = -(lidAngle * ((float)Math.PI / 2f));
		chestLatch.rotateAngleX = chestLid.rotateAngleX;
		chestLid.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
		chestLatch.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
		chestBottom.render(matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
	}

	protected Material getMaterial(T tileEntity, ChestType chestType) {
		return Atlases.getChestMaterial(tileEntity, chestType, this.isChristmas);
	}
}
