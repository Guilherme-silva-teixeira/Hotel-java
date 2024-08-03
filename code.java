package test;

import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class index0{

    // Estado dos quartos (true = vago, false = ocupado) assim que é feito um cadastro o quarto sera ocupado
    private static boolean[][] suites = {
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true}
    };

    // Histórico dos clientes do hotel
    private static String[] clientes = new String[100];
    private static int indice = 0;

    public static void main(String[] args) {
        //função que chama o menu do sistema do Hotel
        menu();
    }

    private static void menu() {
        //string onde impreme as opções do menu do hotel
        String[] options = {
                "Ver Quartos",
                "Reservar Quarto",
                "Realizar Check-out",
                "Listar Quartos Vagos",
                "Listar Quartos Ocupados",
                "Mostrar Histórico de Reservas",
                "Sair"
        };

        while (true) {
            //função onde obtem a opção selecionada pelo usuario do sistema e imprime o que selecionou
            int selectedOption = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção",
                    "Sistema de reservas de hotel",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            //função que trata da opção escolhida pelo usuario, ela chama outras funções
            switch (selectedOption) {
                case 0:
                //exibe os quartos
                    mostrarQuartos();
                    break;
                case 1:
                //reserva de um quarto
                    reservarQuarto();
                    break;
                case 2:
                //realiza o check-out do cliente
                    realizarCheckOut();
                    break;
                case 3:
                // mostra os quartos que ainda estãoi vagos
                    mostrarQuartosVagos();
                    break;
                case 4:
                //exibe oa quartos que estão ocupados
                    mostrarQuartosOcupados();
                    break;
                case 5:
                //imprime as informações das resevas feitas pelo usuario
                    mostrarHistoricoReservas();
                    break;
                case 6:
                //opção onde sai do sistema, fecha o progama
                    System.out.println("Saindo do sistema");
                    return;
                default:
                //a seleção invalida dos campos.
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        }
    }
    //string que imprime o estado que se encontra a suite, vaga ou ocupada
    private static void mostrarQuartos() {
        StringBuilder quartos = new StringBuilder("Estado dos quartos:\n");
        for (int i = 0; i < suites.length; i++) {
            for (int j = 0; j < suites[i].length; j++) {
                quartos.append("Suite S").append(i * 4 + j + 1).append(": ");
                quartos.append(suites[i][j] ? "Vago\n" : "Ocupado\n");
            }
        }
        JOptionPane.showMessageDialog(null, quartos.toString());
    }

//Foi feito uma string para coleta de dados da hospedagem do cliente com informações basicas para o cadastro
    private static void reservarQuarto() {
        String suiteEscolhida = escolherSuite();
        if (suiteEscolhida != null) {
            String nome = JOptionPane.showInputDialog("Digite o nome do cliente: ");
            String cpf = JOptionPane.showInputDialog("Informe o CPF do cliente: ");
            String formaDePagamento = JOptionPane.showInputDialog("Qual a forma de pagamento: ");
            String diasHospedagem = JOptionPane.showInputDialog("O cliente deseja ficar quanto tempo hospedado: ");
            String email = JOptionPane.showInputDialog("Digite o email do cliente: ");
            String telefone = JOptionPane.showInputDialog("Número do telefone do cliente: ");

            //para indicar o dia do check-in e check-out do cliente foi utiliza o biblioteca SimpleDateFormat; calendar; data para definir quando foi realizado a rezerva e calcular quando a rezerva acaba
            Date dataAtual = new Date();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dataAtual);
            calendario.add(Calendar.DAY_OF_YEAR, Integer.parseInt(diasHospedagem));
            Date checkoutDate = calendario.getTime();

            // foi feito uma string usando os dados do clientem, ser impresso na tela todas de uma vez com JOptionPane
            String dadosCliente = "Dados do cliente informado \n";
            dadosCliente += "Nome: " + nome + "\n";
            dadosCliente += "CPF: " + cpf + "\n";
            dadosCliente += "Forma de pagamento: " + formaDePagamento + "\n";
            dadosCliente += "Dias de hospedagem: " + diasHospedagem + "\n";
            dadosCliente += "Check-in: " + formatoData.format(dataAtual) + "\n";
            dadosCliente += "Check-out: " + formatoData.format(checkoutDate) + "\n";
            dadosCliente += "E-mail do cliente: " + email + "\n";
            dadosCliente += "Telefone do cliente: " + telefone + "\n";

            JOptionPane.showMessageDialog(null, dadosCliente);

            clientes[indice++] = "Nome: " + nome + ", CPF: " + cpf + ", Forma de pagamento: " + formaDePagamento + ", Dias de hospedagem: " + diasHospedagem + ", E-mail: " + email + ", Telefone: " + telefone + ", Suite escolhida: " + suiteEscolhida;
        }
    }

    //função que realiza o check-out do cliente usando o CPF para identificar o cliente 
    private static void realizarCheckOut() {
        String cpf = JOptionPane.showInputDialog("Informe o CPF do cliente para check-out: ");
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].contains("CPF: " + cpf)) {
                int suiteIndex = getSuiteIndex(clientes[i].split(",")[6].trim().substring("Suite escolhida: ".length()));
                if (suiteIndex != -1) {
                    //ao realizar o check-out essa função atualiza o status do quarto de ocupado para vago
                    suites[suiteIndex / 4][suiteIndex % 4] = true;
                    clientes[i] = null;
                    JOptionPane.showMessageDialog(null, "Check-out realizado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Suite não encontrada.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
    }
    //uma string que exibe os quartos que estão livres para hospedagem
    private static void mostrarQuartosVagos() {
        StringBuilder quartosVagos = new StringBuilder("Quartos vagos:\n");
        for (int i = 0; i < suites.length; i++) {
            for (int j = 0; j < suites[i].length; j++) {
                if (suites[i][j]) {
                    quartosVagos.append("Suite S").append(i * 4 + j + 1).append("\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, quartosVagos.toString());
    }
    //string para imprimir os quartos que estão ocupados
    private static void mostrarQuartosOcupados() {
        StringBuilder quartosOcupados = new StringBuilder("Quartos ocupados:\n");
        for (int i = 0; i < suites.length; i++) {
            for (int j = 0; j < suites[i].length; j++) {
                if (!suites[i][j]) {
                    quartosOcupados.append("Suite S").append(i * 4 + j + 1).append("\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, quartosOcupados.toString());
    }
    //uma string que imprime historico da hospedagem que forom feitas dos clientes
    private static void mostrarHistoricoReservas() {
        StringBuilder historicoReservas = new StringBuilder("Histórico de Reservas:\n");
        for (String cliente : clientes) {
            if (cliente != null) {
                historicoReservas.append(cliente).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, historicoReservas.toString());
    }
    //função que escolhe a suite a ser hospedada, nela é exibida os quartos que estão ocupados e se estiver ocupada retorna novamente a escolha da suite, e em caso de suite não existente o progama imprime que a suite é invalida
    private static String escolherSuite() {
        StringBuilder suiteEscolhida = new StringBuilder("Escolha uma suite \n");
        for (int i = 0; i < suites.length; i++) {
            for (int j = 0; j < suites[i].length; j++) {
                suiteEscolhida.append(suites[i][j] ? " S" + (i * 4 + j + 1) + "  " : " S" + (i * 4 + j + 1) + "- Ocupada!! ");
            }
            suiteEscolhida.append("\n");
        }

        String input = JOptionPane.showInputDialog(suiteEscolhida.toString());
        for (int i = 0; i < suites.length; i++) {
            for (int j = 0; j < suites[i].length; j++) {
                if (input.equalsIgnoreCase("S" + (i * 4 + j + 1))) {
                    if (suites[i][j]) {
                        suites[i][j] = false;
                        return input;

                    } else {
                        JOptionPane.showMessageDialog(null, "Esta suite está ocupada.");
                        return escolherSuite();
                    }
                }
            }
        }

        JOptionPane.showMessageDialog(null, "ERRO! Esta suite não existe, selecione outra");
        return escolherSuite();
    }

    private static int getSuiteIndex(String suite) {
        //try catch para tratar erros de conversão
        if (suite != null && suite.startsWith("S")) {
            try {
                //função onde converte uma string pora um indicie da suite
                int index = Integer.parseInt(suite.substring(1)) - 1;
                if (index >= 0 && index < suites.length * 4) {
                    return index;
                }
            } catch (NumberFormatException e) {
                // Ignorar
            }
        }
        return -1;
    }
}
