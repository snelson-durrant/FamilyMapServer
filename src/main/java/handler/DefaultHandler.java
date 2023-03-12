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

            File addrPage = new File(pathToFile.toUri());
            if (!addrPage.isFile()) {

                // sends 404 error page in case of invalid website file
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
                pathToFile = FileSystems.getDefault().getPath("web/HTML/404.html");
                Files.copy(pathToFile, exchange.getResponseBody());
                exchange.getResponseBody().close();
            } else {

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                Files.copy(pathToFile, exchange.getResponseBody());
                exchange.getResponseBody().close();
            }
        } catch (IOException e) {

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }
}