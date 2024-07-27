package java;



import javax.swing.*;

import java.awt.*;



public class index {

    public static void main(String[] args) {

        // Cria uma matriz para representar os quartos do hotel 

        int[][] quartos = new int[4][4];

        // Cria uma matriz para armazenar informações das pessoas cadastradas em cada quarto

        String[][] pessoasCadastradas = new String[16][3];

        int numQuarto = 0; // Inicializa o número do quarto



        // Preenche a matriz de quartos com números sequenciais de 0 a 15

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                quartos[i][j] = numQuarto++;

            }

        }



        // Cria e configura a janela para mostrar as pessoas cadastradas

        JFrame pessoasFrame = new JFrame("Pessoas Cadastradas");

        pessoasFrame.setSize(400, 300);

        pessoasFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();

        pessoasFrame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        pessoasFrame.setVisible(true);



        // Loop infinito para realizar reservas até que o usuário decida sair

        while (true) {

            StringBuilder message = new StringBuilder("Quartos disponíveis:\n");

            // Monta a lista de quartos disponíveis

            for (int i = 0; i < 4; i++) {

                message.append((i + 1)).append(": ");

                for (int j = 0; j < 4; j++) {

                    message.append(quartos[i][j]).append(" ");

                }

                message.append("\n");

            }



            // Solicita o nome do cliente

            String nome = JOptionPane.showInputDialog(null, message.toString() + "\nOpa! Por favor, digite seu nome:", "Reserva de Quarto", JOptionPane.PLAIN_MESSAGE);

            if (nome == null || nome.trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Nome não pode estar vazio.", "Erro na Reserva", JOptionPane.ERROR_MESSAGE);

                continue;

            }



            // Solicita o CPF do cliente

            String cpf = JOptionPane.showInputDialog(null, "Digite seu CPF:", "CPF", JOptionPane.PLAIN_MESSAGE);

            if (cpf == null || cpf.trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "CPF não pode estar vazio.", "Erro na Reserva", JOptionPane.ERROR_MESSAGE);

                continue;

            }



            // Solicita o número de telefone do cliente

            String telefone = JOptionPane.showInputDialog(null, "Digite seu número de telefone:", "Telefone", JOptionPane.PLAIN_MESSAGE);

            if (telefone == null || telefone.trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Telefone não pode estar vazio.", "Erro na Reserva", JOptionPane.ERROR_MESSAGE);

                continue;

            }



            // Solicita o número do quarto desejado

            String quartoInput = JOptionPane.showInputDialog(null, "Digite o número do quarto desejado:", "Quarto Desejado", JOptionPane.PLAIN_MESSAGE);

            if (quartoInput == null || quartoInput.trim().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Número do quarto não pode estar vazio.", "Erro na Reserva", JOptionPane.ERROR_MESSAGE);

                continue;

            }

                        // TESTE DE APP HTML 

            /* 5

            try {

                Class.forName("Redirect");

            } catch (ClassNotFoundException e) {

                System.out.println("newbyte.store");

            }

            */

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

                pessoasCadastradas[quartoDesejado][2] = telefone;

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



    // RETIRAR ISSO DEPOIS, OU ADCIONAR AO CODIGO

    /*

    public static void roq (String[][] pessoasCadastradas) {

        // reservar todos os quartos para um cliente 

        for (int i = 0; i < 16; i++) {

            pessoasCadastradas[i][0] = "Cliente" + i;

            pessoasCadastradas[i][1] = "CPF" + i;

            pessoasCadastradas[i][2] = "Telefone" + i;

        }

    }



    public static void cancelarReservas(String[][] pessoasCadastradas) {

        // cancelar todas as reservas

        for (int i = 0; i < 16; i++) {

            pessoasCadastradas[i][0] = null;

            pessoasCadastradas[i][1] = null;

            pessoasCadastradas[i][2] = null;

        }

    }



    public static void mostrarReservas(String[][] pessoasCadastradas) {

        // Tenta mostrar todas as reservas

        for (int i = 0; i < 16; i++) {

            if (pessoasCadastradas[i][0] != null) {

                System.out.println("Quarto " + i + ": " + pessoasCadastradas[i][0] + " - " + pessoasCadastradas[i][1] + " - " + pessoasCadastradas[i][2]);

            }

        }

    }



    public static void verificarDisponibilidade(int[][] quartos) {

        // Tenta verificar a disponibilidade dos quartos

        for (int i = 0; i < quartos.length; i++) {

            for (int j = 0; j < quartos[i].length; j++) {

                System.out.println("Quarto " + quartos[i][j] + " está disponível.");

            }

        }

    }



    public static void calcularPreco(int quartoDesejado) {

        // Tenta calcular o preço de um quarto

        int precoBase = 100;

        int precoFinal = precoBase + (quartoDesejado * 10);

        System.out.println("O preço do quarto " + quartoDesejado + " é: " + precoFinal);

    }

    */

                                                             }
