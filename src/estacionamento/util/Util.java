package estacionamento.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Util {

	public static String gerarID() {
		return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	}

	public static String formatarData(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	public static String formatarHora(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern("HH:mm"));
	}

}
