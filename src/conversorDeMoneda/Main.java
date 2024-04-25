package conversorDeMoneda;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner inputUser = new Scanner(System.in);
        String exit = "SALIR";

        while (true) {
            String x = "empty";
            String y = "empty";
            double z = 0;

            System.out.println("""
                    Ingrese la divisa que desea convertir o escriba SALIR para finalzar:
                    
                    EUR - Euros
                    USD - Dolares
                    ARS - Pesos argentinos
                    BRL - Reales brasileños
                    
                    """);

            // INPUT
            String currencyToConvert = inputUser.nextLine();


            if (Objects.equals(currencyToConvert, exit)) {
                System.out.println("MUCHAS GRACIAS POR USAR https://www.exchangerate-api.com/docs/pair-conversion-requests NOS VEMOS PRONTO");
                break;
            }
            else {
                x = currencyToConvert;
                System.out.println("""
                    Ahora ingrese la divisa por la cual desea realizar la conversion o escriba SALIR para finalizar:
                    
                    EUR - Euros
                    USD - Dolares
                    ARS - Pesos argentinos
                    BRL - Reales brasileños
                    """);
                String changeDivisa = inputUser.nextLine();
                if (Objects.equals(changeDivisa, exit)) {
                    System.out.println("MUCHAS GRACIAS POR USAR https://www.exchangerate-api.com/docs/pair-conversion-requests NOS VEMOS PRONTO");
                    break;
                }
                else{
                    y = changeDivisa;

                    System.out.println("""
                    Ingrese el valor que desea consultar o 9 para salir:
                    """);
                    double  valueUser = inputUser.nextDouble();
                    double exitCopy = 9;
                    if (Objects.equals(valueUser, exitCopy)) {
                        System.out.println("MUCHAS GRACIAS POR USAR https://www.exchangerate-api.com/docs/pair-conversion-requests NOS VEMOS PRONTO");
                        break;
                    }
                    else {
                        z = valueUser;
                        inputUser.nextLine(); // Consumir el carácter de nueva línea
                    }
                }
            }

            // Setting URL
            String data = "https://v6.exchangerate-api.com/v6/3d3a8c46ed3aa968aa323588/pair/" + x + "/" + y + "/" + z;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(data))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // CODIFICACION DE DATOS
            String json = response.body();
            Gson gson = new Gson();
            Conversion conversion = gson.fromJson(json, Conversion.class);
            System.out.println("Conversion: " + x + " a " + y);
            System.out.println("Resultado:  " + conversion.getConversion_result());
            System.out.println("By Jonathan Ortega");
            System.out.println("###########################");

        }

    }

}
