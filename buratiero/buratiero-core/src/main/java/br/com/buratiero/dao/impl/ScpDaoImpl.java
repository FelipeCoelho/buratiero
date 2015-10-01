package br.com.buratiero.dao.impl;

import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import br.com.buratiero.dao.to.Entidade;
import br.com.buratiero.dao.to.Paginacao;

/**
 * DOCUMENT ME!.
 * @author Felipe Coelho
 * @param <E> tipo de elemento
 */
public class ScpDaoImpl<E extends Entidade> extends GenericoDaoImpl<E>
{

   /** A constante serialVersionUID. */
   private static final long serialVersionUID = -2164802722839961193L;

   /**
    * Consultar unico registro.
    * @param queryStr query str
    * @param param param
    * @return object
    */
   public Object consultarUnicoRegistro(final String queryStr, final Map<String, Object> param)
   {
      final Query query = getEntityManager().createQuery(queryStr);
      if (param != null)
      {
         for (String nome : param.keySet())
         {
            query.setParameter(nome, param.get(nome));
         }
      }
      return query.getSingleResult();
   }

   @SuppressWarnings("unchecked")
   public List<E> consultarPaginadoMap(final String queryStr, final Paginacao paginacao,
      Map<String, Object> params)
   {
      final Query query = getEntityManager().createQuery(queryStr);
      paginar(paginacao, query);
      if (params != null)
      {
         for (String nome : params.keySet())
         {
            query.setParameter(nome, params.get(nome));
         }
      }
      return query.getResultList();
   }

   private void paginar(final Paginacao paginacao, final Query query)
   {
      if (paginacao != null)
      {
         if (paginacao.getPosicao() != null)
         {
            query.setFirstResult(paginacao.getPosicao().intValue());
         }
         if (paginacao.getLimite() != null)
         {
            query.setMaxResults(paginacao.getLimite().intValue());
         }
      }
   }

   @SuppressWarnings("unchecked")
   public List<E> consultarList(final String hql, final Map<String, Object> params)
   {
      final Query query = getEntityManager().createQuery(hql);
      if (params != null)
      {
         for (String nome : params.keySet())
         {
            query.setParameter(nome, params.get(nome));
         }
      }
      return query.getResultList();
   }
}
