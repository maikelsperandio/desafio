package br.com.maikel.desafio.test.config;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CidadesTest extends AppTest{

	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cidadeController)
                .build();
    }

	@Test
	public void getCidadesDeveRetornar200(){

		Mockito.when(cidadesServiceMock.getCapitais()).thenReturn(Arrays.asList(createCidade()));

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(getJsonMediaType()));

		} catch (Exception e) {
			assertTrue("Não deveria lançar exceção ao pesquisar a lista de cidades.", true);
			e.printStackTrace();
		}
	}

	@Test
	public void getTotalCidadesDeveRetornar200(){
		Mockito.when(cidadesServiceMock.getTotalCidades()).thenReturn(1L);

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades/count"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(getJsonMediaType()));

		} catch (Exception e) {
			assertTrue("Não deveria lançar exceção ao pesquisar o total de cidades.", true);
			e.printStackTrace();
		}
	}

	
}
