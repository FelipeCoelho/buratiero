package br.com.buratiero.pages.cadastroprocessos;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.buratiero.pages.inicial.FormularioBase;

public abstract class ModalConfirmacaoPanel extends Panel
{

   private static final long serialVersionUID = -3305704636452137045L;

   private FormularioBase<Void> formulario;

   public ModalConfirmacaoPanel(String id)
   {
      super(id);
      criarFormulario();
      criarBotao();
   }

   private void criarBotao()
   {
      formulario.addOrReplace(new AjaxButton("btnSim")
      {

         private static final long serialVersionUID = -7963002192378684515L;

         @Override
         protected void onSubmit(AjaxRequestTarget target, Form<?> form)
         {
            confirmaCriar(target, form);
         }
      });

      formulario.addOrReplace(new AjaxButton("btnNao")
      {

         private static final long serialVersionUID = -3975579360803287554L;

         @Override
         protected void onSubmit(AjaxRequestTarget target, Form<?> form)
         {
            naoConfirmaNao(target, form);
         }
      });

   }

   private void criarFormulario()
   {
      formulario = new FormularioBase<Void>("formularioModal");
      formulario.setOutputMarkupPlaceholderTag(true);
      addOrReplace(formulario);

   }

   protected abstract void confirmaCriar(AjaxRequestTarget target, Form<?> form);

   protected abstract void naoConfirmaNao(AjaxRequestTarget target, Form<?> form);

}
