package handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import json.Encoder;
import response.PersonIDResponse;
import response.PersonResponse;
import response.TableModResponse;
import service.PersonIDService;

import java.io.*;
import java.net.HttpURLConnection;

public class PersonIDHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {

            if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {

                    String authToken = reqHeaders.getFirst("Authorization");
                    // TODO add here
                    if (authToken.equals("afj232hj2332")) {

                        PersonIDService hlPersonIDService = new PersonIDService();
                        String[] parameters = exchange.getRequestURI().toString().split("/");

                        if (parameters.length == 3) {

                            PersonIDResponse hlPersonIDResponse = hlPersonIDService.personID(authToken, parameters[2]);

                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                            String jsonResp = Encoder.encode(hlPersonIDResponse);
                            OutputStream respBody = exchange.getResponseBody();
                            writeString(jsonResp, respBody);
                            respBody.close();

                        } else {

                            TableModResponse hlPersonIDResponse = new TableModResponse();
                            hlPersonIDResponse.setSuccess(false);
                            hlPersonIDResponse.setMessage("Error: Invalid number of parameters.");

                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                            String jsonResp = Encoder.encode(hlPersonIDResponse);
                            OutputStream respBody = exchange.getResponseBody();
                            writeString(jsonResp, respBody);
                            respBody.close();
                        }

                    } else {

                        TableModResponse authErrResponse = new TableModResponse();
                        authErrResponse.setSuccess(false);
                        authErrResponse.setMessage("Error: Invalid auth token.");

                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        String jsonResp = Encoder.encode(authErrResponse);
                        OutputStream respBody = exchange.getResponseBody();
                        writeString(jsonResp, respBody);
                        respBody.close();

                    }
                }

                success = true;
            }

            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}

