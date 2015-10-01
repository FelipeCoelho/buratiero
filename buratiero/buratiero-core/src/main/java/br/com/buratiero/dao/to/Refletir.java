package br.com.buratiero.dao.to;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

public final class Refletir implements Serializable
{

   private static final long serialVersionUID = 1004369577726141023L;

   private Refletir()
   {
      //
   }

   public static Field getPropriedade(Class<?> c, String key)
   {
      Field propriedade = null;
      try
      {
         for (final String nome : key.split("[.]"))
         {
            if (propriedade != null)
            {
               c = propriedade.getType();
               propriedade = c.getDeclaredField(nome);
            }
            else
            {
               propriedade = c.getDeclaredField(nome);
            }
         }
      }
      catch (final SecurityException e)
      {
         e.printStackTrace();
      }
      catch (final NoSuchFieldException e)
      {
         e.printStackTrace();
      }
      return propriedade;
   }

   public static void setNovoValorPropriedade(Object obj, Field propiedade, Object valor)
   {
      propiedade.setAccessible(true);
      try
      {
         propiedade.set(obj, valor);
      }
      catch (final IllegalArgumentException e)
      {
         e.printStackTrace();
      }
      catch (final IllegalAccessException e)
      {
         e.printStackTrace();
      }
   }

   public static Object getValorPropriedade(Object obj, Field propiedade)
   {
      Object retorno = null;
      try
      {
         propiedade.setAccessible(true);
         retorno = propiedade.get(obj);
      }
      catch (final IllegalArgumentException e)
      {
         e.printStackTrace();
      }
      catch (final IllegalAccessException e)
      {
         e.printStackTrace();
      }
      return retorno;
   }

   public static Object recuperaValor(Class<?> c, String key, Object valor)
   {
      Object obj = null;
      final Field propiedade = Refletir.getPropriedade(c, key);
      if (propiedade.getType().equals(Integer.class))
      {
         obj = new Integer(valor.toString());
      }
      else if (propiedade.getType().equals(Long.class))
      {
         obj = new Long(valor.toString());
      }
      else
      {
         obj = valor.toString();
      }
      return obj;
   }

   @SuppressWarnings("unchecked")
   public static <T extends Serializable> List<T> cloneLista(List<T> src)
   {
      List<T> dest = null;
      try
      {
         dest = src.getClass().newInstance();
         final Object[] obj = src.toArray().clone();
         for (final Object item : obj)
         {
            dest.add((T) item);
         }
      }
      catch (final InstantiationException e)
      {
         e.printStackTrace();
      }
      catch (final IllegalAccessException e)
      {
         e.printStackTrace();
      }
      return dest;
   }

   public static Object getValorClassesInternas(Class<?> classe, String key, Object entidade)
   {
      final String[] atributos = key.split("[.]");
      Object obj = entidade;
      Class<?> interna = null;
      for (final String item : atributos)
      {
         try
         {
            if (obj == null)
            {
               obj = interna.newInstance();
            }
            final Field propriedade = Refletir.getPropriedade(obj.getClass(), item);
            interna = propriedade.getType();
            propriedade.setAccessible(true);
            obj = propriedade.get(obj);
         }
         catch (final IllegalArgumentException e)
         {
            e.printStackTrace();
         }
         catch (final IllegalAccessException e)
         {
            e.printStackTrace();
         }
         catch (final InstantiationException e)
         {
            e.printStackTrace();
         }
      }
      return obj;
   }

}
