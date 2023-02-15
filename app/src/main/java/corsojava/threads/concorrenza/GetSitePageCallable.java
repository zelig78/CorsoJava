package corsojava.threads.concorrenza;

import corsojava.tools.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class GetSitePageCallable implements Callable<String> {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public GetSitePageCallable(String url){
        super();
        this.url = url;
    }

    @Override
    public String call() throws Exception {
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
