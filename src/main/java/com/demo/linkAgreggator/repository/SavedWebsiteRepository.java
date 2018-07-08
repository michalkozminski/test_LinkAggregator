package com.demo.linkAgreggator.repository;

import com.demo.linkAgreggator.model.SavedWebsite;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedWebsiteRepository extends ReactiveMongoRepository<SavedWebsite, String> {

}
