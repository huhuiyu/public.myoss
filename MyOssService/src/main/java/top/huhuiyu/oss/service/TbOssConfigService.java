package top.huhuiyu.oss.service;

import top.huhuiyu.api.utils.mybase.JsonMessage;
import top.huhuiyu.oss.model.TbOssConfigModel;

/**
 * TbOssConfig的Service
 * 
 * @author 胡辉煜
 */
public interface TbOssConfigService {

  /**
   * 修改TbOssConfig信息
   * 
   * @param model
   *              页面提交数据
   * @return 修改TbOssConfig信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage update(TbOssConfigModel model) throws Exception;

  /**
   * 删除TbOssConfig信息
   * 
   * @param model
   *              页面提交数据
   * @return 删除TbOssConfig信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage delete(TbOssConfigModel model) throws Exception;

  /**
   * 添加TbOssConfig信息
   * 
   * @param model
   *              页面提交数据
   * @return 添加TbOssConfig信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage add(TbOssConfigModel model) throws Exception;

  /**
   * 按照主键查询TbOssConfig信息
   * 
   * @param model
   *              页面提交数据
   * @return 主键查询TbOssConfig信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage queryByKey(TbOssConfigModel model) throws Exception;

  /**
   * 分页查询TbOssConfig信息
   * 
   * @param model
   *              页面提交数据
   * @return 分页查询TbOssConfig信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage queryAll(TbOssConfigModel model) throws Exception;

  /**
   * 查询用户的TbOssConfig信息
   * 
   * @param model
   *              页面提交数据
   * @return 查询用户的TbOssConfig信息的结果
   * @throws Exception
   *                   处理发生错误
   */
  JsonMessage queryByAdmin(TbOssConfigModel model) throws Exception;

}
