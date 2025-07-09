
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    public Moneda obtenerMonedas(String base) {
        String url = "https://v6.exchangerate-api.com/v6/1b453b41a2626ea39ce4121a/latest/" + base;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return new Gson().fromJson(response.body(), Moneda.class);
            } else {
                System.out.println("Error al consultar API. Código: " + response.statusCode());
                return null;
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }
}