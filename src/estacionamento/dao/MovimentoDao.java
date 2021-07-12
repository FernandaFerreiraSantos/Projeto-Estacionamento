package estacionamento.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import estacionamento.model.Movimento;

public class MovimentoDao {

	private Movimento movimento;
	private static final String LOCAL_PATH = "C:/Users/ferna/Desktop/SENAI/FPOO/eclipse-workspace/estacionamento/src/estacionamento/arquivos/movimentos.ds1";

	public MovimentoDao() {
	}

	public MovimentoDao(Movimento movimento) {
		this.movimento = movimento;
	}

	public void registrarMovimento() {

		Path path = Paths.get(LOCAL_PATH);

		try {
			BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.WRITE,
					StandardOpenOption.APPEND);

			writer.newLine();
			writer.write(movimento.toStringEntrance());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void registrarMovimentoSaida(Movimento movimento) {
		Path path = Paths.get(LOCAL_PATH);

		try {
			BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.WRITE,
					StandardOpenOption.APPEND);

			writer.newLine();
			writer.write(movimento.toStringExit());
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Movimento> listarMovimento() {

		Path path = Paths.get(LOCAL_PATH);

		try {
			BufferedReader leitor = Files.newBufferedReader(path, Charset.forName("UTF-8"));

			String linha = leitor.readLine();
			ArrayList<Movimento> movimentos = new ArrayList<>();

			while (linha != null) {
				String[] vetorMovimento = linha.split(";");
				Movimento movimento = new Movimento();

				ZonedDateTime entrarResultado = ZonedDateTime.parse(vetorMovimento[3], DateTimeFormatter.ISO_DATE_TIME);
				LocalDateTime entrarData = entrarResultado.toLocalDateTime();

				movimento.setID(vetorMovimento[0]);
				movimento.setPlaca(vetorMovimento[1]);
				movimento.setModelo(vetorMovimento[2]);
				movimento.setDataEntrada(entrarData);

				movimentos.add(movimento);
				linha = leitor.readLine();
			}

			leitor.close();
			return movimentos;
		} catch (IOException ex) {
			System.out.println("Não foi possível encontrar o arquivo específicado!");
			ex.printStackTrace();
			return null;
		}
	}

	public Movimento procurarMovimento(String placa) {

		Path path = Paths.get(LOCAL_PATH);

		try {
			BufferedReader leitor = Files.newBufferedReader(path, Charset.forName("UTF-8"));

			String linha = leitor.readLine();
			movimento = new Movimento();

			while (linha != null) {
				String[] movementVector = linha.split(";");

				if (movementVector[1].equals(placa.toUpperCase().replaceAll("-", "").replaceAll(" ", ""))) {
					ZonedDateTime entrarResultado = ZonedDateTime.parse(movementVector[3],
							DateTimeFormatter.ISO_DATE_TIME);
					LocalDateTime entrarData = entrarResultado.toLocalDateTime();
					LocalDateTime exitDate = null;

					movimento.setID(movementVector[0]);
					movimento.setPlaca(movementVector[1]);
					movimento.setModelo(movementVector[2]);
					movimento.setDataEntrada(entrarData);
					break;
				}

				linha = leitor.readLine();
			}

			leitor.close();
			return movimento;
		} catch (IOException ex) {
			System.out.println("Não foi possível encontrar o arquivo específicado!");
			ex.printStackTrace();
			return null;
		}
	}

}
