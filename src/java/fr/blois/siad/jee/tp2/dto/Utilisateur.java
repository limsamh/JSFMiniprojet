package fr.blois.siad.jee.tp2.dto;

import java.io.Serializable;
import java.util.Date;
/**
 * Classe définissant l'objet métier Utilisateur
 * @author Salim IGUE
 */
public class Utilisateur implements Serializable {
    
    private Integer id;
    private String email;
    private String motDePasse;
    private String nom;
    private Date dateInscription;
    private Boolean blocage;
    /**
     * Constructeur sans paramètre
     */
    public Utilisateur() {
    }
    /**
     * Constructeur avec paramètre
     * @param id l'identifiant autogénéré de l'utilisateur
     * @param email l'email de l'utilisateur
     * @param motDePasse le mot de passe de l'utilisateur
     * @param nom le nom de l'utilisateur
     * @param dateInscription la date d'inscription de l'utilisateur
     * @param blocage booléen permet de savoir si un utilisateur est bloqué ou non
     */
    public Utilisateur(Integer id, String email, String motDePasse, String nom, Date dateInscription, Boolean blocage) {
        this.id = id;
        this.email = email;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.dateInscription = dateInscription;
        this.blocage = blocage;
    }
    /**
     *
     * @return le booléen blocage
     */
    public Boolean getBlocage() {
        return blocage;
    }
    /**
     * Méthode permettant de modifier le booléen blocage
     * @param blocage le blocage
     */
    public void setBlocage(Boolean blocage) {
        this.blocage = blocage;
    }
    /**
     *
     * @return l'identifiant utilisateur
     */
    public Integer getId() {
        return id;
    }
    /**
     * Méthode permettant de modifier l'identifiant
     * @param id l'identifiant
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * Méthode permettant de modifier l'email
     * @param email l'email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     *
     * @return l'email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Méthode permettant de modifier le mot de passe
     * @param motDePasse  le mot de passe
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    /**
     *
     * @return le mot de passe
     */
    public String getMotDePasse() {
        return motDePasse;
    }
    /**
     * Méthode permettant de modifier le nom d'utilisateur
     * @param nom le nom de l'utilisateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     *
     * @return le nom de l'utilisateur
     */
    public String getNom() {
        return nom;
    }
    /**
     *
     * @return la date d'inscription
     */
    public Date getDateInscription() {
        return dateInscription;
    }
    /**
     * Méthode permettant de modifier la date d'inscription
     * @param dateInscription la date d'inscription
     */
    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }
    
    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", email=" + email + ", motDePasse=" + motDePasse + ", nom=" + nom + ", dateInscription=" + dateInscription + ", blocage=" + blocage + '}';
    }
    
}
