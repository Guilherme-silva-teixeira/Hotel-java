package test;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class sex {  
    private static Cliente[] clientes = new Cliente[100]; // initialize the array
    private static int indice = 0; // initialize the index variable

    public static void main(String [] agrs){
        cadastrarUser();
    }

    public static void cadastrarUser()
    {
        int n = 0;
        while (n != 1) {
            n++;
            String nome = JOptionPane.showInputDialog("digite o nome do cliente: ");
            String cpf = JOptionPane.showInputDialog("informe o cpf do cliente: ");
            String formaDepagamento = JOptionPane.showInputDialog("Qual a forma de pagamento:  ");
            String diasHospedagem = JOptionPane.showInputDialog("o cliente deseja ficar quanto tempo hospedado:  ");
            String emailCliente = JOptionPane.showInputDialog("digite o email do cliente: ");
            String telefoneCliente = JOptionPane.showInputDialog("numero do cliente: ");

            Date dataAtual = new Date ();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dataAtual);
            calendario.add(Calendar.DAY_OF_YEAR, Integer.parseInt(diasHospedagem));
            Date CheckoutDate = calendario.getTime();

            String dadosCliente = ("dados do cliente informado \n");
            dadosCliente += "nome: " + nome + "\n";
            dadosCliente += "CPF: " + cpf + "\n";
            dadosCliente += "forma de pagamento: " + formaDepagamento + "\n";
            dadosCliente += "dias de Hospedagem: " + diasHospedagem + "\n";
            dadosCliente += "checkin: " + formatoData.format(dataAtual) + "\n"; 
            dadosCliente += "checkout: " + formatoData.format(CheckoutDate) + "\n";
            dadosCliente += "e-mail do cliente: " + emailCliente + "\n";
            dadosCliente += "telefone do cliente: " + telefoneCliente + "\n";

            JOptionPane.showMessageDialog(null, dadosCliente);

            SuiteHotel suiteshotel = new SuiteHotel();
            String suiteEscolhida = suiteshotel.escolherSuite();
            JOptionPane.showMessageDialog(null, "suite escolhida " + suiteEscolhida + "\n");

            clientes[indice] = new Cliente(nome, cpf, formaDepagamento, diasHospedagem, emailCliente, telefoneCliente, suiteEscolhida);
            indice++;

            int resposta = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outro cliente?");
            if (resposta == JOptionPane.NO_OPTION) {
                break;
            }
        }
    }

    public static class SuiteHotel{
        private static boolean[][] suites = {
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true}
        };
        private static Cliente[][] clientesSuite = new Cliente[4][4];

        public String escolherSuite(){
            String suiteEscolhida = "escolha uma suite \n";
            for(int i = 0; i < suites.length; i++){
                for(int j = 0; j < suites[i].length; j++){
                    if(suites[i][j]){
                        suiteEscolhida += " S" + (i*4 + j + 1) + "  ";
                    }else{
                        suiteEscolhida += " S" + (i*4 + j + 1) + "- Ocupada!! ";
                    }

                }
                suiteEscolhida += "\n";
            }

            String input = JOptionPane.showInputDialog(suiteEscolhida);
            for(int i = 0; i < suites.length; i++){
                for(int j = 0; j < suites[i].length; j++){
                    if(input.equalsIgnoreCase("S" + (i*4 + j + 1))){
                        if(suites[i][j]){
                            suites[i][j] = false;
                            return input;
                        }else{
                            JOptionPane.showMessageDialog(null, "Esta suite está ocupada pelo cliente: \n" + clientesSuite[i][j].toString());
                            return escolherSuite();
                        }
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "ERRO! Esta suite não existe, Selecione outra");
            return escolherSuite();
        }

        public void ocuparSuite(int i, int j, Cliente cliente){
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

        public Cliente(String nome, String cpf, String formaDepagamento, String diasHospedagem, String email, String telefone, String suiteEscolhida){
            this.nome = nome;
            this.cpf = cpf;
            this.formaDepagamento = formaDepagamento;
            this.diasHospedagem = diasHospedagem;
            this.email = email;
            this.telefone = telefone;
            this.suiteEscolhida = suiteEscolhida;
        }

        public String toString(){
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
