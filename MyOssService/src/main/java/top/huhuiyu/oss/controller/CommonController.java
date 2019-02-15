package top.huhuiyu.oss.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.huhuiyu.api.utils.mybase.INoToken;
import top.huhuiyu.api.utils.mybase.JsonMessage;
import top.huhuiyu.oss.model.TbOssInfoModel;
import top.huhuiyu.oss.service.TbOssInfoService;

/**
 * 对外公用控制器
 * 
 * @author 胡辉煜
 */
@RestController
@RequestMapping("/common")
public class CommonController implements INoToken {
  @Autowired
  private TbOssInfoService tbOssInfoService;

  @RequestMapping("/getOssObjUrl")
  public void getOssObjUrl(TbOssInfoModel model, HttpServletResponse response) throws Exception {
    JsonMessage message = tbOssInfoService.getOssObjUrl(model);
    if (message.isSuccess()) {
      response.sendRedirect(message.getMessage());
    }
    else {
      response.setContentType("text/html");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().println(message.getMessage());
    }
  }
}
