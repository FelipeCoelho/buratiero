package br.com.buratiero.wicket;

import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.settings.IExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;
import br.com.buratiero.pages.error.ErroPage;
import br.com.buratiero.pages.inicial.InicioPage;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see wicket.myproject.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
	/** A constante VISAO. */
	public static final String VISAO = "visao";

	@Override
	public Class<InicioPage> getHomePage() {
		return InicioPage.class;
	}

	@Override
	public void init() {
		new AnnotatedMountScanner().scanPackage("br.com.buratiero.pages").mount(this);
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		Application.get().getMarkupSettings().setStripWicketTags(true);

		getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_EXCEPTION_PAGE);
		if (usesDeploymentConfig()) {
			getApplicationSettings().setInternalErrorPage(ErroPage.class);
			getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
		}
	}

}
