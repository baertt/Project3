package dataBaseConstructor;

//import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
//import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class ConstructJson {
	public void loadJson (String url, String filename) throws FileNotFoundException {

		try {
			URL urlObject = new URL(url);
			ReadableByteChannel rbc = Channels.newChannel(urlObject.openStream());
			FileOutputStream fos = new FileOutputStream(filename + ".json");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
//			URLConnection uc = urlObject.openConnection();
//			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
//			String inputLine = null;
//			while ((inputLine = in.readLine()) != null) {
//
//			}
//			in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public void checkFile (String filename) {
//
//	}

}
