package com.example.glow.service;

import com.example.glow.entity.postgreSql.ApplicationsResource;
import com.example.glow.repository.postgreSqlRep.LoanDataResourceCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuarantorDataService {

    private LoanDataResourceCrudRepository loanDataResourceCrudRepository;

    @Autowired
    public GuarantorDataService(LoanDataResourceCrudRepository loanDataResourceCrudRepository){
        this.loanDataResourceCrudRepository = loanDataResourceCrudRepository;
    }

    public List<ApplicationsResource> getAll(){
        List<ApplicationsResource> res = new ArrayList<>();
        loanDataResourceCrudRepository.findAll().forEach(res::add);
        return res;
    }

}
