
package com.tcs.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tcs.entity.Loan;
import com.tcs.exception.*;
import com.tcs.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository repo;

    public Loan createLoan(Loan loan) {
        if (loan.getLoanAmount() <= 0 || loan.getLoanAmount() > 5000000) {
            throw new InvalidLoanAmountException("Loan amount must be between 1 and 5000000");
        }

        repo.findByApplicantNameAndStatus(loan.getApplicantName(), "PENDING")
            .ifPresent(l -> { 
            	throw new DuplicateLoanApplicationException("There is already a pending loan"); 
            	}
            );

        loan.setStatus("PENDING");
        return repo.save(loan);
    }

    public List<Loan> getAllLoans() {
        return repo.findAll();
    }

    public Loan getLoanById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new LoanNotFoundException("There is no loan by this id"));
    }

    public Loan updateLoanStatus(Long id, String status) {
        Loan loan = getLoanById(id);
        loan.setStatus(status);
        return repo.save(loan);
    }
}
