package br.com.buratiero.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import br.com.buratiero.dao.GenericoDao;
import br.com.buratiero.model.Cidade;
import br.com.buratiero.model.Estado;

@Service("cidadeService")
public class CidadeService extends GenericoService<Cidade>
{
   private static final long serialVersionUID = 8530219694849512721L;

   @Autowired
   @Qualifier("cidadeDao")
   private CidadeDao cidadeDao;

   @Override
   protected GenericoDao<Cidade> getDao()
   {
      return cidadeDao;
   }

   public List<String> recuperaNomeCidade(String input, Estado estado)
   {
      return cidadeDao.recuperaNomeCidade(input,estado);
   }

   public Cidade recuperaCidadePorUfNome(String nome, Estado estado)
   {
      return cidadeDao.recuperaCidadePorUfNome(nome, estado);
   }



}
