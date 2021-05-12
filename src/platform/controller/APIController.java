package platform.controller;

import org.springframework.web.bind.annotation.*;
import platform.model.Code;
import platform.model.CodeHash;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class APIController {

    @GetMapping(path = "/api/code/{id}")
    public Code getCodeById(@PathVariable int id) {
        return CodeHash.getCodeHashMap(id);
    }

    @GetMapping(path = "/api/code/latest")
    public List<Code> getCodeLatest() {
        return CodeHash.getLatest();
    }

    @PostMapping(path = "/api/code/new", consumes = "application/json")
    public String newCode(@RequestBody Code code) {
        code.setDate(LocalDateTime.now());
        return CodeHash.setCodeHashMap(code);
        }

}
