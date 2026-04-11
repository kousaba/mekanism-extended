package com.github.kousaba.mekanism_extended.datagen;

import com.github.kousaba.mekanism_extended.registration.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper existingFileHelper){
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Casing: 全ての面(cube_all)に transmutation_casing.png を貼る
        simpleBlock(ModBlocks.TRANSMUTER_CASING.get());

        // Port: 全ての面に transmutation_port.png を貼る
        simpleBlock(ModBlocks.TRANSMUTER_PORT.get());

        // Glass: 透明ブロックの設定
        simpleBlock(ModBlocks.TRANSMUTER_GLASS.get(),
            models().cubeAll("transmutation_glass", modLoc("block/transmutation_glass")).renderType("cutout"));
    }
}
