package br.com.buratiero.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.buratiero.dao.JpaDao;
import br.com.buratiero.dao.to.Paginacao;

@Repository("jpaDao")
public class JpaDaoImpl<T> implements JpaDao<T>
{

   private static final long serialVersionUID = 6907111403428623938L;

   private Class<T> persistentClass;

   @PersistenceContext
   private EntityManager entityManager;

   private Query createQuery(final String queryStr, final Paginacao paginacao,
      final Object... params)
   {
      final Query query = this.entityManager.createQuery(queryStr);
      setQueryParams(query, params);
      paginar(paginacao, query);
      return query;
   }

   @Override
   public void delete(final Serializable id)
   {
      this.entityManager.remove(findById(id));
   }

   @Override
   public List<?> find(final String queryStr, final Object... params)
   {
      return find(queryStr, null, params);
   }

   @Override
   public List<?> find(final String queryStr, final Paginacao paginacao, final Object... params)
   {
      return createQuery(queryStr, paginacao, params).getResultList();
   }

   @Override
   public List<T> findAll()
   {
      return findAll(null);
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<T> findAll(final Paginacao paginacao)
   {
      final Query query =
         this.entityManager.createQuery(" FROM " + getPersistentClass().getSimpleName());
      paginar(paginacao, query);
      return query.getResultList();
   }

   @Override
   public T findById(final Serializable id)
   {
      return this.entityManager.find(getPersistentClass(), id);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<T> findByNamedParams(final String queryname, final Map<String, Object> params)
   {
      final Query query = this.entityManager.createNamedQuery(queryname);
      setQueryParams(query, params);
      return query.getResultList();
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<T> findByNamedParams(final String queryname, final Paginacao paginacao,
      final Map<String, Object> params)
   {
      final Query query = this.entityManager.createNamedQuery(queryname);
      setQueryParams(query, params);
      paginar(paginacao, query);
      return query.getResultList();
   }

   @Override
   public List<T> findByNamedQuery(final String namedQuery, final Object... params)
   {
      return findByNamedQuery(namedQuery, null, params);
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<T> findByNamedQuery(final String namedQuery, final Paginacao paginacao,
      final Object... params)
   {
      final Query query = this.entityManager.createNamedQuery(namedQuery);
      setQueryParams(query, params);
      paginar(paginacao, query);
      return query.getResultList();
   }

   @Override
   public List<T> findByNativeQuery(final String sql, final Object... params)
   {
      return findByNativeQuery(sql, null, params);
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<T> findByNativeQuery(final String sql, final Paginacao paginacao,
      final Object... params)
   {
      final Query query = this.entityManager.createNativeQuery(sql);
      setQueryParams(query, params);
      paginar(paginacao, query);
      return query.getResultList();
   }

   @Override
   public Object findSingleResult(final String queryStr, final Object... params)
   {
      return findSingleResult(queryStr, null, params);
   }

   @Override
   public Object findSingleResult(final String queryStr, final Paginacao paginacao,
      final Object... params)
   {
      return createQuery(queryStr, paginacao, params).getSingleResult();
   }

   public Class<T> getPersistentClass()
   {
      if (persistentClass == null)
      {
         throw new RuntimeException(
            "É necessário invocar o método setPersistentClass(Class<T> clazz)");
      }
      return persistentClass;
   }

   /**
    * Atribuir paginação a query.
    * @param paginacao
    * @param query
    */
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

   @Override
   public void refresh(final T entity)
   {
      this.entityManager.refresh(entity);
   }

   @Override
   public void save(final T obj)
   {
      this.entityManager.persist(obj);
   }

   @Override
   public void setPersistentClass(final Class<T> persistentClass)
   {
      this.persistentClass = persistentClass;
   }

   /**
    * Atribuir os parâmetros nomeados da query.
    * @param query
    * @param params
    */
   private void setQueryParams(final Query query, final Map<String, Object> params)
   {
      for (final Entry<String, Object> entry : params.entrySet())
      {
         query.setParameter(entry.getKey(), entry.getValue());
      }
   }

   /**
    * Atribuir os parâmetros da query.
    * @param query
    * @param params
    */
   private void setQueryParams(final Query query, final Object... params)
   {
      if (params != null && params.length > 0)
      {
         int i = 0;
         for (final Object param : params)
         {
            if (param != null)
            {
               if ((param instanceof String && ((String) param).trim().isEmpty()))
               {
                  continue;
               }
               i++;
               query.setParameter(i, param);
            }
         }
      }
   }

   @Override
   public void update(final T obj)
   {
      this.entityManager.merge(obj);
   }

}
