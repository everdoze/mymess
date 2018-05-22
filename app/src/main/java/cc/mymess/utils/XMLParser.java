package cc.mymess.utils;

import android.os.Handler;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XMLParser {

    // constructor
    public XMLParser() {

    }

    /**
     * Getting XML from URL making HTTP request
     * @param url string
     * */
    public static String getXmlFromUrl(String url, final Handler h) throws IOException {
        final String xml = null;

            OkHttpClient httpClient = new OkHttpClient();
            // defaultHttpClient
            //DefaultHttpClient httpClient = new DefaultHttpClient();
            //HttpPost httpPost = new HttpPost(url);
            Request request = new Request.Builder()
                            .url(url)
                            .build();


            Call newCall = httpClient.newCall(request);
//            newCall.enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    e.printStackTrace();
//                }
//
//                @Override
//                public void onResponse(Call call, final Response response) throws IOException {
//                    if (!response.isSuccessful()) {
//                        throw new IOException("Unexpected code " + response);
//                    } else {
//                        Message msg = new Message();
//                        Bundle data = new Bundle();
//                        data.putString("response",response.body().string());
//                        msg.setData(data);
//                        h.sendMessage(msg);// do something wih the result
//                    }
//                }});
           Response response = newCall.execute();
            //Response response = httpClient.newCall(request).enqueue(). ;

            //HttpResponse httpResponse = httpClient.execute(httpPost);
           // HttpEntity httpEntity = httpResponse.getEntity();
            //xml = EntityUtils.toString(httpEntity);

        // return XML
        return response.body().string();//xml;
    };

    /**
     * Getting XML DOM element
     * @param XML string
     * */
    public Document getDomElement(String xml){
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            DocumentBuilder db = dbf.newDocumentBuilder();

            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            doc = db.parse(is);

        } catch (ParserConfigurationException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (SAXException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e("Error: ", e.getMessage());
            return null;
        }

        return doc;
    }

    /** Getting node value
     * @param elem element
     */
    public final String getElementValue( Node elem ) {
        Node child;
        if( elem != null){
            if (elem.hasChildNodes()){
                for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
                    if( child.getNodeType() == Node.TEXT_NODE  ){
                        return child.getNodeValue();
                    }
                }
            }
        }
        return "";
    }


    public String getValue(Element item, String str) {
        NodeList n = item.getElementsByTagName(str);
        return this.getElementValue(n.item(0));
    }
}