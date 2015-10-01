package br.com.buratiero.enuns;

/**
 * Status dos Registros das Entidades.
 * @author felipe.coelho
 */
public enum StatusAtivoInativo
{

   /** ativo. */
   ATIVO("S", "Ativo", true),
   /** inativo. */
   INATIVO("N", "Inativo", false);

   /** codigo. */
   private String codigo;

   private String descricao;

   /** status. */
   private boolean status;

   /**
    * Instancia um novo status ativo inativo.
    * @param codigo codigo
    * @param status status
    */
   private StatusAtivoInativo(String codigo, String descricao, boolean status)
   {
      this.setCodigo(codigo);
      setDescricao(descricao);
      this.setStatus(status);
   }

   /**
    * Recupera status por codigo.
    * @param codigo codigo
    * @return status ativo inativo
    */
   public StatusAtivoInativo recuperaStatusPorCodigo(String codigo)
   {

      for (StatusAtivoInativo status : StatusAtivoInativo.values())
      {
         if (status.getCodigo().equals(codigo))
         {
            return status;
         }
      }

      return null;
   }

   /**
    * Obtém codigo.
    * @return codigo
    */
   public String getCodigo()
   {
      return codigo;
   }

   /**
    * Define codigo.
    * @param codigo novo codigo
    */
   public void setCodigo(String codigo)
   {
      this.codigo = codigo;
   }

   /**
    * Verifica se é status.
    * @return true, se é status
    */
   public boolean isStatus()
   {
      return status;
   }

   /**
    * Define status.
    * @param status novo status
    */
   public void setStatus(boolean status)
   {
      this.status = status;
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
