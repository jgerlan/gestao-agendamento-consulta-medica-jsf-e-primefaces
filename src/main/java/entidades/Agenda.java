package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Agenda {
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "nome_paciente", length = 255)
	private String paciente;
	private String medico;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraConsulta;
	
	@Override
	public String toString() {
		return "Agenda [id=" + id + ", paciente=" + paciente + ", medico=" + medico + ", dataHoraConsulta="
				+ dataHoraConsulta + "]";
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPaciente() {
		return paciente;
	}
	
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	
	public String getMedico() {
		return medico;
	}
	
	public void setMedico(String medico) {
		this.medico = medico;
	}
	
	public Date getDataHoraConsulta() {
		return dataHoraConsulta;
	}
	
	public void setDataHoraConsulta(Date dataHoraConsulta) {
		this.dataHoraConsulta = dataHoraConsulta;
	}
}
