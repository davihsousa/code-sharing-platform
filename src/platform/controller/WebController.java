package platform.controller;

import freemarker.template.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import platform.model.Code;
import platform.model.CodeHash;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class WebController {

    private Configuration cfg;

    public void setTemplateConfiguration() throws IOException {
        this.cfg = new Configuration(Configuration.VERSION_2_3_30);
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
//        cfg.setDirectoryForTemplateLoading(new File("./Code Sharing Platform/task/src/platform/templates"));
        cfg.setDirectoryForTemplateLoading(new File("./src/platform/templates"));
    }

    @GetMapping(value = "/code/{id}")
    public String getCodeById(@PathVariable int id) throws IOException, TemplateException {
        setTemplateConfiguration();
        HashMap<String, Object> root = new HashMap<>();
        List<Code> singleCode = new ArrayList<>();
        singleCode.add(CodeHash.getCodeHashMap(id));
        root.put("codeList", singleCode);
        Template template = cfg.getTemplate("code.ftl");
        StringWriter out = new StringWriter();
        template.process(root, out);
        return out.toString();
    }

    @GetMapping(value = "/code/latest")
    public String getCodeLatest() throws IOException, TemplateException {
        setTemplateConfiguration();
        HashMap<String, Object> root = new HashMap<>();
        root.put("codeList", CodeHash.getLatest());
        Template template = cfg.getTemplate("codeLatest.ftl");
        StringWriter out = new StringWriter();
        template.process(root, out);
        return out.toString();
    }

    @GetMapping(value = "code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String newCode() {
        return "<html>\n" +
                "<header>\n" +
                "  <title>Create</title>\n" +
                "</header>\n" +
                "<script>\n" +
                "  function send() {\n" +
                "    let object = {\n" +
                "      \"code\": document.getElementById(\"code_snippet\").value\n" +
                "    };\n" +
                "    let json = JSON.stringify(object);\n" +
                "    let xhr = new XMLHttpRequest();\n" +
                "    xhr.open(\"POST\", '/api/code/new', false)\n" +
                "    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');\n" +
                "    xhr.send(json);\n" +
                "    if (xhr.status == 200) {\n" +
                "      alert(\"Success!\");\n" +
                "    }\n" +
                "  }\n" +
                "</script>\n" +
                "<style>\n" +
                "  #code_snippet {\n" +
                "    background: #f4f4f4;\n" +
                "    border: 1px solid #ddd;\n" +
                "    border-left: 3px solid #f36d33;\n" +
                "    color: #666;\n" +
                "    page-break-inside: avoid;\n" +
                "    font-family: monospace;\n" +
                "    font-size: 15px;\n" +
                "    line-height: 1.6;\n" +
                "    margin-top: 1.5px;\n" +
                "    margin-bottom: 1.6em;\n" +
                "    width: 400px;\n" +
                "    max-width: 100%;\n" +
                "    overflow: auto;\n" +
                "    padding: 1em 1.5em;\n" +
                "    display: block;\n" +
                "    word-wrap: break-word;\n" +
                "  }\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "  <textarea id=\"code_snippet\">// write your code here\n" +
                "  </textarea>\n" +
                "  <button id=\"send_snippet\" type=\"submit\" onclick=\"send()\">Submit</button>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }


}
