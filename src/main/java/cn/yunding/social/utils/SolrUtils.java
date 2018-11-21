package cn.yunding.social.utils;

import cn.yunding.social.pojo.SolrMessage;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @anthor : beokwithanything
 * @createtime : 2018:10.20 14:21
 * @discription :
 */
public class SolrUtils {

    /**
     * solr访问URL
     */
    String solrURL = "http://localhost/solr/hellosolr";

    HttpSolrClient client = new HttpSolrClient.Builder(solrURL).build();

    /**
     * 保存或更新solr数据 (使用javaBean)
     * @param o
     * @throws IOException
     * @throws SolrServerException
     */
    public void saveSolrResource1(Object o) throws IOException, SolrServerException {
            client.addBean(o);
            client.commit();
    }

    /**
     * 保存或更新solr数据 (不用javaBean)
     * @param document
     * @throws IOException
     * @throws SolrServerException
     */
    public void saveSolrResource2(SolrInputDocument document) throws IOException, SolrServerException {
        client.add(document);
        client.commit();
    }

    /**
     * 删除数据
     * @param id
     * @throws IOException
     * @throws SolrServerException
     */
    public boolean delete(String id) throws IOException, SolrServerException {
        try {
            client.deleteById(id);
            client.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 查询数据:
     * 找一个自己想要的输出方式(控制台打印关键词对应的目录或者通过接口return给前端)
     * @param keywords
     * @throws IOException
     * @throws SolrServerException
     */
    public Object search(String keywords) throws IOException, SolrServerException {

        SolrQuery solrQuery = new SolrQuery();

        /*设置查询语句*/
        //这里的“:”号还必须加，不加查不到（因为solr的主页上Query的q文本框中规定的格式为 " *:* "）
        solrQuery.setQuery("solr_contents:"+keywords);

        // 设置分页数据
        solrQuery.setStart(0);
        solrQuery.setRows(5);

        /* 设置高亮数据*/
        // 设置开启高亮
        solrQuery.setHighlight(true);
        // 设置高亮字段
        solrQuery.addHighlightField("solr_contents");
        // 设置高亮的前缀
        solrQuery.setHighlightSimplePre("<font color='red'>");
        // 设置高粱的后缀
        solrQuery.setHighlightSimplePost("</font>");

        //搜索
        QueryResponse response = client.query(solrQuery);
        //从response里获取结果集
        SolrDocumentList documents = response.getResults();
        // 打印查询的数据条数
        System.out.println("查询到的结果条数是：" + documents.getNumFound());
        List<String> list = new ArrayList<String>();
        //用自己想要的方式得到查询的结果，这里是在控制台打印输出
        for (SolrDocument solrDocument : documents){
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            SolrMessage message = new SolrMessage();
            String x = highlighting.get(solrDocument.get("id")).get("solr_contents").get(0);
            System.out.println(x);
            list.add(x);
        }
        return list;
    }
}
