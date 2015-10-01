package br.com.buratiero.dao;

import java.util.Map;

import br.com.buratiero.dao.to.Entidade;

/**
 * DOCUMENT ME!.
 * @author ElianaMaria
 * @param <E> tipo de elemento
 */
public interface ScpDao<E extends Entidade> extends GenericoDao<E>
{

   /**
    * Consultar unico registro.
    * @param queryStr query str
    * @param params params
    * @return object
    */
   Object consultarUnicoRegistro(final String queryStr, final Map<String, Object> params);
}
