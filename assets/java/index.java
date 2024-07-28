package index;

import javax.swing.*; 
import java.text.SimpleDateFormat; 
import java.util.Date; 
import java.util.Calendar; 
import java.awt.*; 


public class index {
    
    public static void main(String[] args) {
        // Cria uma matriz para armazenar informações das pessoas cadastradas em cada quarto
        String[][] pessoasCadastradas = new String[16][3];

        // Cria e configura a janela para mostrar as pessoas cadastradas
        JFrame pessoasFrame = new JFrame("Pessoas Cadastradas");
        pessoasFrame.setSize(400, 300);
        pessoasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea textArea = new JTextArea();
        pessoasFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        pessoasFrame.setVisible(true);

        // Loop infinito para cadastro de pessoas
        while (true) {
            // Solicita o nome do cliente
            String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
            if (nome == null || nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome não pode estar vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
                continue; // Reinicia o loop se o nome estiver vazio
            }

            // Solicita o CPF do cliente
            String cpf = JOptionPane.showInputDialog("Informe o CPF do cliente (XXX.XXX.XXX-XX):");
            /* Descomente para habilitar a validação do CPF
            if (cpf == null || !isValidCPF(cpf)) {
                JOptionPane.showMessageDialog(null, "CPF inválido. O formato deve ser XXX.XXX.XXX-XX.", "Erro", JOptionPane.ERROR_MESSAGE);
                continue; // Reinicia o loop se o CPF estiver vazio ou inválido
            }
            */

            // Solicita a forma de pagamento
            String formaDePagamento = JOptionPane.showInputDialog("Qual a forma de pagamento?");
            if (formaDePagamento == null || formaDePagamento.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Forma de pagamento não pode estar vazia.", "Erro", JOptionPane.ERROR_MESSAGE);
                continue; // Reinicia o loop se a forma de pagamento estiver vazia
            }

            // Solicita o número de dias de hospedagem
            String diasHospedagem = JOptionPane.showInputDialog("Por quantos dias o cliente deseja ficar hospedado?");
            if (diasHospedagem == null || diasHospedagem.trim().isEmpty() || !diasHospedagem.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Número de dias de hospedagem inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                continue; // Reinicia o loop se o número de dias de hospedagem estiver vazio ou não for um número
            }

            // Solicita o email do cliente
            String emailCliente = JOptionPane.showInputDialog("Digite o email do cliente:");

            // Solicita o telefone do cliente
            String telefoneCliente = JOptionPane.showInputDialog("Digite o número de telefone do cliente:");
            /* Descomente para habilitar a validação do telefone
            if (telefoneCliente == null || !isValidPhone(telefoneCliente)) {
                JOptionPane.showMessageDialog(null, "Telefone inválido. Deve conter de 10 a 11 dígitos.", "Erro", JOptionPane.ERROR_MESSAGE);
                continue; // Reinicia o loop se o telefone estiver vazio ou inválido
            }
            */

            // Obtém a data atual e calcula a data de checkout
            Date dataAtual = new Date();
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dataAtual);
            calendario.add(Calendar.DAY_OF_YEAR, Integer.parseInt(diasHospedagem));
            Date checkoutDate = calendario.getTime();

            // Monta os dados do cliente para exibição
            String dadosCliente = "Dados do cliente informados:\n";
            dadosCliente += "Nome: " + nome + "\n";
            dadosCliente += "CPF: " + cpf + "\n";
            dadosCliente += "Forma de pagamento: " + formaDePagamento + "\n";
            dadosCliente += "Dias de hospedagem: " + diasHospedagem + "\n";
            dadosCliente += "Check-in: " + formatoData.format(dataAtual) + "\n";
            dadosCliente += "Check-out: " + formatoData.format(checkoutDate) + "\n";
            dadosCliente += "Email do cliente: " + emailCliente + "\n";
            dadosCliente += "Telefone do cliente: " + telefoneCliente + "\n";

            // Exibe os dados do cliente em uma mensagem
            JOptionPane.showMessageDialog(null, dadosCliente);

            // Cria uma instância da classe 'SuiteHotel' para escolher uma suite
            SuiteHotel suiteshotel = new SuiteHotel();
            String suiteEscolhida = suiteshotel.escolherSuite();
            JOptionPane.showMessageDialog(null, "Suite escolhida: " + suiteEscolhida);

            // Solicita o número do quarto desejado
            String quartoInput = JOptionPane.showInputDialog(null, "Digite o número do quarto desejado:", "Quarto Desejado", JOptionPane.PLAIN_MESSAGE);
            if (quartoInput == null || quartoInput.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Número do quarto não pode estar vazio.", "Erro na Reserva", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            int quartoDesejado;
            try {
                quartoDesejado = Integer.parseInt(quartoInput);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Número do quarto inválido.", "Erro na Reserva", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            // Verifica se o quarto está disponível e se ainda não foi reservado
            if (quartoDesejado >= 0 && quartoDesejado < 16 && pessoasCadastradas[quartoDesejado][0] == null) {
                // Armazena as informações do cliente no quarto desejado
                pessoasCadastradas[quartoDesejado][0] = nome;
                pessoasCadastradas[quartoDesejado][1] = cpf;
                pessoasCadastradas[quartoDesejado][2] = telefoneCliente;
                // Mostra mensagem de confirmação de reserva
                JOptionPane.showMessageDialog(null, "Quarto " + quartoDesejado + " reservado com sucesso para " + nome + "!", "Confirmação de Reserva", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Mostra mensagem de erro se o quarto não estiver disponível ou já estiver reservado
                JOptionPane.showMessageDialog(null, "Quarto " + quartoDesejado + " não disponível ou já reservado.", "Erro na Reserva", JOptionPane.ERROR_MESSAGE);
            }

            // Atualiza a janela com as pessoas cadastradas
            StringBuilder pessoasMessage = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                if (pessoasCadastradas[i][0] != null) {
                    pessoasMessage.append("Quarto ").append(i).append(": ").append(pessoasCadastradas[i][0]).append(" - ").append(pessoasCadastradas[i][1]).append(" - ").append(pessoasCadastradas[i][2]).append("\n");
                }
            }
            textArea.setText(pessoasMessage.toString());

            // Pergunta ao usuário se ele deseja realizar outra reserva
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realizar outra reserva?", "Nova Reserva", JOptionPane.YES_NO_OPTION);
            // Se a resposta for "Não", encerra o loop e termina o programa
            if (resposta == JOptionPane.NO_OPTION) {
                break;
            }
        }
    }

    // Classe aninhada para manipulação das suites do hotel
    public static class SuiteHotel {
        // Matriz de strings representando as suites disponíveis
        private static String[][] suites = {
            {"S01", "S02", "S03", "S04"},
            {"S05", "S06", "S07", "S08"},
            {"S09", "S10", "S11", "S12"},
            {"S13", "S14", "S15", "S16"}
        };

        // Método para escolher uma suite
        public String escolherSuite() {
            // Monta a string com as opções de suites disponíveis
            String suiteEscolhida = "Escolha uma suite:\n";
            for (String[] suite : suites) {
                for (String s : suite) {
                    suiteEscolhida += s + " ";
                }
                suiteEscolhida += "\n";
            }
            return JOptionPane.showInputDialog(suiteEscolhida);
        }
    }

    /* Método de validação de CPF usando regex para verificar o formato XXX.XXX.XXX-XX
    public static boolean isValidCPF(String cpf) {
        String regex = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";
        return Pattern.matches(regex, cpf);
    }

    // Método de validação de telefone usando regex para verificar se contém de 10 a 11 dígitos
    public static boolean isValidPhone(String telefone) {
        String regex = "^\\d{10,11}$";
        return Pattern.matches(regex, telefone);
    }
    */
}
