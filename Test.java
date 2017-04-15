package test;
import java.io.*;
import java.net.*;
import java.util.*;

class Test {
    public static void main(String[] args) throws Exception {
    //	PrintStream out = new PrintStream(new FileOutputStream("C:/Users/BUDAY/Desktop/page5.html"));
		//System.setOut(out);
        URL url = new URL("http://journalfinder.elsevier.com/index.cfm/searchResult");
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("form_submit_action", "search");
        params.put("paper_title", "novelty");
        params.put("paper_abstract" , "novelty detection");
        params.put("thesauri", "cpx");
      //  params.put("message", "Shark attacks in Botany Bay have gotten out of control. We need more defensive dolphins to protect the schools here, but Mayor Porpoise is too busy stuffing his snout with lobsters. He's so shellfish.");

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        System.out.println(postData);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c);
    }
}