package kommunikasjon;

import java.io.Serializable;

/**
 * Created by Bror on 11.12.2016.
 */
public class Mail implements Serializable {

    String content;

    public Mail (String content) {
        this.content = content;
    }

    public String readContent(){
        return this.content;
    }

}
