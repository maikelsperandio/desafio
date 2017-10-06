package br.com.maikel.desafio.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;

import br.com.maikel.desafio.entities.Cidade;

public class CidadeDao extends BasicDAO<Cidade, ObjectId>{

	public CidadeDao(Datastore ds) {
		super(ds);
		ensureIndexes();
	}

	public List<Cidade> getCidades(){
		return createQuery().asList();
	}

	public Cidade getCidadeByIbge(Long id){
		return createQuery().field("ibge_id").equal(id).get();
	}

	public List<Cidade> getCapitais(){
		return createQuery().field("capital").equal("true").asList();
	}

	public List<Cidade> getCidadesByUf(String uf){
		return createQuery().field("uf").equalIgnoreCase(uf).asList();
	}

	public Long getCountCidadesByUf(String uf){
		return createQuery().field("uf").equalIgnoreCase(uf).count();
	}

	public Long getTotalCidades(){
		return createQuery().count();
	}

	public void salva(Cidade cidade){
		Key<Cidade> key = save(cidade);
		System.out.println(key);
	}
}
