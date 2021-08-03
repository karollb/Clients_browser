package pl.edu.wszib.client_browser.session;

import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Setter
public class SessionObject {
    private String info = null;

    public String getInfo() {
        String tmp = this.info;
        this.info = null;
        return tmp;
    }
}
