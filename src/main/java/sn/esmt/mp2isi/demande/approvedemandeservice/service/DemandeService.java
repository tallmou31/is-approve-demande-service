package sn.esmt.mp2isi.demande.approvedemandeservice.service;


import sn.esmt.mp2isi.demande.approvedemandeservice.controller.ResponseWrapper;

public interface DemandeService {
    ResponseWrapper approve(String accountNumber);
}
