package monitordata.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/14.
 */
@RestController
public class LiftController {

    @RequestMapping("/Lift/GetLiftStatus")
    public void GetLiftStatus(HttpServletResponse httpServletResponse) throws IOException {
        final String url = "http://192.168.33.203:8090/Lift/GetLiftStatus";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        Map<String, String> param = null;
        CloseableHttpResponse response = null;
        InputStream inputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                inputStream = response.getEntity().getContent();
            }
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(httpServletResponse.getOutputStream());
            byte[] buff = new byte[1024];
            int bytesRead = 0;

            while(-1 !=(bytesRead = (bis.read(buff, 0, buff.length)))){
                bos.write(buff, 0, buff.length);
            }
            /*byte[] data = new byte[1024];
            int len = 0;
            if (inputStream != null) {
                try {
                    while ((len =inputStream.read(data)) != -1) {
                        outputStream.write(data, 0,len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    if(outputStream != null){
                        try {
                            outputStream.close();
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if( null != response){
                response.close();
            }

            if(null != inputStream){
                inputStream.close();
            }

            if(bis != null){
                bis.close();
            }
            if(bos != null){
                bos.close();
            }
        }
        return;
    }
}
