package com.Practice.myThirdProject.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.Practice.myThirdProject.Entity.ConfigJournal;

public interface ConfigJournalRepository extends MongoRepository<ConfigJournal, ObjectId>{

}
