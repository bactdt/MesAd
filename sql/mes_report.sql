-- ----------------------------
-- 生产报表表
-- ----------------------------
drop table if exists mes_report;
create table mes_report (
  report_id       bigint(20)      not null auto_increment    comment '报表ID',
  report_name     varchar(200)    default ''                 comment '报表名称',
  report_type     varchar(50)     default ''                 comment '报表类型（日报 周报 月报）',
  report_content  text                                       comment '报表内容',
  status          char(1)         default '0'                comment '状态（0正常 1停用）',
  remark          varchar(500)    default null               comment '备注',
  create_by       varchar(64)     default ''                 comment '创建者',
  create_time     datetime                                   comment '创建时间',
  update_by       varchar(64)     default ''                 comment '更新者',
  update_time     datetime                                   comment '更新时间',
  primary key (report_id)
) engine=innodb auto_increment=1 comment = '生产报表表';

-- ----------------------------
-- 菜单 SQL（生产报表）
-- ----------------------------
-- 一级菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('生产报表', '0', '6', 'reports', null, 1, 0, 'M', '0', '0', '', 'chart', 'admin', sysdate(), '', null, '生产报表目录');

-- 二级菜单
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('报表管理', (select menu_id from sys_menu t where t.menu_name = '生产报表' and t.parent_id = 0 limit 1), '1', 'report', 'reports/index', 1, 0, 'C', '0', '0', 'report:report:list', 'list', 'admin', sysdate(), '', null, '报表管理菜单');

-- 按钮权限
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('报表查询', (select menu_id from sys_menu t where t.menu_name = '报表管理' and t.perms = 'report:report:list' limit 1), '1', '#', '', 1, 0, 'F', '0', '0', 'report:report:query', '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('报表新增', (select menu_id from sys_menu t where t.menu_name = '报表管理' and t.perms = 'report:report:list' limit 1), '2', '#', '', 1, 0, 'F', '0', '0', 'report:report:add', '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('报表修改', (select menu_id from sys_menu t where t.menu_name = '报表管理' and t.perms = 'report:report:list' limit 1), '3', '#', '', 1, 0, 'F', '0', '0', 'report:report:edit', '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('报表删除', (select menu_id from sys_menu t where t.menu_name = '报表管理' and t.perms = 'report:report:list' limit 1), '4', '#', '', 1, 0, 'F', '0', '0', 'report:report:remove', '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('报表导出', (select menu_id from sys_menu t where t.menu_name = '报表管理' and t.perms = 'report:report:list' limit 1), '5', '#', '', 1, 0, 'F', '0', '0', 'report:report:export', '#', 'admin', sysdate(), '', null, '');
