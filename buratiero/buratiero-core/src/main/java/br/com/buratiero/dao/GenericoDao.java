package br.com.buratiero.dao;

import java.io.Serializable;
import java.util.List;

import br.com.buratiero.dao.to.Entidade;
import br.com.buratiero.dao.to.Paginacao;

public interface GenericoDao<E extends Entidade> extends JpaDao<E>
{

   List<E> consultarPaginado(final String queryStr, final Paginacao paginacao,
      final Object... params);

   Object contadorPaginado(final String queryStr, final Object... params);

   public E obter(final Serializable id);

   public List<E> consultar(final E entidade);

   public List<E> listar();

   public void incluir(final E entidade);

   public void excluir(final E entidade);

   public void alterar(final E entidade);

   public void excluirTodos(final List<E> entidades);

}
