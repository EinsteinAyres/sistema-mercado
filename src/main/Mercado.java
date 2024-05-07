package main;

import model.Produto;
import utils.Utils;
import java.util.*;

public class Mercado {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static Scanner getInput() {
        return input;
    }

    public static void setInput(Scanner input) {
        Mercado.input = input;
    }

    public static void main(String[] args) {
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    private static void menu() {

        System.out.println("------------------------------------------------------");
        System.out.println("------------ Bem-vindo ao mercado do Kid -------------");
        System.out.println("------------------------------------------------------");
        System.out.println("****** Selecione a operação de sua preferência *******");
        System.out.println("------------------------------------------------------");
        System.out.println("|                Opção 1 - Cadastrar                 |");
        System.out.println("|                Opção 2 - Listar                    |");
        System.out.println("|                Opção 3 - Comprar                   |");
        System.out.println("|                Opção 4 - Carrinho                  |");
        System.out.println("|                Opção 5 - Sair                      |");


        int option = input.nextInt();

        if (input.nextInt() >5) {
            System.out.println("Opção inválida. Tente novamente.");
            menu();
        }

        switch (option) {
            case 1:
                cadastrarProdutos();
                break;
            case 2:
                listarProdutos();
                break;
            case 3:
                comprarProdutos();
                break;
            case 4:
                verCarrinho();
                break;
            case 5:
                System.out.println("Obrigado pela preferência. O Mercado do Kid agradece!");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                menu();
        }
    }

        private static void cadastrarProdutos() {

            System.out.println( "Nome do produto: ");
            String nome = input.next();

            System.out.println( "Preço do produto: ");
            Double preco = input.nextDouble();

            Produto produto = new Produto(nome, preco);
            produtos.add(produto);

            System.out.println(produto.getNome() + " cadastrado com sucesso.");
            menu();
    }

    private static void listarProdutos() {
        if (produtos.size() >0) {
            System.out.println("Lista de produtos! \n");

            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }else {
            System.out.println("Nenhum produto cadastrado.");
        }
        menu();
    }
    private static void comprarProdutos() {
        if (produtos.size() >0) {
            System.out.println("Código do produto: \n");

            System.out.println("------------ Produtos disponíveis -------------");
            for (Produto produto : produtos) {
                System.out.println(produto + "\n");
            }
            int id = Integer.parseInt(input.next());
            boolean isPresent = false;

            for (Produto produto: produtos){
                if (produto.getId() == id){
                    int qtd =0;
                    try {
                        qtd = carrinho.get(produto);
                        carrinho.put(produto, qtd +1);
                    }catch (NullPointerException e) {
                        carrinho.put(produto, 1);
                    }
                    System.out.println(produto.getNome() + " adicionado ao carrinho.");
                    isPresent = true;

                    if(isPresent) {
                        System.out.println("Deseja adicionar outro produto ao carrinho?\n");
                        System.out.println("Digite 1 para sim ou 0 para finalizar a compra.\n");
                        int option = Integer.parseInt(input.next());

                        if ( option ==1) {
                            comprarProdutos();
                        }else {
                            finalizarCompra();
                        }
                    }
                }else {
                    System.out.println("Produto não encontrado.");
                    menu();
                }
            }
        }else {
            System.out.println("Não existe cadastrado deste produto.");
            menu();
        }
    }
    private static void verCarrinho() {
        System.out.println("---- Produtos do seu carrinho ----");
        if(carrinho.size() >0) {
            for ( Produto produto : carrinho.keySet()) {
                System.out.println("Produto : " + produto + "\nQuantidade: " + carrinho.get(produto));
            }
        }else {
            System.out.println("Carrinho está vazio");
            menu();
        }
    }

    private static void finalizarCompra() {
        Double valorCompra = 0.0;
        System.out.println("Seus produtos");
        for (Produto produto: carrinho.keySet()) {
            int qtd = carrinho.get(produto);
            valorCompra += produto.getPreco() *qtd;
            System.out.println("Quantidade: " + qtd);
            System.out.println("---------------------------------------");
        }
        System.out.println("O valor da sua compra é de: " + Utils.doubleToString(valorCompra));
        carrinho.clear();
        System.out.println(" O Mercado do Kid agradece! Volte sempre.");
        menu();
    }
}
