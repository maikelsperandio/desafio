package br.com.maikel.desafio.dao;

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

	public Long getTotalCidades(){
		return createQuery().count();
	}

	public void salva(Cidade cidade){
		Key<Cidade> key = save(cidade);
		System.out.println(key);
	}
}
