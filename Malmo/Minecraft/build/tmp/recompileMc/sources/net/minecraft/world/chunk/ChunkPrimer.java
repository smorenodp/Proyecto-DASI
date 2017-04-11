package net.minecraft.world.chunk;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class ChunkPrimer
{
    private final short[] data = new short[65536];
    private final IBlockState defaultState;
    private static final String __OBFID = "CL_00002007";

    public ChunkPrimer()
    {
        this.defaultState = Blocks.air.getDefaultState();
    }

    public IBlockState getBlockState(int x, int y, int z)
    {
        int l = x << 12 | z << 8 | y;
        return this.getBlockState(l);
    }

    public IBlockState getBlockState(int index)
    {
        if (index >= 0 && index < this.data.length)
        {
            IBlockState iblockstate = (IBlockState)Block.BLOCK_STATE_IDS.getByValue(this.data[index]);
            return iblockstate != null ? iblockstate : this.defaultState;
        }
        else
        {
            throw new IndexOutOfBoundsException("The coordinate is out of range");
        }
    }

    public void setBlockState(int x, int y, int z, IBlockState state)
    {
        int l = x << 12 | z << 8 | y;
        this.setBlockState(l, state);
    }

    public void setBlockState(int index, IBlockState state)
    {
        if (index >= 0 && index < this.data.length)
        {
            this.data[index] = (short)Block.BLOCK_STATE_IDS.get(state);
        }
        else
        {
            throw new IndexOutOfBoundsException("The coordinate is out of range");
        }
    }
}