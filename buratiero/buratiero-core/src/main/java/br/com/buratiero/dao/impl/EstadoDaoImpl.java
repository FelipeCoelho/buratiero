package br.com.buratiero.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import br.com.buratiero.dao.EstadoDao;
import br.com.buratiero.model.Estado;

@Repository("estadoDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class EstadoDaoImpl extends ScpDaoImpl<Estado> implements EstadoDao
{

   private static final long serialVersionUID = 6754026754491550192L;

   @Override
   public List<String> recuperaUfEstado(String input)
   {

      StringBuilder sb = new StringBuilder();
      Map<String, Object> param = new HashMap<String, Object>();

      sb.append(" SELECT e FROM Estado e ");
      sb.append(" WHERE 1 = 1 ");
      if (!input.equals(""))
      {
         sb.append(" AND UPPER(e.uf) LIKE :input ");
         input = input.toUpperCase();
         param.put("input", "%" + input + "%");
      }
      sb.append(" ORDER BY e.uf ");

      List<Estado> listaEstado = (List<Estado>) consultarList(sb.toString(), param);
      List<String> lista = new ArrayList<String>();
      for (Estado estado : listaEstado)
      {
         lista.add(estado.getUf());
      }

      return lista;
   }

   @Override
   public Estado recuperaEstadoPorUf(String uf)
   {
      StringBuilder sb = new StringBuilder();
      Map<String, Object> param = new HashMap<String, Object>();

      sb.append(" SELECT e FROM Estado e ");
      sb.append(" WHERE 1 = 1 ");
      if (!uf.equals(""))
      {
         sb.append(" AND UPPER(e.uf) LIKE :uf ");
         uf = uf.toUpperCase();
         param.put("input", "%" + uf + "%");
      }
      sb.append(" ORDER BY e.uf ");

      return (Estado) consultarUnicoRegistro(sb.toString(), param);
   }

   @Override
   public List<Estado> recuperaListaEstado()
   {
      StringBuilder sb = new StringBuilder();

      sb.append(" SELECT e FROM Estado e ");

      return consultarList(sb.toString(), null);
   }
}
