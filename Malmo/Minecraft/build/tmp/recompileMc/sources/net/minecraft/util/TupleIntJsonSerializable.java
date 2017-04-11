package net.minecraft.util;

public class TupleIntJsonSerializable
{
    private int integerValue;
    private IJsonSerializable jsonSerializableValue;
    private static final String __OBFID = "CL_00001478";

    /**
     * Gets the integer value stored in this tuple.
     */
    public int getIntegerValue()
    {
        return this.integerValue;
    }

    /**
     * Sets this tuple's integer value to the given value.
     */
    public void setIntegerValue(int integerValueIn)
    {
        this.integerValue = integerValueIn;
    }

    /**
     * Gets the JsonSerializable value stored in this tuple.
     */
    public IJsonSerializable getJsonSerializableValue()
    {
        return this.jsonSerializableValue;
    }

    /**
     * Sets this tuple's JsonSerializable value to the given value.
     */
    public void setJsonSerializableValue(IJsonSerializable jsonSerializableValueIn)
    {
        this.jsonSerializableValue = jsonSerializableValueIn;
    }
}