# CRMdemo 基于SSM+Vue的客户关系管理系统

## 更新及优化
   2021/11/04 前端修改信息弹窗逻辑；对数据刷新方法进行封装优化  
   2021/11/05 前端表单重置优化；报错后逻辑优化  
   2021/11/06 去除登录时封装信息对象冗余；优化数据库操作方法逻辑  
   2021/11/14 更改登录逻辑 显示登录用户角色 完成角色资源授权树形列表数据加载  
   2021/11/17 优化在获取最大编号时List集合的判空判断 基本完成RABC权限管理（除资源管理）  
   2021/11/22 完成RABC权限管理 前端无权限判定 后端权限码注解待编写 已完成除字典管理外系统设置模块的所有内容  
   2021/12/03 完成客户信息管理、客户流失管理（仅流失客户更新）重构更改角色资源授权、流失客户最后下单时间查询时的后端逻辑 将操作数据库方法移至遍历外层 优化数据库性能  
   2021/12/12 基本完成所有基本功能 修复角色-资源授权时无法删除原授权信息的问题  
   2021/12/31 前端项目运行 npm i/npm install 指令时安装依赖时发生错误的问题解决方案：删除C盘用户文件夹下AppData文件夹npm和npm-cache，重新运行指令。若仍发生错误，请尝试卸载重装Node.js   
   2022/01/05 引入基于cookie的自定义全局拦截器 以拦截除登录请求外所有未登录、恶意伪造cookie的非法请求  
   2022/01/05 完善系统内所有资源信息以及后端所有资源授权  
   2022/01/07 完成系统设置模块中的字典管理  
   2022/01/12 完成统计报表模块中的客户服务分析  
   2022/02/16 修改错误字段名 流失客户管理可进行手动更新 
## 现完成的模块及功能：

* 登录模块

* 营销管理模块
    * 营销机会管理
    * 客户开发计划  

* 客户管理模块
    * 客户信息管理（包括联系人管理、交往记录管理、订单管理）
    * 客户流失管理（包括流失客户更新、暂缓流失措施管理） 

* 统计报表模块
    * 客户贡献分析
    * 客户构成分析
    * 客户服务分析

* 系统设置模块
    * 资料修改模块
    * 字典管理模块
    * 用户管理模块（包括角色分配）
    * 角色管理模块（包括资源授权树形列表加载、资源授权角色、资源权限拦截）
    * 资源管理模块
