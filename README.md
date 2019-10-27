# 2h2c-query-builder

# Example of use

This is basically a small develop to test how to work with interfaces and its implementations with method chaining.

```java 

   QueryBuilder queryBuilder = new QueryBuilderImpl();

        List data = queryBuilder
                .connect("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres")
                .select("nombre")
                .table("persona", "public")
                .where("nombre", "juan")
                .collectData();

        data.forEach(System.out::println);

```
