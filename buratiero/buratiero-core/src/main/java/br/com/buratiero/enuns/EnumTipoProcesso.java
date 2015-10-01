package br.com.buratiero.enuns;

public enum EnumTipoProcesso
{
   FISICO("F","Fisico"),
   CEL("C","CEL"),
   PJE("P","PJE");
   
   private String codigo;
   
   private String descricao;
   
   private EnumTipoProcesso(String codigo,String descricao)
   {
      this.codigo = codigo;
      this.descricao = descricao;
   }
   
   public EnumTipoProcesso recuperaPorCodigo(String codigo){
      for (EnumTipoProcesso enumTipoprocesso : EnumTipoProcesso.values())
      {
         if(enumTipoprocesso.equals(enumTipoprocesso.getCodigo())){
            return enumTipoprocesso;
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
