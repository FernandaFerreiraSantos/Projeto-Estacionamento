package estacionamento.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import estacionamento.dao.MovimentoDao;
import estacionamento.model.Movimento;
import estacionamento.util.Util;

public class FrameEstacionamento {

	private static final String LOCAL_PATH = "C:/Users/ferna/Desktop/SENAI/FPOO/eclipse-workspace/estacionamento/src/estacionamento/arquivos/movimentos.ds1";

	private JLabel labelEntrada;
	private JLabel labelPlaca;
	private JTextField textPlaca;
	private JLabel labelModelo;
	private JTextField textModelo;
	private JButton buttonEntrar;
	private JLabel labelSaida;
	private JLabel labelBuscarPlaca;
	private JTextField textBuscarPlaca;
	private JButton buttonBuscar;
	private JLabel labelModelo2;
	private JTextField textModelo2;
	private JLabel labelDataEntrada;
	private JTextField textDataEntrada;
	private JLabel labelHoraEntrada;
	private JTextField textHoraEntrada;
	private JLabel labelDataSaida;
	private JTextField textDataSaida;
	private JLabel labelHoraSaida;
	private JTextField textHoraSaida;
	private JLabel labelTempo;
	private JTextField textTempo;
	private JLabel labelValorPagar;
	private JTextField textValorPagar;
	private JButton buttonEfetuarSaida;
	private JButton buttonFecharSistema;

	Color Azul = new Color(0, 121, 175);
	Color verde = new Color(109, 147, 74);
	Color vermelho = new Color(184, 0, 0);
	Font fontEntrada = new Font("Helvetica", Font.BOLD, 20);
	Font font1 = new Font("Helvetica", Font.BOLD, 12);
	Font fontBotao = new Font("Helvetica", Font.BOLD, 12);

	public void criarTela() {

		JFrame iniciarTela = new JFrame();
		iniciarTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iniciarTela.setTitle("ESTACIONA BEM");
		iniciarTela.setSize(710, 555);
		iniciarTela.setLayout(null);
		iniciarTela.setResizable(false);
		iniciarTela.setLocationRelativeTo(null);

		labelEntrada = new JLabel("ENTRADA");
		labelEntrada.setBounds(30, 30, 150, 20);
		labelEntrada.setFont(fontEntrada);
		labelEntrada.setForeground(verde);

		labelPlaca = new JLabel("PLACA:");
		labelPlaca.setBounds(30, 70, 100, 15);
		labelPlaca.setFont(font1);
		labelPlaca.setForeground(Color.BLACK);
		textPlaca = new JTextField();
		textPlaca.setBounds(30, 90, 250, 30);

		labelModelo = new JLabel("MODELO:");
		labelModelo.setBounds(298, 70, 100, 15);
		labelModelo.setFont(font1);
		labelModelo.setForeground(Color.BLACK);
		textModelo = new JTextField();
		textModelo.setBounds(298, 90, 250, 30);

		buttonEntrar = new JButton("ENTRAR");
		buttonEntrar.setBounds(565, 90, 100, 30);
		buttonEntrar.setFont(fontBotao);
		buttonEntrar.setContentAreaFilled(false);
		buttonEntrar.setOpaque(true);
		buttonEntrar.setBackground(Azul);
		buttonEntrar.setForeground(Color.WHITE);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		DefaultTableModel tabelaModel = new DefaultTableModel();
		tabelaModel.addColumn("CÓDIGO");
		tabelaModel.addColumn("PLACA");
		tabelaModel.addColumn("MODELO");
		tabelaModel.addColumn("DATA ENTRADA");

		MovimentoDao dao = new MovimentoDao();
		ArrayList<Movimento> movimentos = dao.listarMovimento();

		for (Movimento c : movimentos) {
			ZonedDateTime entranceResult = ZonedDateTime.parse(c.getDataEntrada() + "+03:00",
					DateTimeFormatter.ISO_DATE_TIME);
			LocalDateTime entranceDate = entranceResult.toLocalDateTime();

			String[] clientConstructor = { c.getID(), c.getPlaca(), c.getModelo(), Util.formatarData(entranceDate) };
			tabelaModel.addRow(clientConstructor);
		}

		JTable tabelaMovimento = new JTable(tabelaModel);
		tabelaMovimento.getTableHeader().setReorderingAllowed(false);
		tabelaMovimento.setEnabled(false);

		JScrollPane ScrollMovimento = new JScrollPane(tabelaMovimento);
		ScrollMovimento.setBounds(30, 130, 635, 125);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		labelSaida = new JLabel("SAÍDA");
		labelSaida.setBounds(30, 268, 150, 20);
		labelSaida.setFont(fontEntrada);
		labelSaida.setForeground(verde);

		labelBuscarPlaca = new JLabel("PLACA:");
		labelBuscarPlaca.setBounds(30, 305, 100, 15);
		labelBuscarPlaca.setFont(font1);
		labelBuscarPlaca.setForeground(Color.BLACK);
		textBuscarPlaca = new JTextField();
		textBuscarPlaca.setBounds(30, 325, 150, 30);

		buttonBuscar = new JButton("BUSCAR");
		buttonBuscar.setBounds(195, 325, 100, 30);
		buttonBuscar.setFont(fontBotao);
		buttonBuscar.setContentAreaFilled(false);
		buttonBuscar.setOpaque(true);
		buttonBuscar.setBackground(Azul);
		buttonBuscar.setForeground(Color.WHITE);

		JLabel status = new JLabel();
		status.setText("Placa não encontrada");
		status.setBounds(300, 340, 200, 30);
		status.setForeground(Color.RED);
		status.setFont(font1);
		status.setVisible(false);

		labelModelo2 = new JLabel("MODELO:");
		labelModelo2.setBounds(30, 370, 100, 15);
		labelModelo2.setFont(font1);
		labelModelo2.setForeground(Color.BLACK);
		textModelo2 = new JTextField();
		textModelo2.setBounds(30, 390, 125, 30);

		labelDataEntrada = new JLabel("DATA ENTRADA:");
		labelDataEntrada.setBounds(165, 370, 100, 15);
		labelDataEntrada.setFont(font1);
		labelDataEntrada.setForeground(Color.BLACK);
		textDataEntrada = new JTextField();
		textDataEntrada.setBounds(165, 390, 100, 30);

		labelHoraEntrada = new JLabel("HORA ENTRADA:");
		labelHoraEntrada.setBounds(275, 370, 100, 15);
		labelHoraEntrada.setFont(font1);
		labelHoraEntrada.setForeground(Color.BLACK);
		textHoraEntrada = new JTextField();
		textHoraEntrada.setBounds(275, 390, 100, 30);

		labelDataSaida = new JLabel("DATA SAÍDA:");
		labelDataSaida.setBounds(385, 370, 100, 15);
		labelDataSaida.setFont(font1);
		labelDataSaida.setForeground(Color.BLACK);
		textDataSaida = new JTextField();
		textDataSaida.setBounds(385, 390, 100, 30);

		labelHoraSaida = new JLabel("DATA SAÍDA:");
		labelHoraSaida.setBounds(495, 370, 100, 15);
		labelHoraSaida.setFont(font1);
		labelHoraSaida.setForeground(Color.BLACK);
		textHoraSaida = new JTextField();
		textHoraSaida.setBounds(495, 390, 100, 30);

		labelTempo = new JLabel("TEMPO:");
		labelTempo.setBounds(605, 370, 100, 15);
		labelTempo.setFont(font1);
		labelTempo.setForeground(Color.BLACK);
		textTempo = new JTextField();
		textTempo.setBounds(605, 390, 60, 30);

		labelValorPagar = new JLabel("VALOR A PAGAR:");
		labelValorPagar.setBounds(42, 455, 100, 15);
		labelValorPagar.setFont(font1);
		labelValorPagar.setForeground(Color.BLACK);

		textValorPagar = new JTextField();
		textValorPagar.setBounds(165, 435, 150, 50);

		buttonEfetuarSaida = new JButton("EFETUAR SAÍDA");
		buttonEfetuarSaida.setBounds(340, 435, 150, 50);
		buttonEfetuarSaida.setFont(fontBotao);
		buttonEfetuarSaida.setContentAreaFilled(false);
		buttonEfetuarSaida.setOpaque(true);
		buttonEfetuarSaida.setBackground(Azul);
		buttonEfetuarSaida.setForeground(Color.WHITE);

		buttonFecharSistema = new JButton("FECHAR SISTEMA");
		buttonFecharSistema.setBounds(515, 435, 150, 50);
		buttonFecharSistema.setFont(fontBotao);
		buttonFecharSistema.setContentAreaFilled(false);
		buttonFecharSistema.setOpaque(true);
		buttonFecharSistema.setBackground(vermelho);
		buttonFecharSistema.setForeground(Color.WHITE);

		iniciarTela.getContentPane().add(ScrollMovimento);
		iniciarTela.getContentPane().add(labelEntrada);
		iniciarTela.getContentPane().add(labelPlaca);
		iniciarTela.getContentPane().add(textPlaca);
		iniciarTela.getContentPane().add(labelModelo);
		iniciarTela.getContentPane().add(textModelo);
		iniciarTela.getContentPane().add(buttonEntrar);
		iniciarTela.getContentPane().add(labelSaida);
		iniciarTela.getContentPane().add(labelBuscarPlaca);
		iniciarTela.getContentPane().add(textBuscarPlaca);
		iniciarTela.getContentPane().add(buttonBuscar);
		iniciarTela.getContentPane().add(status);
		iniciarTela.getContentPane().add(labelModelo2);
		iniciarTela.getContentPane().add(textModelo2);
		iniciarTela.getContentPane().add(labelDataEntrada);
		iniciarTela.getContentPane().add(textDataEntrada);
		iniciarTela.getContentPane().add(labelHoraEntrada);
		iniciarTela.getContentPane().add(textHoraEntrada);
		iniciarTela.getContentPane().add(labelDataSaida);
		iniciarTela.getContentPane().add(textDataSaida);
		iniciarTela.getContentPane().add(labelHoraSaida);
		iniciarTela.getContentPane().add(textHoraSaida);
		iniciarTela.getContentPane().add(labelTempo);
		iniciarTela.getContentPane().add(textTempo);
		iniciarTela.getContentPane().add(labelValorPagar);
		iniciarTela.getContentPane().add(textValorPagar);
		iniciarTela.getContentPane().add(buttonEfetuarSaida);
		iniciarTela.getContentPane().add(buttonFecharSistema);

		iniciarTela.setVisible(true);

		buttonEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (validarFormulario() == true) {
					Movimento movimento = new Movimento();
					movimento.setID(Util.gerarID());
					movimento.setPlaca(textPlaca.getText());
					movimento.setModelo(textModelo.getText());
					movimento.setDataEntrada(LocalDateTime.now());

					MovimentoDao dao = new MovimentoDao(movimento);
					dao.registrarMovimento();

					JOptionPane.showMessageDialog(null,
							"O veículo " + movimento.getPlaca() + "\ncadastrado com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);

					limparFormulario();
					tabelaModel.addRow(new String[] { movimento.getID(), movimento.getPlaca(), movimento.getModelo(),
							Util.formatarData(ZonedDateTime
									.parse(movimento.getDataEntrada() + "+03:00", DateTimeFormatter.ISO_DATE_TIME)
									.toLocalDateTime()) });
				} else {
					JOptionPane.showMessageDialog(null, "Deve preencher todos os campos.", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		buttonBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (textBuscarPlaca.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Você deve inserir a placa", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				} else {
					MovimentoDao dao = new MovimentoDao();
					try {
						String[] movementVector = dao.procurarMovimento(textBuscarPlaca.getText()).toStringEntrance()
								.split(";");

						String horaEntrada = Util.formatarHora(ZonedDateTime
								.parse(movementVector[3], DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime());
						String horaSaida = Util.formatarHora(LocalDateTime.now());

						status.setVisible(false);
						textModelo2.setText(movementVector[2]);
						textDataEntrada.setText(Util.formatarData(ZonedDateTime
								.parse(movementVector[3], DateTimeFormatter.ISO_DATE_TIME).toLocalDateTime()));
						textHoraEntrada.setText(horaEntrada);
						textDataSaida.setText(Util.formatarData(LocalDateTime.now()));
						labelHoraSaida.setText(horaSaida);

						int entradaMinutos = Integer.parseInt(horaEntrada.split(":")[0]) * 60
								+ Integer.parseInt(horaEntrada.split(":")[1]);
						int saidaMinutos = Integer.parseInt(horaSaida.split(":")[0]) * 60
								+ Integer.parseInt(horaSaida.split(":")[1]);
						int minutosTotais = saidaMinutos - entradaMinutos;
						int minutos = minutosTotais % 60;
						int horas = (minutosTotais - minutos) / 60;
						String tempo = minutosTotais > 60 ? horas + "h" + minutos + "m" : minutosTotais + "m";
						textTempo.setText(tempo);

						Path path = Paths.get(LOCAL_PATH);
						BufferedReader leitor = Files.newBufferedReader(path, Charset.forName("UTF-8"));
						String linha = leitor.readLine();
						int precoPrimeiraHora = Integer.parseInt(linha.split(":")[1]);
						linha = leitor.readLine();
						int outrasHoras = Integer.parseInt(linha.split(":")[1]);
						linha = leitor.readLine();
						int toleranceMinutes = Integer.parseInt(linha.split(":")[1]);
						int preco = horas == 1 && minutos <= toleranceMinutes || horas == 0 ? precoPrimeiraHora
								: precoPrimeiraHora + (horas - 1) * outrasHoras
										+ (minutos > toleranceMinutes ? outrasHoras : 0);
						textValorPagar.setText("R$" + String.valueOf(preco));
					} catch (Exception e) {
						e.printStackTrace();
						status.setVisible(true);
					}
				}
			}
		});

		buttonEntrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				MovimentoDao dao = new MovimentoDao();
				Movimento movimento = dao.procurarMovimento(textBuscarPlaca.getText());
				movimento.setDataSaida(LocalDateTime.now());
				movimento.setPreco(Double.parseDouble(textValorPagar.getText().replace("R$", "")));
				movimento.setTempo(textTempo.getText());
				dao.registrarMovimentoSaida(movimento);
				JOptionPane.showMessageDialog(null,
						"A saída do veículo " + movimento.getPlaca() + "\nfoi efetuada com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				limparFormulario();
			}
		});

		buttonFecharSistema.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				iniciarTela.dispose();
				iniciarTela.setVisible(false);
			}
		});
	}

	private void limparFormulario() {
		textPlaca.setText("");
		textModelo.setText("");

		textModelo2.setText("");
		textDataEntrada.setText("");
		textHoraEntrada.setText("");
		textDataSaida.setText("");
		textHoraSaida.setText("");
		textTempo.setText("");
		labelValorPagar.setText("");

		textModelo.requestFocus();
	}

	private boolean validarFormulario() {
		boolean validar = true;

		if (textPlaca.getText().trim().length() == 0 || textModelo.getText().trim().length() == 0) {
			validar = false;
		}

		return validar;

	}

}
