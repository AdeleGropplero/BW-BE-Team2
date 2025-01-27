package esami.epicode.Entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private LocalDate data_di_nascita;
    @OneToOne(mappedBy = "utente")
    private Tessera tessera;

    public Utente() {
    }

    public Utente(String cognome, String nome, LocalDate data_di_nascita, Tessera tessera) {
        this.cognome = cognome;
        this.nome = nome;
        this.data_di_nascita = data_di_nascita;
        this.tessera = tessera;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(LocalDate data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data_di_nascita=" + data_di_nascita +
                ", tessera=" + tessera +
                '}';
    }
}
