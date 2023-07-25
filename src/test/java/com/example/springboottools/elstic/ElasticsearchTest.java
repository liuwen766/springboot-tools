package com.example.springboottools.elstic;


import com.alibaba.fastjson.JSON;
import com.example.springboottools.entity.elstic.UserElastic;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.user.User;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 测试ES的增删改查
 *
 * @author Promsing(张有博)
 * @version 1.0.0
 * @since 2022/1/26 - 20:57
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void check() {
        System.err.println("template:" + client);
    }

    /*创建索引*/
    @Test
    public void test01() throws Exception {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("demo01");
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse.isAcknowledged());//查看是否创建成功
    }

    /*删除索引*/
    @Test
    public void test02() throws Exception {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("demo01");
        AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    /*判断索引是否存在*/
    @Test
    public void test03() throws Exception {
        GetIndexRequest getIndexRequest = new GetIndexRequest();
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }


    /*添加文档*/
    @Test
    public void test04() throws Exception {
        IndexRequest indexRequest = new IndexRequest("demo01");
        indexRequest.id("1"); //指定文档多的id
        //指定文档的内容：指定文档的json内容，XContentType xContentType：以什么格式
        indexRequest.source(JSON.toJSONString(new UserElastic("0","张三", "北京", 18)), XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse.getResult());
    }


    /*通过id查询文档*/
    @Test
    public void test05() throws Exception{
        GetRequest getRequest = new GetRequest("demo01");
        getRequest.id("1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        //得到"_source"中的值
        String string = getResponse.getSourceAsString();
        //转为user对象
        UserElastic user = JSON.parseObject(string, UserElastic.class);
        System.out.println(user);

        //取出部分值 第二种写法
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        System.out.println(sourceAsMap.get("address"));
    }

    /*判断文档是否存在*/
    @Test
    public  void test06() throws Exception{
        GetRequest getRequest = new GetRequest("demo01");
        getRequest.id("1");
        boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }


    /*修改文档*/
    @Test
    public void test07() throws Exception{
        UpdateRequest updateRequest = new UpdateRequest("demo01","1");
        UserElastic user = new UserElastic();
        user.setName("李四");
        updateRequest.doc(JSON.toJSONString(user),XContentType.JSON);
        UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(update.getResult());
    }


    /*删除文档*/
    @Test
    public void test08() throws Exception{
        DeleteRequest deleteRequest = new DeleteRequest("demo01");
        deleteRequest.id("1");
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse.getResult());
    }


    /*批量添加文档*/
    @Test
    public void test09() throws Exception{
        BulkRequest bulkRequest = new BulkRequest("demo01");
        List<UserElastic> list = new ArrayList<>();
        list.add(new UserElastic("1","张三","北京",18));
        list.add(new UserElastic("2","张三三","上海",19));
        list.add(new UserElastic("3","李四","北京",18));
        list.add(new UserElastic("4","李四四","上海",19));
        list.add(new UserElastic("5","王五","北京",18));
        list.add(new UserElastic("6","王五五","上海",19));
        /*用stream流循环添加*/
        list.stream().forEach(item->bulkRequest.add(new IndexRequest().id(item.getId()).source(JSON.toJSONString(item),XContentType.JSON)));
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());

    }

    /*条件查询*/
    @Test
    public  void test10() throws Exception{
        //1.搜索请求对象SearchRequest
        SearchRequest searchRequest = new SearchRequest("demo01");
        //2. 构建一个条件对象SearchSourceBuilder
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "王");
        //3. 把条件对象放入搜索请求对象中
        SearchSourceBuilder query = sourceBuilder.query(termQueryBuilder);
        SearchRequest source = searchRequest.source(query);
        //4. 执行搜索功能
        SearchResponse search = client.search(source, RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();
        Arrays.stream(hits).forEach(item-> System.out.println(item.getSourceAsString()));
    }


    /*条件查询*/
    @Test
    public void test11() throws Exception{
        //1.搜索请求对象SearchRequest
        SearchRequest searchRequest = new SearchRequest("demo01");
        //2. 构建一个条件对象SearchSourceBuilder
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        /*查询年龄大于18且姓名中含有王*/
        BoolQueryBuilder must = QueryBuilders
                .boolQuery()
                .must(QueryBuilders.rangeQuery("age").gt(17))
                .must(QueryBuilders.matchQuery("name", "王"));
        //3. 把条件对象放入搜索请求对象中
        SearchSourceBuilder query = sourceBuilder.query(must);
        /*分页：从第一页开始每页显示1条*/
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        /*降序排列  可不写SortOrder 默认升序*/
        sourceBuilder.sort("age", SortOrder.DESC);

        /*高亮*/
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder field = highlightBuilder.field("name");
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        sourceBuilder.highlighter(highlightBuilder);

        SearchRequest source = searchRequest.source(query);
        //4. 执行搜索功能
        SearchResponse search = client.search(source, RequestOptions.DEFAULT);
        SearchHit[] hits = search.getHits().getHits();
        /*查询条数 搭配分页的时候使用*/
        System.out.println("总条数: "+search.getHits().getTotalHits().value);
        Arrays.stream(hits).forEach(item-> System.out.println(item.getSourceAsString()));
        Arrays.stream(hits).forEach(item-> System.out.println(item.getHighlightFields()));

    }


}








