/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.jfoenix.controls.JFXButton;
import entities.Application;
import entities.Offre;
import entities.Session;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import services.ServiceApplication;
import services.ServiceOffre;
import utils.Getlists;
import utils.OfrreSession;

/**
 * FXML Controller class
 *
 * @author Sadbo
 */
public class DetailsOffrePostulationController implements Initializable {

    @FXML
    private Text nom;
    @FXML
    private Text date;
    @FXML
    private Text id;
    @FXML
    private Text entreprise;
    @FXML
    private Text domaine;
    @FXML
    private Text skills;
    @FXML
    private Text nbr;
    @FXML
    private Text nbr1;
    @FXML
    private Text nbr2;
    @FXML
    private Text notel;
    @FXML
    private Text notec;
    @FXML
    private ImageView notecomp;
    @FXML
    private ImageView notelange;
    @FXML
    private ProgressIndicator ringprogress;
    @FXML
    private Text nom1;
    @FXML
    private Text nom11;
    @FXML
    private Text contrat;
    @FXML
    private Text salaire;
    @FXML
    private JFXButton postuler;
    @FXML
    private Text message;
    @FXML
    private ImageView img;
    
    private int nb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Parent root2;
        try {
            
           
            ServiceOffre o = new ServiceOffre();
            ServiceApplication a = new ServiceApplication ();
            for (Offre e: o.AfficherOffresByID(OfrreSession.getId())) {
                root2 = FXMLLoader.load(getClass().getResource("/fxml/Offre_Utilisateur.fxml"));

                nom.setText(e.getTitre());

                id.setText(Integer.toString(e.getId()));
                System.out.println(id.getText());

                nbr.setText(Integer.toString(a.NbrApplicationOffre(e.getId()))+ " Candidatures");
                nb = a.NbrApplicationOffre(e.getId());

                date.setText("Publié le "+e.getDate_publication());
                
                entreprise.setText(o.getEntrepriseByID(e));

                contrat.setText(o.getTypeDePosteByID(e));

                domaine.setText(o.getDomaineByID(e));
                
                salaire.setText(Integer.toString(e.getSalaire()));

//                Image i = new Image(e.getPhoto());
            //    img.setImage(i);
                
                skills.setText(o.getSkillByID(e.getSkill1_id())+ o.getSkillByID(e.getSkill2_id())+ o.getSkillByID(e.getSkill3_id()));
                
                
            }


        } catch (IOException ex) {
            Logger.getLogger(MesOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        Getlists g = new Getlists();
        notec.setText(Integer.toString((int) Math.ceil(3.33 * g.getNoteSkills(Session.getId(),OfrreSession.getId()))) +"/10");
        if (g.getNoteSkills(Session.getId(),OfrreSession.getId()) == 0)
            notecomp.setImage(new Image("/fxml/assets/error.png"));
        else
            notecomp.setImage(new Image("/fxml/assets/ok.png"));
        
        notel.setText(Integer.toString((int) Math.ceil(10 * g.getNoteLangues(Session.getId(),OfrreSession.getId())))+"/10");
        if (g.getNoteLangues(Session.getId(),OfrreSession.getId()) == 0)
            notelange.setImage(new Image("/fxml/assets/error.png"));
        else
            notelange.setImage(new Image("/fxml/assets/ok.png"));
        
        
        
        
        ringprogress.setProgress((g.getNoteSkills(Session.getId(),OfrreSession.getId())*4.87 + g.getNoteLangues(Session.getId(),OfrreSession.getId()))/(1 + 5*g.getSkillsOffre(OfrreSession.getId()).size()));
     
        if (g.sicandidater(OfrreSession.getId(), Session.getId()) > 0) {
   
                postuler.setVisible(false);
                message.setVisible(true);
        }
        
    }    

    @FXML
    private void postulerAction(ActionEvent event) {
        
        
        Application a = new Application(Session.getId(), OfrreSession.getId(), ringprogress.getProgress()*100);
        ServiceApplication sa = new ServiceApplication();
        sa.ajouterApplication(a);
        nbr.setText(nb+1+ " Candidatures");
                
        postuler.setVisible(false);
        message.setVisible(true);
    }
    
}
