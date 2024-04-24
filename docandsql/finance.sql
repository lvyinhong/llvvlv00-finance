-- auto-generated definition
create table tenant(
    id              bigint auto_increment primary key,
    name            varchar(50) default ''                not null comment '租户名称',
    disable         tinyint(1)  default 0                 not null comment '是否禁用',
    create_time     datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     datetime    default CURRENT_TIMESTAMP not null comment '修改时间',
    admin_id        bigint      default 0                 not null comment '管理员id',
    update_admin_id bigint      default 0                 not null comment '修改管理员id',
    del_flag        tinyint(1)  default 0                 not null comment '是否删除，0：删除，1：未删除'
)comment '租户表';

-- 这块sys_role_id 字段名称后面改为sys_role_ids 字段类型从int 改为json格式
create table member(
    id           bigint auto_increment primary key,
    nick_name    varchar(50)  default ''                not null comment '用户昵称',
    disable      tinyint(1)   default 0                 not null comment '是否禁用',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    name         varchar(50)  default ''                not null comment '姓名',
    avatar_url   varchar(200) default ''                not null comment '头像',
    sys_role_ids json                                   not null comment '角色id，多个以英文逗号分隔',
    tenant_id    bigint       default 0                 not null comment '租户id',
    email        varchar(50)  default ''                not null comment '邮箱地址'
)comment '用户表';

-- auto-generated definition
create table member_bind_phone(
    id          bigint auto_increment primary key,
    phone       varchar(20) default ''                not null comment '手机号',
    member_id   bigint      default 0                 not null comment '用户id',
    disable     tinyint(1)  default 0                 not null comment '是否禁用',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime    default CURRENT_TIMESTAMP not null comment '修改时间',
    password    varchar(100)                          null comment '密码',
    constraint member_bind_mobile_member_id_uindex
        unique (member_id),
    constraint member_bind_mobile_mobile_member_id_uindex
        unique (phone, member_id),
    constraint member_bind_mobile_mobile_uindex
        unique (phone)
)comment '用户表绑定手机表';

-- auto-generated definition
create table member_bind_wx_openid (
    id          bigint auto_increment primary key,
    app_id      varchar(50) default ''                not null comment '小程序或者公众号appid',
    open_id     varchar(50) default ''                not null comment '微信openid',
    member_id   bigint      default 0                 not null comment '用户id',
    disable     tinyint(1)  default 0                 not null comment '是否禁用',
    create_time datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime    default CURRENT_TIMESTAMP not null comment '修改时间',
    constraint member_bind_wx_openid_app_id_open_id_member_id_uindex unique (app_id, open_id, member_id),
    constraint member_bind_wx_openid_app_id_open_id_uindex unique (app_id, open_id)
) comment '用户表绑定微信openid表';