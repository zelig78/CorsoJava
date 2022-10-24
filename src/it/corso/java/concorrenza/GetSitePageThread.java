package it.corso.java.concorrenza;

import it.corso.java.tools.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class GetSitePageThread extends Thread {
    private String url;
    private String content;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GetSitePageThread(String url){
        super();
        this.url = url;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(this.url);

            URLConnection con = url.openConnection();

            InputStream is = con.getInputStream();

            setContent(Utils.getString(is));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
