/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ifpr.usuario.gerenciar;

import ifpr.usuario.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

/**
 *
 * @author paulojr
 */
public class GerenciarUsuario {
    
    private EntityManagerFactory emf;
    private EntityManager em;

    public GerenciarUsuario() {
        emf = Persistence.createEntityManagerFactory("xuxu");
        em = emf.createEntityManager();
    }
    
    public void salvar(Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }
    
    public void atualizar(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }
    
    public void remover(int id) {
        Usuario usuario = buscarUsuario(id);
        em.getTransaction().begin();
        em.remove(usuario);
        em.getTransaction().commit();
    }
    
    public List<Usuario> listar()
    {
        String hql = "from Usuario";
        Query query = em.createQuery(hql);
        return query.getResultList();
    }
    
    public List<Usuario> pesquisarPorNome(String nome)
    {
        // select * from tb_usuario u where nome like '%paulo%'
        String hql = "from Usuario u"
                + " where u.nome like(concat('%', :nome, '%'))";
        Query query = em.createQuery(hql);
        query.setParameter("nome", nome);
        return query.getResultList();
    }
    
    public Usuario buscarUsuario(int id)
    {
        return em.find(Usuario.class, id);
    }
    
}
