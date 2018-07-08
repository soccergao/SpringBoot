package com.soccergao.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.soccergao.entity.mongo.CaseEntity;

@Repository
public interface CaseMongoRepository extends MongoRepository<CaseEntity, Long> {

}
