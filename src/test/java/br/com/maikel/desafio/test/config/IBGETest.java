package br.com.maikel.desafio.test.config;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class IBGETest extends AppTest {

	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cidadeController)
                .build();
    }

	@Test
	public void getCidadesByIbgeRetorna200(){

		Mockito.when(cidadesServiceMock.getCidadeByIbge(2828282L)).thenReturn(createCidade());

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades/ibge/{ibge}", 2828282L))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(getJsonMediaType()));

		} catch (Exception e) {
			assertTrue("Não deveria lançar exceção ao pesquisar a cidade por código do IBGE.", true);
			e.printStackTrace();
		}
	}

	@Test
	public void getCidadesByIbgeComStringRetorna400(){

		Mockito.when(cidadesServiceMock.getCidadeByIbge(2828282L)).thenReturn(createCidade());

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades/ibge/{ibge}", "2828282L"))
					.andExpect(MockMvcResultMatchers.status().is4xxClientError());

		} catch (Exception e) {
			assertTrue("Não deveria lançar exceção ao pesquisar a cidade por código do IBGE.", true);
			e.printStackTrace();
		}
	}
}
