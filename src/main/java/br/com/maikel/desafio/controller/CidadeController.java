package br.com.maikel.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.maikel.desafio.entities.Cidade;
import br.com.maikel.desafio.services.CidadesService;
import br.com.maikel.desafio.services.RestClientService;

@Controller
@RequestMapping(value="/v1/cidades", produces=MediaType.APPLICATION_JSON_VALUE)
public class CidadeController {

	private @Autowired CidadesService cidadeService;
	private @Autowired RestClientService restService;

	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public List<Cidade> getCidades(){
		return cidadeService.getCidades();
	}

	@ResponseBody
	@RequestMapping(value="/count", method=RequestMethod.GET)
	public Long getTotalCidades(){
		return cidadeService.getTotalCidades();
	}

	@ResponseBody
	@ResponseStatus(value=HttpStatus.CREATED)
	@RequestMapping(method=RequestMethod.POST, consumes={"application/json"})
	public void add(@RequestBody Cidade cidade){
		cidadeService.salva(cidade);
	}

	@ResponseBody
	@RequestMapping(value="/capitais", method=RequestMethod.GET)
	public List<Cidade> getCapitais(){
		return cidadeService.getCapitais();
	}

	@ResponseBody
	@RequestMapping(value="/extremas", method=RequestMethod.GET)
	public String getCidadesMaisExtremas() throws Exception{
		return restService.getDistancias();
	}

	@ResponseBody
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	@RequestMapping(value="/{ibge}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("ibge") Long ibge){
		cidadeService.delete(ibge);
	}

	@ResponseBody
	@RequestMapping(value="/ibge/{ibge}", method=RequestMethod.GET)
	public Cidade getCidadeByIbge(@PathVariable("ibge") Long id){
		return cidadeService.getCidadeByIbge(id);
	}

	@ResponseBody
	@RequestMapping(value="/uf/{uf}",method=RequestMethod.GET)
	public List<Cidade> getCidadesByUf(@PathVariable("uf") String uf){
		return cidadeService.getCidadesByUf(uf);
	}

	@ResponseBody
	@RequestMapping(value="/uf/{uf}/count",method=RequestMethod.GET)
	public Long getCountCidadesByUf(@PathVariable("uf") String uf){
		return cidadeService.getCountCidadesByUf(uf);
	}

	@ResponseBody
	@RequestMapping(value="/uf/maior", method=RequestMethod.GET)
	public String getMaiorUf(){
		return cidadeService.getMaiorUf();
	}

	@ResponseBody
	@RequestMapping(value="/uf/menor", method=RequestMethod.GET)
	public String getMenorUf(){
		return cidadeService.getMenorUf();
	}

	@ResponseBody
	@RequestMapping(value="/uf/extremos", method=RequestMethod.GET)
	public String getExtremos(){
		return cidadeService.getExtremos();
	}

	@ResponseBody
	@RequestMapping(value="/csv/{coluna}/{valor}", method=RequestMethod.GET)
	public List<Cidade> getCidadesByColunaCsv(@PathVariable("coluna") String coluna, @PathVariable("valor") String valor){
		return cidadeService.getCidadesByColunaCsv(coluna, valor);
	}

	@ResponseBody
	@RequestMapping(value="/csv/{coluna}/count", method=RequestMethod.GET)
	public Integer getCountByColunaCsv(@PathVariable("coluna") String coluna){
		return cidadeService.getCountByColunaCsv(coluna);
	}
}
