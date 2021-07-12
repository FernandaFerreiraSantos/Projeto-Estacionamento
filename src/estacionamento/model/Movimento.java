package estacionamento.model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Movimento {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

	private String ID;
	private String placa;
	private String modelo;
	private LocalDateTime dataEntrada;
	private LocalDateTime dataSaida;
	private String tempo;
	private double preco;

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public String getTempo() {
		return tempo;
	}

	public void setTempo(String tempo) {
		this.tempo = tempo;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String toStringEntrance() {
		String constructor = ID + ';' + placa + ';' + modelo + ';' + dataEntrada.toString().substring(0, 19) + "+03:00"
				+ ";;;";
		return constructor.toUpperCase();
	}

	public String toStringExit() {
		String constructor = ID + ';' + placa + ';' + modelo + ';' + dataEntrada.toString().substring(0, 19) + "+03:00"
				+ ";" + dataSaida.toString() + "+03:00;" + tempo + ";" + preco;
		return constructor.toUpperCase();
	}

}
