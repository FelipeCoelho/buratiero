package br.com.buratiero.enuns;

public enum EnumProcessoNovoImpugnacao
{
   NOVO("N", "Novo", true), IMPUGNACAO("I", "Impugnação", false);

   private String codigo;
   private String descricao;
   private boolean status;

   private EnumProcessoNovoImpugnacao(String codigo, String descricao, boolean status)
   {
      this.codigo = codigo;
      this.descricao = descricao;
      this.status = status;
   }

   public EnumProcessoNovoImpugnacao recuperaPorCodigo(String codigo)
   {
      for (EnumProcessoNovoImpugnacao enumProcesso : EnumProcessoNovoImpugnacao.values())
      {
         if (codigo.equals(enumProcesso.getCodigo()))
         {
            return enumProcesso;
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

   public boolean isStatus()
   {
      return status;
   }

   public void setStatus(boolean status)
   {
      this.status = status;
   }

}
