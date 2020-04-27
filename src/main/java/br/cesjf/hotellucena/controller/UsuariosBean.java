/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.hotellucena.controller;

import br.cesjf.hotellucena.dao.UsuariosDAO;
import br.cesjf.hotellucena.model.Usuarios;
import java.util.ArrayList;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;

/**
 *
 * @author tassio
 */
@ManagedBean(name = "usuariosBean")
@ViewScoped
public class UsuariosBean {

    Usuarios usuario = new Usuarios();

    List usuarios = new ArrayList();
    

    //construtor
    public UsuariosBean() {
        usuarios = new UsuariosDAO().buscarTodas();
        usuario = new Usuarios();
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
        new UsuariosDAO().persistir(usuario);
        usuarios = new UsuariosDAO().buscarTodas();
        usuario = new Usuarios();
    }

    public void exclude(ActionEvent actionEvent) {
        new UsuariosDAO().remover(usuario);
        usuarios = new UsuariosDAO().buscarTodas();
        usuario = new Usuarios();
    }

    //getters and setters
    public Usuarios getUsuarios() {
        return usuario;
    }

    public void setUsuarios(Usuarios usuario) {
        this.usuario = usuario;
    }

    public List getUsuarioss() {
        return usuarios;
    }

    public void setUsuarioss(List usuarios) {
        this.usuarios = usuarios;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
            HSSFCell cell = header.getCell(i);

            cell.setCellStyle(cellStyle);
        }
    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4);

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //String logo = servletContext.getRealPath("") + File.separator + "resources" + File.separator + "demo" + File.separator + "images" + File.separator + "prime_logo.png";

        // pdf.add(Image.getInstance(logo));
    }
}
