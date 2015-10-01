package br.com.buratiero.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import br.com.buratiero.dao.GenericoDao;
import br.com.buratiero.dao.to.Entidade;
import br.com.buratiero.dao.to.Paginacao;

@SuppressWarnings("unchecked")
public abstract class GenericoDaoImpl<E extends Entidade> extends JpaDaoImpl<E> implements
   GenericoDao<E>
{

   private static final long serialVersionUID = 3207593623716337968L;

   @PersistenceContext
   private EntityManager entityManager;

   private HibernateTemplate hibernateTemplate = new HibernateTemplate();

   @Autowired
   private void instanciar()
   {
      try
      {
         final ParameterizedType tipo = (ParameterizedType) getClass().getGenericSuperclass();
         final Type[] parametro = tipo.getActualTypeArguments();
         Class<?> tipoGenerico = null;
         if (parametro != null && (0 < parametro.length))
         {
            tipoGenerico = (Class<?>) parametro[0];
            setPersistentClass((Class<E>) tipoGenerico);
            setHibernateTemplate(new HibernateTemplate(
               ((Session) entityManager.getDelegate()).getSessionFactory()));
         }
      }
      catch (final Exception e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public E obter(final Serializable id)
   {
      return findById(id);
   }

   @Override
   public List<E> consultar(final E entidade)
   {
      return getHibernateTemplate().findByExample(entidade);
   }

   @Override
   public List<E> listar()
   {
      return findAll();
   }

   @Override
   public void incluir(final E entidade)
   {
      save(entidade);
   }

   @Override
   public void alterar(final E entidade)
   {
      update(entidade);
   }

   @Override
   @Transactional(readOnly = false)
   public void excluir(final E entidade)
   {
      delete(entidade.getId());
   }

   @Override
   @Transactional(readOnly = false)
   public void excluirTodos(final List<E> entidades)
   {
      if (entidades != null && !entidades.isEmpty())
      {
         for (final E entidade : entidades)
         {
            excluir(entidade);
         }
      }
   }

   @Override
   public List<E> consultarPaginado(final String queryStr, final Paginacao paginacao,
      final Object... params)
   {
      final Query query = getEntityManager().createQuery(queryStr);
      setQueryParams(query, params);
      paginar(paginacao, query);
      return query.getResultList();
   }

   @Override
   public Object contadorPaginado(final String queryStr, final Object... params)
   {
      final Query query = getEntityManager().createQuery(queryStr);
      setQueryParams(query, params);
      return query.getSingleResult();
   }

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

   public EntityManager getEntityManager()
   {
      return entityManager;
   }

   public void setHibernateTemplate(final HibernateTemplate hibernateTemplate)
   {
      this.hibernateTemplate = hibernateTemplate;
   }

   public HibernateTemplate getHibernateTemplate()
   {
      return hibernateTemplate;
   }

}
