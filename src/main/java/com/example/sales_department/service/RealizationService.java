package com.example.sales_department.service;

import com.example.sales_department.entity.Realization;
import com.example.sales_department.repository.RealizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Service
public class RealizationService {
    @Autowired
    RealizationRepository realizationRepository;

    public List<Realization> findByAll(String status, LocalDate date, Long numberUPD, BigInteger inn){

        List<Realization> realizations = realizationRepository.findAll();
        for (int i = 0; i < realizations.size(); ){
            if (status != null){
                if(!realizations.get(i).getPaymentStatus().equals(status)){
                    realizations.remove(i);
                    continue;
                }
            }
            if (date != null){
                if (!realizations.get(i).getDate().equals(date)){
                    realizations.remove(i);
                    continue;
                }
            }
            if (numberUPD != null){
                if (!realizations.get(i).getUpdNumber().equals(numberUPD)){
                    realizations.remove(i);
                    continue;
                }
            }
            if (inn != null){
                if (!realizations.get(i).getIdOrder().getIdSpecification().getIdContract().getIdCustomer().getInn().equals(inn)){
                    realizations.remove(i);
                    continue;
                }
            }
            i++;
        }
        return realizations;
    }
}
