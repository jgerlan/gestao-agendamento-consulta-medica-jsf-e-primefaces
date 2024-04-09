package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

	public static List<Agenda> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select a from Agenda a");
		List<Agenda> resultado = q.getResultList();
		em.close();
		return resultado;
	}

	public static Agenda buscarAgendaPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		try {
			Agenda agenda = em.find(Agenda.class, id);
			return agenda;
		} finally {
			em.close();
		}
	}

	public static int contarAgendamentos() {
		EntityManager em = JPAUtil.criarEntityManager();
		try {
			Query q = em.createQuery("SELECT COUNT(a) FROM Agenda a");
			long resultado = (long) q.getSingleResult();
			return (int) resultado;
		} finally {
			em.close();
		}
	}
	
	public static List<Agenda> verificarAgendamentoExistente(Date dataHora, String medico) {
        EntityManager em = JPAUtil.criarEntityManager();
        try {
            String jpql = "SELECT a FROM Agenda a WHERE a.dataHoraConsulta = :dataHora AND a.medico = :medico";
            TypedQuery<Agenda> query = em.createQuery(jpql, Agenda.class);
            query.setParameter("dataHora", dataHora);
            query.setParameter("medico", medico);
            List<Agenda> resultado = query.getResultList();
            
            return resultado;
        } finally {
            em.close();
        }
    }
}
