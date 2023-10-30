package sn.esmt.mp2isi.demande.approvedemandeservice.model;

import javax.persistence.*;

@Entity
@Table(name = "t_comptes")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;

    private double solvabilite;


    public Account(String numero, double solvabilite) {
        this.numero = numero;
        this.solvabilite = solvabilite;
    }

    public Account() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSolvabilite() {
        return solvabilite;
    }

    public void setSolvabilite(double solvabilite) {
        this.solvabilite = solvabilite;
    }
}
