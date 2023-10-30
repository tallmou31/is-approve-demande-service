package sn.esmt.mp2isi.demande.approvedemandeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.esmt.mp2isi.demande.approvedemandeservice.model.Demande;
import sn.esmt.mp2isi.demande.approvedemandeservice.model.Status;

import java.util.Optional;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
    Optional<Demande> findTopByAccountNumberAndStatus(String accountNumber, Status status);
}
