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

package com.terraforged.fabricapi.material.layer;

import me.dags.noise.util.NoiseUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;

public class LayerMaterial {

    private static final BlockState AIR = Blocks.AIR.getDefaultState();

    private final int min;
    private final int max;
    private final BlockState fullState;
    private final BlockState layerState;
    private final Property<Integer> layerProperty;

    private LayerMaterial(BlockState fullState, BlockState layerState, Property<Integer> layerProperty) {
        this.layerProperty = layerProperty;
        this.min = min(layerProperty);
        this.max = max(layerProperty);
        this.layerState = layerState;
        this.fullState = fullState;
    }

    public Block getLayerType() {
        return layerState.getBlock();
    }

    public BlockState getFull() {
        return fullState;
    }

    public BlockState getState(float value) {
        return getState(getLevel(value));
    }

    public BlockState getState(int level) {
        if (level < min) {
            return LayerMaterial.AIR;
        }
        if (level >= max) {
            return fullState;
        }
        return layerState.with(layerProperty, level);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getLevel(float depth) {
        if (depth > 1) {
            depth = getDepth(depth);
        } else if (depth < 0) {
            depth = 0;
        }
        return NoiseUtil.round(depth * max);
    }

    public float getDepth(float height) {
        return height - (int) height;
    }

    private static int min(Property<Integer> property) {
        return property.getValues().stream().min(Integer::compareTo).orElse(0);
    }

    private static int max(Property<Integer> property) {
        return property.getValues().stream().max(Integer::compareTo).orElse(0);
    }

    public static LayerMaterial of(Block block) {
        return of(block, Properties.LAYERS);
    }

    public static LayerMaterial of(Block block, Property<Integer> property) {
        return of(block.getDefaultState(), block.getDefaultState(), property);
    }

    public static LayerMaterial of(Block full, Block layer) {
        return of(full.getDefaultState(), layer.getDefaultState());
    }

    public static LayerMaterial of(Block full, Block layer, Property<Integer> property) {
        return of(full.getDefaultState(), layer.getDefaultState(), property);
    }

    public static LayerMaterial of(BlockState layer) {
        return of(layer, Properties.LAYERS);
    }

    public static LayerMaterial of(BlockState layer, Property<Integer> property) {
        return of(layer.with(property, max(property)), layer);
    }

    public static LayerMaterial of(BlockState full, BlockState layer) {
        return of(full, layer, Properties.LAYERS);
    }

    public static LayerMaterial of(BlockState full, BlockState layer, Property<Integer> property) {
        return new LayerMaterial(full, layer, property);
    }
}
