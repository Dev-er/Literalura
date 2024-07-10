package com.br.alura.literalura.principal;

import com.br.alura.literalura.dto.LivroDTO;
import com.br.alura.literalura.dto.ResultadoDTO;
import com.br.alura.literalura.model.Autor;
import com.br.alura.literalura.model.Livro;
import com.br.alura.literalura.repository.AutorRepository;
import com.br.alura.literalura.repository.LivroRepository;
import com.br.alura.literalura.service.ConsumindoGutendexApi;
import com.br.alura.literalura.service.ConversorDeDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    ConsumindoGutendexApi consumirAPI = new ConsumindoGutendexApi();
    ConversorDeDados conversorDeDados = new ConversorDeDados();
    String URL = "https://gutendex.com/books/?search=";
    Scanner scanner = new Scanner(System.in);
    int menuNumber = 0;

    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {

        while (menuNumber != 6) {
            System.out.println("******************************************");
            System.out.println("             CHALLENGE LITERALURA "      );
            System.out.println("             ALURA/ONE - T6    "         );
            System.out.println("******************************************");
            System.out.println("              MENU              ");
            System.out.println("1 = BUSCAR LIVROS POR TITULO");
            System.out.println("2 = LISTAR LIVROS REGISTRADO");
            System.out.println("3 = LISTAR AUTORES REGISTRADOS ");
            System.out.println("4 = BUSCAR AUTORES POR ANO DE REFERÊNCIA");
            System.out.println("5 = LISTAR LIVROS POR IDIOMA");
            System.out.println("6 = SAIR");
            System.out.println("\nSELECIONE UMA OPÇÃO");
            menuNumber = scanner.nextInt();

            if (menuNumber == 1) {
                System.out.println("DIGITE O TITULO DO LIVRO");
                scanner.nextLine();
                var tituloLivro = scanner.nextLine();

                SelectLivroFromAPI(tituloLivro);
            }

            if (menuNumber == 2) {
                System.out.println("LIVROS REGISTRADOS");
                SelectAllLivro();
            }

            if (menuNumber == 3) {
                System.out.println("AUTORES REGISTRADOS");
                SelectAllAutor();
            }

            if (menuNumber == 4) {
                System.out.println("DIGITE O ANO QUE DESEJA CONSULTAR:");
                Integer anoSelected = scanner.nextInt();

                if (anoSelected <= 0){
                    System.out.println("ANO INVÁLIDO");
                }else{
                    SelectAutorVivos(anoSelected);
                }
            }

            if (menuNumber == 5) {
                System.out.println("1 = PORTUGUES");
                System.out.println("2 = INGLES");
                System.out.println("SELECIONE O IDIOMA:");
                int idiomaSelected = scanner.nextInt();

                if (idiomaSelected >= 1 || idiomaSelected <= 2 ) {
                    SelectIdiomaByName(idiomaSelected);
                }else{
                    System.out.println("IDIOMA SELECIONADO INVÁLIDO");
                }
            }
        }
    }

    private void InsertLivro(LivroDTO livroDados) {

        Optional<Livro> verificarLivro = livroRepository.findBytituloEqualsIgnoreCase(livroDados.titulo().toString());

        if (verificarLivro.isEmpty()){

            Livro livro = new Livro(livroDados);

            try {
                livroRepository.save(livro);
                System.out.println(livroDados.titulo().toString() + " inserido com sucesso!");
                System.out.println("----------------------------------");
            }catch (Exception e) {
                System.out.println("ERROR :" + e.getMessage());
            }
        }else{
            System.out.println("LIVRO JÁ PESQUISADO / CADASTRADO");
        }

    }

    private void InsertAutor(Integer autorId, String nome, Integer anoNascimento, Integer anoFalecimento) {
        Autor autor = new Autor(autorId,nome,anoNascimento,anoFalecimento);

        try {
            autorRepository.save(autor);
            System.out.println(autor.getNome() + " inserido com sucesso!");
            System.out.println("----------------------------------");

        } catch (Exception e) {
            System.out.println("ERROR :" + e.getMessage());
        }

    }

    private void SelectLivroFromAPI(String livrotitulo) {
        String ConversorDeDados = consumirAPI.obterDados(URL+livrotitulo.replace(" ","%20").toLowerCase());

        var books = conversorDeDados.obterDados(ConversorDeDados, ResultadoDTO.class);

        Optional<LivroDTO> livroSelecionado = books.livros().stream()
                .findFirst();

        if (livroSelecionado.isPresent()){
            LivroDTO livroDados = livroSelecionado.get();

            InsertLivro(livroDados);
            InsertAutor(livroDados.id(),livroDados.autores().get(0).nome().toString(),livroDados.autores().get(0).anoNascimento(),livroDados.autores().get(0).anoFalecimento());
        }
        else{
            System.out.println("NENHUM LIVRO ENCONTRADO");
        }
    }

    private void SelectAllLivro() {

        try {
            List<Livro> livros = livroRepository.findAll();

            if (livros.size() > 0){
                livros.forEach(System.out::println);
            }else{
                System.out.println("====== NENHUM LIVRO REGISTRADO =======");
            }

        } catch (Exception e) {
            System.out.println("ERROR :" + e.getMessage());
        }

    }

    private void SelectAllAutor() {

        try {
            List<Autor> autores = autorRepository.findAll();

            if (autores.size() > 0){
                autores.forEach(System.out::println);
            }else{
                System.out.println("====== NENHUM AUTOR REGISTRADO =======");
            }
        } catch (Exception e) {
            System.out.println("ERROR :" + e.getMessage());
        }

    }

    private void SelectAutorVivos(Integer anoFalecimento) {
        List<Autor> autorData = autorRepository.findByanoFalecimentoLessThan(anoFalecimento);

        if (!autorData.isEmpty()) {
            System.out.println(autorData);
            System.out.println("TOTAL DE AUTORES ENCONTRADOS NESTE PERÍODO: " + autorData.size());
        } else {
            System.out.println("====================================");
            System.out.println(" NENHUM AUTOR ENCONTRADO NESTE ANO" );
            System.out.println("====================================");
        }
    }

    private void SelectIdiomaByName(int idiomaSelect) {
        var idioma = "";

        if (idiomaSelect == 1)
            idioma = "br";
        else
            idioma = "en";

        List<Livro> livroIdioma = livroRepository.findByidiomaContainingIgnoreCase(idioma);

        if (!livroIdioma.isEmpty()) {
            System.out.println(livroIdioma);
            System.out.println("TOTAL DE LIVROS: " + livroIdioma.size());
        } else {
            System.out.println("====================================");
            System.out.println("NENHUM LIVRO ENCONTRADO NESTE IDIOMA");
            System.out.println("====================================");
        }
    }
}
