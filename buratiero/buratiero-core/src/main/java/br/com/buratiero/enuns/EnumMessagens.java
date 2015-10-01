package br.com.buratiero.enuns;

/**
 * A classe ${class}.
 * @author Eliana
 */
public enum EnumMessagens
{
   /* Menssagens de sucesso */
   /** M s_1. */
   MS_1("Operação concluida com sucesso!"),
   /** M s_2. */
   MS_2("Processo salvo com sucesso!"),
   /* Menssagens de informação */
   MS_3("Colaborador Salvo Com Sucesso"),
   
   MS_4("Processo excluido com sucesso"),
   /** M i_1. */
   MI_1("Sistema fora de operação"),
   /* Menssagens de erros */
   /** M e_1. */
   ME_1("Operação não pode ser concluida!"),

   /** M e_2. */
   ME_2("Cidade não existe!"),

   /** M e_3. */
   ME_3("Usuario não existe na base de dados!"),

   ME_4("Colaborador Já Existe!");

   /** menssagem. */
   private String menssagem;

   /**
    * Instancia um novo messagens.
    * @param menssagem menssagem
    */
   private EnumMessagens(String menssagem)
   {
      this.setMenssagem(menssagem);
   }

   /**
    * Obtém menssagem.
    * @return menssagem
    */
   public String getMenssagem()
   {
      return menssagem;
   }

   /**
    * Define menssagem.
    * @param menssagem novo menssagem
    */
   public void setMenssagem(String menssagem)
   {
      this.menssagem = menssagem;
   }
}
