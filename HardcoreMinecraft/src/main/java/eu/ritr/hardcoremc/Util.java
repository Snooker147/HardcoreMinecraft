package eu.ritr.hardcoremc;

import java.lang.reflect.Field;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public final class Util
{
    private Util() {}

    public static <T, E> void setPrivateValue(Class<T> cls, T instance, String fieldMapped, String fieldUnmapped, E value)
    {
    	Field field = null;
    	
    	try
    	{
    		field = ObfuscationReflectionHelper.findField(cls, fieldUnmapped);
    	}
    	catch(Exception e)
    	{
    		field = ObfuscationReflectionHelper.findField(cls, fieldMapped);
    	}
    	
    	field.setAccessible(true);
    	
    	try
    	{
    		ObfuscationReflectionHelper.setPrivateValue(cls, instance, value, fieldUnmapped);
    	}
    	catch(Exception e)
    	{
    		ObfuscationReflectionHelper.setPrivateValue(cls, instance, value, fieldMapped);
    	}
    }
    
    
    public static int clamp(int value, int min, int max)
    {
        if(value < min)
        {
            return min;
        }

        return value > max ? max : value;
    }


    public static float clamp(float value, float min, float max)
    {
        if(value < min)
        {
            return min;
        }

        return value > max ? max : value;
    }

}
