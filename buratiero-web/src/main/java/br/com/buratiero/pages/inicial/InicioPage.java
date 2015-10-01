package br.com.buratiero.pages.inicial;

import org.wicketstuff.annotation.mount.MountPath;

@MountPath("principal")
public class InicioPage extends BasePage
{

   private static final long serialVersionUID = 8132433916132011070L;

   public InicioPage()
   {
      addOrReplace(new InicioForm(ID_FORMULARIO));
   }

}
