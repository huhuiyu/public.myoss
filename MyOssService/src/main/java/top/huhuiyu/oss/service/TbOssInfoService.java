package top.huhuiyu.oss.service;

import org.springframework.web.multipart.MultipartFile;
import top.huhuiyu.api.utils.mybase.JsonMessage;
import top.huhuiyu.oss.model.TbOssInfoModel;

/**
 * TbOssInfo的Service
 * 
 * @author 胡辉煜
 */
public interface TbOssInfoService {

  /**
   * 修改TbOssInfo信息
   * 
   * @param model
   *              页面提交数据
   * @param file
   *              上传文件
   * @return 修改TbOssInfo信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage update(TbOssInfoModel model, MultipartFile file) throws Exception;

  /**
   * 删除TbOssInfo信息
   * 
   * @param model
   *              页面提交数据
   * @return 删除TbOssInfo信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage delete(TbOssInfoModel model) throws Exception;

  /**
   * 添加TbOssInfo信息
   * 
   * @param file
   *              上传文件
   * @param model
   *              页面提交数据
   * @return 添加TbOssInfo信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage add(TbOssInfoModel model, MultipartFile file) throws Exception;

  /**
   * 按照主键查询TbOssInfo信息
   * 
   * @param model
   *              页面提交数据
   * @return 主键查询TbOssInfo信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage queryByKey(TbOssInfoModel model) throws Exception;

  /**
   * 分页查询TbOssInfo信息
   * 
   * @param model
   *              页面提交数据
   * @return 分页查询TbOssInfo信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage queryAll(TbOssInfoModel model) throws Exception;

  /**
   * 通过主键查询url
   * 
   * @param model
   *              页面提交数据
   * @return 主键查询url
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage getOssUrl(TbOssInfoModel model) throws Exception;

  /**
   * 通过objectName查询url
   * 
   * @param model
   *              页面提交数据
   * @return objectName查询url
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage getOssObjUrl(TbOssInfoModel model) throws Exception;

}
