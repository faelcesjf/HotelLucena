package br.cesjf.hotellucena.dao;

import br.cesjf.hotellucena.model.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.cesjf.hotellucena.util.PersistenceUtil;
import javax.persistence.NoResultException;

public class UsuariosDAO {

    
    private PersistenceUtil persitence;
    public static UsuariosDAO usuarioDAO;

    
    public UsuariosDAO(){
        this.persitence = new PersistenceUtil();
    }
    public static UsuariosDAO getInstance() {
        if (usuarioDAO == null) {
            usuarioDAO = new UsuariosDAO();
        }
   
        return usuarioDAO;
    }
    
    public Usuarios getUsuario(String cpf, String password) {
 
      try {
        Usuarios usuario = (Usuarios)this.persitence.getEntityManager()
         .createQuery(
             "SELECT u from Usuarios u where u.cpf =" +  
             ":cpf and u.password = :password")
         .setParameter("cpf", cpf)
         .setParameter("password", password).getSingleResult();
 
        return usuario;
      } catch (NoResultException e) {
            return null;
      }
    }

    public Usuarios buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Usuarios a where a.name =:nome ");
        query.setParameter("nome", nome);

        List<Usuarios> usuarios = query.getResultList();
        if (usuarios != null && usuarios.size() > 0) {
            return usuarios.get(0);
        }

        return null;
    }

    public List<Usuarios> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Usuarios As a");
        return query.getResultList();
    }

    public void remover(Usuarios usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(usuario)) {
            usuario = em.merge(usuario);
        }
        em.remove(usuario);
        em.getTransaction().commit();
    }

    public Usuarios persistir(Usuarios usuario) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            usuario = em.merge(usuario);
            em.getTransaction().commit();
            System.out.println("Registro Usuarios gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void removeAll() {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(" delete from Usuarios ");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    private Object setParameter(String cpf, String cpf0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
