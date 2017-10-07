package br.com.maikel.desafio.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.maikel.desafio.config.MorphiaConnection;
import br.com.maikel.desafio.dao.CidadeDao;
import br.com.maikel.desafio.entities.Cidade;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

@Component
public class CidadesService {

	private @Autowired MorphiaConnection morphiaConnection;

	private CidadeDao dao;

	@PostConstruct
	public void config(){
		this.dao = new CidadeDao(morphiaConnection.getDatastore());
	}

	public List<Cidade> getCidades(){
		return dao.createQuery().asList();
	}

	public Cidade getCidadeByIbge(Long id){
		return dao.createQuery().field("ibge_id").equal(id).get();
	}

	public List<Cidade> getCapitais(){
		return dao.createQuery().field("capital").equal("true").asList();
	}

	public List<Cidade> getCidadesByUf(String uf){
		return dao.createQuery().field("uf").equalIgnoreCase(uf).asList();
	}

	public Long getCountCidadesByUf(String uf){
		return dao.createQuery().field("uf").equalIgnoreCase(uf).count();
	}

	public Long getTotalCidades(){
		return dao.getTotalCidades();
	}

	public void salva(Cidade cidade){
		dao.salva(cidade);
	}

	public void delete(Long ibge){
		dao.deleteByQuery(dao.createQuery().field("ibge_id").equal(ibge));
	}

	public Integer getCountByColunaCsv(String coluna){
		return dao.getCollection().distinct(coluna.toLowerCase()).size();
	}

	public List<Cidade> getCidadesByColunaCsv(String coluna, String valor){
		return dao.createQuery().field(coluna.toLowerCase()).equalIgnoreCase(valor).asList();
	}

	public String getMaiorUf(){
		AggregationOutput out = dao.getCollection().aggregate(createBuilder(-1));
		return out.results().iterator().next().toString();
	}

	public String getMenorUf(){
		AggregationOutput out = dao.getCollection().aggregate(createBuilder(1));
		return out.results().iterator().next().toString();
	}

	private List<DBObject> createBuilder(Integer orderOpt){
		BasicDBObjectBuilder builder = new BasicDBObjectBuilder();
		builder.push("$group");
		builder.add("_id", "$uf");
		builder.push("total");
		builder.add("$sum", 1);
		builder.pop();
		builder.pop();

		BasicDBObjectBuilder sort = new BasicDBObjectBuilder();
		sort.push("$sort");
		sort.add("total", orderOpt);
		sort.pop();

		List<DBObject> lista = new ArrayList<DBObject>();
		lista.add(builder.get());
		lista.add(sort.get());

		return lista;
	}

	public String getExtremos(){
		StringBuilder ext = new StringBuilder();
		ext.append(getMaiorUf());
		ext.append(",");
		ext.append(getMenorUf());
		return ext.toString();
	}
}
