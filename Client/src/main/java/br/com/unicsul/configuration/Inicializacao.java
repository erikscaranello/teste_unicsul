package br.com.unicsul.configuration;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;

public class Inicializacao implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		ServletRegistration.Dynamic facesServlet = container.addServlet("Faces Servlet", FacesServlet.class);
		facesServlet.setLoadOnStartup(1);
		facesServlet.addMapping("*.xhtml");
		
	    }

}
