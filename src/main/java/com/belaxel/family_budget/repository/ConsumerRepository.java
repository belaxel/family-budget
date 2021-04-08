package com.belaxel.family_budget.repository;

import com.belaxel.family_budget.model.Consumer;
import org.springframework.data.repository.CrudRepository;

public interface ConsumerRepository extends CrudRepository<Consumer, Integer> {

    Consumer findByName(String name);

}
