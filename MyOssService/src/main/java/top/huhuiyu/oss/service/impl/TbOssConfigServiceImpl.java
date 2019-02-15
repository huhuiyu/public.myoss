package top.huhuiyu.oss.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import top.huhuiyu.api.utils.StringUtils;
import top.huhuiyu.api.utils.exception.AppException;
import top.huhuiyu.api.utils.mybase.JsonMessage;
import top.huhuiyu.api.utils.mybase.PageBean;
import top.huhuiyu.oss.dao.TbAdminOssConfigDAO;
import top.huhuiyu.oss.dao.TbOssConfigDAO;
import top.huhuiyu.oss.dao.TbOssInfoDAO;
import top.huhuiyu.oss.entity.TbAdminOssConfig;
import top.huhuiyu.oss.entity.TbOssConfig;
import top.huhuiyu.oss.entity.TbOssInfo;
import top.huhuiyu.oss.model.TbOssConfigModel;
import top.huhuiyu.oss.service.TbOssConfigService;

/**
 * TbOssConfig的实现层
 * 
 * @author 胡辉煜
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TbOssConfigServiceImpl implements TbOssConfigService {
  @Autowired
  private TbOssInfoDAO        tbOssInfoDAO;
  @Autowired
  private TbOssConfigDAO      tbOssConfigDAO;
  @Autowired
  private TbAdminOssConfigDAO tbAdminOssConfigDAO;

  @Override
  public JsonMessage queryByAdmin(TbOssConfigModel model) throws Exception {
    List<TbOssConfig> list    = tbOssConfigDAO.queryByAdmin(model.getLoginAdmin());
    JsonMessage       message = JsonMessage.getSuccess("");
    message.putData("list", list);
    return message;
  }

  @Override
  public JsonMessage queryAll(TbOssConfigModel model) throws Exception {
    PageBean page = model.getPage();
    PageMethod.startPage(page.getPageNumber(), page.getPageSize());
    Page<TbOssConfig> list = (Page<TbOssConfig>) tbOssConfigDAO.queryAll();
    model.setPageInfo(list);
    JsonMessage message = JsonMessage.getSuccess("");
    message.putData("page", page);
    message.putData("list", list);
    return message;
  }

  @Override
  public JsonMessage queryByKey(TbOssConfigModel model) throws Exception {
    JsonMessage message = JsonMessage.getSuccess("");
    message.putData("tbOssConfig", tbOssConfigDAO.queryByKey(model.getTbOssConfig()));
    return message;
  }

  @Override
  public JsonMessage add(TbOssConfigModel model) throws Exception {
    TbOssConfig config = model.getTbOssConfig();
    config.setTbAdmin(model.getLoginAdmin());
    config.setOcid(UUID.randomUUID().toString());
    if (StringUtils.isEmpty(config.getEndpoint())) {
      return JsonMessage.getFail("endpoint必须填写");
    }
    if (StringUtils.isEmpty(config.getAccessKeyId())) {
      return JsonMessage.getFail("accessKeyId必须填写");
    }
    if (StringUtils.isEmpty(config.getAccessKeySecret())) {
      return JsonMessage.getFail("accessKeySecret必须填写");
    }
    if (StringUtils.isEmpty(config.getBucketName())) {
      return JsonMessage.getFail("bucketName必须填写");
    }
    if ((config.getExpiration() == null) || (config.getExpiration() <= 0)) {
      return JsonMessage.getFail("expiration必须是大于0的整数");
    }
    int result = tbOssConfigDAO.add(config);
    if (result != 1) {
      throw AppException.getAppException("添加数据失败");
    }
    // 保存关联信息
    TbAdminOssConfig tbAdminOssConfig = new TbAdminOssConfig();
    tbAdminOssConfig.setAid(model.getLoginAdmin().getAid());
    tbAdminOssConfig.setOcid(config.getOcid());
    result = tbAdminOssConfigDAO.add(tbAdminOssConfig);
    if (result != 1) {
      throw AppException.getAppException("添加关联数据失败");
    }
    return JsonMessage.getSuccess("添加数据成功");
  }

  @Override
  public JsonMessage delete(TbOssConfigModel model) throws Exception {
    TbOssConfig config = model.getTbOssConfig();
    // 查询有没有关联记录
    TbOssInfo ossInfo = new TbOssInfo();
    ossInfo.setOcid(config.getOcid());
    int count = tbOssInfoDAO.queryCountByOcid(ossInfo);
    if (count > 0) {
      return JsonMessage.getFail("已经存在上传数据，无法删除");
    }
    // 删除数据
    TbAdminOssConfig aocofig = new TbAdminOssConfig();
    aocofig.setAid(model.getLoginAdmin().getAid());
    aocofig.setOcid(config.getOcid());
    int result = tbAdminOssConfigDAO.delete(aocofig);
    if (result != 1) {
      throw AppException.getAppException("删除关联数据失败");
    }
    result = tbOssConfigDAO.delete(config);
    if (result != 1) {
      throw AppException.getAppException("删除数据失败");
    }
    return JsonMessage.getSuccess("删除数据成功");
  }

  @Override
  public JsonMessage update(TbOssConfigModel model) throws Exception {
    TbOssConfig config = model.getTbOssConfig();
    config.setTbAdmin(model.getLoginAdmin());
    if (StringUtils.isEmpty(config.getEndpoint())) {
      return JsonMessage.getFail("endpoint必须填写");
    }
    if (StringUtils.isEmpty(config.getAccessKeyId())) {
      return JsonMessage.getFail("accessKeyId必须填写");
    }
    if (StringUtils.isEmpty(config.getAccessKeySecret())) {
      return JsonMessage.getFail("accessKeySecret必须填写");
    }
    if (StringUtils.isEmpty(config.getBucketName())) {
      return JsonMessage.getFail("bucketName必须填写");
    }
    if ((config.getExpiration() == null) || (config.getExpiration() <= 0)) {
      return JsonMessage.getFail("expiration必须是大于0的整数");
    }
    int result = tbOssConfigDAO.update(model.getTbOssConfig());
    return result == 1 ? JsonMessage.getSuccess("修改数据成功") : JsonMessage.getFail("修改数据失败");
  }
}
