package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import json.Encoder;
import response.TableModResponse;
import service.FillService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {

            if (exchange.getRequestMethod().equalsIgnoreCase("post")) {

                FillService hlFillService = new FillService();
                String[] parameters = exchange.getRequestURI().toString().split("/");
                TableModResponse hlFillResponse;

                if (parameters.length == 3) {

                    hlFillResponse = hlFillService.fill(parameters[2], 4); // default #
                    if (hlFillResponse.isSuccess()) {

                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    } else {

                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }
                } else if (parameters.length == 4) {

                    try {

                        hlFillResponse = hlFillService.fill(parameters[2], Integer.parseInt(parameters[3]));
                        if (hlFillResponse.isSuccess()) {

                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                        } else {

                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                        }
                    } catch (NumberFormatException ex) {

                        hlFillResponse = new TableModResponse();
                        hlFillResponse.setSuccess(false);
                        hlFillResponse.setMessage("Error: Invalid generation parameter.");
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }
                } else {

                    hlFillResponse = new TableModResponse();
                    hlFillResponse.setSuccess(false);
                    hlFillResponse.setMessage("Error: Invalid number of parameters.");
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                }

                String jsonResp = Encoder.encode(hlFillResponse);
                OutputStream respBody = exchange.getResponseBody();
                writeString(jsonResp, respBody);
                respBody.close();

                success = true;
            }

            if (!success) {

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                exchange.getResponseBody().close();
            }
        } catch (IOException e) {

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
