package top.huhuiyu.oss.controller.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.api.utils.mybase.JsonMessage;
import top.huhuiyu.oss.base.IAdminAuth;
import top.huhuiyu.oss.model.TbAdminModel;

/**
 * auth测试控制器
 * 
 * @author 胡辉煜
 */
@RestController
@RequestMapping("/auth")
public class AuthController implements IAdminAuth {

  @RequestMapping("/getAdmin")
  public JsonMessage getAdmin(TbAdminModel model) throws Exception {
    return JsonMessage.getSuccess("测试成功").putData("admin", model.getLoginAdmin());
  }

}
