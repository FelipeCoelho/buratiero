package br.com.buratiero.pages.ui;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import br.com.buratiero.model.Processo;

public class FormularioBase<T> extends Form<T>
{
   private static final long serialVersionUID = -5262964465965255989L;

   private static String pathName;
   @SpringBean
   private Processo processoService;

   public FormularioBase(final String id, final IModel<T> model)
   {
      super(id, model);
      setOutputMarkupId(true);
      FormularioBase.setPathName("c:\\pictures");
   }

   public FormularioBase(final String id)
   {
      super(id);
   }

   public void atualizaTela(final AjaxRequestTarget target, final Component... component)
   {
      target.appendJavaScript("addScriptCss();");
      target.add(component);
   }

   public static String getPathName()
   {
      return FormularioBase.pathName;
   }

   public static void setPathName(final String pathName)
   {
      FormularioBase.pathName = pathName;
   }
}
