package top.huhuiyu.oss.entity;

import top.huhuiyu.api.utils.mybase.BaseEntity;

/**
 * TbAdminOssConfig表
 * 
 * @author 胡辉煜
 */
public class TbAdminOssConfig extends BaseEntity {

  private static final long serialVersionUID = -1695878665198949332L;

  private java.lang.Integer aid;
  private java.lang.String  ocid;
  private java.util.Date    lastupdate;

  public TbAdminOssConfig() {
  }

  public java.lang.Integer getAid() {
    return aid;
  }

  public void setAid(java.lang.Integer aid) {
    this.aid = aid;
  }

  public java.lang.String getOcid() {
    return ocid;
  }

  public void setOcid(java.lang.String ocid) {
    this.ocid = ocid;
  }

  public java.util.Date getLastupdate() {
    return lastupdate;
  }

  public void setLastupdate(java.util.Date lastupdate) {
    this.lastupdate = lastupdate;
  }

}
