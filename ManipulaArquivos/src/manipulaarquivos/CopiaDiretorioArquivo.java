/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulaarquivos;

import java.io.*;
import java.nio.file.*;


/**
 *
 * @author Ilton
 */
public class CopiaDiretorioArquivo {

    public static void copiarArquivos(Path origem, Path destino) throws IOException {
        // se é um diretório, tentamos criar. se já existir, não tem problema.
        if (Files.isDirectory(origem)) {
            Files.createDirectories(destino);

            // listamos todas as entradas do diretório
            DirectoryStream<Path> entradas = Files.newDirectoryStream(origem);

            for (Path entrada : entradas) {
                // para cada entrada, achamos o arquivo equivalente dentro de cada arvore
                Path novaOrigem = origem.resolve(entrada.getFileName());
                Path novoDestino = destino.resolve(entrada.getFileName());

                // invoca o metodo de maneira recursiva
                copiarArquivos(novaOrigem, novoDestino);
            }
        } else {
            // copiamos o arquivo
            Files.copy(origem, destino);
        }
    }
    
    public static void main(String[] args) throws IOException {
        Path origem = Paths.get("C:\\Ilton\\Inst");
        Path destino = Paths.get("C:\\Ilton\\fez");        
        CopiaDiretorioArquivo.copiarArquivos(origem, destino);
        
    }
}

