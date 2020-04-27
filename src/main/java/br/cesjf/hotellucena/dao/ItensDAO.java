package br.cesjf.hotellucena.dao;

import br.cesjf.hotellucena.model.Item;
import br.cesjf.hotellucena.model.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.cesjf.hotellucena.util.PersistenceUtil;

public class ItensDAO {

    public static ItensDAO itensDAO;

    public static ItensDAO getInstance() {
        if (itensDAO == null) {
            itensDAO = new ItensDAO();
        }
        return itensDAO;
    }

    public Item buscar(String nome) {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("select a from Itens a where a.nome =:nome ");
        query.setParameter("nome", nome);

        List<Item> itens = query.getResultList();
        if (itens != null && itens.size() > 0) {
            return itens.get(0);
        }

        return null;
    }

    public List<Item> buscarTodas() {
        EntityManager em = PersistenceUtil.getEntityManager();
        Query query = em.createQuery("from Item As i");
        return query.getResultList();
    }

    public void remover(Item item) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        if (!em.contains(item)) {
            item = em.merge(item);
        }
        em.remove(item);
        em.getTransaction().commit();
    }

    public Item persistir(Item item) {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            item = em.merge(item);
            em.getTransaction().commit();
            System.out.println("Registro Itens gravado com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public void removeAll() {
        EntityManager em = PersistenceUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(" delete from Item ");
        query.executeUpdate();
        em.getTransaction().commit();
    }

}
