create table commodity
(
    id      bigint(32)   not null comment '主键',
    name    varchar(100) null comment '商品名称',
    type    varchar(100) null comment '商品类型',
    comment varchar(255) null comment '商品描述',
    state   varchar(1)   null comment '状态，0：删除，1：在售'
)
    charset = utf8;

INSERT INTO neusoft.commodity (id, name, type, comment, state) VALUES (1, 'iPhone 13', '智能手机', '苹果公司最新款手机', '0');
INSERT INTO neusoft.commodity (id, name, type, comment, state) VALUES (2, '小米手环7', '智能手环/手表', '小米公司最新款手环', '0');
INSERT INTO neusoft.commodity (id, name, type, comment, state) VALUES (3, 'huawei', '智能手機', '華爲最新的智能手機', '0');
INSERT INTO neusoft.commodity (id, name, type, comment, state) VALUES (4, 'iPad Pro', '平板电脑', '苹果新一代平板电脑', '1');
