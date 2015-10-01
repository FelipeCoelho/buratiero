package br.com.buratiero.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import br.com.buratiero.dao.to.Entidade;
import br.com.buratiero.util.Constante;

/**
 * A classe ${class}.
 * @author Coelho
 */
@Entity
@Table(name = "TB_CIDADE", schema = Constante.SCHEMA)
public class Cidade implements Entidade
{

   /** A constante serialVersionUID. */
   private static final long serialVersionUID = 1004348717225093898L;

   /** codigo. */
   @Id
   @Column(name = "COD_CIDADE")
   private Long codigo;

   /** nome. */
   @Column(name = "DS_NOME")
   private String nome;

   /** estado. */
   @ManyToOne
   @JoinColumn(name = "CO_ESTADO", referencedColumnName = "COD_ESTADO")
   private Estado estado;

   /*
    * (non-Javadoc)
    * @see br.com.buratiero.dao.to.Entidade#getId()
    */
   @Override
   public Serializable getId()
   {
      return null;
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
    * Obtém estado.
    * @return estado
    */
   public Estado getEstado()
   {
      if (estado == null)
      {
         estado = new Estado();
      }
      return estado;
   }

   /**
    * Define estado.
    * @param estado novo estado
    */
   public void setEstado(Estado estado)
   {
      this.estado = estado;
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
      Cidade other = (Cidade) obj;
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
