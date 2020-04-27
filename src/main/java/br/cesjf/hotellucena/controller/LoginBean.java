/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.hotellucena.controller;

import br.cesjf.hotellucena.dao.UsuariosDAO;
import br.cesjf.hotellucena.model.Usuarios;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author rafael
 */

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean {
    
    
    Usuarios usuario = new Usuarios();

    
    public LoginBean() {
        this.usuario = new Usuarios();
    }

    
    public String logar() {
         
    this.usuario = new UsuariosDAO().getUsuario(usuario.getCpf(), usuario.getPassword());
    if (usuario == null) {
      this.usuario = new Usuarios();
      FacesContext.getCurrentInstance().addMessage(
         null,
         new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!",
           "Erro no Login!"));
      return null;
    } else {
        
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);

          return "/index";
    }
         
    
         
  }
    
    //getters and setters
    public Usuarios getUsuarios() {
        return usuario;
    }

    public void setUsuarios(Usuarios usuario) {
        this.usuario = usuario;
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
}
