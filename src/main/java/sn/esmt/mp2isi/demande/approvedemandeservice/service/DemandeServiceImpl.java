package sn.esmt.mp2isi.demande.approvedemandeservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sn.esmt.mp2isi.demande.approvedemandeservice.controller.ResponseWrapper;
import sn.esmt.mp2isi.demande.approvedemandeservice.model.Account;
import sn.esmt.mp2isi.demande.approvedemandeservice.model.Demande;
import sn.esmt.mp2isi.demande.approvedemandeservice.model.Status;
import sn.esmt.mp2isi.demande.approvedemandeservice.repository.AccountRepository;
import sn.esmt.mp2isi.demande.approvedemandeservice.repository.DemandeRepository;

import java.util.Optional;


@Service
public class DemandeServiceImpl implements DemandeService {

    private final DemandeRepository demandeRepository;

    private final AccountRepository accountRepository;

    public DemandeServiceImpl(DemandeRepository demandeRepository, AccountRepository accountRepository) {
        this.demandeRepository = demandeRepository;
        this.accountRepository = accountRepository;
    }


    @Override
    public ResponseWrapper approve(String accountNumber) {
        Optional<Demande> optionalDemande = demandeRepository.findTopByAccountNumberAndStatus(accountNumber, Status.TRAITEMENT_EN_COURS);
        if(optionalDemande.isPresent()) {
            Demande demande = optionalDemande.get();
            Optional<Account> optionalAccount = accountRepository.findTopByNumeroIgnoreCase(accountNumber);
            if(optionalAccount.isPresent()) {
                Account account = optionalAccount.get();
                if(account.getSolvabilite() >= 0.8) {
                  demande.setStatus(Status.TRAITEMENT_VALIDE);
                  demandeRepository.save(demande);
                  return new ResponseWrapper(HttpStatus.OK.value(), "Approved request");
                } else {
                    demande.setStatus(Status.TRAITEMENT_REJETE);
                    demandeRepository.save(demande);
                    return new ResponseWrapper(HttpStatus.NOT_ACCEPTABLE.value(), "Rejected request. Solvency issues");
                }
            } else {
                demande.setStatus(Status.TRAITEMENT_REJETE);
                demandeRepository.save(demande);
                return new ResponseWrapper(HttpStatus.NOT_FOUND.value(), "Rejected request. Account not found");
            }
        } else {
            return new ResponseWrapper(HttpStatus.NOT_FOUND.value(), "Rejected request. Request not found");
        }

    }
}
