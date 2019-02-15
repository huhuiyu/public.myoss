package top.huhuiyu.oss.model;

import top.huhuiyu.oss.base.MyBasePageModel;
import top.huhuiyu.oss.entity.TbOssInfo;

/**
 * TbOssInfo的Model
 * 
 * @author 胡辉煜
 */
public class TbOssInfoModel extends MyBasePageModel {

  private static final long serialVersionUID = 5485168198030413233L;

  private TbOssInfo tbOssInfo = new TbOssInfo();

  public TbOssInfoModel() {
  }

  public TbOssInfo getTbOssInfo() {
    return tbOssInfo;
  }

  public void setTbOssInfo(TbOssInfo tbOssInfo) {
    this.tbOssInfo = tbOssInfo;
  }

}
