package br.com.buratiero.pages.ui;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.util.resource.AbstractResourceStreamWriter;
import org.apache.wicket.util.time.Duration;

public abstract class AjaxDownload<T> extends AbstractAjaxBehavior {
	private static final long serialVersionUID = -136189200817835648L;
	private boolean antiCache;
	private T entidade;
	private String nomeArquivo;

	public AjaxDownload() {
		this(true);
	}

	public AjaxDownload(boolean antiCache) {
		this.antiCache = antiCache;
	}

	public AjaxDownload(String nomeArquivo) {
		this(nomeArquivo, true);
	}

	public AjaxDownload(String nomeArquivo, boolean antiCache) {
		this.antiCache = antiCache;
		this.nomeArquivo = nomeArquivo;
	}

	public void iniciar(AjaxRequestTarget target) {
		String url = getCallbackUrl().toString();
		if (antiCache) {
			url = url + (url.contains("?") ? "&" : "?");
			url = url + "antiCache=" + System.currentTimeMillis();
		}
		target.appendJavaScript("setTimeout(\"window.location.href='" + url + "'\", 100);");
	}

	public void iniciar(AjaxRequestTarget target, T entidade) {
		iniciar(target);
		setEntidade(entidade);
	}

	@Override
	public void onRequest() {
		ResourceStreamRequestHandler handler = new ResourceStreamRequestHandler(new AbstractResourceStreamWriter() {
			private static final long serialVersionUID = -4234161758492585332L;

			@Override
			public void write(OutputStream output) throws IOException {
				escrever(output);
			}
		}, getNomeArquivo());
		handler.setFileName(getNomeArquivo());
		handler.setContentDisposition(ContentDisposition.ATTACHMENT);
		if (antiCache) {
			handler.setCacheDuration(Duration.NONE);
		}
		getComponent().getRequestCycle().scheduleRequestHandlerAfterCurrent(handler);
	}

	protected abstract void escrever(OutputStream output) throws IOException;

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public T getEntidade() {
		return entidade;
	}

	public void setEntidade(T entidade) {
		this.entidade = entidade;
	}

}