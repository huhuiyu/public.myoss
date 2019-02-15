package top.huhuiyu.oss.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.huhuiyu.api.utils.mybase.JsonMessage;
import top.huhuiyu.oss.base.IAdminAuth;
import top.huhuiyu.oss.model.TbOssInfoModel;
import top.huhuiyu.oss.service.TbOssInfoService;

/**
 * TbOssInfo的控制器
 * 
 * @author 胡辉煜
 */
@RestController
@RequestMapping("/TbOssInfo")
public class TbOssInfoController implements IAdminAuth {

  @Autowired
  private TbOssInfoService tbOssInfoService;

  @RequestMapping("/queryAll")
  public JsonMessage queryAll(TbOssInfoModel model) throws Exception {
    return tbOssInfoService.queryAll(model);
  }

  @RequestMapping("/add")
  public JsonMessage add(TbOssInfoModel model, MultipartFile file) throws Exception {
    return tbOssInfoService.add(model, file);
  }

  @RequestMapping("/getOssUrl")
  public JsonMessage getOssUrl(TbOssInfoModel model) throws Exception {
    return tbOssInfoService.getOssUrl(model);
  }

  @RequestMapping("/delete")
  public JsonMessage delete(TbOssInfoModel model, MultipartFile file) throws Exception {
    return tbOssInfoService.delete(model);
  }

  @RequestMapping("/update")
  public JsonMessage update(TbOssInfoModel model, MultipartFile file) throws Exception {
    return tbOssInfoService.update(model, file);
  }

}
