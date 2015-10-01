package br.com.buratiero.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import br.com.buratiero.dao.to.Entidade;
import br.com.buratiero.enuns.StatusAtivoInativo;
import br.com.buratiero.util.Constante;

/**
 * DOCUMENT ME!.
 * @author Coelho
 */
@Entity
@Table(name = "tb_colaborador", schema = Constante.SCHEMA)
public class Colaborador implements Entidade
{

   /** A constante serialVersionUID. */
   private static final long serialVersionUID = -8391757248834241418L;

   /** codigo. */
   @Id
   @Column(name = "co_seq_colaborador")
   @SequenceGenerator(sequenceName = "dbburatiero.SEQ_COLABORADOR", name = "SEQUENCIA", initialValue = 1, allocationSize = 1)
   @GeneratedValue(generator = "SEQUENCIA", strategy = GenerationType.SEQUENCE)
   private Long codigo;

   /** ds nome. */
   @Column(name = "ds_nome", nullable = false, length = 50)
   private String dsNome;

   /** st ativo. */
   @Column(name = "st_ativo", nullable = false, length = 1)
   @Enumerated(EnumType.ORDINAL)
   private StatusAtivoInativo stAtivo = StatusAtivoInativo.ATIVO;

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
    * Obtém ds nome.
    * @return ds nome
    */
   public String getDsNome()
   {
      return dsNome;
   }

   /**
    * Define ds nome.
    * @param dsNome novo ds nome
    */
   public void setDsNome(String dsNome)
   {
      this.dsNome = dsNome;
   }

   /**
    * Obtém st ativo.
    * @return st ativo
    */
   public StatusAtivoInativo getStAtivo()
   {
      return stAtivo;
   }

   /**
    * Define st ativo.
    * @param stAtivo novo st ativo
    */
   public void setStAtivo(StatusAtivoInativo stAtivo)
   {
      this.stAtivo = stAtivo;
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
      result = prime * result + ((dsNome == null) ? 0 : dsNome.hashCode());
      result = prime * result + ((stAtivo == null) ? 0 : stAtivo.hashCode());
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
      Colaborador other = (Colaborador) obj;
      if (codigo == null)
      {
         if (other.codigo != null)
            return false;
      }
      else if (!codigo.equals(other.codigo))
         return false;
      if (dsNome == null)
      {
         if (other.dsNome != null)
            return false;
      }
      else if (!dsNome.equals(other.dsNome))
         return false;
      if (stAtivo != other.stAtivo)
         return false;
      return true;
   }

}
