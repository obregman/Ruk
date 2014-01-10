package HTTP;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * 
 * @author kbaig
 *
 */
public class HTTPConnector {

   /**
     * @param address, parameters
     */
    public static String getData(String address, String queryString) throws Exception {
        String result = null;
        if (address.startsWith("http://")){
            try {
                String urlStr = address;
                
                if (queryString != null && queryString.length () > 0)    
                	urlStr += "?" + queryString;
                
                HttpURLConnection httpUrlConnection = (HttpURLConnection) (new URL(urlStr)).openConnection();
                
                // Get the response
                InputStream is = httpUrlConnection.getInputStream();
                InputStreamReader sr = new InputStreamReader(is);
                BufferedReader rd = new BufferedReader(sr);
                StringBuilder data = new StringBuilder(150);
                String line;
                while ((line = rd.readLine()) != null)    data.append(line);
                rd.close();
                result = data.toString();
                httpUrlConnection.disconnect();
	                
            } 
            catch (FileNotFoundException e) {
            	throw new Exception(e);
            }
            catch (Exception e){
            	throw new Exception(e);
            }
        }
        return result;
    }


    /**
     * 
     * @param address, dataToBePosted
     * @throws MalformedURLException
     * @throws IOException
     * @throws ProtocolException
     */
    public static String postData(String address,String dataToBePosted) throws MalformedURLException,IOException,ProtocolException{
        
        /** set up the http connection parameters */
        HttpURLConnection    urlc = (HttpURLConnection) (new URL(address)).openConnection();
        urlc.setRequestMethod("POST");
        urlc.setDoOutput(true);
        urlc.setDoInput(true);
        urlc.setUseCaches(false);
        urlc.setAllowUserInteraction(false);
        urlc.setRequestProperty("Content-type", "text/xml; charset=" + "UTF-8");
        
        /** post the data */
        OutputStream out = null;
        out = urlc.getOutputStream();
        Writer writer = new OutputStreamWriter(out, "UTF-8");
        writer.write(dataToBePosted);
        writer.close();
        out.close();
        
        /** read the response back from the posted data */
        BufferedReader bfreader = new BufferedReader(new InputStreamReader(urlc.getInputStream())); 
        StringBuilder builder = new StringBuilder(100);
        String line = "";
        while ((line = bfreader.readLine()) != null) {
            builder.append(line);
        }
        bfreader.close();
        
        /** return the response back from the POST */
        return builder.toString();
    }
    
    /**
     * 
     * @param address, dataToBePosted
     * @throws MalformedURLException
     * @throws IOException
     * @throws ProtocolException
     */
    public static void deleteData(String address,String dataToBePosted) throws MalformedURLException,IOException,ProtocolException{
    	
    	URL url = new URL(address);
    	HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
    	urlc.setDoOutput(true);
    	urlc.setRequestProperty(
    	    "Content-Type", "application/x-www-form-urlencoded" );
    	urlc.setRequestMethod("DELETE");
    	urlc.connect();
    }
    
}