package br.com.buratiero.pages.inicial;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage {

	private static final long serialVersionUID = 8468710917235827865L;

	public final static String ID_FORMULARIO = "formulario";

	public final static String ID_FEEDBACK = "feedback";

	public BasePage() {
	}

	@Override
	public void renderHead(final IHeaderResponse response) {
		/* Implementação css */
		criarCss(response);
		/* Implementação JavaScript Jquery */
		criarScriptsJs(response);
	}

	private void criarCss(final IHeaderResponse response) {
		response.render(CssHeaderItem.forUrl("comum/css/bootstrap.min.css"));
		response.render(CssHeaderItem.forUrl("comum/css/font-awesome.min.css"));
		response.render(CssHeaderItem.forUrl("comum/css/estilo.css"));
		response.render(CssHeaderItem.forUrl("comum/css/960.css"));
		response.render(CssHeaderItem.forUrl("comum/css/jquery.dataTables.min.css"));
		response.render(CssHeaderItem.forUrl("comum/css/jquery.mobile.custom.structure.css"));
		response.render(CssHeaderItem.forUrl("comum/css/style.css"));
	}

	private void criarScriptsJs(final IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forReference(getApplication().getJavaScriptLibrarySettings().getJQueryReference()));
		/*
		 * response.render(JavaScriptHeaderItem.forUrl(
		 * "comum/js/jquery-2.1.1.min.js"));
		 */
		response.render(JavaScriptHeaderItem.forUrl("comum/js/jquery.maskedinput.min.js"));

		response.render(JavaScriptHeaderItem.forUrl("comum/js/addTheme.js"));
		response.render(JavaScriptHeaderItem.forUrl("comum/js/bootstrap.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("comum/js/jquery.cycle.all.js"));
		response.render(JavaScriptHeaderItem.forUrl("comum/js/jquery.dataTables.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("comum/js/jquery.maskedinput.min.js"));
		response.render(JavaScriptHeaderItem.forUrl("comum/js/jquery.maskMoney.js"));
		response.render(JavaScriptHeaderItem.forUrl("comum/js/jQuery.ui.datepicker.js"));
		response.render(JavaScriptHeaderItem.forUrl("comum/js/jquery.ui.datepicker.mobile.js"));
		response.render(JavaScriptHeaderItem.forUrl("comum/js/relogio.js"));
	}
}
