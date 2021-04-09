package com.belaxel.family_budget.repository;

import com.belaxel.family_budget.model.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {

    Organization findByName(String name);

}
