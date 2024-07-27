import java.util.ArrayList;
import java.util.Scanner;

public class VeiculoController {

    Scanner sc;
    VeiculoDAO daoVeiculo;

    
    public VeiculoController(){
        sc = new Scanner(System.in);
        daoVeiculo = new VeiculoDAO();
    }


    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n----------------------------------------");
            System.out.println("GERENCIAMENTO DE VEICULOS");
            System.out.println("1 - Cadastrar Veiculo");
            System.out.println("2 - Buscar Veiculo");
            System.out.println("3 - Editar Veiculo");
            System.out.println("4 - Excluir Veiculo");
            System.out.println("5 - Listar Veiculos");
            System.out.println("0 - Sair");

            System.out.print("Digite a opção: ");
            opcao = sc.nextInt();
            System.out.print("\n");

            switch (opcao) {
                case 1:
                    this.cadastrarVeiculo();
                    break;
                case 2:
                    this.buscarVeiculo();
                    break;
                case 3:
                    this.editarVeiculo();
                    break;
                case 4:
                    this.excluirVeiculo();
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
     * Cadastro de veiculos
     */
    private void cadastrarVeiculo(){

        Veiculo veiculo = formVeiculo();

        int idGerado = daoVeiculo.inserir(veiculo);

        if (idGerado == 0) {
            System.out.println("Erro ao inserir veiculo!");
        }
        else {
            System.out.println("Veiculo inserido com o id " + idGerado + "!");
        }

    }


    /*
     * Busca um veiculo
     */
    private void buscarVeiculo(){
        System.out.print("Informe o codigo do veiculo: ");
        int codigo = sc.nextInt();

        Veiculo veiculo = daoVeiculo.getVeiculo(codigo);
        System.out.println("--------");
        System.out.println(veiculo);
        System.out.println("--------");
    }


    /*
     * Alterar veiculo
     */
    private void editarVeiculo(){

        System.out.print("Informe o codigo do veiculo: ");
        int codigo = sc.nextInt();
        
        Veiculo veiculoRequerido = daoVeiculo.getVeiculo(codigo);
            
        Veiculo veiculo = formVeiculo();
        if (veiculo != null) {

            veiculo.setCodigo(veiculoRequerido.getCodigo());
            boolean alterado = daoVeiculo.alterar(veiculo);
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
     * Excluir um veiculo
     */
    public void excluirVeiculo() {
        System.out.println("Informe o codigo do veiculo a ser excluido");
        int codigo = sc.nextInt();
        Veiculo veiculoRequerido = daoVeiculo.getVeiculo(codigo);
        if (veiculoRequerido == null){
            System.out.println("Carro inexistente");
        }
        else {
            int linhasAfetadas = daoVeiculo.excluir(codigo);
            if (linhasAfetadas == 1){
                System.out.println("Carro excluido com sucesso");
            } else {
                System.out.println("Erro ao excluir carro");
            }
        }
    }


    /*
     * Buscar todos os veiculos
     */
    private void buscarTodos(){

        ArrayList<Veiculo> resultado = daoVeiculo.buscarTodos(); 
        System.out.println("--------");
        for (Veiculo veiculo : resultado) {
            System.out.println("\n" + veiculo);
        }
        System.out.println("\n--------");

    }


    /*
     * Formulario que pega os dados do veiculo
     */
    private Veiculo formVeiculo() throws IllegalAccessError{

        Veiculo veiculo = new Veiculo();

        sc.nextLine();
        System.out.print("Informe a marca: ");
        veiculo.setMarca(sc.nextLine());

        System.out.print("Informe o modelo: ");
        veiculo.setModelo(sc.nextLine());   
        
        System.out.print("Informe o chassi: ");
        veiculo.setChassi(sc.nextLine());

        System.out.print("Informe o ano: ");
        veiculo.setAno(sc.nextInt());
        
        return veiculo;
    }
}
