package top.huhuiyu.oss.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.api.utils.mybase.JsonMessage;
import top.huhuiyu.oss.base.IAdminAuth;
import top.huhuiyu.oss.model.TbOssConfigModel;
import top.huhuiyu.oss.service.TbOssConfigService;

/**
 * TbOssConfig的控制器
 * 
 * @author 胡辉煜
 */
@RestController
@RequestMapping("/TbOssConfig")
public class TbOssConfigController implements IAdminAuth {
  @Autowired
  private TbOssConfigService tbOssConfigService;

  @RequestMapping("/queryAll")
  public JsonMessage queryAll(TbOssConfigModel model) throws Exception {
    return tbOssConfigService.queryByAdmin(model);
  }

  @RequestMapping("/update")
  public JsonMessage update(TbOssConfigModel model) throws Exception {
    return tbOssConfigService.update(model);
  }

  @RequestMapping("/add")
  public JsonMessage add(TbOssConfigModel model) throws Exception {
    return tbOssConfigService.add(model);
  }

  @RequestMapping("/delete")
  public JsonMessage delete(TbOssConfigModel model) throws Exception {
    return tbOssConfigService.delete(model);
  }

}
