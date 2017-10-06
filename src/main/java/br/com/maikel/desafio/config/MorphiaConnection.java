package br.com.maikel.desafio.config;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MorphiaConnection {

    private String dbHost = "127.0.0.1";
    private Integer dbPort = 27017;
    private String dbName = "senior";
    private Datastore datastore;
    private MongoClient mongoClient;
    private Morphia morphia;

    public MorphiaConnection(String host, String name, Integer port) {
    	super();
    	this.dbHost = host;
    	this.dbName = name;
    	this.dbPort = port;
    }

    private MongoClient createMongoConnection() {
        if (mongoClient == null) {
            try {
            	mongoClient = new MongoClient(new ServerAddress(dbHost, dbPort));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mongoClient;
    }

    private void createMorphia(){
    	if(this.morphia == null){
    		this.morphia = new Morphia();
    	}
    }

    public Datastore getDatastore() {
    	this.createMorphia();
        if (datastore == null) {
            datastore = this.morphia.createDatastore(createMongoConnection(), dbName);
        }
        this.morphia.getMapper().getConverters().addConverter(new MorphiaDateConverter());
        return datastore;
    }

    public void closeMongoClient(){
    	this.datastore.getMongo().close();
    }
}
