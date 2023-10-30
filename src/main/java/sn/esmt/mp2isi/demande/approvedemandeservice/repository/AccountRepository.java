package sn.esmt.mp2isi.demande.approvedemandeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.esmt.mp2isi.demande.approvedemandeservice.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findTopByNumeroIgnoreCase(String numero);
}
