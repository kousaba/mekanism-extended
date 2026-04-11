package com.github.kousaba.mekanism_extended.multiblock;

import com.github.kousaba.mekanism_extended.registration.ModBlocks;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import mekanism.common.lib.multiblock.CuboidStructureValidator;
import mekanism.common.lib.multiblock.FormationProtocol;
import mekanism.common.registries.MekanismBlocks;
import mekanism.generators.common.registries.GeneratorsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;

public class TransmuterValidator extends CuboidStructureValidator<TransmuterMultiblockData> {
    private int foundCoils = 0;
    private boolean hasCentralSuperchargedCoil = false;

    @Override
    protected FormationProtocol.CasingType getCasingType(BlockState state) {
        Block block = state.getBlock();
        if (block == ModBlocks.TRANSMUTER_CASING.get()) return FormationProtocol.CasingType.FRAME;
        if (block == ModBlocks.TRANSMUTER_PORT.get()) return FormationProtocol.CasingType.VALVE;
        if (block == ModBlocks.TRANSMUTER_GLASS.get()) return FormationProtocol.CasingType.OTHER;
        return FormationProtocol.CasingType.INVALID;
    }

@Override
public boolean validateInner(BlockState state, Long2ObjectMap<ChunkAccess> chunkMap, BlockPos pos) {
    if (super.validateInner(state, chunkMap, pos)) return true; // 空気ならOK

    BlockPos minPos = cuboid.getMinPos();
    BlockPos maxPos = cuboid.getMaxPos();

    // 相対座標（0,0,0 からの距離）を計算
    int relX = pos.getX() - minPos.getX();
    int relY = pos.getY() - minPos.getY();
    int relZ = pos.getZ() - minPos.getZ();

    // 5x5なら centerX=2, centerZ=2 になるはず
    int centerX = (maxPos.getX() - minPos.getX()) / 2;
    int centerZ = (maxPos.getZ() - minPos.getZ()) / 2;

    // --- ここから詳細ログ ---
    System.out.println(String.format("Checking Inner at: [%d, %d, %d] (Rel: %d, %d, %d) - Block: %s",
        pos.getX(), pos.getY(), pos.getZ(), relX, relY, relZ, state.getBlock().toString()));

    // 2段目（エンジンレイヤー）の判定
    if (relY == 1) {
        if (relX == centerX && relZ == centerZ) {
            // 真ん中が Supercharged Coil か？
            if (state.is(MekanismBlocks.SUPERCHARGED_COIL.get())) {
                System.out.println("  -> SUCCESS: Found Supercharged Coil at center axis!");
                hasCentralSuperchargedCoil = true;
                return true;
            } else {
                System.out.println("  -> FAIL: Expected Supercharged Coil, but found " + state.getBlock());
            }
        } else if (state.is(GeneratorsBlocks.ELECTROMAGNETIC_COIL.get())) {
            // 周囲が Electromagnetic Coil か？
            System.out.println("  -> SUCCESS: Found Electromagnetic Coil!");
            foundCoils++;
            return true;
        } else {
            System.out.println("  -> FAIL: Block at y=1 must be a Coil, but found " + state.getBlock());
        }
    } else {
        // 3段目以上は「空気」である必要があります（super.validateInnerで判定済みのはず）
        System.out.println("  -> FAIL: Block must be Air at this height!");
    }

    return false;
}

    @Override
    public FormationProtocol.FormationResult postcheck(TransmuterMultiblockData structure, Long2ObjectMap<ChunkAccess> chunkMap) {
        BlockPos minPos = cuboid.getMinPos();
        BlockPos maxPos = cuboid.getMaxPos();

        if (((maxPos.getX() - minPos.getX()) + 1) % 2 == 0) return FormationProtocol.FormationResult.fail(Component.literal("Width must be odd"));
        if (!hasCentralSuperchargedCoil) return FormationProtocol.FormationResult.fail(Component.literal("Missing Central Supercharged Coil"));

        int centerX = (maxPos.getX() - minPos.getX()) / 2;
        int centerZ = (maxPos.getZ() - minPos.getZ()) / 2;
        BlockPos bottomCenter = minPos.offset(centerX, 0, centerZ);

        if (!world.getBlockState(bottomCenter).is(ModBlocks.TRANSMUTER_PORT.get())) {
            return FormationProtocol.FormationResult.fail(Component.literal("Bottom center must be a Port"));
        }

        structure.coilCount = foundCoils;
        structure.successRate = Math.min(1.0, (double) (maxPos.getY() - minPos.getY()) / 17.0);
        return FormationProtocol.FormationResult.SUCCESS;
    }
}
