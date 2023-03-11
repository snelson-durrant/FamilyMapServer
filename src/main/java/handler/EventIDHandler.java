package handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import json.Encoder;
import response.EventIDResponse;
import response.TableModResponse;
import service.EventIDService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class EventIDHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {

            if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {

                    String authToken = reqHeaders.getFirst("Authorization");
                    if (authToken != null) {

                        EventIDService hlEventIDService = new EventIDService();
                        String[] parameters = exchange.getRequestURI().toString().split("/");

                        if (parameters.length == 3) {

                            EventIDResponse hlEventIDResponse = hlEventIDService.eventID(authToken, parameters[2]);
                            String jsonResp;

                            if (hlEventIDResponse.isSuccess()) {

                                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                                jsonResp = Encoder.encode(hlEventIDResponse);
                            } else {

                                TableModResponse errResponse = new TableModResponse();
                                errResponse.setMessage(hlEventIDResponse.getMessage());
                                errResponse.setSuccess(false);
                                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                                jsonResp = Encoder.encode(errResponse);
                            }

                            OutputStream respBody = exchange.getResponseBody();
                            writeString(jsonResp, respBody);
                            respBody.close();
                        } else {

                            TableModResponse hlEventIDResponse = new TableModResponse();
                            hlEventIDResponse.setSuccess(false);
                            hlEventIDResponse.setMessage("Error: Invalid number of parameters.");

                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                            String jsonResp = Encoder.encode(hlEventIDResponse);
                            OutputStream respBody = exchange.getResponseBody();
                            writeString(jsonResp, respBody);
                            respBody.close();
                        }
                    } else {

                        TableModResponse authErrResponse = new TableModResponse();
                        authErrResponse.setSuccess(false);
                        authErrResponse.setMessage("Error: Invalid authtoken.");

                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        String jsonResp = Encoder.encode(authErrResponse);
                        OutputStream respBody = exchange.getResponseBody();
                        writeString(jsonResp, respBody);
                        respBody.close();
                    }

                    success = true;
                }
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

