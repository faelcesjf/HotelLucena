package br.cesjf.hotellucena.controller;

import br.cesjf.hotellucena.dao.ItensDAO;
import br.cesjf.hotellucena.model.Item;
import br.cesjf.hotellucena.model.Usuarios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "itensBean")
@ViewScoped
public class ItensBean {
    
    
    Item item = new Item();

    List itens = new ArrayList();

    //construtor
    public ItensBean() {
       
        itens = new ItensDAO().buscarTodas();
        item = new Item();
    }
    public void checkAlreadyLoggedin(){
        
        Usuarios usuario = (Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        if(usuario == null){
            try {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ItensBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }
    //Métodos dos botões 
    public void record(ActionEvent actionEvent) {
        new ItensDAO().persistir(item);
        itens = new ItensDAO().buscarTodas();
        item = new Item();
    }

    public void exclude(ActionEvent actionEvent) {
        new ItensDAO().remover(item);
        itens = new ItensDAO().buscarTodas();
        item = new Item();
    }

    //getters and setters
    public Item getItem() {
        return this.item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List getItens() {
        return this.itens;
    }

    public void setItens(List itens) {
        this.itens = itens;
    }

    
}
