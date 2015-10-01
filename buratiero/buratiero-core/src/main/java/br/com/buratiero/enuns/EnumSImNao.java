package br.com.buratiero.enuns;

public enum EnumSImNao
{

   SIM("S", "Sim"), NAO("N", "Não");

   private String codigo;

   private String descricao;

   private EnumSImNao(String codigo, String descricao)
   {
      this.codigo = codigo;
      this.descricao = descricao;
   }

   public EnumSImNao recuperaPorCodigo(String codigo)
   {
      for (EnumSImNao simNao : EnumSImNao.values())
      {
         if (codigo.equals(simNao.getCodigo()))
         {
            return simNao;
         }
      }
      return null;
   }

   public String getCodigo()
   {
      return codigo;
   }

   public void setCodigo(String codigo)
   {
      this.codigo = codigo;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

}
