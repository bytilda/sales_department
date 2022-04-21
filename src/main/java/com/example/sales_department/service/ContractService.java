package com.example.sales_department.service;

import com.example.sales_department.entity.Contract;
import com.example.sales_department.entity.Customer;
import com.example.sales_department.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Service
public class ContractService {
    @Autowired
    ContractRepository contractRepository;

    public void add(Contract contract){
        contractRepository.save(contract);
    }

    public List<Contract> getAll(){
        return contractRepository.findAll();
    }

    public List<Contract> getAllCustomerId(Customer customerId) {return contractRepository.findAllByIdCustomer(customerId);}

    public List<Contract> findByAll(Long contractNumber, LocalDate conclusionDate, BigInteger inn, LocalDate validFrom, LocalDate validUntil, String city){
        List<Contract> contracts = contractRepository.findAll();

        for (int i = 0; i < contracts.size(); ){
            if (contractNumber != null){
                if (!contracts.get(i).getContractNumber().equals(contractNumber)){
                    contracts.remove(i);
                    continue;
                }
            }
            if (conclusionDate != null){
                if (!contracts.get(i).getConclusionDate().equals(conclusionDate)){
                    contracts.remove(i);
                    continue;
                }
            }
            if (inn != null){
                if (!contracts.get(i).getIdCustomer().getInn().equals(inn)) {
                    contracts.remove(i);
                    continue;
                }
            }
            if (validFrom != null){
                if (!contracts.get(i).getValidFrom().equals(validFrom)){
                    contracts.remove(i);
                    continue;
                }

            }
            if (validUntil != null){
                if (!contracts.get(i).getValidUntil().equals(validUntil)){
                    contracts.remove(i);
                    continue;
                }
            }
            if (city != null){
                if (!contracts.get(i).getCity().equals(city)){
                    contracts.remove(i);
                    continue;
                }
            }

            i++;
        }
        return contracts;
    }
}
