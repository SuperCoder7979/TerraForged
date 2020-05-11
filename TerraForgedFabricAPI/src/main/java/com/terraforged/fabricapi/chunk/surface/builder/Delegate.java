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

package com.terraforged.fabricapi.chunk.surface.builder;

import com.terraforged.fabricapi.chunk.surface.Surface;
import com.terraforged.fabricapi.chunk.surface.SurfaceContext;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;

import java.util.function.Function;

public class Delegate implements Surface {

    public static final Function<Biome, Surface> FUNC = Delegate::new;

    private final ConfiguredSurfaceBuilder<?> surfaceBuilder;

    private Delegate(Biome biome) {
        this(biome.getSurfaceBuilder());
    }

    private Delegate(ConfiguredSurfaceBuilder<?> surfaceBuilder) {
        this.surfaceBuilder = surfaceBuilder;
    }

    @Override
    public void buildSurface(int x, int z, int height, SurfaceContext context) {
        surfaceBuilder.initSeed(context.seed);

        surfaceBuilder.generate(
                context.random,
                context.chunk,
                context.biome,
                x,
                z,
                height,
                context.noise,
                context.solid,
                context.fluid,
                context.seaLevel,
                context.seed
        );
    }
}
