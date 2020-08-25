# 自动生成vue代码的框架

一个自动从mysql数据库中读取表字段，然后生成matrix-web-admin的前端代码。

## 使用步骤

在plugin-sketon-vue工程下，做以下的配置：

- 在resource/db.setting，配置数据库，目前只支持mysql类型的。
- 在App.class配置tableName，即要生成的表名；generatePath，生成文件的路径
- 执行main函数。
