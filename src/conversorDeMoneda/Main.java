package conversorDeMoneda;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static final String EXIT_COMMAND = "SALIR";

    public static void main(String[] args) {
        Scanner inputUser = new Scanner(System.in);

        while (true) {
            String x = "empty";
            String y = "empty";
            double z = 0;

            System.out.println("""
                    Ingrese la divisa que desea convertir o escriba SALIR para finalizar:
                    
                    EUR - Euros
                    USD - Dólares
                    ARS - Pesos argentinos
                    BRL - Reales brasileños
                    """);

            // INPUT
            String currencyToConvert = inputUser.nextLine().toUpperCase();

            if (Objects.equals(currencyToConvert, EXIT_COMMAND)) {
                System.out.println("MUCHAS GRACIAS POR USAR https://www.exchangerate-api.com/docs/pair-conversion-requests NOS VEMOS PRONTO");
                break;
            } else {
                x = currencyToConvert;
                System.out.println("""
                        Ahora ingrese la divisa por la cual desea realizar la conversión o escriba SALIR para finalizar:
                        
                        EUR - Euros
                        USD - Dólares
                        ARS - Pesos argentinos
                        BRL - Reales brasileños
                        """);
                String changeDivisa = inputUser.nextLine().toUpperCase();
                if (Objects.equals(changeDivisa, EXIT_COMMAND)) {
                    System.out.println("MUCHAS GRACIAS POR USAR https://www.exchangerate-api.com/docs/pair-conversion-requests NOS VEMOS PRONTO");
                    break;
                } else {
                    y = changeDivisa;

                    System.out.println("""
                            Ingrese el valor que desea consultar o 9 para salir:
                            """);
                    double valueUser;
                    try {
                        valueUser = Double.parseDouble(inputUser.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Ingrese un valor numérico válido.");
                        continue;
                    }

                    if (valueUser == 9) {
                        System.out.println("MUCHAS GRACIAS POR USAR https://www.exchangerate-api.com/docs/pair-conversion-requests NOS VEMOS PRONTO");
                        break;
                    } else {
                        z = valueUser;
                    }
                }
            }

            // Setting URL
            String data = "https://v6.exchangerate-api.com/v6/3d3a8c46ed3aa968aa323588/pair/" + x + "/" + y + "/" + z;

            try {
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
                System.out.println("###########################programmed by Jonathan Ortega###########################");
                System.out.println("¿Desea realizar otra conversión?");
                System.out.println("""
                        Escriba:
                            - SI (para convertir otro valor)
                            - SALIR (para finalizar la operación)
                        """);

                String actionUser = inputUser.nextLine().toUpperCase();
                if (Objects.equals(actionUser, EXIT_COMMAND)) {
                    System.out.println("MUCHAS GRACIAS POR USAR https://www.exchangerate-api.com/docs/pair-conversion-requests NOS VEMOS PRONTO");
                    break;
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Error al realizar la conversión de divisas: " + e.getMessage());
            }
        }
    }
}
