/*
 *
 * MIT License
 *
 * Copyright (c) 2020 TerraForged
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.terraforged.fabricapi.chunk.column;

import com.terraforged.fabricapi.chunk.ChunkContext;
import com.terraforged.core.cell.Cell;
import com.terraforged.core.world.climate.Climate;
import com.terraforged.core.world.geology.DepthBuffer;
import com.terraforged.core.world.heightmap.Levels;
import com.terraforged.core.world.terrain.Terrain;
import com.terraforged.core.world.terrain.Terrains;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

public class DecoratorContext extends ChunkContext {

    public final Levels levels;
    public final Climate climate;
    public final Terrains terrains;
    public final DepthBuffer depthBuffer = new DepthBuffer();
    public final BlockPos.Mutable pos = new BlockPos.Mutable();

    public Biome biome;
    public Cell<Terrain> cell;

    public DecoratorContext(Chunk chunk, Levels levels, Terrains terrain, Climate climate) {
        super(chunk);
        this.levels = levels;
        this.climate = climate;
        this.terrains = terrain;
    }
}
