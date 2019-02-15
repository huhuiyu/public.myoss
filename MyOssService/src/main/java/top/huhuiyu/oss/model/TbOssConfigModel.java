package top.huhuiyu.oss.model;

import top.huhuiyu.oss.base.MyBasePageModel;
import top.huhuiyu.oss.entity.TbOssConfig;

/**
 * TbOssConfig的Model
 * 
 * @author 胡辉煜
 */
public class TbOssConfigModel extends MyBasePageModel {

  private static final long serialVersionUID = 5417881982965001189L;

  private TbOssConfig tbOssConfig = new TbOssConfig();

  public TbOssConfigModel() {
  }

  public TbOssConfig getTbOssConfig() {
    return tbOssConfig;
  }

  public void setTbOssConfig(TbOssConfig tbOssConfig) {
    this.tbOssConfig = tbOssConfig;
  }

}
