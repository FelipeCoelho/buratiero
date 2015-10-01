package br.com.buratiero.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.buratiero.dao.to.Paginacao;

public interface JpaDao<T> extends Serializable
{

   void delete(final Serializable id);

   List<?> find(final String queryStr, final Object... params);

   List<?> find(final String queryStr, final Paginacao paginacao, final Object... params);

   List<T> findAll();

   List<T> findAll(final Paginacao paginacao);

   T findById(final Serializable id);

   List<T> findByNamedParams(final String queryname, final Map<String, Object> params);

   List<T> findByNamedParams(final String queryname, final Paginacao paginacao,
      final Map<String, Object> params);

   List<T> findByNamedQuery(final String namedQuery, final Object... params);

   List<T> findByNamedQuery(final String namedQuery, final Paginacao paginacao,
      final Object... params);

   List<T> findByNativeQuery(final String sql, final Object... params);

   List<T> findByNativeQuery(final String sql, final Paginacao paginacao, final Object... params);

   Object findSingleResult(final String queryStr, final Object... params);

   Object findSingleResult(final String queryStr, final Paginacao paginacao, final Object... params);

   void refresh(final T entity);

   void save(final T obj);

   void setPersistentClass(final Class<T> persistentClass);

   void update(final T obj);

}
