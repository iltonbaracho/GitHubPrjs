/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manipulaarquivos;


import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;


/**
 *
 * @author Ilton
 */
public class CopiaArvore extends SimpleFileVisitor {

        private Path origem;
        private Path destino;

        public CopiaArvore(Path origem, Path destino) {
            this.origem = origem;
            this.destino = destino;
            
        }

        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException {
            copiaPath(dir);
            return FileVisitResult.CONTINUE;
        }

        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                throws IOException {
            copiaPath(file);
            return FileVisitResult.CONTINUE;
        }

        private void copiaPath(Path entrada) throws IOException {
            // encontra o caminho equivalente na árvore de cópia
            Path novoDiretorio = destino.resolve(origem.relativize(entrada));
            Files.copy(entrada, novoDiretorio);
        }    
        
    public static void main(String[] args) throws IOException {
        Path origem = Paths.get("C:\\Ilton\\Inst");
        Path destino = Paths.get("C:\\Ilton\\fez");        
        Files.walkFileTree(origem, new CopiaArvore(origem, destino));      
    }
}
