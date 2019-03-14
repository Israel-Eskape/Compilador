/**
 *
 * @author Israel Eskape
 */
import static java.awt.PageAttributes.MediaType.C;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
public class Compilador
{
    String texto;
    String cadena="";
    private int indice=0;
    private String buffer;
    private Pattern nVariable = Pattern.compile("^[a-z]+[0-9]*$");
    public Compilador(){
      analizador();
    }

    public void leer()
    {
        String archivo="C:\\Users\\Israel\\Desktop\\Compilador\\Eskape.txt";
        try{
            File f;
            FileReader leerArchivo;
            f = new File(archivo);
            leerArchivo = new FileReader(f);
            BufferedReader br = new BufferedReader(leerArchivo);
            String aux="";
            while(true)
            {
                aux=br.readLine();
                if(aux!=null)
                    cadena=cadena+aux+"\n";
                else
                    break;
            }
            br.close();
            leerArchivo.close();

        }catch(IOException e){
            System.out.println("Error:"+e.getMessage());
        }
    }

    public void analizador(){
        leer();
        texto=cadena;
        int tamanio=texto.length();
        while(indice<tamanio){
            if (texto.charAt(indice)== ' ' || texto.charAt(indice)== '\t' || texto.charAt(indice)== '\n'){
                indice=indice;
            }else if(texto.charAt(indice)=='['){
                System.out.println("Tokens de: [");
            }else if(texto.charAt(indice)==']'){
                System.out.println("Tokens de: ]");
            }else if(texto.charAt(indice)=='+'){
                System.out.println("Tokens de: +");
            }else if(texto.charAt(indice)=='-'){
                System.out.println("Tokens de: -");
            }else if(texto.charAt(indice)=='='){
                if(texto.charAt(indice+1)=='='){
                    System.out.println("Tokens de comparacion: ==");
                    indice++;
                }else
                    System.out.println("Tokens de asignación: =");
            }else if(texto.charAt(indice)=='{'){
                System.out.println("Tokens de: {");
            }else if(texto.charAt(indice)=='}'){
                System.out.println("Tokens de: }");
            }else if('0'<= texto.charAt(indice) && texto.charAt(indice) <='9' ){
                buffer= "";
                while(0<= texto.charAt(indice) && texto.charAt(indice)<='9'){
                    buffer = buffer+texto.charAt(indice);
                    indice = indice+1;
                }
                indice = indice-1;
                System.out.println("Número entero:   "+buffer);
            }else if (texto.charAt(indice)=='#'){
                while(texto.charAt(indice)!='\n')
                    indice = indice+1;
            }else if ('a'<= texto.charAt(indice)&& texto.charAt(indice)<='z'){
                buffer = "";

                while('a'<=texto.charAt(indice) && texto.charAt(indice)<='z' || '0'<=texto.charAt(indice) && texto.charAt(indice)<='9'){
                    buffer = buffer+texto.charAt(indice);
                    indice = indice+1;
                }
                Matcher mat=nVariable.matcher(buffer);
                if(buffer.indexOf("code")!=-1){
                    System.out.println("Palabra reservada: "+buffer);
                }
                else if(buffer.indexOf("si")!=-1){
                    System.out.println("Palabra reservada: "+buffer);
                }
                else if(buffer.indexOf("print")!=-1){
                    System.out.println("Palabra reservada: "+buffer);
                }
                else if(mat.matches()){
                    System.out.println("archivo de variable: "+buffer);
                }else{
                    System.out.println("Palabra no reconocida: "+buffer);
                }
                indice=indice-1;
            }else if(texto.charAt(indice)=='"'){
                buffer="";
                String bufferVariable = "";
                buffer = buffer+texto.charAt(indice);
                indice = indice+1;
                while(texto.charAt(indice)!='"'){
                  if (texto.charAt(indice) =='%'){
                    if(texto.charAt(indice+1)=='v' ){
                      bufferVariable ="%v";
                      indice++;
                    }
                    indice++;
                  }
                    buffer = buffer+texto.charAt(indice);

                    indice = indice+1;
                }
                buffer=buffer+'"';
                System.out.println("Cadena de texto: "+buffer);
                System.out.println("Tokens de imprimir variable  "+bufferVariable);
            }else{
                System.out.println("Tokens no reconocido: "+texto.charAt(indice));
            }
            indice=indice+1;
        }
    }
    public static void main(String [] args){
        Compilador equipo= new Compilador();
    }
}
