package br.com.buratiero.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import br.com.buratiero.model.Cidade;
import br.com.buratiero.model.Estado;
import br.com.buratiero.service.CidadeDao;

@Repository("cidadeDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class CidadeDaoImpl extends ScpDaoImpl<Cidade> implements CidadeDao
{

   private static final long serialVersionUID = -973493445667487211L;

   @Override
   public List<String> recuperaNomeCidade(String input, Estado estado)
   {
      StringBuilder sb = new StringBuilder();
      Map<String, Object> param = new HashMap<String, Object>();

      sb.append(" SELECT c FROM Cidade c ");
      sb.append(" WHERE 1 = 1 ");
      if (!input.equals(""))
      {
         sb.append(" AND UPPER(c.nome) LIKE :input ");
         input = input.toUpperCase();
         param.put("input", "%" + input + "%");

         sb.append(" AND c.estado.codigo = :codigo ");
         param.put("codigo", estado.getCodigo());
      }
      sb.append(" ORDER BY c.nome ");

      List<Cidade> listaCidade = (List<Cidade>) consultarList(sb.toString(), param);
      List<String> lista = new ArrayList<String>();
      for (Cidade cidade : listaCidade)
      {
         lista.add(cidade.getNome());
      }

      return lista;
   }

   @Override
   public Cidade recuperaCidadePorUfNome(String nome, Estado estado)
   {

      StringBuilder sb = new StringBuilder();
      Map<String, Object> param = new HashMap<String, Object>();

      sb.append(" SELECT c FROM Cidade c ");
      sb.append(" WHERE 1 = 1 ");
      if (!nome.equals(""))
      {
         sb.append(" AND UPPER(c.nome) LIKE :input ");
         nome = nome.toUpperCase();
         param.put("input", "%" + nome + "%");

         sb.append(" AND c.estado.codigo = :codigo ");
         param.put("codigo", estado.getCodigo());
      }
      sb.append(" ORDER BY c.nome ");

      try
      {
         return (Cidade) consultarUnicoRegistro(sb.toString(), param);
      }
      catch (Exception e)
      {
         return new Cidade();
      }
   }

}
