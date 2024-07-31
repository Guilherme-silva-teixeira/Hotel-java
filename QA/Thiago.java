package test;

import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class index0 {
    private static Cliente[] clientes = new Cliente[100]; // clientes (acho que 16)
    private static int indice = 0;

    private static boolean[][] suites = {
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true}
    };

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        try {
            Object[] options = {
                    "Ver Quartos",
                    "Reservar Quarto",
                    "Realizar Check-out",
                    "Listar Quartos Vagos",
                    "Listar Quartos Ocupados",
                    "Mostrar Histórico de Reservas",
                    "Sair"
            };

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


            //selecionar a opção
            switch (selectedOption) {
                case 0: // 
                //mostrar as suites
                    System.out.println("Listando quartos");
                    mostrarQuartos();
                    break;
                case 1: // cadastrar hóspede/quarto
                    System.out.println("Reservando quarto");
                    cadastrarUsuario();
                    break;
                case 2: // desocupar quarto
                    System.out.println("Realizando check-out");
                    break;
                case 3: // quartos vagos
                    System.out.println("Listando quartos vagos");
                    mostrarQuartosVagos();
                    break;
                case 4: // quartos ocupados
                    System.out.println("Listando quartos ocupados");
                    mostrarQuartosOcupados();
                    break;
                case 5: // Mostrar histórico de reservas
                    System.out.println("Mostrando histórico de reservas");
                    break;
                case 6:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //cadastra o cliente
    //mostra os graficos
    public static void cadastrarUsuario() {
        int n = 0;
        while (n != 1) {
            n++;
            String nome = JOptionPane.showInputDialog("Digite o nome do cliente: ");
            String cpf = JOptionPane.showInputDialog("Informe o CPF do cliente: ");
            String formaDepagamento = JOptionPane.showInputDialog("Qual a forma de pagamento: ");
            String diasHospedagem = JOptionPane.showInputDialog("O cliente deseja ficar quanto tempo hospedado: ");
            String emailCliente = JOptionPane.showInputDialog("Digite o email do cliente: ");
            String telefoneCliente = JOptionPane.showInputDialog("Número do cliente: ");

            Date dataAtual = new Date();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dataAtual);
            calendario.add(Calendar.DAY_OF_YEAR, Integer.parseInt(diasHospedagem));
            Date CheckoutDate = calendario.getTime();

            String dadosCliente = "Dados do cliente informado \n";
            dadosCliente += "Nome: " + nome + "\n";
            dadosCliente += "CPF: " + cpf + "\n";
            dadosCliente += "Forma de pagamento: " + formaDepagamento + "\n";
            dadosCliente += "Dias de hospedagem: " + diasHospedagem + "\n";
            dadosCliente += "Checkin: " + formatoData.format(dataAtual) + "\n";
            dadosCliente += "Checkout: " + formatoData.format(CheckoutDate) + "\n";
            dadosCliente += "E-mail do cliente: " + emailCliente + "\n";
            dadosCliente += "Telefone do cliente: " + telefoneCliente + "\n";

            JOptionPane.showMessageDialog(null, dadosCliente);

            SuiteHotel suiteshotel = new SuiteHotel();
            String suiteEscolhida = suiteshotel.escolherSuite();
            JOptionPane.showMessageDialog(null, "Suite escolhida " + suiteEscolhida + "\n");

            clientes[indice] = new Cliente(nome, cpf, formaDepagamento, diasHospedagem, emailCliente, telefoneCliente, suiteEscolhida);
            indice++;

            int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outro cliente?");
            if (resposta == JOptionPane.NO_OPTION) {
                break;
            }
        }
    }

    

    public static void HistoricoReservas()
    {
        SuiteHotel suiteHotel = new SuiteHotel();
        suiteHotel.mostrarHistoricoReservas();   
    }

    public static void mostrarQuartos() {
        StringBuilder quartos = new StringBuilder("Estado dos quartos:\n");
        for (int i = 0; i < suites.length; i++) {
            for (int j = 0; j < suites[i].length; j++) {
                quartos.append("Suite S").append(i * 4 + j + 1).append(": ");
                if (suites[i][j]) {
                    quartos.append("Vago\n");
                } else {
                    quartos.append("Ocupado\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, quartos.toString());
    }

    public static void mostrarQuartosVagos() {
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

    public static void mostrarQuartosOcupados() {
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

    public static class SuiteHotel {


            public void mostrarHistoricoReservas() {
                StringBuilder historicoReservas = new StringBuilder("Histórico de Reservas:\n");
                for (int i = 0; i < clientesSuite.length; i++) {
                    for (int j = 0; j < clientesSuite[i].length; j++) {
                        if (clientesSuite[i][j] != null) {
                            historicoReservas.append(clientesSuite[i][j].toString()).append("\n");
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, historicoReservas.toString());
            }
        

        private static Cliente[][] clientesSuite = new Cliente[4][4];

        public String escolherSuite() {
            String suiteEscolhida = "Escolha uma suite \n";
            for (int i = 0; i < suites.length; i++) {
                for (int j = 0; j < suites[i].length; j++) {
                    if (suites[i][j]) {
                        suiteEscolhida += " S" + (i * 4 + j + 1) + "  ";
                    } else {
                        suiteEscolhida += " S" + (i * 4 + j + 1) + "- Ocupada!! ";
                    }
                }
                suiteEscolhida += "\n";
            }

            String input = JOptionPane.showInputDialog(suiteEscolhida);
            for (int i = 0; i < suites.length; i++) {
                for (int j = 0; j < suites[i].length; j++) {
                    if (input.equalsIgnoreCase("S" + (i * 4 + j + 1))) {
                        if (suites[i][j]) {
                            suites[i][j] = false;
                            return input;
                        } else {
                            JOptionPane.showMessageDialog(null, "Esta suite está ocupada pelo cliente: \n" + clientesSuite[i][j].toString());
                            return escolherSuite();
                        }
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "ERRO! Esta suite não existe, selecione outra");
            return escolherSuite();
        }

        public void ocuparSuite(int i, int j, Cliente cliente) {
            clientesSuite[i][j] = cliente;
        }
    }

    

    public static class Cliente {
        private String nome;
        private String cpf;
        private String formaDepagamento;
        private String diasHospedagem;
        private String email;
        private String telefone;
        private String suiteEscolhida;



        
        public Cliente(String nome, String cpf, String formaDepagamento, String diasHospedagem, String email, String telefone, String suiteEscolhida) {
            this.nome = nome;
            this.cpf = cpf;
            this.formaDepagamento = formaDepagamento;
            this.diasHospedagem = diasHospedagem;
            this.email = email;
            this.telefone = telefone;
            this.suiteEscolhida = suiteEscolhida;
        }

        public String toString() {
            return "Cliente: " + nome + "\n" +
                    "CPF: " + cpf + "\n" +
                    "Forma de pagamento: " + formaDepagamento + "\n" +
                    "Dias de hospedagem: " + diasHospedagem + "\n" +
                    "E-mail: " + email + "\n" +
                    "Telefone: " + telefone + "\n" +
                    "Suite escolhida: " + suiteEscolhida;
        }
    }
}
