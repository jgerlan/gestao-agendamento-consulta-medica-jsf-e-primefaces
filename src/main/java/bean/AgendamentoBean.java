package bean;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import dao.AgendaDao;
import entidades.Agenda;

@ManagedBean
@ViewScoped
public class AgendamentoBean {
	private Agenda agenda = new Agenda();
	private Date minDateTime;
	private List<Agenda> lista;
	private Map<String, String> medicos;
	private Integer qtdAgendamentos;

	@PostConstruct
	public void init() {
		Date today = new Date();
		minDateTime = new Date(today.getTime());

	}

	public String salvar() {
		List<Agenda> existentes = AgendaDao.verificarAgendamentoExistente(agenda.getDataHoraConsulta(), agenda.getMedico());
		boolean vazio = existentes.isEmpty();
		
		if(!vazio) {
			FacesMessage facesMessage = new FacesMessage("Esse horário para o médico "+ agenda.getMedico()+" está lotado.");
	        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	        
	        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	        PrimeFaces.current().ajax().update(Arrays.asList("formulario", "formulario:messages"));
	        
	        return null;
		}
		
		AgendaDao.salvar(agenda);
		agenda = new Agenda();
		return null;
	}

	public String atualizar() {
		List<Agenda> existentes = AgendaDao.verificarAgendamentoExistente(agenda.getDataHoraConsulta(), agenda.getMedico());
		boolean conflito = existentes.stream()
	            .anyMatch(agendamentoExistente -> agendamentoExistente.getId().equals(agenda.getId()));
		boolean vazio = existentes.isEmpty();
		boolean exibirModal = false;
		
		if(!vazio && !conflito) {
			FacesMessage facesMessage = new FacesMessage("Esse horário para o médico "+ agenda.getMedico()+" está lotado.");
	        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
	        
	        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	        PrimeFaces.current().ajax().update(Arrays.asList("form:formEdicao", "form:mensagens"));
	        
	        exibirModal = true;
			PrimeFaces.current().ajax().addCallbackParam("exibirModal", exibirModal);
	        
	        return null;
		}
		
		PrimeFaces.current().ajax().addCallbackParam("exibirModal", exibirModal);
		AgendaDao.atualizar(agenda);
		agenda = new Agenda();
		todosagendamentos();
		return null;
	}
	
	public String excluir(Agenda agenda) {
		AgendaDao.excluir(agenda);
		this.agenda = new Agenda();
		todosagendamentos();
		return null;
	}

	public void prepararEdicao(Integer id) {
		this.agenda = new Agenda();
		this.agenda = AgendaDao.buscarAgendaPorId(id);
	}
	
	public void prepararExibicao(Integer id) {
		this.agenda = new Agenda();
		this.agenda = AgendaDao.buscarAgendaPorId(id);
	}
	
	public void todosagendamentos() {
		this.lista = AgendaDao.listar();
	}
	
	public void buscarQtdAgendamentos() {
		this.qtdAgendamentos = null;
		this.qtdAgendamentos = AgendaDao.contarAgendamentos();
	}
	
	public List<Agenda> getLista() {
		if (lista == null) {
			lista = AgendaDao.listar();
		}
		return lista;
	}

	public void setLista(List<Agenda> lista) {
		this.lista = lista;
	}

	public Map<String, String> getMedicos() {
		if (medicos == null) {
			medicos = new HashMap<>();
			medicos.put("Dr. Fulano", "dr. fulano");
			medicos.put("Dr. Sicrano", "dr. sicrano");
			medicos.put("Dr. Beltrano", "dr. beltrano");
		}
		return medicos;
	}

	public void setMedicos(Map<String, String> medicos) {
		this.medicos = medicos;
	}

	public Date getMinDateTime() {
		return minDateTime;
	}

	public void setMinDateTime(Date minDateTime) {
		this.minDateTime = minDateTime;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Integer getQtdAgendamentos() {
		return qtdAgendamentos;
	}

	public void setQtdAgendamentos(Integer qtdAgendamentos) {
		this.qtdAgendamentos = qtdAgendamentos;
	}
	
}
