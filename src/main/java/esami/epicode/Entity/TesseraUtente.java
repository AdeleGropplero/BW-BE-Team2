package esami.epicode.Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tessere")
public class TesseraUtente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private LocalDate data_di_nascita;
    private LocalDate data_attivazione;
    private LocalDate scadenza;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tesseraUtente")
    private Abbonamento abbonamento;

    public TesseraUtente() {
    }

    public TesseraUtente(String nome, String cognome, LocalDate data_di_nascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.data_di_nascita = data_di_nascita;
        this.data_attivazione = LocalDate.now();
        this.scadenza = data_attivazione.plusDays(365);
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

    public LocalDate getData_attivazione() {
        return data_attivazione;
    }

    public void setData_attivazione(LocalDate data_attivazione) {
        this.data_attivazione = data_attivazione;
    }

    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }

    @Override
    public String toString() {
        return "TesseraUtente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", data_di_nascita=" + data_di_nascita +
                ", data_attivazione=" + data_attivazione +
                ", scadenza=" + scadenza +
                ", abbonamento=" + abbonamento +
                '}';
    }
}
