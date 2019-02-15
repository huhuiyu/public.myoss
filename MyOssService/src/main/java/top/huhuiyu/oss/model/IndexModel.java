package top.huhuiyu.oss.model;

import top.huhuiyu.oss.base.MyBaseModel;

/**
 * 首页model层
 * 
 * @author 胡辉煜
 */
public class IndexModel extends MyBaseModel {

  private static final long serialVersionUID = 4678008575615328863L;
  private String            echo;

  public IndexModel() {
  }

  public String getEcho() {
    return echo;
  }

  public void setEcho(String echo) {
    this.echo = echo;
  }

}
