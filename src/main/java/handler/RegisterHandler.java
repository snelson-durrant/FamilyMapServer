package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import json.*;
import request.RegisterRequest;
import response.RegisterResponse;
import response.TableModResponse;
import service.RegisterService;

import java.io.*;
import java.net.HttpURLConnection;

public class RegisterHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        boolean success = false;

        try {

            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                Reader reader = new InputStreamReader(exchange.getRequestBody());
                RegisterRequest registerReqObj = Decoder.decodeRegisterReq(reader);

                // TODO check for errors

                RegisterService hlRegisterService = new RegisterService();
                RegisterResponse hlRegisterResponse = hlRegisterService.register(registerReqObj);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                String jsonResp = Encoder.encode(hlRegisterResponse);
                OutputStream respBody = exchange.getResponseBody();
                writeString(jsonResp, respBody);
                respBody.close();

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

