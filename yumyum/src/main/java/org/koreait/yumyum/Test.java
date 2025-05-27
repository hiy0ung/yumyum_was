package org.koreait.yumyum;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

//@Component
public class Test implements CommandLineRunner {

    @Value("${user.dir}")
    private String path;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(path + "/upload");
    }
}
