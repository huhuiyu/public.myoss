package top.huhuiyu.oss.entity;

import top.huhuiyu.api.utils.mybase.BaseEntity;

/**
 * TbOssInfo表
 * 
 * @author 胡辉煜
 */
public class TbOssInfo extends BaseEntity {

  private static final long serialVersionUID = -2436866622232020069L;

  private java.lang.Integer oiid;
  private java.lang.String  ocid;
  private java.lang.String  objectName;
  private java.lang.String  filename;
  private java.lang.String  contentType;
  private java.lang.Long    filesize;
  private java.lang.String  fileinfo;
  private java.util.Date    lastupdate;
  private TbAdmin           tbAdmin;

  public TbOssInfo() {
  }

  public java.lang.Integer getOiid() {
    return oiid;
  }

  public void setOiid(java.lang.Integer oiid) {
    this.oiid = oiid;
  }

  public java.lang.String getOcid() {
    return ocid;
  }

  public void setOcid(java.lang.String ocid) {
    this.ocid = ocid;
  }

  public java.lang.String getObjectName() {
    return objectName;
  }

  public void setObjectName(java.lang.String objectName) {
    this.objectName = objectName;
  }

  public java.lang.String getFilename() {
    return filename;
  }

  public void setFilename(java.lang.String filename) {
    this.filename = filename;
  }

  public java.lang.String getContentType() {
    return contentType;
  }

  public void setContentType(java.lang.String contentType) {
    this.contentType = contentType;
  }

  public java.lang.Long getFilesize() {
    return filesize;
  }

  public void setFilesize(java.lang.Long filesize) {
    this.filesize = filesize;
  }

  public java.lang.String getFileinfo() {
    return fileinfo;
  }

  public void setFileinfo(java.lang.String fileinfo) {
    this.fileinfo = fileinfo;
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
