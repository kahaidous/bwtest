package com.example.burningwave.bw;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class Controller {
    @GetMapping("/generate/{classname}")
    void generate(@PathVariable String classname){
        SourceGenerationTester.main(classname);
    }

    @GetMapping("/compile/{classname}")
    void compile(@PathVariable String classname) throws ClassNotFoundException, InterruptedException {
        SourceCompilationTester.main(classname);
    }
}
