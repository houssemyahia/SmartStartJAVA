/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entities.Forum;
import entities.Offre;
import java.util.List;
/**
 *
 * @author radhwen
 */
public interface IForumService {
    public void AjouterCommentaire(Forum f);
    public void SupprimerCommentaire(Forum f);
    public void ModifierCommentaire(Forum f);
    public int FindIdCommentaire( String comm);
    public String FindEmailId(int id);
    
}
