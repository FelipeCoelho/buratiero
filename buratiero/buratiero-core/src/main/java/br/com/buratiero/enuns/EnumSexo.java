package br.com.buratiero.enuns;

/**
 * A classe ${class}.
 * @author felipe.coelho
 */
public enum EnumSexo
{

   /** masculino. */
   M(true, "male.ico", "Masculino"),
   /** feminino. */
   F(false, "female.ico", "Feminino");

   /** sexo. */
   private Boolean sexo;

   /** icone. */
   private String icone;

   /** descricao. */
   private String descricao;

   /**
    * Instancia um novo sexo.
    * @param sexo sexo
    * @param icone icone
    * @param descricao descricao
    */
   private EnumSexo(Boolean sexo, String icone, String descricao)
   {
      this.sexo = sexo;
      this.icone = icone;
      this.descricao = descricao;
   }

   /**
    * Recupera por sexo.
    * @param sexo sexo
    * @return sexo
    */
   public EnumSexo recuperaPorSexo(Boolean sexo)
   {
      for (EnumSexo s : EnumSexo.values())
      {
         if (sexo)
         {
            return s;
         }
         else
         {
            return s;
         }
      }
      return null;
   }

   /**
    * Obtém sexo.
    * @return sexo
    */
   public Boolean getSexo()
   {
      return sexo;
   }

   /**
    * Define sexo.
    * @param sexo novo sexo
    */
   public void setSexo(Boolean sexo)
   {
      this.sexo = sexo;
   }

   /**
    * Obtém icone.
    * @return icone
    */
   public String getIcone()
   {
      return icone;
   }

   /**
    * Define icone.
    * @param icone novo icone
    */
   public void setIcone(String icone)
   {
      this.icone = icone;
   }

   /**
    * Obtém descricao.
    * @return descricao
    */
   public String getDescricao()
   {
      return descricao;
   }

   /**
    * Define descricao.
    * @param descricao novo descricao
    */
   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }
}
