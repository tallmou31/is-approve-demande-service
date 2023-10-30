package sn.esmt.mp2isi.demande.approvedemandeservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.esmt.mp2isi.demande.approvedemandeservice.service.DemandeService;


@RestController
@RequestMapping("/api/demande")
public class DemandeController {
    private final DemandeService demandeService;

    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> approve (@RequestBody DemandeRequest dto) {
        ResponseWrapper responseWrapper = demandeService.approve(dto.getNumero());
        return ResponseEntity.status(responseWrapper.getCode()).body(responseWrapper);
    }
}
