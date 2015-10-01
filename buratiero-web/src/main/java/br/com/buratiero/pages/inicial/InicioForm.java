package br.com.buratiero.pages.inicial;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import br.com.buratiero.pages.cadastroprocessos.CadastroProcessoPage;
import br.com.buratiero.pages.pesquisaprocessos.PesquisaProcessoPage;

public class InicioForm extends FormularioBase<Void>
{
   private static final long serialVersionUID = -7766315043149288671L;

   private FeedbackPanel feedBack;

   public InicioForm(String id)
   {
      super(id);
      criarCampos();
   }

   private void criarCampos()
   {
      criarCampoFeedBack();
      criarLinkCadastroProcessos();
      criarLinkPesquisaProcessos();
   }

   private void criarCampoFeedBack()
   {
      feedBack = new FeedbackPanel(ID_FEEDBACK);
      feedBack.setOutputMarkupPlaceholderTag(true);
      addOrReplace(feedBack);
   }

   private void criarLinkCadastroProcessos()
   {
      addOrReplace(new Link<String>("linkCadastroProcessos")
      {
         private static final long serialVersionUID = -1077989950123069151L;

         @Override
         public void onClick()
         {
            setResponsePage(new CadastroProcessoPage());
         }
      });
   }

   private void criarLinkPesquisaProcessos()
   {
      addOrReplace(new Link<String>("linkPesquisaProcessos")
      {

         private static final long serialVersionUID = -1077989950123069151L;

         @Override
         public void onClick()
         {
            setResponsePage(new PesquisaProcessoPage());
         }
      });
   }
}
