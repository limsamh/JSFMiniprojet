package fr.blois.siad.jee.tp2.beans;

import fr.blois.siad.jee.tp2.dto.Utilisateur;
import fr.blois.siad.jee.tp2.services.UtilisateurService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import org.primefaces.event.RowEditEvent;
/**
 * Backing bean permettant de gerer le comportement des facelets
 * @author Salim IGUE
 */
@ManagedBean
@ViewScoped
public class UtilisateursBean implements Serializable {
    
    @Column(unique = true)
    private String email;
    private String motDePasse;
    @Column(unique = true)
    private String nom;
    
    private Boolean blocage;
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
    private List<Utilisateur> userArrayList;
    
    private static final long serialVersionUID = 1L;
    
    @PostConstruct
    public void init() {
        userArrayList = this.getService().listerTous();
    }
    
    public UtilisateursBean() {
    }
    /**
     *
     * @return un UtilisateurService
     */
    private UtilisateurService getService() {
        try {
            UtilisateurService beanRemote = (UtilisateurService) new InitialContext().lookup("UtilisateurService");
            return beanRemote;
        } catch (NamingException ne) {
            System.err.println(ne);
        }
        return null;
    }
    /**
     *
     * @return l'email
     */
    public String getEmail() {
        return email;
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
     * @return le mot de passe
     */
    public String getMotDePasse() {
        return motDePasse;
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
    public String getNom() {
        return nom;
    }
    /**
     * Méthode permettant de modifier le nom d'utilisateur
     * @param nom le nom de l'utilisateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    /**
     * Méthode de vérification et d'ajout d'un utilisateur
     * @return un string représentant la page de redirection
     */
    public String checkAddUser() {
        
        // Validation
        try{
            FacesContext context = FacesContext.getCurrentInstance();
            if (email == null || email.isEmpty()) {
                context.addMessage(null, new FacesMessage("Email obligatoire"));
            }
            if (motDePasse == null || motDePasse.isEmpty()) {
                context.addMessage(null, new FacesMessage("Mot de Passe obligatoire"));
            }
            if (nom == null || nom.isEmpty()) {
                context.addMessage(null, new FacesMessage("Nom obligatoire"));
            }
            if (!context.getMessageList().isEmpty()) {
                return null;
            }
            
            // Cas nominal
            getService().ajouter(new Utilisateur(null, email, motDePasse, nom, new Date(),false));
        }catch (EJBException e)
        {
            FacesMessage message = new FacesMessage(" email ou nom d'utilisateur deja existant");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
        
        return "index";
    }
    /**
     * Méthode permettant de gérer le comportement lors de la modification du mot de passe
     * @param event l'évenement à traiter
     */
    public void onRowEdit(RowEditEvent event) {
        
        if((((Utilisateur)event.getObject()).getMotDePasse().length() < 8) ||
                ((Utilisateur)event.getObject()).getMotDePasse().isEmpty()){
            
            onRowCancel(event)  ;
            FacesMessage msg = new FacesMessage("Nouveau mot de passe invalide pour : ", ((Utilisateur)event.getObject()).getNom());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else{
            getService().modifier(((Utilisateur)event.getObject()).getId(), ((Utilisateur)event.getObject()).getMotDePasse() );
            
            FacesMessage msg = new FacesMessage("Utilisateur modifié : ", ((Utilisateur)event.getObject()).getNom());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    /**
     *
     * @return la liste d'utilisateurs
     */
    public List<Utilisateur> getUserArrayList() {
        return userArrayList;
    }
    /**
     * Méthode permettant de modifier la liste d'utilisateurs
     * @param userArrayList la liste d'utilisateur à modifier
     */
    public void setUserArrayList(List<Utilisateur> userArrayList) {
        this.userArrayList = userArrayList;
    }
    /**
     * Méthode permettant de traiter un comportement lors de l'annulation d'une modification de mot de passe
     * @param event l'évenement à traiter
     */
    public void onRowCancel(RowEditEvent event) {
        /*
        FacesMessage msg = new FacesMessage("Modification annulée", ((Utilisateur) event.getObject()).getNom());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        */
    }
    
    /**
     * Méthode permettant de supprimer un utilisateur à partir de son identifiant
     * @param id l'identifiant utilisateur
     * @return  un string représentant la page de redirection
     */
    public String supprimer(Integer id) {
        getService().supprimer(id);
        return "index";
    }
    
    /**
     * Méthode permettant de bloquer et débloquer un utilisateur
     * @param id l'identifiant utilisateur
     * @param blocage le booléen correspondant à l'information concernant le blocage
     * @return  un string représentant la page de redirection
     */
    public String bloquer(Integer id, Boolean blocage) {
        if(!blocage) {blocage = true;
        //on fai appel à la méthode correspondant
        getService().bloquer(id, blocage);
        }
        
        else{
            blocage = false ;
            getService().bloquer(id, blocage);
        }
        
        return "index";
    }
}
