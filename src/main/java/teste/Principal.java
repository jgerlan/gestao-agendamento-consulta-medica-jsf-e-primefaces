package teste;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import dao.AgendaDao;
import entidades.Agenda;

public class Principal {

	public static void main(String[] args) {
		/*
		 * Agenda g = new Agenda();
		 * 
		 * g.setMedico("Dr. Ciclano"); g.setPaciente("Fulano da Silva");
		 * 
		 * LocalDate localDate = LocalDate.of(2024, 4, 12); Date data =
		 * Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		 * g.setDataHoraConsulta(data);
		 * 
		 * AgendaDao.salvar(g);
		 */
		
		
		System.out.println(AgendaDao.contarAgendamentos());
		System.out.println(AgendaDao.buscarAgendaPorId(3));
		System.out.println(AgendaDao.listar());
	}

}
