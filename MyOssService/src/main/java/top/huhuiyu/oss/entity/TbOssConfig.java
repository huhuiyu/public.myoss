package top.huhuiyu.oss.entity;

import top.huhuiyu.api.utils.mybase.BaseEntity;

/**
 * TbOssConfig表
 * 
 * @author 胡辉煜
 */
public class TbOssConfig extends BaseEntity {

  private static final long serialVersionUID = -581765207043906469L;

  private java.lang.String ocid;
  private java.lang.String endpoint;
  private java.lang.String accessKeyId;
  private java.lang.String accessKeySecret;
  private java.lang.String bucketName;
  private java.lang.Long   expiration;
  private java.lang.String enable;
  private java.util.Date   lastupdate;
  private TbAdmin          tbAdmin;

  public TbOssConfig() {
  }

  public java.lang.String getOcid() {
    return ocid;
  }

  public void setOcid(java.lang.String ocid) {
    this.ocid = ocid;
  }

  public java.lang.String getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(java.lang.String endpoint) {
    this.endpoint = endpoint;
  }

  public java.lang.String getAccessKeyId() {
    return accessKeyId;
  }

  public void setAccessKeyId(java.lang.String accessKeyId) {
    this.accessKeyId = accessKeyId;
  }

  public java.lang.String getAccessKeySecret() {
    return accessKeySecret;
  }

  public void setAccessKeySecret(java.lang.String accessKeySecret) {
    this.accessKeySecret = accessKeySecret;
  }

  public java.lang.String getBucketName() {
    return bucketName;
  }

  public void setBucketName(java.lang.String bucketName) {
    this.bucketName = bucketName;
  }

  public java.lang.Long getExpiration() {
    return expiration;
  }

  public void setExpiration(java.lang.Long expiration) {
    this.expiration = expiration;
  }

  public java.lang.String getEnable() {
    return enable;
  }

  public void setEnable(java.lang.String enable) {
    this.enable = enable;
  }

  public java.util.Date getLastupdate() {
    return lastupdate;
  }

  public void setLastupdate(java.util.Date lastupdate) {
    this.lastupdate = lastupdate;
  }

  public TbAdmin getTbAdmin() {
    return tbAdmin;
  }

  public void setTbAdmin(TbAdmin tbAdmin) {
    this.tbAdmin = tbAdmin;
  }

}
