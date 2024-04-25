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

            System.out.println("""
                    Ingrese la divisa que desea convertir o SALIR para salir del programa:
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
                var x = currencyToConvert;
                System.out.println("""
                    Ingrese la divisa por la cual desea realizar la conversion o escriba SALIR finalizar:
                    EUR - Euros
                    USD - Dolares
                    ARS - Pesos argentinos
                    BRL - Reales brasileños
                    """);
                Scanner inputUserSecond = new Scanner(System.in);
                String changeDivisa = inputUserSecond.nextLine();
                if (Objects.equals(changeDivisa, exit)) {
                    System.out.println("MUCHAS GRACIAS POR USAR https://www.exchangerate-api.com/docs/pair-conversion-requests NOS VEMOS PRONTO");
                    break;
                }
                else{
                    var y = changeDivisa;

                    System.out.println("""
                    Ingrese el valor que desea consultar o 9.0 para salir:
                    """);
                    Scanner inputUserThird = new Scanner(System.in);
                    double  valueUser = inputUserThird.nextDouble();
                    double exitCopy = 9.0;
                    if (Objects.equals(valueUser, exitCopy)) {
                        System.out.println("MUCHAS GRACIAS POR USAR https://www.exchangerate-api.com/docs/pair-conversion-requests NOS VEMOS PRONTO");
                        break;
                    }
                    else {
                        var z = valueUser;


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
                        System.out.println("Valor convertido " + conversion.getConversion_result());
                        System.out.println("By Jonathan Ortega");
                        System.out.println("###########################33");
                    }
                }
            }





        }


        //JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();


        // Gson gson = new Gson();


        //Setting Url


        // System.out.println(response.body());

    }

}
