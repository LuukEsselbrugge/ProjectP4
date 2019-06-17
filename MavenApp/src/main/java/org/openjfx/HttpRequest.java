package org.openjfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    private static final String USER_AGENT = "Mozilla/5.0";

    public HttpRequest(){

    }

    /**
     * Do a httprequest on a specific url and search term.
     * @param search , searchterm trying to send.
     * @param url , url to send it to.
     * @return , returns a jsonString.
     * @throws IOException
     */
    public static String sendPOST(String search, String url) throws IOException {
        URL obj = new URL(url);

        assert obj != null;
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        String POST_PARAMS = "search=" + search;
        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
            String failPost = "POST request not worked";
            return failPost;
        }
    }


}
