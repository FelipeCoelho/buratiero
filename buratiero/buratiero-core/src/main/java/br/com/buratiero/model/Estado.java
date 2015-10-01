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
@Table(name = "TB_ESTADO", schema = Constante.SCHEMA)
public class Estado implements Entidade
{

   /** A constante serialVersionUID. */
   private static final long serialVersionUID = -4170842430720882874L;

   /** codigo. */
   @Id
   @Column(name = "COD_ESTADO")
   private Long codigo;

   /** nome. */
   @Column(name = "DS_NOME")
   private String nome;

   /** uf. */
   @Column(name = "DS_UF")
   private String uf;

   /** pais. */
   @ManyToOne
   @JoinColumn(name = "CO_PAIS", referencedColumnName = "COD_PAIS")
   private Pais pais;

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
    * Obtém uf.
    * @return uf
    */
   public String getUf()
   {
      return uf;
   }

   /**
    * Define uf.
    * @param uf novo uf
    */
   public void setUf(String uf)
   {
      this.uf = uf;
   }

   /**
    * Obtém pais.
    * @return pais
    */
   public Pais getPais()
   {
      return pais;
   }

   /**
    * Define pais.
    * @param pais novo pais
    */
   public void setPais(Pais pais)
   {
      this.pais = pais;
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
      Estado other = (Estado) obj;
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
