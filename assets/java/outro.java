package test;

import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.IOException;
import java.math.*;;

/**
 * goat
 */
public class goat
{
    public static void main(String[] args)
    {
        Scanner Scan = new Scanner(System.in);

        showMenu();
    }

    public static void showMenu()
    {
        try
        {
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

            switch (selectedOption) {
                case 0:
                    System.out.println("Listando quartos");
                    rooms();
                    break;
                case 1:
                    // Code for reserving a room
                    System.out.println("Reservando quarto");
                    break;
                case 2:
                    // Code for check-out
                    System.out.println("Realizando check-out");
                    break;
                case 3:
                    // Code for listing vacant rooms
                    System.out.println("Listando quartos vagos");
                    break;
                case 4:
                    // Code for listing occupied rooms
                    System.out.println("Listando quartos ocupados");
                    break;
                case 5:
                    // Code for showing reservation history
                    System.out.println("Mostrando histórico de reservas");
                    break;
                case 6:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
        catch (Exception e)
        {
            System.out.println("Erro: "+e.getMessage());
        }
        }

        public static void rooms()
        {
            System.out.println("\nCriarndo quartos...");
            String[][] rooms = new String[16][3];
            //String ocupado = "Não";
            //String value = "";
            //String hospede = "???";


            // rooms[i][1] = value;
            // rooms[i][2] = ocupado;
            // rooms[i][3] = hospede;
            // Criar quartos
            // S101 < S104 ; S201 < S204 ; S301 < S304 ; S401 < S404 = 16

            int firstDigit = 1;
            int lastDigit = 1;

            for (int i = 0 ; i < 16 ; i++)
            {
                rooms[i][0] = "S" + firstDigit + "0" + lastDigit;
                rooms[i][1] = "Livre";
                rooms[i][2] = "???";
                lastDigit++;

                if (lastDigit == 5)
                {
                    lastDigit = 1;
                    firstDigit++;
                }

                StringBuilder infoSalas = new StringBuilder("Listando Quartos:\n");
                for (int j = 0 ; j < 16; j++)
                {
                    infoSalas.append("Quarto: "+ rooms[i][0] + "| Ocupação: "+ rooms[i][1] + "| Hospede: "+ rooms[i][2] + "\n");
                }

                JOptionPane.showMessageDialog(null, infoSalas.toString(), "Quartos do hotel", JOptionPane.INFORMATION_MESSAGE);
            }
        }
}
