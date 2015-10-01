package br.com.buratiero.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import br.com.buratiero.dao.EstadoDao;
import br.com.buratiero.dao.GenericoDao;
import br.com.buratiero.model.Estado;

@Service("estadoService")
public class EstadoService extends GenericoService<Estado>
{

   private static final long serialVersionUID = -7729341570712454296L;

   @Autowired
   @Qualifier("estadoDao")
   private EstadoDao estadoDao;

   @Override
   protected GenericoDao<Estado> getDao()
   {
      return estadoDao;
   }

   public List<String> recuperaUfEstado(String input)
   {
      return estadoDao.recuperaUfEstado(input);
   }

   public Estado recuperaEstadoPorUf(String uf)
   {
      return estadoDao.recuperaEstadoPorUf(uf);
   }

   public List<Estado> recuperaListaEstado()
   {
      return estadoDao.recuperaListaEstado();
   }

}
