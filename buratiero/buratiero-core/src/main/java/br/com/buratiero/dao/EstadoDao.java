package br.com.buratiero.dao;

import java.util.List;
import br.com.buratiero.model.Estado;

public interface EstadoDao extends ScpDao<Estado>
{

   List<String> recuperaUfEstado(String input);

   Estado recuperaEstadoPorUf(String uf);

   List<Estado> recuperaListaEstado();

}
