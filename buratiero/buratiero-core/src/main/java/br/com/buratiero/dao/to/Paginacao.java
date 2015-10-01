package br.com.buratiero.dao.to;

import java.io.Serializable;

public class Paginacao implements Serializable
{

   private static final long serialVersionUID = -1787117477723959171L;

   private Long posicao;

   private Long limite;

   public Paginacao()
   {
   }

   public Paginacao(Long posicao, Long limite)
   {
      this.posicao = posicao;
      this.limite = limite;
   }

   public Long getLimite()
   {
      return limite;
   }

   public Long getPosicao()
   {
      return posicao;
   }

   public void setLimite(Long limite)
   {
      this.limite = limite;
   }

   public void setPosicao(Long posicao)
   {
      this.posicao = posicao;
   }

}
