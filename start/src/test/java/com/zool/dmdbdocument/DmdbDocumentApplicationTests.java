package com.zool.dmdbdocument;

import com.zool.dmdbdocument.api.DmdbDocService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DmdbDocumentApplicationTests {

    @Autowired
    private DmdbDocService dmdbDocService;

    @Test
    void contextLoads() {
        dmdbDocService.generateDoc();
    }

}
