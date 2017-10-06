package br.com.maikel.desafio.test.config;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UFTest extends AppTest {

	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cidadeController)
                .build();
    }

	@Test
	public void getCidadesByUfRetorna200(){

		Mockito.when(cidadesServiceMock.getCidadesByUf("GO")).thenReturn(Arrays.asList(createCidade()));

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades/uf/{uf}", "GO"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(getJsonMediaType()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getCidadesByUfComNumeroRetorna200(){

		Mockito.when(cidadesServiceMock.getCidadesByUf("GO")).thenReturn(Arrays.asList(createCidade()));

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades/uf/{uf}", 55))
					.andExpect(MockMvcResultMatchers.status().isOk());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getCountCidadesByUfRetorna200(){

		Mockito.when(cidadesServiceMock.getCidadesByUf("GO")).thenReturn(Arrays.asList(createCidade()));

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades/uf/{uf}/count", "GO"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(getJsonMediaType()));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getMaiorUfRetorna200(){

		Mockito.when(cidadesServiceMock.getCidadesByUf("GO")).thenReturn(Arrays.asList(createCidade()));

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades//uf/maior"))
					.andExpect(MockMvcResultMatchers.status().isOk());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getMenorUfRetorna200(){

		Mockito.when(cidadesServiceMock.getCidadesByUf("GO")).thenReturn(Arrays.asList(createCidade()));

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades//uf/menor"))
					.andExpect(MockMvcResultMatchers.status().isOk());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getExtremosRetorna200(){

		Mockito.when(cidadesServiceMock.getCidadesByUf("GO")).thenReturn(Arrays.asList(createCidade()));

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/v1/cidades//uf/extremos"))
					.andExpect(MockMvcResultMatchers.status().isOk());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
