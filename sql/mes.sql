-- ----------------------------
-- 1、订单管理表
-- ----------------------------
drop table if exists mes_order;
create table mes_order (
  order_id           bigint(20)      not null auto_increment    comment '订单ID',
  order_code         varchar(50)     not null                   comment '订单编号',
  customer_id        bigint(20)      not null                   comment '客户ID',
  customer_name      varchar(100)    not null                   comment '客户名称',
  product_id         bigint(20)      not null                   comment '产品ID',
  product_code       varchar(50)     not null                   comment '产品编码',
  product_name       varchar(100)    not null                   comment '产品名称',
  order_quantity     decimal(10,2)   not null                   comment '订单数量',
  unit               varchar(20)     not null                   comment '单位',
  plan_start_time    datetime                                   comment '计划开始时间',
  plan_end_time      datetime                                   comment '计划结束时间',
  actual_start_time  datetime                                   comment '实际开始时间',
  actual_end_time    datetime                                   comment '实际结束时间',
  priority           int(4)          default 5                  comment '优先级(1-10，10为最高)',
  order_status       char(1)         default '0'                comment '订单状态（0待排产 1已排产 2生产中 3已完成 4已取消）',
  remark             varchar(500)    default null               comment '备注',
  create_by          varchar(64)     default ''                 comment '创建者',
  create_time        datetime                                   comment '创建时间',
  update_by          varchar(64)     default ''                 comment '更新者',
  update_time        datetime                                   comment '更新时间',
  primary key (order_id)
) engine=innodb auto_increment=100 comment = '订单管理表';

-- ----------------------------
-- 订单明细表
-- ----------------------------
drop table if exists mes_order_detail;
create table mes_order_detail (
  detail_id          bigint(20)      not null auto_increment    comment '明细ID',
  order_id           bigint(20)      not null                   comment '订单ID',
  product_id         bigint(20)      not null                   comment '产品ID',
  product_code       varchar(50)     not null                   comment '产品编码',
  product_name       varchar(100)    not null                   comment '产品名称',
  specification      varchar(200)    default null               comment '规格型号',
  quantity           decimal(10,2)   not null                   comment '数量',
  unit               varchar(20)     not null                   comment '单位',
  plan_quantity      decimal(10,2)   default 0                  comment '已排产数量',
  produced_quantity  decimal(10,2)   default 0                  comment '已生产数量',
  qualified_quantity decimal(10,2)   default 0                  comment '合格数量',
  unqualified_quantity decimal(10,2) default 0                  comment '不合格数量',
  status             char(1)         default '0'                comment '状态（0未生产 1部分生产 2完成生产）',
  remark             varchar(500)    default null               comment '备注',
  create_by          varchar(64)     default ''                 comment '创建者',
  create_time        datetime                                   comment '创建时间',
  update_by          varchar(64)     default ''                 comment '更新者',
  update_time        datetime                                   comment '更新时间',
  primary key (detail_id)
) engine=innodb auto_increment=100 comment = '订单明细表';

-- ----------------------------
-- 订单排产表
-- ----------------------------
drop table if exists mes_order_schedule;
create table mes_order_schedule (
  schedule_id        bigint(20)      not null auto_increment    comment '排产ID',
  order_id           bigint(20)      not null                   comment '订单ID',
  order_code         varchar(50)     not null                   comment '订单编号',
  detail_id          bigint(20)      not null                   comment '订单明细ID',
  product_id         bigint(20)      not null                   comment '产品ID',
  product_code       varchar(50)     not null                   comment '产品编码',
  product_name       varchar(100)    not null                   comment '产品名称',
  workshop_id        bigint(20)      not null                   comment '车间ID',
  workshop_name      varchar(100)    not null                   comment '车间名称',
  line_id            bigint(20)      not null                   comment '生产线ID',
  line_name          varchar(100)    not null                   comment '生产线名称',
  schedule_quantity  decimal(10,2)   not null                   comment '排产数量',
  unit               varchar(20)     not null                   comment '单位',
  plan_start_time    datetime                                   comment '计划开始时间',
  plan_end_time      datetime                                   comment '计划结束时间',
  actual_start_time  datetime                                   comment '实际开始时间',
  actual_end_time    datetime                                   comment '实际结束时间',
  schedule_status    char(1)         default '0'                comment '排产状态（0待生产 1生产中 2已完成 3已取消）',
  remark             varchar(500)    default null               comment '备注',
  create_by          varchar(64)     default ''                 comment '创建者',
  create_time        datetime                                   comment '创建时间',
  update_by          varchar(64)     default ''                 comment '更新者',
  update_time        datetime                                   comment '更新时间',
  primary key (schedule_id)
) engine=innodb auto_increment=100 comment = '订单排产表';

-- ----------------------------
-- 2、生产执行表
-- ----------------------------
drop table if exists mes_production_execution;
create table mes_production_execution (
  execution_id       bigint(20)      not null auto_increment    comment '执行ID',
  schedule_id        bigint(20)      not null                   comment '排产ID',
  order_id           bigint(20)      not null                   comment '订单ID',
  order_code         varchar(50)     not null                   comment '订单编号',
  product_id         bigint(20)      not null                   comment '产品ID',
  product_code       varchar(50)     not null                   comment '产品编码',
  product_name       varchar(100)    not null                   comment '产品名称',
  workshop_id        bigint(20)      not null                   comment '车间ID',
  workshop_name      varchar(100)    not null                   comment '车间名称',
  line_id            bigint(20)      not null                   comment '生产线ID',
  line_name          varchar(100)    not null                   comment '生产线名称',
  plan_quantity      decimal(10,2)   not null                   comment '计划生产数量',
  actual_quantity    decimal(10,2)   default 0                  comment '实际生产数量',
  qualified_quantity decimal(10,2)   default 0                  comment '合格数量',
  unqualified_quantity decimal(10,2) default 0                  comment '不合格数量',
  unit               varchar(20)     not null                   comment '单位',
  start_time         datetime                                   comment '开始时间',
  end_time           datetime                                   comment '结束时间',
  execution_status   char(1)         default '0'                comment '执行状态（0未开始 1进行中 2已暂停 3已完成 4已取消）',
  remark             varchar(500)    default null               comment '备注',
  create_by          varchar(64)     default ''                 comment '创建者',
  create_time        datetime                                   comment '创建时间',
  update_by          varchar(64)     default ''                 comment '更新者',
  update_time        datetime                                   comment '更新时间',
  primary key (execution_id)
) engine=innodb auto_increment=100 comment = '生产执行表';

-- ----------------------------
-- 生产工序执行表
-- ----------------------------
drop table if exists mes_process_execution;
create table mes_process_execution (
  process_execution_id bigint(20)    not null auto_increment    comment '工序执行ID',
  execution_id       bigint(20)      not null                   comment '生产执行ID',
  process_id         bigint(20)      not null                   comment '工序ID',
  process_code       varchar(50)     not null                   comment '工序编码',
  process_name       varchar(100)    not null                   comment '工序名称',
  workstation_id     bigint(20)      not null                   comment '工位ID',
  workstation_code   varchar(50)     not null                   comment '工位编码',
  workstation_name   varchar(100)    not null                   comment '工位名称',
  worker_id          bigint(20)                                 comment '操作工ID',
  worker_name        varchar(100)                               comment '操作工姓名',
  plan_quantity      decimal(10,2)   not null                   comment '计划生产数量',
  actual_quantity    decimal(10,2)   default 0                  comment '实际生产数量',
  qualified_quantity decimal(10,2)   default 0                  comment '合格数量',
  unqualified_quantity decimal(10,2) default 0                  comment '不合格数量',
  unit               varchar(20)     not null                   comment '单位',
  start_time         datetime                                   comment '开始时间',
  end_time           datetime                                   comment '结束时间',
  process_status     char(1)         default '0'                comment '工序状态（0未开始 1进行中 2已暂停 3已完成 4已取消）',
  remark             varchar(500)    default null               comment '备注',
  create_by          varchar(64)     default ''                 comment '创建者',
  create_time        datetime                                   comment '创建时间',
  update_by          varchar(64)     default ''                 comment '更新者',
  update_time        datetime                                   comment '更新时间',
  primary key (process_execution_id)
) engine=innodb auto_increment=100 comment = '生产工序执行表';

-- ----------------------------
-- 生产参数记录表
-- ----------------------------
drop table if exists mes_production_parameter;
create table mes_production_parameter (
  parameter_id       bigint(20)      not null auto_increment    comment '参数ID',
  process_execution_id bigint(20)    not null                   comment '工序执行ID',
  parameter_name     varchar(100)    not null                   comment '参数名称',
  parameter_code     varchar(50)     not null                   comment '参数编码',
  parameter_value    varchar(200)    not null                   comment '参数值',
  standard_value     varchar(200)                               comment '标准值',
  upper_limit        varchar(50)                                comment '上限',
  lower_limit        varchar(50)                                comment '下限',
  unit               varchar(20)                                comment '单位',
  is_qualified       char(1)         default '1'                comment '是否合格（0不合格 1合格）',
  record_time        datetime                                   comment '记录时间',
  remark             varchar(500)    default null               comment '备注',
  create_by          varchar(64)     default ''                 comment '创建者',
  create_time        datetime                                   comment '创建时间',
  update_by          varchar(64)     default ''                 comment '更新者',
  update_time        datetime                                   comment '更新时间',
  primary key (parameter_id)
) engine=innodb auto_increment=100 comment = '生产参数记录表';

-- ----------------------------
-- 3、质量管理表
-- ----------------------------
drop table if exists mes_quality_inspection;
create table mes_quality_inspection (
  inspection_id      bigint(20)      not null auto_increment    comment '检验ID',
  inspection_code    varchar(50)     not null                   comment '检验单编号',
  inspection_type    char(1)         not null                   comment '检验类型（1原料检验 2过程检验 3成品检验）',
  related_id         bigint(20)      not null                   comment '关联ID（原料ID/工序执行ID/成品ID）',
  product_id         bigint(20)                                 comment '产品ID',
  product_code       varchar(50)                                comment '产品编码',
  product_name       varchar(100)                               comment '产品名称',
  batch_code         varchar(50)                                comment '批次号',
  inspection_quantity decimal(10,2)  not null                   comment '检验数量',
  qualified_quantity decimal(10,2)   default 0                  comment '合格数量',
  unqualified_quantity decimal(10,2) default 0                  comment '不合格数量',
  unit               varchar(20)     not null                   comment '单位',
  inspector_id       bigint(20)      not null                   comment '检验员ID',
  inspector_name     varchar(100)    not null                   comment '检验员姓名',
  inspection_time    datetime                                   comment '检验时间',
  inspection_result  char(1)         default '1'                comment '检验结果（0不合格 1合格）',
  remark             varchar(500)    default null               comment '备注',
  create_by          varchar(64)     default ''                 comment '创建者',
  create_time        datetime                                   comment '创建时间',
  update_by          varchar(64)     default ''                 comment '更新者',
  update_time        datetime                                   comment '更新时间',
  primary key (inspection_id)
) engine=innodb auto_increment=100 comment = '质量检验表';

-- ----------------------------
-- 质量检验项目表
-- ----------------------------
drop table if exists mes_inspection_item;
create table mes_inspection_item (
  item_id            bigint(20)      not null auto_increment    comment '检验项ID',
  inspection_id      bigint(20)      not null                   comment '检验ID',
  item_name          varchar(100)    not null                   comment '检验项名称',
  item_code          varchar(50)     not null                   comment '检验项编码',
  standard_value     varchar(200)                               comment '标准值',
  upper_limit        varchar(50)                                comment '上限',
  lower_limit        varchar(50)                                comment '下限',
  actual_value       varchar(200)    not null                   comment '实际值',
  unit               varchar(20)                                comment '单位',
  is_qualified       char(1)         default '1'                comment '是否合格（0不合格 1合格）',
  remark             varchar(500)    default null               comment '备注',
  create_by          varchar(64)     default ''                 comment '创建者',
  create_time        datetime                                   comment '创建时间',
  update_by          varchar(64)     default ''                 comment '更新者',
  update_time        datetime                                   comment '更新时间',
  primary key (item_id)
) engine=innodb auto_increment=100 comment = '质量检验项目表';

-- ----------------------------
-- 不合格品处理表
-- ----------------------------
drop table if exists mes_defect_handling;
create table mes_defect_handling (
  handling_id        bigint(20)      not null auto_increment    comment '处理ID',
  inspection_id      bigint(20)      not null                   comment '检验ID',
  defect_type        varchar(100)    not null                   comment '不合格类型',
  defect_quantity    decimal(10,2)   not null                   comment '不合格数量',
  unit               varchar(20)     not null                   comment '单位',
  handling_method    char(1)         not null                   comment '处理方式（1返工 2让步接收 3报废 4降级使用）',
  handler_id         bigint(20)      not null                   comment '处理人ID',
  handler_name       varchar(100)    not null                   comment '处理人姓名',
  handling_time      datetime                                   comment '处理时间',
  handling_result    varchar(500)                               comment '处理结果',
  remark             varchar(500)    default null               comment '备注',
  create_by          varchar(64)     default ''                 comment '创建者',
  create_time        datetime                                   comment '创建时间',
  update_by          varchar(64)     default ''                 comment '更新者',
  update_time        datetime                                   comment '更新时间',
  primary key (handling_id)
) engine=innodb auto_increment=100 comment = '不合格品处理表';

-- ----------------------------
-- 初始化-基础数据
-- ----------------------------
-- 插入一些测试数据
insert into mes_order values(100, 'ORD20240629001', 1, '测试客户A', 1, 'PRD001', '测试产品1', 100.00, '个', '2024-06-29 08:00:00', '2024-06-30 17:00:00', null, null, 5, '0', '测试订单', 'admin', sysdate(), '', null);
insert into mes_order_detail values(100, 100, 1, 'PRD001', '测试产品1', '标准型号', 100.00, '个', 0, 0, 0, 0, '0', '测试订单明细', 'admin', sysdate(), '', null);