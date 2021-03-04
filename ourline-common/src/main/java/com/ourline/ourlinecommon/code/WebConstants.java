package com.ourline.ourlinecommon.code;

/**
 * @ClassName WebConstants
 * @Description 系统常量定义类
 * @date 20210303 07:27:00
 * @Copyright
 */
public class WebConstants {

	/**
	 * @Fields SESSION_TOKEN request请求header中携带sessionid的属性名称
	 */
	public static final String SESSION_TOKEN = "Authorization";

	/**
	 * @Fields SESSION_LANG request请求header中携带lang的属性名称
	 */
	public static final String SESSION_LANG = "CurrentLanguage";

	/**
	 * 默认角色
	 */
	public final static String BASE_ROLE = "base_role";

	/**
	 * 默认密码 manager<br>
	 *
	 */
	public final static String DEFAULT_PWD = "1d0258c2440a8d19e716292b231e3190";

	/**
	 * 初始化用户
	 */
	public final static String DEFAULT_USERNAME = "admin";

//
	public static final String ORGATTRID = "org_obj";// 机构授权的授权对象id
//

	public static final String USERATTRID = "user_obj";// 用户授权的授权对象id
//

	public static final String ROLEATTRID = "role_obj";// 角色授权的授权对象id

	/**
	 * 用户是否是管理员
	 */
	public final static String IS_ADMIN = "1";

	/**
	 * session中保存的登录用户键值
	 */
	public final static String SESSION_USER = "authUser";

	/**
	 * session中保存的登录用户岗位键值
	 */
	public final static String SESSION_USER_POSITION = "authUserPosition";

	/**
	 * 用户所具有的操作权限
	 */
	public final static String SESSION_OPERATIONS = "sessionOperations";

	/**
	 * 默认导出的记录条数
	 */
	public final static int DEFAULT_EXP_PERSIZE = 100;

	/**
	 * 批量导出的最大客户数
	 */
	public final static int EXPORT_MAX_SIZE = 500;

	/**
	 * 菜单树结构中，默认根节点id
	 */
	public final static String DEFAULT_MENU_TREE_ROOT_ID = "ROOT";

	/**
	 * 机构根节点
	 */
	public final static String DEFAULT_ORG_ROOT_ID = "00000";

	/**
	 * 菜单类型-模块
	 */
	public final static String MENU_TYPE_MODULE = "MODULE";

	/**
	 * 菜单类型-菜单
	 */
	public final static String MENU_TYPE_MENU = "MENU";

	/**
	 * cooike 存储用户名的键值
	 */
	public final static String LOGIN_COOKIE_NAME = "SUNLINEUSERNAME";

	/**
	 * cooike 存储密码的键值
	 */
	public final static String LOGIN_COOKIE_PWD = "SUNLINEUSERPWD";

	/**
	 * like 语句的转义
	 */
	public final static String ESCAPE = "/";

	/**
	 * 是管理层
	 */
	public static final String USER_MANAGERHEAD = "1";

	/**
	 * 不是管理层
	 */
	public static final String USER_NOT_MANAGERHEAD = "0";

	/**
	 * 下载标记，下载模板
	 */
	public final static String DOWNLOAD_TEMPLATE = "template";

	/**
	 * 下载标记，导出数据，导出excel
	 */
	public final static String DOWNLOAD_EXCEL = "excel";

	/**
	 * 下载标记，导出数据，导出word
	 */
	public final static String DOWNLOAD_DOC = "doc";

	/**
	 * 下载标记,文件下载预览
	 */
	public final static String DOWNLOAD_FILE = "file";

	/**
	 * service层响应为下载文件
	 */
	public final static String DOWNLOAD_FLAG = "downloadfile";

	/**
	 * service层响应下载文件路径
	 */
	public final static String DOWNLOAD_FLAG_PATH = "downloadpath";

	/**
	 * service层响应下载文件名称
	 */
	public final static String DOWNLOAD_FLAG_FILENAME = "downloadfilename";

	/**
	 * 申请状态-未审核
	 */
	public final static String APPLY_STATUS_UNAUDIT = "1";

	/**
	 * 申请状态-审核中
	 */
	public final static String APPLY_STATUS_AUDITING = "2";

	/**
	 * 申请状态-不通过
	 */
	public final static String APPLY_STATUS_REJECT = "4";

	/**
	 * 申请状态-通过
	 */
	public final static String APPLY_STATUS_PASS = "3";

	/**
	 * 审核流程-通过
	 */
	public final static String AUDIT_RESULT_PASS = "1";

	/**
	 * 审核流程-不通过
	 */
	public final static String AUDIT_RESULT_REJECT = "2";

	/**
	 * 审核流程-退回
	 */
	public final static String AUDIT_RESULT_RETURN = "3";

	/**
	 * 审核流程-废弃
	 */
	public final static String AUDIT_RESULT_DISCARD = "4";

	/**
	 * 流程审批下一审批人
	 */
	public final static String TASK_NEXT = "next";
	/**
	 * 流程审批结束
	 */
	public final static String TASK_FINISH = "finish";

	/**
	 * 流程审批不通过
	 */
	public final static String TASK_REJECT = "reject";

	/**
	 * 任务状态-未处理
	 */
	public final static String TASK_STATUS_NON = "0";

	/**
	 * 任务状态-完成
	 */
	public final static String TASK_STATUS_SUCCESS = "1";

	/**
	 * 任务状态-失败
	 */
	public final static String TASK_STATUS_FAILED = "2";

	/**
	 * 任务状态-执行中(ETL需要的状态)
	 */
	public final static String TASK_STATUS_DISCARD = "3";

	/**
	 * 任务状态-结束(ETL需要的状态)
	 */
	public final static String TASK_STATUS_END = "5";

	/**
	 * 系统临时文件目录
	 */
	public final static String FILE_TEMP_DIR = "temp";
}
