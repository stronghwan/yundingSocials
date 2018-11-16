package cn.yunding.social.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_solr_message")
public class SolrMessage {
    @Id
    private Integer id;

    @Column(name = "solr_title")
    private String solrTitle;

    @Column(name = "solr_contents")
    private String solrContents;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return solr_title
     */
    public String getSolrTitle() {
        return solrTitle;
    }

    /**
     * @param solrTitle
     */
    public void setSolrTitle(String solrTitle) {
        this.solrTitle = solrTitle;
    }

    /**
     * @return solr_contents
     */
    public String getSolrContents() {
        return solrContents;
    }

    /**
     * @param solrContents
     */
    public void setSolrContents(String solrContents) {
        this.solrContents = solrContents;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}