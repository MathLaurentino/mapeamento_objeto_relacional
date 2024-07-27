import java.util.ArrayList;
import java.util.Scanner;

public class VendedorController {
    
    Scanner sc;
    VendedorDAO vendedorDAO;

    
    public VendedorController(){
        sc = new Scanner(System.in);
        vendedorDAO = new VendedorDAO();
    }


    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n----------------------------------------");
            System.out.println("GERENCIAMENTO DE VendedorS");
            System.out.println("1 - Cadastrar Vendedor");
            System.out.println("2 - Buscar Vendedor");
            System.out.println("3 - Editar Vendedor");
            System.out.println("4 - Excluir Vendedor");
            System.out.println("5 - Listar Vendedor");
            System.out.println("0 - Sair");

            System.out.print("Digite a opção: ");
            opcao = sc.nextInt();
            System.out.print("\n");

            switch (opcao) {
                case 1:
                    this.cadastrarVendedor();
                    break;
                case 2:
                    this.buscarVendedor();
                    break;
                case 3:
                    this.editarVendedor();
                    break;
                case 4:
                    this.excluirVendedor();
                    break;
                case 5:
                    this.buscarTodos();
                    break;
                case 0:
                    break;
                default:
                    break;
            }

        }
    }


    /*
     * Cadastro de Vendedors
     */
    private void cadastrarVendedor(){

        Vendedor Vendedor = formVendedor();

        int idGerado = vendedorDAO.inserir(Vendedor);

        if (idGerado == 0) {
            System.out.println("Erro ao inserir Vendedor!");
        }
        else {
            System.out.println("Vendedor inserido com o id " + idGerado + "!");
        }

    }


    /*
     * Busca um Vendedor
     */
    private void buscarVendedor(){
        System.out.print("Informe o codigo do Vendedor: ");
        int codigo = sc.nextInt();

        Vendedor Vendedor = vendedorDAO.getVendedor(codigo);
        System.out.println("--------");
        System.out.println(Vendedor);
        System.out.println("--------");
    }


    /*
     * Alterar Vendedor
     */
    private void editarVendedor(){

        System.out.print("Informe o codigo do Vendedor: ");
        int codigo = sc.nextInt();
        
        Vendedor VendedorRequerido = vendedorDAO.getVendedor(codigo);
            
        Vendedor Vendedor = formVendedor();
        if (Vendedor != null) {

            Vendedor.setCodigo(VendedorRequerido.getCodigo());
            boolean alterado = vendedorDAO.alterar(Vendedor);
            if (alterado)
                System.out.println("Alterado com sucesso!");
            else 
                System.out.println("Falha ao alterar!");

        }
        else {
            System.out.println("Falha ao alterar!");
        }
         
    }


    /*
     * Excluir um Vendedor
     */
    public void excluirVendedor() {
        System.out.println("Informe o codigo do Vendedor a ser excluido");
        int codigo = sc.nextInt();
        Vendedor VendedorRequerido = vendedorDAO.getVendedor(codigo);
        if (VendedorRequerido == null){
            System.out.println("Vendedor inexistente");
        }
        else {
            int linhasAfetadas = vendedorDAO.excluir(codigo);
            if (linhasAfetadas == 1){
                System.out.println("Vendedor excluido com sucesso");
            } else {
                System.out.println("Erro ao excluir Vendedor");
            }
        }
    }


    /*
     * Buscar todos os Vendedors
     */
    private void buscarTodos(){

        ArrayList<Vendedor> resultado = vendedorDAO.buscarTodos(); 
        System.out.println("--------");
        for (Vendedor Vendedor : resultado) {
            System.out.println("\n" + Vendedor);
        }
        System.out.println("\n--------");

    }


    /*
     * Formulario que pega os dados do Vendedor
     */
    private Vendedor formVendedor() throws IllegalAccessError{

        Vendedor Vendedor = new Vendedor();

        sc.nextLine();
        System.out.print("Informe a nome: ");
        Vendedor.setNome(sc.nextLine());

        System.out.print("Informe o sobrenome: ");
        Vendedor.setSobrenome(sc.nextLine());   
        
        System.out.print("Informe o salario: ");
        Vendedor.setSalario(sc.nextFloat());

        System.out.print("Informe o quantidade vendas: ");
        Vendedor.setQuantVendas(sc.nextInt());
        
        return Vendedor;
    }

}
