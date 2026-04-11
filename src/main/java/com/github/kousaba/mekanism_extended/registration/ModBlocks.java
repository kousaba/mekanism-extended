package com.github.kousaba.mekanism_extended.registration;

import com.github.kousaba.mekanism_extended.MekanismExtended;
import com.github.kousaba.mekanism_extended.multiblock.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TintedGlassBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MekanismExtended.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MekanismExtended.MODID);
    public static final DeferredBlock<TransmuterBlock<TileEntityTransmuterCasing>> TRANSMUTER_CASING = BLOCKS.register("transmutation_casing",
        () -> new TransmuterBlock<>(Block.Properties.of().strength(5.0F, 12.0F).requiresCorrectToolForDrops(),
            ModTileEntities.TRANSMUTER_CASING)); // ここで ModTileEntities を直接渡す
    public static final DeferredItem<BlockItem> TRANSMUTER_CASING_ITEM = ITEMS.register("transmutation_casing",
        () -> new BlockItem(TRANSMUTER_CASING.get(), new Item.Properties()));
    public static final DeferredBlock<TransmuterBlock<TileEntityTransmuterPort>> TRANSMUTER_PORT = BLOCKS.register("transmutation_port",
        () -> new TransmuterBlock<>(Block.Properties.of().strength(5.0F, 12.0F).requiresCorrectToolForDrops(),
            ModTileEntities.TRANSMUTER_PORT));
    public static final DeferredItem<BlockItem> TRANSMUTER_PORT_ITEM = ITEMS.register("transmutation_port",
        () -> new BlockItem(TRANSMUTER_PORT.get(), new Item.Properties()));
    public static final DeferredBlock<TransmuterGlassBlock<TileEntityTransmuterGlass>> TRANSMUTER_GLASS = BLOCKS.register("transmutation_glass",
        () -> new TransmuterGlassBlock<>(Block.Properties.of(),
            ModTileEntities.TRANSMUTER_GLASS));
        public static final DeferredItem<BlockItem> TRANSMUTER_GLASS_ITEM = ITEMS.register("transmutation_glass",
            () -> new BlockItem(TRANSMUTER_GLASS.get(), new Item.Properties()));
}
