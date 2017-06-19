package com.apis.entity.fixture.template;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.TemplateHolder;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.apis.entity.Client;

public class ClientTemplateLoader implements TemplateLoader {

    public static final String VALID = "valid";
    public static final String INVALID = "invalid";

    public void load() {

        TemplateHolder th =
            Fixture.of(Client.class);

        th.addTemplate(VALID, new Rule() {{
            add("id", random(Long.class, range(1, 1000)));
            add("name", name());
            add("screenName", random(String.class, "${name}${id}"));
            add("email", random(String.class, "${screenName}@gmail.com"));
            add("birthday", instant("27 years ago"));
        }});

        th.addTemplate(INVALID).inherits(VALID, new Rule() {{
//            add("name", "");
            add("birthday", null);
        }});

    }

}
