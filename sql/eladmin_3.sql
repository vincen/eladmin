DROP TABLE IF EXISTS mk3_code_column_config;
CREATE TABLE mk3_code_column_config (
    column_id bigint NOT NULL primary key,
    table_name varchar(255),
    column_name varchar(255),
    column_type varchar(255),
    dict_name varchar(255),
    extra varchar(255),
    form_show bit(1),
    form_type varchar(255),
    key_type varchar(255),
    list_show bit(1),
    not_null bit(1),
    query_type varchar(255),
    remark varchar(255),
    date_annotation varchar(255)
)

DROP TABLE IF EXISTS mk3_code_gen_config;
CREATE TABLE mk3_code_gen_config (
    config_id bigint NOT NULL PRIMARY KEY,
    table_name varchar(255) COMMENT '表名',
    author varchar(255) COMMENT '作者',
    cover bit(1) COMMENT '是否覆盖',
    module_name varchar(255) COMMENT '模块名称',
    pack varchar(255) COMMENT '至于哪个包下',
    path varchar(255) COMMENT '前端代码生成的路径',
    api_path varchar(255) COMMENT '前端Api文件路径',
    prefix varchar(255) COMMENT '表前缀',
    api_alias varchar(255)  COMMENT '接口名称'
)