package br.com.buratiero.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.wicket.util.string.Strings;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import br.com.buratiero.dao.ProcessoDao;
import br.com.buratiero.dao.to.Entidade;
import br.com.buratiero.dao.to.Paginacao;
import br.com.buratiero.model.Processo;
import br.com.buratiero.util.ParametrosOrdenacao;

@Repository("processoDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class ProcessoDaoImpl extends ScpDaoImpl<Processo> implements ProcessoDao
{

   private static final long serialVersionUID = -3753302217682687537L;

   @Override
   public List<String> recuperaNumeroProcesso(String input)
   {
      StringBuilder sb = new StringBuilder();
      Map<String, Object> params = new HashMap<String, Object>();

      sb.append(" SELECT p FROM Processo p ");
      sb.append(" WHERE 1 = 1 ");
      if (!Strings.isEmpty(input))
      {
         sb.append(" AND UPPER(p.nuProcesso) LIKE :input  ");
         params.put("input", "%" + input.toUpperCase() + "%");
      }

      List<Processo> listProcesso = (List<Processo>) consultarList(sb.toString(), params);
      List<String> lista = new ArrayList<String>();
      for (Processo processo : listProcesso)
      {
         lista.add(processo.getNuProcesso());
      }

      return lista;
   }

   @Override
   public List<String> recuperaNomeReclamada(String input)
   {
      StringBuilder sb = new StringBuilder();
      Map<String, Object> params = new HashMap<String, Object>();

      sb.append(" SELECT p FROM Processo p ");
      sb.append(" WHERE 1 = 1 ");
      if (!Strings.isEmpty(input))
      {
         sb.append(" AND UPPER(p.dsReclamada) LIKE :input  ");
         params.put("input", "%" + input.toUpperCase() + "%");
      }

      List<Processo> listProcesso = (List<Processo>) consultarList(sb.toString(), params);
      List<String> lista = new ArrayList<String>();
      for (Processo processo : listProcesso)
      {
         if (!lista.contains(processo.getDsReclamada()))
         {
            lista.add(processo.getDsReclamada());
         }
      }

      return lista;
   }

   @Override
   public List<String> recuperaNomeReclamante(String input)
   {
      StringBuilder sb = new StringBuilder();
      Map<String, Object> params = new HashMap<String, Object>();

      sb.append(" SELECT p FROM Processo p ");
      sb.append(" WHERE 1 = 1 ");
      if (!Strings.isEmpty(input))
      {
         sb.append(" AND UPPER(p.dsReclamante) LIKE :input  ");
         params.put("input", "%" + input.toUpperCase() + "%");
      }

      List<Processo> listProcesso = (List<Processo>) consultarList(sb.toString(), params);
      List<String> lista = new ArrayList<String>();
      for (Processo processo : listProcesso)
      {
         if (!lista.contains(processo.getDsReclamante()))
         {
            lista.add(processo.getDsReclamante());
         }
      }

      return lista;
   }

   @Override
   public List<String> recuperaNomeVara(String input)
   {
      StringBuilder sb = new StringBuilder();
      Map<String, Object> params = new HashMap<String, Object>();

      sb.append(" SELECT p FROM Processo p ");
      sb.append(" WHERE 1 = 1 ");
      if (!Strings.isEmpty(input))
      {
         sb.append(" AND UPPER(p.dsVara) LIKE UPPER(:input)  ");
         params.put("input", "%" + input + "%");
      }

      List<Processo> listProcesso = (List<Processo>) consultarList(sb.toString(), params);
      List<String> lista = new ArrayList<String>();
      for (Processo processo : listProcesso)
      {
         if (!lista.contains(processo.getDsVara()))
         {
            lista.add(processo.getDsVara());
         }
      }

      return lista;
   }

   @Override
   public List<Processo> buscaListaGrid(Entidade entidade, long first, long count,
      ParametrosOrdenacao ordernar)
   {
      Processo filtro = (Processo) entidade;
      Map<String, Object> params = new HashMap<String, Object>();
      StringBuilder sb = new StringBuilder();
      sb.append(" SELECT p FROM Processo p ");
      if (filtro != null)
      {
         montaConsultaGenerica(sb, params, filtro);
      }
      if (ordernar != null)
      {
         sb.append("ORDER BY p." + ordernar.getColuna() + " " + ordernar.getOrdernar());
      }
      if (params.isEmpty())
      {
         params = null;
      }
      return consultarPaginadoMap(sb.toString(), new Paginacao(first, count), params);
   }

   @Override
   public int contadorListaGrid(Entidade entidade)
   {
      final Processo filtro = (Processo) entidade;
      final StringBuilder sb = new StringBuilder();
      final Map<String, Object> params = new HashMap<String, Object>();
      sb.append(" SELECT COUNT(p.codigo) FROM Processo p ");
      if (filtro != null)
      {
         montaConsultaGenerica(sb, params, filtro);
      }
      return Integer.parseInt(consultarUnicoRegistro(sb.toString(), params).toString());
   }

   private void montaConsultaGenerica(StringBuilder sb, Map<String, Object> params, Processo filtro)
   {
      sb.append(" WHERE 1 = 1 ");
      if (filtro.getNuProcesso() != null)
      {
         sb.append(" AND p.nuProcesso LIKE :nuProcesso ");
         params.put("nuProcesso", filtro.getNuProcesso());
      }

      if (filtro.getCidade() != null && filtro.getCidade().getCodigo() != null)
      {
         sb.append(" AND p.cidade.codigo  = :codCidade ");
         params.put("codCidade", filtro.getCidade().getCodigo());
      }

      if (filtro.getDsVara() != null)
      {
         sb.append(" AND UPPER(p.dsVara) = UPPER(:dsVara) ");
         params.put("dsVara", "%" + filtro.getDsVara() + "%");
      }

      if (filtro.getDtpublicacao() != null)
      {
         sb.append(" AND p.dtpublicacao = :dtpublicacao ");
         
         params.put("dtpublicacao", filtro.getDtpublicacao());
      }

      if (filtro.getDsReclamante() != null)
      {
         sb.append(" AND UPPER(p.dsReclamante) LIKE UPPER(:dsReclamante) ");
         params.put("dsReclamante", "%" + filtro.getDsReclamante() + "%");
      }

      if (filtro.getDsReclamada() != null)
      {
         sb.append(" AND UPPER(p.dsReclamada) LIKE UPPER(:dsReclamada) ");
         params.put("dsReclamada", "%" + filtro.getDsReclamada() + "%");
      }
      if (filtro.getStNovoImpugnacao() != null)
      {
         sb.append(" AND p.stNovoImpugnacao = :stNovoImpugnacao ");
         params.put("stNovoImpugnacao", filtro.getStNovoImpugnacao());
      }
      if (filtro.getStTipoProcesso() != null)
      {
         sb.append(" AND p.stTipoProcesso = :stTipoProcesso ");
         params.put("stTipoProcesso", filtro.getStTipoProcesso());
      }
      if (filtro.getColaborador() != null && filtro.getColaborador().getCodigo() != null)
      {
         sb.append(" AND p.colaborador.codigo = :colaborador ");
         params.put("colaborador", filtro.getColaborador().getCodigo());
      }
      if (filtro.getDtCarga() != null)
      {
         sb.append(" AND p.dtCarga = :dtCarga ");
         params.put("dtCarga", filtro.getDtCarga());
      }
      if (filtro.getDtPraso() != null)
      {
         sb.append(" AND p.dtPraso = :dtPraso ");
         params.put("dtPraso", filtro.getDtPraso());
      }
      if (filtro.getDtDevolucaoCarga() != null)
      {
         sb.append(" AND p.dtDevolucaoCarga = :dtDevolucaoCarga ");
         params.put("dtDevolucaoCarga", filtro.getDtDevolucaoCarga());
      }
      if (filtro.getDtProtocolo() != null)
      {
         sb.append(" AND p.dtProtocolo = :dtProtocolo ");
         params.put("dtProtocolo", filtro.getDtProtocolo());
      }
      if (filtro.getVlHonorarioSolicitado() != null)
      {
         sb.append(" AND p.vlHonorarioSolicitado = :vlHonorarioSolicitado ");
         params.put("vlHonorarioSolicitado", filtro.getVlHonorarioSolicitado());
      }
      if (filtro.getVlHonorarioFixado() != null)
      {
         sb.append(" AND p.vlHonorarioFixado = :vlHonorarioFixado ");
         params.put("vlHonorarioFixado", filtro.getVlHonorarioFixado());
      }
      if (filtro.getTpRecebido() != null)
      {
         sb.append(" AND p.tpRecebido = :tpRecebido ");
         params.put("tpRecebido", filtro.getTpRecebido());
      }
      if (filtro.getDtRecebimento() != null)
      {
         sb.append(" AND p.dtRecebimento = :dtRecebimento ");
         params.put("dtRecebimento", filtro.getDtRecebimento());
      }
      if (filtro.getDtRecebimento() != null)
      {
         sb.append(" AND p.vlEfetivamenteRecebido = :vlEfetivamenteRecebido ");
         params.put("vlEfetivamenteRecebido", filtro.getVlEfetivamenteRecebido());
      }
   }

   @Override
   public List<Processo> recuperaListaProcesso(Processo filtro)
   {
      Map<String, Object> params = new HashMap<String, Object>();
      StringBuilder sb = new StringBuilder();
      sb.append(" SELECT p FROM Processo p ");
      if (filtro != null)
      {
         montaConsultaGenerica(sb, params, filtro);
      }
      if (params.isEmpty())
      {
         params = null;
      }
      return consultarPaginadoMap(sb.toString(), null, params);
   }

}
