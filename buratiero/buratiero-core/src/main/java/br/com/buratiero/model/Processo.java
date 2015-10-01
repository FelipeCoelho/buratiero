package br.com.buratiero.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import br.com.buratiero.dao.to.Entidade;
import br.com.buratiero.enuns.EnumProcessoNovoImpugnacao;
import br.com.buratiero.enuns.EnumSImNao;
import br.com.buratiero.enuns.EnumTipoProcesso;
import br.com.buratiero.util.Constante;

/**
 * A classe ${class}.
 * @author Coelho
 */
@Entity
@Table(name = "tb_processo", schema = Constante.SCHEMA)
public class Processo implements Entidade
{

   /** A constante serialVersionUID. */
   private static final long serialVersionUID = 6281529581321478294L;

   /** codigo. */
   @Id
   @Column(name = "CO_SEQ_CLIENTE")
   @SequenceGenerator(sequenceName = "dbburatiero.seq_processo", name = "SEQUENCIA_PROCESSO", initialValue = 1, allocationSize = 1)
   @GeneratedValue(generator = "SEQUENCIA_PROCESSO", strategy = GenerationType.SEQUENCE)
   private Long codigo;

   /** nu processo. */
   @Column(name = "NU_PROCESSO")
   private String nuProcesso;

   /** cidade. */
   @ManyToOne
   @JoinColumn(name = "CO_CIDADE", referencedColumnName = "COD_CIDADE")
   private Cidade cidade;

   /** ds vara. */
   @Column(name = "DS_VARA")
   private String dsVara;

   /** dtpublicacao. */
   @Column(name = "DT_PUBLICACAO")
   private Date dtpublicacao;

   /** ds reclamante. */
   @Column(name = "DS_RECLAMANTE")
   private String dsReclamante;

   /** ds reclamada. */
   @Column(name = "DS_RECLAMADA")
   private String dsReclamada;

   /** st novo impugnacao. */
   @Column(name = "ST_NOVOIMPUGNACAO")
   @Enumerated(EnumType.ORDINAL)
   private EnumProcessoNovoImpugnacao stNovoImpugnacao;

   /** st tipo processo. */
   @Column(name = "ST_TIPOPROCESSO")
   @Enumerated(EnumType.ORDINAL)
   private EnumTipoProcesso stTipoProcesso;

   /** colaborador. */
   @ManyToOne
   @JoinColumn(name = "COD_COLABORADOR", referencedColumnName = "CO_SEQ_COLABORADOR")
   private Colaborador colaborador;

   /** dt carga. */
   @Column(name = "DT_CARGA")
   private Date dtCarga;

   /** dt praso. */
   @Column(name = "DT_PRASO")
   private Date dtPraso;

   /** dt devolucao carga. */
   @Column(name = "DT_DEVOLUCAOCARGA")
   private Date dtDevolucaoCarga;

   /** dt protocolo. */
   @Column(name = "DT_PROTOCOLO")
   private Date dtProtocolo;

   /** vl honorario solicitado. */
   @Column(name = "VL_HONORARIOSOLICITADO")
   private Double vlHonorarioSolicitado;

   /** vl honorario fixado. */
   @Column(name = "VL_HONORARIOFIXADO")
   private Double vlHonorarioFixado;

   /** tp recebido. */
   @Column(name = "TP_RECEBIDO")
   @Enumerated(EnumType.ORDINAL)
   private EnumSImNao tpRecebido;

   /** dt recebimento. */
   @Column(name = "DT_RECEBIMENTO")
   private Date dtRecebimento;

   /** vl efetivamente recebido. */
   @Column(name = "VL_EFETIVAMENTERECEBIDO")
   private Double vlEfetivamenteRecebido;

   /** ds historico processo. */
   @Column(name = "DS_HISTORICOPROCESSO")
   private String dsHistoricoProcesso;

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
    * Obtém nu processo.
    * @return nu processo
    */
   public String getNuProcesso()
   {
      return nuProcesso;
   }

   /**
    * Define nu processo.
    * @param nuProcesso novo nu processo
    */
   public void setNuProcesso(String nuProcesso)
   {
      this.nuProcesso = nuProcesso;
   }

   /**
    * Obtém cidade.
    * @return cidade
    */
   public Cidade getCidade()
   {
      if (cidade == null)
      {
         cidade = new Cidade();
      }
      return cidade;
   }

   /**
    * Define cidade.
    * @param cidade novo cidade
    */
   public void setCidade(Cidade cidade)
   {
      this.cidade = cidade;
   }

   /**
    * Obtém ds vara.
    * @return ds vara
    */
   public String getDsVara()
   {
      return dsVara;
   }

   /**
    * Define ds vara.
    * @param dsVara novo ds vara
    */
   public void setDsVara(String dsVara)
   {
      this.dsVara = dsVara;
   }

   /**
    * Obtém dtpublicacao.
    * @return dtpublicacao
    */
   public Date getDtpublicacao()
   {
      return dtpublicacao;
   }

   /**
    * Define dtpublicacao.
    * @param dtpublicacao novo dtpublicacao
    */
   public void setDtpublicacao(Date dtpublicacao)
   {
      this.dtpublicacao = dtpublicacao;
   }

   /**
    * Obtém ds reclamante.
    * @return ds reclamante
    */
   public String getDsReclamante()
   {
      return dsReclamante;
   }

   /**
    * Define ds reclamante.
    * @param dsReclamante novo ds reclamante
    */
   public void setDsReclamante(String dsReclamante)
   {
      this.dsReclamante = dsReclamante;
   }

   /**
    * Obtém ds reclamada.
    * @return ds reclamada
    */
   public String getDsReclamada()
   {
      return dsReclamada;
   }

   /**
    * Define ds reclamada.
    * @param dsReclamada novo ds reclamada
    */
   public void setDsReclamada(String dsReclamada)
   {
      this.dsReclamada = dsReclamada;
   }

   /**
    * Obtém st novo impugnacao.
    * @return st novo impugnacao
    */
   public EnumProcessoNovoImpugnacao getStNovoImpugnacao()
   {
      return stNovoImpugnacao;
   }

   /**
    * Define st novo impugnacao.
    * @param stNovoImpugnacao novo st novo impugnacao
    */
   public void setStNovoImpugnacao(EnumProcessoNovoImpugnacao stNovoImpugnacao)
   {
      this.stNovoImpugnacao = stNovoImpugnacao;
   }

   /**
    * Obtém st tipo processo.
    * @return st tipo processo
    */
   public EnumTipoProcesso getStTipoProcesso()
   {
      return stTipoProcesso;
   }

   /**
    * Define st tipo processo.
    * @param stTipoProcesso novo st tipo processo
    */
   public void setStTipoProcesso(EnumTipoProcesso stTipoProcesso)
   {
      this.stTipoProcesso = stTipoProcesso;
   }

   /**
    * Obtém colaborador.
    * @return colaborador
    */
   public Colaborador getColaborador()
   {
      return colaborador;
   }

   /**
    * Define colaborador.
    * @param colaborador novo colaborador
    */
   public void setColaborador(Colaborador colaborador)
   {
      this.colaborador = colaborador;
   }

   /**
    * Obtém dt carga.
    * @return dt carga
    */
   public Date getDtCarga()
   {
      return dtCarga;
   }

   /**
    * Define dt carga.
    * @param dtCarga novo dt carga
    */
   public void setDtCarga(Date dtCarga)
   {
      this.dtCarga = dtCarga;
   }

   /**
    * Obtém dt praso.
    * @return dt praso
    */
   public Date getDtPraso()
   {
      return dtPraso;
   }

   /**
    * Define dt praso.
    * @param dtPraso novo dt praso
    */
   public void setDtPraso(Date dtPraso)
   {
      this.dtPraso = dtPraso;
   }

   /**
    * Obtém dt devolucao carga.
    * @return dt devolucao carga
    */
   public Date getDtDevolucaoCarga()
   {
      return dtDevolucaoCarga;
   }

   /**
    * Define dt devolucao carga.
    * @param dtDevolucaoCarga novo dt devolucao carga
    */
   public void setDtDevolucaoCarga(Date dtDevolucaoCarga)
   {
      this.dtDevolucaoCarga = dtDevolucaoCarga;
   }

   /**
    * Obtém dt protocolo.
    * @return dt protocolo
    */
   public Date getDtProtocolo()
   {
      return dtProtocolo;
   }

   /**
    * Define dt protocolo.
    * @param dtProtocolo novo dt protocolo
    */
   public void setDtProtocolo(Date dtProtocolo)
   {
      this.dtProtocolo = dtProtocolo;
   }

   /**
    * Obtém vl honorario solicitado.
    * @return vl honorario solicitado
    */
   public Double getVlHonorarioSolicitado()
   {
      return vlHonorarioSolicitado;
   }

   /**
    * Define vl honorario solicitado.
    * @param vlHonorarioSolicitado novo vl honorario solicitado
    */
   public void setVlHonorarioSolicitado(Double vlHonorarioSolicitado)
   {
      this.vlHonorarioSolicitado = vlHonorarioSolicitado;
   }

   /**
    * Obtém vl honorario fixado.
    * @return vl honorario fixado
    */
   public Double getVlHonorarioFixado()
   {
      return vlHonorarioFixado;
   }

   /**
    * Define vl honorario fixado.
    * @param vlHonorarioFixado novo vl honorario fixado
    */
   public void setVlHonorarioFixado(Double vlHonorarioFixado)
   {
      this.vlHonorarioFixado = vlHonorarioFixado;
   }

   /**
    * Obtém tp recebido.
    * @return tp recebido
    */
   public EnumSImNao getTpRecebido()
   {
      return tpRecebido;
   }

   /**
    * Define tp recebido.
    * @param tpRecebido novo tp recebido
    */
   public void setTpRecebido(EnumSImNao tpRecebido)
   {
      this.tpRecebido = tpRecebido;
   }

   /**
    * Obtém dt recebimento.
    * @return dt recebimento
    */
   public Date getDtRecebimento()
   {
      return dtRecebimento;
   }

   /**
    * Define dt recebimento.
    * @param dtRecebimento novo dt recebimento
    */
   public void setDtRecebimento(Date dtRecebimento)
   {
      this.dtRecebimento = dtRecebimento;
   }

   /**
    * Obtém vl efetivamente recebido.
    * @return vl efetivamente recebido
    */
   public Double getVlEfetivamenteRecebido()
   {
      return vlEfetivamenteRecebido;
   }

   /**
    * Define vl efetivamente recebido.
    * @param vlEfetivamenteRecebido novo vl efetivamente recebido
    */
   public void setVlEfetivamenteRecebido(Double vlEfetivamenteRecebido)
   {
      this.vlEfetivamenteRecebido = vlEfetivamenteRecebido;
   }

   /**
    * Obtém ds historico processo.
    * @return ds historico processo
    */
   public String getDsHistoricoProcesso()
   {
      return dsHistoricoProcesso;
   }

   /**
    * Define ds historico processo.
    * @param dsHistoricoProcesso novo ds historico processo
    */
   public void setDsHistoricoProcesso(String dsHistoricoProcesso)
   {
      this.dsHistoricoProcesso = dsHistoricoProcesso;
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
      result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
      result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
      result = prime * result + ((colaborador == null) ? 0 : colaborador.hashCode());
      result =
         prime * result + ((dsHistoricoProcesso == null) ? 0 : dsHistoricoProcesso.hashCode());
      result = prime * result + ((dsReclamada == null) ? 0 : dsReclamada.hashCode());
      result = prime * result + ((dsReclamante == null) ? 0 : dsReclamante.hashCode());
      result = prime * result + ((dsVara == null) ? 0 : dsVara.hashCode());
      result = prime * result + ((dtCarga == null) ? 0 : dtCarga.hashCode());
      result = prime * result + ((dtDevolucaoCarga == null) ? 0 : dtDevolucaoCarga.hashCode());
      result = prime * result + ((dtPraso == null) ? 0 : dtPraso.hashCode());
      result = prime * result + ((dtProtocolo == null) ? 0 : dtProtocolo.hashCode());
      result = prime * result + ((dtRecebimento == null) ? 0 : dtRecebimento.hashCode());
      result = prime * result + ((dtpublicacao == null) ? 0 : dtpublicacao.hashCode());
      result = prime * result + ((nuProcesso == null) ? 0 : nuProcesso.hashCode());
      result = prime * result + ((stNovoImpugnacao == null) ? 0 : stNovoImpugnacao.hashCode());
      result = prime * result + ((stTipoProcesso == null) ? 0 : stTipoProcesso.hashCode());
      result = prime * result + ((tpRecebido == null) ? 0 : tpRecebido.hashCode());
      result =
         prime * result
            + ((vlEfetivamenteRecebido == null) ? 0 : vlEfetivamenteRecebido.hashCode());
      result = prime * result + ((vlHonorarioFixado == null) ? 0 : vlHonorarioFixado.hashCode());
      result =
         prime * result + ((vlHonorarioSolicitado == null) ? 0 : vlHonorarioSolicitado.hashCode());
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
      Processo other = (Processo) obj;
      if (cidade == null)
      {
         if (other.cidade != null)
            return false;
      }
      else if (!cidade.equals(other.cidade))
         return false;
      if (codigo == null)
      {
         if (other.codigo != null)
            return false;
      }
      else if (!codigo.equals(other.codigo))
         return false;
      if (colaborador == null)
      {
         if (other.colaborador != null)
            return false;
      }
      else if (!colaborador.equals(other.colaborador))
         return false;
      if (dsHistoricoProcesso == null)
      {
         if (other.dsHistoricoProcesso != null)
            return false;
      }
      else if (!dsHistoricoProcesso.equals(other.dsHistoricoProcesso))
         return false;
      if (dsReclamada == null)
      {
         if (other.dsReclamada != null)
            return false;
      }
      else if (!dsReclamada.equals(other.dsReclamada))
         return false;
      if (dsReclamante == null)
      {
         if (other.dsReclamante != null)
            return false;
      }
      else if (!dsReclamante.equals(other.dsReclamante))
         return false;
      if (dsVara == null)
      {
         if (other.dsVara != null)
            return false;
      }
      else if (!dsVara.equals(other.dsVara))
         return false;
      if (dtCarga == null)
      {
         if (other.dtCarga != null)
            return false;
      }
      else if (!dtCarga.equals(other.dtCarga))
         return false;
      if (dtDevolucaoCarga == null)
      {
         if (other.dtDevolucaoCarga != null)
            return false;
      }
      else if (!dtDevolucaoCarga.equals(other.dtDevolucaoCarga))
         return false;
      if (dtPraso == null)
      {
         if (other.dtPraso != null)
            return false;
      }
      else if (!dtPraso.equals(other.dtPraso))
         return false;
      if (dtProtocolo == null)
      {
         if (other.dtProtocolo != null)
            return false;
      }
      else if (!dtProtocolo.equals(other.dtProtocolo))
         return false;
      if (dtRecebimento == null)
      {
         if (other.dtRecebimento != null)
            return false;
      }
      else if (!dtRecebimento.equals(other.dtRecebimento))
         return false;
      if (dtpublicacao == null)
      {
         if (other.dtpublicacao != null)
            return false;
      }
      else if (!dtpublicacao.equals(other.dtpublicacao))
         return false;
      if (nuProcesso == null)
      {
         if (other.nuProcesso != null)
            return false;
      }
      else if (!nuProcesso.equals(other.nuProcesso))
         return false;
      if (stNovoImpugnacao != other.stNovoImpugnacao)
         return false;
      if (stTipoProcesso != other.stTipoProcesso)
         return false;
      if (tpRecebido != other.tpRecebido)
         return false;
      if (vlEfetivamenteRecebido == null)
      {
         if (other.vlEfetivamenteRecebido != null)
            return false;
      }
      else if (!vlEfetivamenteRecebido.equals(other.vlEfetivamenteRecebido))
         return false;
      if (vlHonorarioFixado == null)
      {
         if (other.vlHonorarioFixado != null)
            return false;
      }
      else if (!vlHonorarioFixado.equals(other.vlHonorarioFixado))
         return false;
      if (vlHonorarioSolicitado == null)
      {
         if (other.vlHonorarioSolicitado != null)
            return false;
      }
      else if (!vlHonorarioSolicitado.equals(other.vlHonorarioSolicitado))
         return false;
      return true;
   }

}
