package handler;

import java.io.*;
import java.net.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import com.sun.net.httpserver.*;

public class DefaultHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {

            Path pathToFile;
            String requestedURI = exchange.getRequestURI().toString();

            if (requestedURI.equals("/")) {
                pathToFile = FileSystems.getDefault().getPath("web/index.html");
            } else {
                pathToFile = FileSystems.getDefault().getPath("web" + requestedURI);
            }

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            Files.copy(pathToFile, exchange.getResponseBody());
            exchange.getResponseBody().close();

        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }

    }
}