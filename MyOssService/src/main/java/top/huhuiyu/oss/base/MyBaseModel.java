package top.huhuiyu.oss.base;

import top.huhuiyu.api.utils.mybase.BaseModel;
import top.huhuiyu.oss.entity.TbAdmin;
import top.huhuiyu.oss.entity.TbToken;
import top.huhuiyu.oss.entity.TbTokenInfo;

/**
 * model层基类
 * 
 * @author 胡辉煜
 */
public class MyBaseModel extends BaseModel {
  private static final long serialVersionUID = -3109974581213500677L;

  private TbAdmin loginAdmin;

  public MyBaseModel() {
  }

  public TbAdmin getLoginAdmin() {
    return loginAdmin;
  }

  public void setLoginAdmin(TbAdmin loginAdmin) {
    this.loginAdmin = loginAdmin;
  }

  /**
   * 获取token的委托方法
   * 
   * @return TbToken信息
   */
  public TbToken makeTbToken() {
    TbToken tbToken = new TbToken();
    tbToken.setToken(getToken());
    return tbToken;
  }

  /**
   * 获取tokeninfo的委托方法
   * 
   * @return TbTokenInfo信息
   */
  public TbTokenInfo makeTbTokenInfo() {
    TbTokenInfo tokenInfo = new TbTokenInfo();
    tokenInfo.setToken(getToken());
    return tokenInfo;
  }

}
