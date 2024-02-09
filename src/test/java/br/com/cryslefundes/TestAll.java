package test.java.br.com.cryslefundes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.java.br.com.cryslefundes.repository.ClienteRepositoryTest;
import test.java.br.com.cryslefundes.repository.ProdutoRepositoryTest;
import test.java.br.com.cryslefundes.service.ClienteServiceTest;
import test.java.br.com.cryslefundes.service.ProdutoServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClienteRepositoryTest.class, ClienteServiceTest.class,
        ProdutoRepositoryTest.class, ProdutoServiceTest.class
})
public class TestAll {

}
