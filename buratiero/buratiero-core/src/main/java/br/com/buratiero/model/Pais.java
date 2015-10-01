package br.com.buratiero.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import br.com.buratiero.dao.to.Entidade;
import br.com.buratiero.util.Constante;

/**
 * A classe ${class}.
 * @author Coelho
 */
@Entity
@Table(name = "TB_PAIS", schema = Constante.SCHEMA)
public class Pais implements Entidade
{

   /** A constante serialVersionUID. */
   private static final long serialVersionUID = 7923040691691440824L;

   /** codigo. */
   @Id
   @Column(name = "COD_PAIS")
   private Long codigo;

   /** nome. */
   @Column(name = "DS_NOME")
   private String nome;

   /** sigla. */
   @Column(name = "DS_SIGLA")
   private String sigla;

   /*
    * (non-Javadoc)
    * @see br.com.buratiero.dao.to.Entidade#getId()
    */
   @Override
   public Serializable getId()
   {
      return getCodigo();
   }

   /**
    * Obtém codigo.
    * @return codigo
    */
   public Long getCodigo()
   {
      return codigo;
   }

   /**
    * Define codigo.
    * @param codigo novo codigo
    */
   public void setCodigo(Long codigo)
   {
      this.codigo = codigo;
   }

   /**
    * Obtém nome.
    * @return nome
    */
   public String getNome()
   {
      return nome;
   }

   /**
    * Define nome.
    * @param nome novo nome
    */
   public void setNome(String nome)
   {
      this.nome = nome;
   }

   /**
    * Obtém sigla.
    * @return sigla
    */
   public String getSigla()
   {
      return sigla;
   }

   /**
    * Define sigla.
    * @param sigla novo sigla
    */
   public void setSigla(String sigla)
   {
      this.sigla = sigla;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
      return result;
   }

   /*
    * (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      Pais other = (Pais) obj;
      if (codigo == null)
      {
         if (other.codigo != null)
            return false;
      }
      else if (!codigo.equals(other.codigo))
         return false;
      return true;
   }

}
