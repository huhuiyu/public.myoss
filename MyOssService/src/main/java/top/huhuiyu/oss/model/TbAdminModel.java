package top.huhuiyu.oss.model;

import top.huhuiyu.oss.base.MyBasePageModel;
import top.huhuiyu.oss.entity.TbAdmin;

/**
 * TbAdmin的Model
 * 
 * @author 胡辉煜
 */
public class TbAdminModel extends MyBasePageModel {

  private static final long serialVersionUID = 534043788568120778L;

  private TbAdmin tbAdmin = new TbAdmin();
  private String  oldPwd;

  public TbAdminModel() {
  }

  public TbAdmin getTbAdmin() {
    return tbAdmin;
  }

  public void setTbAdmin(TbAdmin tbAdmin) {
    this.tbAdmin = tbAdmin;
  }

  public String getOldPwd() {
    return oldPwd;
  }

  public void setOldPwd(String oldPwd) {
    this.oldPwd = oldPwd;
  }
}
