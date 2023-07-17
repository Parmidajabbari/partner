package com.digiexpress.partner.repositories;

import com.digiexpress.partner.domains.Partner;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PartnerRepository extends MongoRepository<Partner, Long> {
    Boolean existsByDocument(String document);



    @Query("{ $and: [ { address : { $near : { $geometry : { type : ?0 , coordinates : [ ?1 , ?2 ] } } } }, { coverageArea : { $geoIntersects : { $geometry : { type : 'Point' , coordinates : [ ?0 , ?1 ] } } } } ] }")
    Optional<Partner> findByLocationNearAndCoverageAreaIntersecting(String type, Double X, Double Y);

}
