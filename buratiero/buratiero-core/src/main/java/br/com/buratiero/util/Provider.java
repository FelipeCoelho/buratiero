package br.com.buratiero.util;

import java.util.List;
import br.com.buratiero.dao.to.Entidade;

public interface Provider<T>
{
   List<T> buscaListaGrid(Entidade entidade, long first, long count, ParametrosOrdenacao ordernar);

   int contadorListaGrid(Entidade entidade);
}
