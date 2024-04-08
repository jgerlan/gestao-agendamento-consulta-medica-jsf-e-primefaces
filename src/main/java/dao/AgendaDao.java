package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Agenda;
import util.JPAUtil;

public class AgendaDao {
	public static void salvar(Agenda Agenda) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(Agenda);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void atualizar(Agenda Agenda) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(Agenda);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void excluir(Agenda Agenda) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		Agenda = em.find(Agenda.class, Agenda.getId());
		em.remove(Agenda);
		em.getTransaction().commit();
		em.close();
	}
	
	public static List<Agenda> listar(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select g from Agenda g");
		List<Agenda> resultado = q.getResultList();
		em.close();
		return resultado;
	}
}
