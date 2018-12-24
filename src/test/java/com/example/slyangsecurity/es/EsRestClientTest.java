package com.example.slyangsecurity.es;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.*;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

@Slf4j
public class EsRestClientTest {

    private static JestClient esClient;

    static {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200")
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                //.defaultMaxTotalConnectionPerRoute(<YOUR_DESIRED_LEVEL_OF_CONCURRENCY_PER_ROUTE>)
                // and no more 20 connections in total
                //.maxTotalConnection(<YOUR_DESIRED_LEVEL_OF_CONCURRENCY_TOTAL>)
                .build());
        esClient = factory.getObject();
    }

    @Test
    public void saveDocument() throws IOException {
        //1: source json
        //2: soucre Map
        //3: POJO
        TestPojo source = new TestPojo("doc_id", "f_from_java", "f_from_java", 18L);
        Index index = new Index.Builder(source).index("first_index").type("this_index_type").build();
        DocumentResult documentResult = esClient.execute(index);
        log.info(documentResult.toString());
    }

    @Test
    public void GetDocumentById() throws IOException {
        Get get = new Get.Builder("first_index", "1").type("this_index_type").build();
        DocumentResult result = esClient.execute(get);
        log.info(result.toString());
    }

    @Test
    public void MGetDocumentByIdS() throws IOException {
        MultiGet multiGet = new MultiGet.Builder.ById("first_index", "this_index_type").addId("1").addId("2").build();
        JestResult result = esClient.execute(multiGet);
        log.info(result.toString());
    }


    @Test
    public void SearchDocumentByJson() throws IOException {

        String jsonQuery = "";        // todo your json
        Search search = new Search.Builder(jsonQuery)
                .addIndex("first_index")
                .addType("this_index_type")
                .build();
        SearchResult result = esClient.execute(search);
        log.info(result.toString());
    }

    // TODO
    // https://github.com/searchbox-io/Jest/tree/master/jest
    @Test
    public void SearchDocumentByTemplate() throws IOException {

        String query = "";

        Search search = new Search.TemplateBuilder(query)
                .addIndex("first_index")
                .addType("this_index_type")
                .build();
        SearchResult result = esClient.execute(search);
        log.info(result.toString());

    }

    @Test
    public void SearchDocumentSearchSourceBuilder() throws IOException {

        // org.elasticsearch.search.builder
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("user", "kimchy"));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("twitter")
                .addType("tweet")
                .build();
        SearchResult result = esClient.execute(search);
        log.info(result.toString());

        // 获得结果
        List<SearchResult.Hit<TestPojo, Void>> hits = result.getHits(TestPojo.class);
        List<TestPojo> articles = result.getSourceAsObjectList(TestPojo.class);
        // es 复杂查询
        // https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl.html
    }


    // 简单查询
    //Match Query
    @Test
    public void SearchMatchQuery() throws IOException {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(
                QueryBuilders.matchQuery("last_name", "is this")
        );

        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("first_index")
                .addType("this_index_type")
                .build();
        SearchResult result = esClient.execute(search);
        log.info(result.toString());
    }

    //Match Phrase Query
    @Test
    public void SearchMatchPhraseQuery() throws IOException {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(
                QueryBuilders.matchPhraseQuery("last_name", "this is")
        );

        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("first_index")
                .addType("this_index_type")
                .build();
        SearchResult result = esClient.execute(search);
        log.info(result.toString());

    }

    // Query String Query  todo
    @Test
    public void SearchQueryStringQuery() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(
                // QueryBuilders.queryStringQuery("last_name", "this is")
        );
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("first_index")
                .addType("this_index_type")
                .build();
        SearchResult result = esClient.execute(search);
        log.info(result.toString());
    }

    // Query BoolQuery  todo
    @Test
    public void SearchBoolQuery() throws IOException {
    }


    // Aggregation 聚合查询
    @Test
    public void AggregationsTest() throws IOException {


    }


}
