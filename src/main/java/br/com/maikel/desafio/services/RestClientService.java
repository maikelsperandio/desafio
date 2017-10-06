package br.com.maikel.desafio.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class RestClientService {

	public String getDistancias() throws Exception{
		URL url = new URL("http://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=4.5854403875,-60.195716148&destinations=-33.6875668687,-53.4626751417");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");

		if (con.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
		String output;
		StringBuilder ret = new StringBuilder();

		while ((output = br.readLine()) != null) {
			ret.append(output);
		}

		con.disconnect();
		return ret.toString();
	}
}
