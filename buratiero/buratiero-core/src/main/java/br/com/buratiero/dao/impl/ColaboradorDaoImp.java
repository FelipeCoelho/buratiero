package br.com.buratiero.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.NonUniqueResultException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import br.com.buratiero.dao.ColaboradorDao;
import br.com.buratiero.model.Colaborador;

@Repository("colaboradorDao")
@Scope(proxyMode = ScopedProxyMode.NO, value = "prototype")
public class ColaboradorDaoImp extends ScpDaoImpl<Colaborador> implements ColaboradorDao
{

   private static final long serialVersionUID = 2223856807915893968L;

   @Override
   public List<String> recuperaNomeColaborador(String input)
   {
      StringBuilder sb = new StringBuilder();
      Map<String, Object> param = new HashMap<String, Object>();

      sb.append(" SELECT c FROM Colaborador c ");
      sb.append(" WHERE 1 = 1 ");
      if (!input.equals(""))
      {
         sb.append(" AND UPPER(c.dsNome) LIKE :input ");
         input = input.toUpperCase();
         param.put("input", "%" + input.toUpperCase() + "%");
      }
      sb.append(" ORDER BY c.dsNome ");

      List<Colaborador> listaCidade = (List<Colaborador>) consultarList(sb.toString(), param);
      List<String> lista = new ArrayList<String>();
      for (Colaborador colaborador : listaCidade)
      {
         lista.add(colaborador.getDsNome());
      }

      return lista;
   }

   @Override
   public Colaborador recuperaColaboradorPorNome(String nome)
   {
      StringBuilder sb = new StringBuilder();
      Map<String, Object> param = new HashMap<String, Object>();

      sb.append(" SELECT c FROM Colaborador c ");
      sb.append(" WHERE 1 = 1 ");
      if (!nome.equals(""))
      {
         sb.append(" AND UPPER(c.dsNome) LIKE UPPER(:input) ");
         param.put("input", nome);
      }
      sb.append(" ORDER BY c.dsNome ");

      try
      {
         return (Colaborador) consultarUnicoRegistro(sb.toString(), param);
      }
      catch (NonUniqueResultException e)
      {
         e.printStackTrace();
      }
      catch (Exception e)
      {
         return new Colaborador();
      }
      return null;
   }

   @Override
   public boolean existeColaborador(Colaborador colaboradorSelecionado)
   {
      String input = colaboradorSelecionado.getDsNome();
      StringBuilder sb = new StringBuilder();
      Map<String, Object> param = new HashMap<String, Object>();

      sb.append(" SELECT c FROM Colaborador c ");
      sb.append(" WHERE 1 = 1 ");
      if (!input.equals(""))
      {
         sb.append(" AND UPPER(c.dsNome) LIKE :input ");
         input = input.toUpperCase();
         param.put("input", "%" + input.toUpperCase() + "%");
      }
      sb.append(" ORDER BY c.dsNome ");

      List<Colaborador> listaColaborador = (List<Colaborador>) consultarList(sb.toString(), param);
      return listaColaborador.isEmpty();
   }

}
