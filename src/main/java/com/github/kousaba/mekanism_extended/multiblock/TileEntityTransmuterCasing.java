package com.github.kousaba.mekanism_extended.multiblock;

import com.github.kousaba.mekanism_extended.MekanismExtended;
import com.github.kousaba.mekanism_extended.registration.ModBlocks;
import mekanism.common.lib.multiblock.MultiblockManager;
import mekanism.common.tile.prefab.TileEntityMultiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;


public class TileEntityTransmuterCasing extends TileEntityMultiblock<TransmuterMultiblockData> {
    public TileEntityTransmuterCasing(BlockPos pos, BlockState state) {
        super(ModBlocks.TRANSMUTER_CASING, pos, state);
        System.out.println("DEBUG: Transmuter Casing TileEntity created at " + pos);
    }

    // 引数なしでBlockHolderから取得するように修正されたコンストラクタ用
    public TileEntityTransmuterCasing(Holder<Block> blockProvider, BlockPos pos, BlockState state) {
        super(blockProvider, pos, state);
    }

    @Override
    public TransmuterMultiblockData createMultiblock() {
        return new TransmuterMultiblockData(this);
    }

    @Override
    public MultiblockManager<TransmuterMultiblockData> getManager() {
        return MekanismExtended.TRANSMUTER_MANAGER;
    }

    // 1.21.1では TileEntity 内での getValidator() のオーバーライドは不要（Manager側が管理）
    // もし必要な場合は StructureValidator を返す必要があります。
}
