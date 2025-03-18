package com.fiap.bank.repository;

import com.fiap.bank.bean.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long>, JpaSpecificationExecutor<AccountModel> {

     Optional<AccountModel> findByCpf(String cpf);
}
