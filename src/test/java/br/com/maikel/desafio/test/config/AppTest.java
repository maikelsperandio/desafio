package br.com.maikel.desafio.test.config;

import java.nio.charset.Charset;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import br.com.maikel.desafio.config.AppConfig;
import br.com.maikel.desafio.controller.CidadeController;
import br.com.maikel.desafio.entities.Cidade;
import br.com.maikel.desafio.services.CidadesService;
import br.com.maikel.desafio.services.RestClientService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class})
@ImportResource({"classpath*:/config.xml"} )
@WebAppConfiguration
public class AppTest extends TestCase{

	private @Autowired CidadeController controller;
	private @Autowired CidadesService cidadeService;
	private @Autowired RestClientService restService;

	protected MockMvc mockMvc;

	protected @Mock CidadesService cidadesServiceMock;

	protected @InjectMocks CidadeController cidadeController;

	protected MediaType getJsonMediaType(){
		return new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
	}

	protected MediaType getJsonMediaTypeUTF8(){
		return new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	}

	protected Cidade createCidade(){
		Cidade city = new Cidade();
		city.setAlternativeNames("Goiania");
		city.setIbgeId(2828282L);
		city.setLatitude(-45.9632);
		city.setLongitude(-35.96321);
		city.setMesoRegiao("Goiania");
		city.setMicroRegiao("Centro de Goias");
		city.setName("Goiania");
		city.setNoAccents("Goiania");
		city.setUf("GO");
		return city;
	}

	@Test
	public void whenSpringContextInitializeNoException(){
		assertTrue("Contexto inicializado corretamente", true);
	}
}
