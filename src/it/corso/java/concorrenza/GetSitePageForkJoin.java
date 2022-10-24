package it.corso.java.concorrenza;

import it.corso.java.tools.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.RecursiveTask;

public class GetSitePageForkJoin extends RecursiveTask<String> {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GetSitePageForkJoin(String url){
        super();
        this.url = url;
    }

    @Override
    protected String compute() {
        try {
            URL url = new URL(this.url);

            URLConnection con = url.openConnection();

            InputStream is = con.getInputStream();

            return Utils.getString(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
