package top.huhuiyu.oss.service.impl;

import java.net.URL;
import java.util.Date;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.page.PageMethod;
import top.huhuiyu.api.fileutil.FileUtil;
import top.huhuiyu.api.utils.StringUtils;
import top.huhuiyu.api.utils.mybase.JsonMessage;
import top.huhuiyu.api.utils.mybase.PageBean;
import top.huhuiyu.oss.dao.TbOssConfigDAO;
import top.huhuiyu.oss.dao.TbOssInfoDAO;
import top.huhuiyu.oss.dao.UtilsDAO;
import top.huhuiyu.oss.entity.TbOssConfig;
import top.huhuiyu.oss.entity.TbOssInfo;
import top.huhuiyu.oss.model.TbOssInfoModel;
import top.huhuiyu.oss.service.TbOssInfoService;

/**
 * TbOssInfo的实现层
 * 
 * @author 胡辉煜
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TbOssInfoServiceImpl implements TbOssInfoService {

  private static final Logger log = LoggerFactory.getLogger(TbOssInfoServiceImpl.class);

  @Autowired
  private TbOssInfoDAO   tbOssInfoDAO;
  @Autowired
  private TbOssConfigDAO tbOssConfigDAO;
  @Autowired
  private UtilsDAO       utilsDAO;

  @Override
  public JsonMessage queryAll(TbOssInfoModel model) throws Exception {
    TbOssInfo ossInfo = model.getTbOssInfo();
    ossInfo.setTbAdmin(model.getLoginAdmin());
    if (!StringUtils.isEmpty(ossInfo.getFileinfo())) {
      ossInfo.setFileinfo(StringUtils.getLikeStr(ossInfo.getFileinfo()));
    }
    PageBean page = model.getPage();
    PageMethod.startPage(page.getPageNumber(), page.getPageSize());
    Page<TbOssInfo> list = (Page<TbOssInfo>) tbOssInfoDAO.queryAll(ossInfo);
    model.setPageInfo(list);
    JsonMessage message = JsonMessage.getSuccess("");
    message.putData("page", page);
    message.putData("list", list);
    message.putData("ocids", tbOssConfigDAO.queryByAdminNoInfo(model.getLoginAdmin()));
    return message;
  }

  @Override
  public JsonMessage queryByKey(TbOssInfoModel model) throws Exception {
    JsonMessage message = JsonMessage.getSuccess("");
    message.putData("tbOssInfo", tbOssInfoDAO.queryByKey(model.getTbOssInfo()));
    return message;
  }

  @Override
  public JsonMessage getOssObjUrl(TbOssInfoModel model) throws Exception {
    TbOssInfo ossInfo = model.getTbOssInfo();
    ossInfo = tbOssInfoDAO.queryByObjectName(ossInfo);
    if (ossInfo == null) {
      return JsonMessage.getFail("资源不存在");
    }
    // 获取oss信息
    TbOssConfig config = new TbOssConfig();
    config.setOcid(ossInfo.getOcid());
    config = tbOssConfigDAO.queryByKey(config);
    if (config == null) {
      return JsonMessage.getFail("配置信息不存在");
    }
    OSSClientBuilder ocb        = new OSSClientBuilder();
    OSS              ossClient  = ocb.build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
    Date             expiration = new Date(utilsDAO.queryTime().getTime() + config.getExpiration());
    URL              url        = ossClient.generatePresignedUrl(config.getBucketName(), ossInfo.getObjectName(), expiration);
    ossClient.shutdown();
    return JsonMessage.getSuccess(url.toString());
  }

  @Override
  public JsonMessage getOssUrl(TbOssInfoModel model) throws Exception {
    TbOssInfo ossInfo = model.getTbOssInfo();
    ossInfo = tbOssInfoDAO.queryByKey(ossInfo);
    if (ossInfo == null) {
      return JsonMessage.getFail("资源不存在");
    }
    // 获取oss信息
    TbOssConfig config = new TbOssConfig();
    config.setOcid(ossInfo.getOcid());
    config = tbOssConfigDAO.queryByKey(config);
    if (config == null) {
      return JsonMessage.getFail("配置信息不存在");
    }
    OSSClientBuilder ocb        = new OSSClientBuilder();
    OSS              ossClient  = ocb.build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
    Date             expiration = new Date(utilsDAO.queryTime().getTime() + config.getExpiration());
    URL              url        = ossClient.generatePresignedUrl(config.getBucketName(), ossInfo.getObjectName(), expiration);
    ossClient.shutdown();
    return JsonMessage.getSuccess(url.toString());
  }

  @Override
  public JsonMessage add(TbOssInfoModel model, MultipartFile file) throws Exception {
    // 基础信息检查
    if (file == null) {
      return JsonMessage.getFail("请选择上传的文件");
    }
    TbOssInfo ossInfo = model.getTbOssInfo();
    if (StringUtils.isEmpty(ossInfo.getOcid())) {
      return JsonMessage.getFail("ocid必须填写");
    }
    TbOssConfig config = new TbOssConfig();
    config.setOcid(ossInfo.getOcid());
    config = tbOssConfigDAO.queryByKey(config);
    if (config == null) {
      return JsonMessage.getFail("ocid不合法");
    }
    // 文件名称
    String filename = UUID.randomUUID().toString() + FileUtil.getExtName(file.getOriginalFilename());
    // 数据库信息
    ossInfo.setFileinfo(StringUtils.trim(ossInfo.getFileinfo()));
    ossInfo.setObjectName(filename);
    ossInfo.setFilename(file.getOriginalFilename());
    ossInfo.setContentType(file.getContentType());
    ossInfo.setFilesize(file.getSize());
    int result = tbOssInfoDAO.add(ossInfo);
    if (result != 1) {
      JsonMessage.getFail("添加数据失败");
    }
    // oss存储
    OSSClientBuilder ocb       = new OSSClientBuilder();
    OSS              ossClient = ocb.build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
    PutObjectResult  putResult = ossClient.putObject(config.getBucketName(), filename, file.getInputStream());
    log.debug(String.format("ETag:%s,RequestId:%s,ClientCRC:%s,ServerCRC:%s", putResult.getETag(), putResult.getRequestId(), putResult.getClientCRC(), putResult.getServerCRC()));
    ossClient.shutdown();
    return JsonMessage.getSuccess("添加数据成功");
  }

  @Override
  public JsonMessage delete(TbOssInfoModel model) throws Exception {
    TbOssInfo ossInfo = model.getTbOssInfo();
    String    ocid    = ossInfo.getOcid();
    if (StringUtils.isEmpty(ocid)) {
      return JsonMessage.getFail("ocid必须填写");
    }
    ossInfo = tbOssInfoDAO.queryByKey(ossInfo);
    if (ossInfo == null) {
      return JsonMessage.getFail("资源不存在");
    }
    // 获取oss信息
    TbOssConfig config = new TbOssConfig();
    config.setOcid(ossInfo.getOcid());
    config = tbOssConfigDAO.queryByKey(config);
    if (config == null) {
      return JsonMessage.getFail("配置信息不存在");
    }
    if (!ocid.equals(config.getOcid())) {
      return JsonMessage.getFail("ocid不正确");
    }
    // 数据库删除
    int result = tbOssInfoDAO.delete(model.getTbOssInfo());
    if (result != 1) {
      JsonMessage.getFail("删除数据失败");
    }
    // oss删除
    OSSClientBuilder ocb       = new OSSClientBuilder();
    OSS              ossClient = ocb.build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
    ossClient.deleteObject(config.getBucketName(), ossInfo.getObjectName());
    ossClient.shutdown();
    return JsonMessage.getSuccess("删除数据成功");
  }

  @Override
  public JsonMessage update(TbOssInfoModel model, MultipartFile file) throws Exception {
    TbOssInfo ossInfo = model.getTbOssInfo();
    String    ocid    = ossInfo.getOcid();
    if (StringUtils.isEmpty(ocid)) {
      return JsonMessage.getFail("ocid必须填写");
    }
    TbOssInfo sossInfo = tbOssInfoDAO.queryByKey(ossInfo);
    if (sossInfo == null) {
      return JsonMessage.getFail("资源不存在");
    }
    // 获取oss信息
    TbOssConfig config = new TbOssConfig();
    config.setOcid(sossInfo.getOcid());
    config = tbOssConfigDAO.queryByKey(config);
    if (config == null) {
      return JsonMessage.getFail("配置信息不存在");
    }
    if (!ocid.equals(config.getOcid())) {
      return JsonMessage.getFail("ocid不正确");
    }
    // 是否有修改文件的情况
    if (file == null) {
      // 没有文件就只修改fileinfo
      sossInfo.setFileinfo(ossInfo.getFileinfo());
      int result = tbOssInfoDAO.update(model.getTbOssInfo());
      return result == 1 ? JsonMessage.getSuccess("修改数据成功") : JsonMessage.getFail("修改数据失败");
    }
    // 文件存在就要上传oss并更改文件信息
    // 数据库信息
    sossInfo.setFileinfo(StringUtils.trim(ossInfo.getFileinfo()));
    sossInfo.setFilename(file.getOriginalFilename());
    sossInfo.setContentType(file.getContentType());
    sossInfo.setFilesize(file.getSize());
    int result = tbOssInfoDAO.update(sossInfo);
    if (result != 1) {
      JsonMessage.getFail("修改数据失败");
    }
    // oss存储
    OSSClientBuilder ocb       = new OSSClientBuilder();
    OSS              ossClient = ocb.build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
    PutObjectResult  putResult = ossClient.putObject(config.getBucketName(), sossInfo.getObjectName(), file.getInputStream());
    log.debug(String.format("ETag:%s,RequestId:%s,ClientCRC:%s,ServerCRC:%s", putResult.getETag(), putResult.getRequestId(), putResult.getClientCRC(), putResult.getServerCRC()));
    ossClient.shutdown();
    return JsonMessage.getSuccess("修改数据成功");
  }
}
