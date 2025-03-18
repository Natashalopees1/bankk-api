package com.fiap.bank.repository;

import com.fiap.bank.bean.model.AccountModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountEspecification {

    public static Specification<AccountModel> filterByParams(Map<String, String> params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (params.containsKey("accountId")) {
                predicates.add(criteriaBuilder.equal(root.get("id"), Long.parseLong(params.get("accountId"))));
            }
            if (params.containsKey("cpf")) {
                predicates.add(criteriaBuilder.equal(root.get("cpf"), params.get("cpf")));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
