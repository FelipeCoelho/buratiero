package br.com.buratiero.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.buratiero.dao.ColaboradorDao;
import br.com.buratiero.dao.GenericoDao;
import br.com.buratiero.model.Colaborador;

@Service("colaboradorService")
public class ColaboradorService extends GenericoService<Colaborador>
{

   private static final long serialVersionUID = -6411040155335109755L;
   @Autowired
   @Qualifier("colaboradorDao")
   private ColaboradorDao colaboradorDao;

   @Override
   protected GenericoDao<Colaborador> getDao()
   {
      return colaboradorDao;
   }

   public List<String> recuperaNomeColaborador(String input)
   {
      return colaboradorDao.recuperaNomeColaborador(input);
   }

   public Colaborador recuperaColaboradorPorNome(String nome)
   {
      return colaboradorDao.recuperaColaboradorPorNome(nome);
   }

   @Transactional(propagation = Propagation.REQUIRES_NEW)
   public void salvaColaborador(Colaborador colaborador)
   {
      try
      {
         colaboradorDao.incluir(colaborador);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public boolean existeColaborador(Colaborador colaboradorSelecionado)
   {
      return colaboradorDao.existeColaborador(colaboradorSelecionado);
   }

}
