import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;

class index
{
    public static void main(String[] args)
    {
        try
        {
        
        Scanner scan = new Scanner(System.in);
        int A00[] = new int[4], A01[] = new int[4], A02[] = new int[4], A03[] = new int[4];
        String nomes[] = new String[16];
        int ad = 0;
        int[] suites = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int total = 16;
        System.out.println("Digite o total de usuários que você quer cadastrar");
            if(total > 0)
            {
                for (int i = 0 ; i <= total ; i++)
                {
                    total--;
                    System.out.println("Digite o nome do hóspede 0" + i);
                    nomes[i] = scan.nextLine();
                    System.out.println("Digite o número da suíte do hóspede");
                    suites[i] = scan.nextInt();
                }
            }else
            {
                System.out.println("Suítes não suficientes\n");
            }
        }
            catch(Exception e)
            {
                System.out.println("error");
            }
                finally
                {

                }
    }

    public String CADASTRAR_USUARIO_DE_SUITE(int[] ARRAY, String USERNAME, String POS)
    {
        try
        {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do usuário: \n");
        USERNAME = scan.nextLine();
        System.out.println("digite o número do quarto desse usuário\n");
        POS = scan.nextLine();
        return USERNAME;
        }
            catch (Exception e)
            {
                System.out.println("error");;
            }
        return USERNAME;
    }
}
