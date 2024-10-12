import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ConsultaDeApi {
    private String api_key = "646268e98f69c42222306a87";
    private String url_str = "https://v6.exchangerate-api.com/v6/" + api_key + "/latest/USD";

    //Metodo para realizar la conversión
    public double realizarConversion(String opcionMoneda, double cantidad) {
        Moneda moneda = buscarMoneda();
        Map<String, Double> tasas = (Map<String, Double>) moneda.conversion_rates();
        double tasaConversion = 0;

        switch (opcionMoneda) {
            case "1": //Dolar a Peso argentino
                tasaConversion = tasas.get("ARS");
                break;
            case "2": //Peso argentino a Dolar
                tasaConversion = 1 / tasas.get("ARS");
                break;
            case "3": //Dolar a Real brasileño
                tasaConversion = tasas.get("BRL");
                break;
            case "4": //Dolar a Peso colombiano
                tasaConversion = tasas.get("COP");
                break;
            case "5": //Peso colombiano a Dolar
                tasaConversion = 1 / tasas.get("COP");
                break;
            default:
                throw new RuntimeException("Opción no válida.");
        }

        return cantidad * tasaConversion;
    }

    // Metodo para buscar la moneda base USD y obtener todas las tasas de conversion
    public Moneda buscarMoneda() {
        URI direccion = URI.create(url_str);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al conectar con la API: " + e.getMessage());
        }
    }
}
