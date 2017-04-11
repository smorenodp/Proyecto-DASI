package net.minecraft.client.util;

import java.nio.FloatBuffer;
import java.util.Comparator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class QuadComparator implements Comparator
{
    private float field_147630_a;
    private float field_147628_b;
    private float field_147629_c;
    private FloatBuffer field_147627_d;
    private int field_178079_e;
    private static final String __OBFID = "CL_00000958";

    public QuadComparator(FloatBuffer p_i46247_1_, float p_i46247_2_, float p_i46247_3_, float p_i46247_4_, int p_i46247_5_)
    {
        this.field_147627_d = p_i46247_1_;
        this.field_147630_a = p_i46247_2_;
        this.field_147628_b = p_i46247_3_;
        this.field_147629_c = p_i46247_4_;
        this.field_178079_e = p_i46247_5_;
    }

    public int compare(Integer p_compare_1_, Integer p_compare_2_)
    {
        float f = this.field_147627_d.get(p_compare_1_.intValue()) - this.field_147630_a;
        float f1 = this.field_147627_d.get(p_compare_1_.intValue() + 1) - this.field_147628_b;
        float f2 = this.field_147627_d.get(p_compare_1_.intValue() + 2) - this.field_147629_c;
        float f3 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e + 0) - this.field_147630_a;
        float f4 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e + 1) - this.field_147628_b;
        float f5 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e + 2) - this.field_147629_c;
        float f6 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 2 + 0) - this.field_147630_a;
        float f7 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 2 + 1) - this.field_147628_b;
        float f8 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 2 + 2) - this.field_147629_c;
        float f9 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 3 + 0) - this.field_147630_a;
        float f10 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 3 + 1) - this.field_147628_b;
        float f11 = this.field_147627_d.get(p_compare_1_.intValue() + this.field_178079_e * 3 + 2) - this.field_147629_c;
        float f12 = this.field_147627_d.get(p_compare_2_.intValue()) - this.field_147630_a;
        float f13 = this.field_147627_d.get(p_compare_2_.intValue() + 1) - this.field_147628_b;
        float f14 = this.field_147627_d.get(p_compare_2_.intValue() + 2) - this.field_147629_c;
        float f15 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e + 0) - this.field_147630_a;
        float f16 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e + 1) - this.field_147628_b;
        float f17 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e + 2) - this.field_147629_c;
        float f18 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 2 + 0) - this.field_147630_a;
        float f19 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 2 + 1) - this.field_147628_b;
        float f20 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 2 + 2) - this.field_147629_c;
        float f21 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 3 + 0) - this.field_147630_a;
        float f22 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 3 + 1) - this.field_147628_b;
        float f23 = this.field_147627_d.get(p_compare_2_.intValue() + this.field_178079_e * 3 + 2) - this.field_147629_c;
        float f24 = (f + f3 + f6 + f9) * 0.25F;
        float f25 = (f1 + f4 + f7 + f10) * 0.25F;
        float f26 = (f2 + f5 + f8 + f11) * 0.25F;
        float f27 = (f12 + f15 + f18 + f21) * 0.25F;
        float f28 = (f13 + f16 + f19 + f22) * 0.25F;
        float f29 = (f14 + f17 + f20 + f23) * 0.25F;
        float f30 = f24 * f24 + f25 * f25 + f26 * f26;
        float f31 = f27 * f27 + f28 * f28 + f29 * f29;
        return Float.compare(f31, f30);
    }

    public int compare(Object p_compare_1_, Object p_compare_2_)
    {
        return this.compare((Integer)p_compare_1_, (Integer)p_compare_2_);
    }
}