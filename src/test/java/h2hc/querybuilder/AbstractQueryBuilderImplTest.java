package h2hc.querybuilder;

import h2hc.querybuilder.core.QueryBuilderImpl;
import h2hc.querybuilder.core.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AbstractQueryBuilderImplTest {

    @Test
    public void testShould() {


        QueryBuilder queryBuilder = new QueryBuilderImpl();

        List data = queryBuilder
                .connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")
                .select("nombre")
                .table("persona", "public")
                .where("nombre", "juan")
                .collectData();

        data.forEach(System.out::println);


    }


}
