package com.Invilis.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.Invilis.dimensions.Elven.ElvenDimension;
import com.Invilis.dimensions.Elven.ElvenTeleporter;
import com.google.common.cache.LoadingCache;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SChangeGameStatePacket;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.network.play.server.SPlaySoundEventPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.CachedBlockInfo;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class MithrilPortal extends NetherPortalBlock {
	private static final Block PORTAL_BLOCK = EpicBlocks.RubyBlock;
	
	public MithrilPortal() {
		super(Block.Properties.create(Material.PORTAL).doesNotBlockMovement().tickRandomly().hardnessAndResistance(-1.0F).sound(SoundType.GLASS).noDrops().lightValue(11));
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public void tick(BlockState state, World world, BlockPos pos, Random random) {
	}

	public void portalSpawn(World world, BlockPos pos) {
		MithrilPortal.Size portalsize = this.isValid(world, pos);
		if (portalsize != null)
			portalsize.placePortalBlocks();
	}

	@Nullable
	public MithrilPortal.Size isValid(IWorld p_201816_1_, BlockPos p_201816_2_) {
		MithrilPortal.Size netherportalblock$size = new MithrilPortal.Size(p_201816_1_, p_201816_2_, Direction.Axis.X);
		if (netherportalblock$size.isValid() && netherportalblock$size.portalBlockCount == 0) {
			return netherportalblock$size;
		} else {
			MithrilPortal.Size netherportalblock$size1 = new MithrilPortal.Size(p_201816_1_, p_201816_2_, Direction.Axis.Z);
			return netherportalblock$size1.isValid() && netherportalblock$size1.portalBlockCount == 0 ? netherportalblock$size1 : null;
		}
	}

	@Override
	public BlockPattern.PatternHelper createPatternHelper(IWorld worldIn, BlockPos p_181089_2_) {
		Direction.Axis direction$axis = Direction.Axis.Z;
		MithrilPortal.Size netherportalblock$size = new MithrilPortal.Size(worldIn, p_181089_2_, Direction.Axis.X);
		LoadingCache<BlockPos, CachedBlockInfo> loadingcache = BlockPattern.createLoadingCache(worldIn, true);
		if (!netherportalblock$size.isValid()) {
			direction$axis = Direction.Axis.X;
			netherportalblock$size = new MithrilPortal.Size(worldIn, p_181089_2_, Direction.Axis.Z);
		}
		if (!netherportalblock$size.isValid()) {
			return new BlockPattern.PatternHelper(p_181089_2_, Direction.NORTH, Direction.UP, loadingcache, 1, 1, 1);
		} else {
			int[] aint = new int[Direction.AxisDirection.values().length];
			Direction direction = netherportalblock$size.rightDir.rotateYCCW();
			BlockPos blockpos = netherportalblock$size.bottomLeft.up(netherportalblock$size.getHeight() - 1);
			for (Direction.AxisDirection direction$axisdirection : Direction.AxisDirection.values()) {
				BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(
						direction.getAxisDirection() == direction$axisdirection ? blockpos : blockpos.offset(netherportalblock$size.rightDir,
								netherportalblock$size.getWidth() - 1), Direction.getFacingFromAxis(direction$axisdirection, direction$axis),
						Direction.UP, loadingcache, netherportalblock$size.getWidth(), netherportalblock$size.getHeight(), 1);
				for (int i = 0; i < netherportalblock$size.getWidth(); ++i) {
					for (int j = 0; j < netherportalblock$size.getHeight(); ++j) {
						CachedBlockInfo cachedblockinfo = blockpattern$patternhelper.translateOffset(i, j, 1);
						//if (!(cachedblockinfo.getBlockState() == Blocks.AIR.getDefaultState())) {
						if (!cachedblockinfo.getBlockState().isAir()) {
							++aint[direction$axisdirection.ordinal()];
						}
					}
				}
			}
			Direction.AxisDirection direction$axisdirection1 = Direction.AxisDirection.POSITIVE;
			for (Direction.AxisDirection direction$axisdirection2 : Direction.AxisDirection.values()) {
				if (aint[direction$axisdirection2.ordinal()] < aint[direction$axisdirection1.ordinal()]) {
					direction$axisdirection1 = direction$axisdirection2;
				}
			}
			return new BlockPattern.PatternHelper(direction.getAxisDirection() == direction$axisdirection1 ? blockpos : blockpos.offset(
					netherportalblock$size.rightDir, netherportalblock$size.getWidth() - 1), Direction.getFacingFromAxis(
					direction$axisdirection1, direction$axis), Direction.UP, loadingcache, netherportalblock$size.getWidth(),
					netherportalblock$size.getHeight(), 1);
		}
	}

	@Override
	/** 
	 * Update the provided state given the provided neighbor facing and neighbor state, returning a new state. For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately returns its solidified counterpart. Note that this method should ideally consider only the specific face passed in.
	 */
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos,
			BlockPos facingPos) {
		Direction.Axis direction$axis = facing.getAxis();
		Direction.Axis direction$axis1 = stateIn.get(AXIS);
		boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
		return !flag && facingState.getBlock() != this && !(new MithrilPortal.Size(worldIn, currentPos, direction$axis1)).func_208508_f()
				? Blocks.AIR.getDefaultState()
				: super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
		for (int i = 0; i < 3; i++) {
			double px = pos.getX() + random.nextFloat();
			double py = pos.getY() + random.nextFloat();
			double pz = pos.getZ() + random.nextFloat();
			double vx = (random.nextFloat() - 0.5) / 2f;
			double vy = (random.nextFloat() - 0.5) / 2f;
			double vz = (random.nextFloat() - 0.5) / 2f;
			int j = random.nextInt(4) - 1;
			if (world.getBlockState(pos.west()).getBlock() != this && world.getBlockState(pos.east()).getBlock() != this) {
				px = pos.getX() + 0.5 + 0.25 * j;
				vx = random.nextFloat() * 2 * j;
			} else {
				pz = pos.getZ() + 0.5 + 0.25 * j;
				vz = random.nextFloat() * 2 * j;
			}
			world.addParticle(ParticleTypes.CLOUD, px, py, pz, vx, vy, vz);
		}
		if (random.nextInt(110) == 0)
			world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(("block.conduit.ambient"))),
					SoundCategory.BLOCKS, 0.5f, random.nextFloat() * 0.4F + 0.8F, false);
	}

	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!world.isRemote && !entity.isPassenger() && !entity.isBeingRidden() && entity instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) entity;
			if (player.timeUntilPortal > 0) {
				player.timeUntilPortal = 10;
			} else if (player.dimension != ElvenDimension.type) {
				player.timeUntilPortal = 10;
				teleportToDimension(player, ElvenDimension.type);
			} else {
				player.timeUntilPortal = 10;
				teleportToDimension(player, DimensionType.OVERWORLD);
			}
		}
	}

	private void teleportToDimension(ServerPlayerEntity player, DimensionType destinationType) {
		ObfuscationReflectionHelper.setPrivateValue(ServerPlayerEntity.class, player, true, "field_184851_cj");
		ServerWorld nextWorld = player.getServer().getWorld(destinationType);
		ElvenTeleporter teleporter = getTeleporterForDimension(player, player.getPosition(), nextWorld);
		player.connection.sendPacket(new SChangeGameStatePacket(4, 0));
		if (!teleporter.func_222268_a(player, player.rotationYaw)) {
			teleporter.makePortal(player);
			teleporter.func_222268_a(player, player.rotationYaw);
		}
		player.teleport(nextWorld, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ(), player.rotationYaw,
				player.rotationPitch);
		player.connection.sendPacket(new SPlayerAbilitiesPacket(player.abilities));
		for (EffectInstance effectinstance : player.getActivePotionEffects()) {
			player.connection.sendPacket(new SPlayEntityEffectPacket(player.getEntityId(), effectinstance));
		}
		player.connection.sendPacket(new SPlaySoundEventPacket(1032, BlockPos.ZERO, 0, false));
	}

	private ElvenTeleporter getTeleporterForDimension(Entity entity, BlockPos pos, ServerWorld nextWorld) {
		BlockPattern.PatternHelper bph = EpicBlocks.mithrilPortal.createPatternHelper(entity.world, new BlockPos(pos));
		double d0 = bph.getForwards().getAxis() == Direction.Axis.X ? (double) bph.getFrontTopLeft().getZ() : (double) bph.getFrontTopLeft()
				.getX();
		double d1 = bph.getForwards().getAxis() == Direction.Axis.X ? entity.posZ : entity.posX;
		d1 = Math.abs(MathHelper.pct(d1 - (double) (bph.getForwards().rotateY().getAxisDirection() == Direction.AxisDirection.NEGATIVE ? 1 : 0),
				d0, d0 - (double) bph.getWidth()));
		double d2 = MathHelper.pct(entity.posY - 1, (double) bph.getFrontTopLeft().getY(),
				(double) (bph.getFrontTopLeft().getY() - bph.getHeight()));
		return new ElvenTeleporter(nextWorld, new Vec3d(d1, d2, 0), bph.getForwards());
	}

	public static class Size {
		private final IWorld world;
		private final Direction.Axis axis;
		private final Direction rightDir;
		private final Direction leftDir;
		private int portalBlockCount;
		@Nullable
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(IWorld p_i48740_1_, BlockPos p_i48740_2_, Direction.Axis p_i48740_3_) {
			this.world = p_i48740_1_;
			this.axis = p_i48740_3_;
			if (p_i48740_3_ == Direction.Axis.X) {
				this.leftDir = Direction.EAST;
				this.rightDir = Direction.WEST;
			} else {
				this.leftDir = Direction.NORTH;
				this.rightDir = Direction.SOUTH;
			}
			for (BlockPos blockpos = p_i48740_2_; p_i48740_2_.getY() > blockpos.getY() - 21 && p_i48740_2_.getY() > 0
					&& this.func_196900_a(p_i48740_1_.getBlockState(p_i48740_2_.down())); p_i48740_2_ = p_i48740_2_.down()) {
				;
			}
			int i = this.getDistanceUntilEdge(p_i48740_2_, this.leftDir) - 1;
			if (i >= 0) {
				this.bottomLeft = p_i48740_2_.offset(this.leftDir, i);
				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
				if (this.width < 2 || this.width > 21) {
					this.bottomLeft = null;
					this.width = 0;
				}
			}
			if (this.bottomLeft != null) {
				this.height = this.calculatePortalHeight();
			}
		}

		protected int getDistanceUntilEdge(BlockPos p_180120_1_, Direction p_180120_2_) {
			int i;
			for (i = 0; i < 22; ++i) {
				BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);
				if (!this.func_196900_a(this.world.getBlockState(blockpos))
						|| !(this.world.getBlockState(blockpos.down()).getBlock() == PORTAL_BLOCK.getDefaultState().getBlock())) {
					break;
				}
			}
			BlockPos framePos = p_180120_1_.offset(p_180120_2_, i);
			return (this.world.getBlockState(framePos).getBlock() == PORTAL_BLOCK.getDefaultState().getBlock()) ? i : 0;
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}

		protected int calculatePortalHeight() {
			label56 : for (this.height = 0; this.height < 21; ++this.height) {
				for (int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
					BlockState blockstate = this.world.getBlockState(blockpos);
					if (!this.func_196900_a(blockstate)) {
						break label56;
					}
					Block block = blockstate.getBlock();
					if (block == EpicBlocks.mithrilPortal) {
						++this.portalBlockCount;
					}
					if (i == 0) {
						BlockPos framePos = blockpos.offset(this.leftDir);
						if (!(this.world.getBlockState(framePos).getBlock() == PORTAL_BLOCK.getDefaultState().getBlock())) {
							break label56;
						}
					} else if (i == this.width - 1) {
						BlockPos framePos = blockpos.offset(this.rightDir);
						if (!(this.world.getBlockState(framePos).getBlock() == PORTAL_BLOCK.getDefaultState().getBlock())) {
							break label56;
						}
					}
				}
			}
			for (int j = 0; j < this.width; ++j) {
				BlockPos framePos = this.bottomLeft.offset(this.rightDir, j).up(this.height);
				if (!(this.world.getBlockState(framePos).getBlock() == PORTAL_BLOCK.getDefaultState().getBlock())) {
					this.height = 0;
					break;
				}
			}
			if (this.height <= 21 && this.height >= 3) {
				return this.height;
			} else {
				this.bottomLeft = null;
				this.width = 0;
				this.height = 0;
				return 0;
			}
		}

		protected boolean func_196900_a(BlockState p_196900_1_) {
			Block block = p_196900_1_.getBlock();
			return block == Blocks.AIR || block == Blocks.FIRE || block == EpicBlocks.mithrilPortal;
		}

		public boolean isValid() {
			return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
		}

		public void placePortalBlocks() {
			for (int i = 0; i < this.width; ++i) {
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);
				for (int j = 0; j < this.height; ++j) {
					this.world.setBlockState(blockpos.up(j), EpicBlocks.mithrilPortal.getDefaultState().with(NetherPortalBlock.AXIS, this.axis), 18);
				}
			}
		}

		private boolean func_196899_f() {
			return this.portalBlockCount >= this.width * this.height;
		}

		public boolean func_208508_f() {
			return this.isValid() && this.func_196899_f();
		}
	}
}
