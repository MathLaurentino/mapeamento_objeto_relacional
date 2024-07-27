import java.sql.Connection;
import java.util.Scanner;

public class Locadora {
    public static void main(String[] args) throws Exception{
        Connection conn = GerenciadorConexao.pegarConexao();
        System.out.println("teste");
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int opcao = -1;
        Scanner sc = new Scanner(System.in);
        while (opcao != 0) {
            System.out.println("\n----------------------------------------");
            System.out.println("GERENCIAMENTO");
            System.out.println("1 - Gerenciar Veiculos");
            System.out.println("2 - Gerenciar Vendedores");
            System.out.println("0 - Sair");
            System.out.print("Digite a opção: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    VeiculoController veiculoController = new VeiculoController();
                    veiculoController.menu();
                    break;
                case 2:
                    VendedorController vendedorController = new VendedorController();
                    vendedorController.menu();
                    break;
                case 0:
                    System.out.println("Ate logo");
                    break;
            }

        }
    }
}
