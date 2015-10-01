package br.com.buratiero.pages.cadastroprocessos;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;

public abstract class ModalConfirmacao extends ModalWindow
{

   private static final long serialVersionUID = 6828377239187688228L;

   public ModalConfirmacao(String id)
   {
      super(id);
      setTitle(new Model<String>("Confirma Exclusão"));
   }

   public void inicializar()
   {
      setContent(criarPanel());
      setWindowClosedCallback(new WindowClosedCallback()
      {

         private static final long serialVersionUID = -2361057068650530173L;

         @Override
         public void onClose(AjaxRequestTarget target)
         {
            close(target);
         }

      });
   }

   private Component criarPanel()
   {
      return new ModalConfirmacaoPanel(getContentId())
      {

         private static final long serialVersionUID = -1680547990884118185L;

         @Override
         protected void confirmaCriar(AjaxRequestTarget target, Form<?> form)
         {
            ModalConfirmacao.this.confirmaCriar(target, form);
         }

         @Override
         protected void naoConfirmaNao(AjaxRequestTarget target, Form<?> form)
         {
            close(target);
         }

      };
   }

   protected abstract void confirmaCriar(AjaxRequestTarget target, Form<?> form);

}
