DROP TABLE IF EXISTS tvq_violation;
DROP TABLE IF EXISTS tvq_vehicle;
DROP TABLE IF EXISTS tvq_user;

-- 用户信息
CREATE TABLE tvq_user(
     id INT NOT NULL AUTO_INCREMENT ,            -- ID,自动递增
     phone VARCHAR(30) NULL ,                    -- 手机号码
     iptv VARCHAR(30) NULL ,                     -- IPTV账号
     PRIMARY KEY (id)       					 -- 主键
);
-- 创建组合索引
ALTER TABLE tvq_user ADD INDEX user (phone, iptv);

-- 机动车信息
CREATE TABLE tvq_vehicle(
    id INT NOT NULL AUTO_INCREMENT ,			-- ID,自动递增
    userid INT NOT NULL ,                       -- tvq_user id
    plate VARCHAR(20) NOT NULL ,                -- 机动车号牌
    newenegry VARCHAR(10) NOT NULL ,            -- 是否是新能源车 1:是 0:否
    platetype VARCHAR(10) NOT NULL ,            -- 机动车号牌种类
    identification VARCHAR(10) NOT NULL ,       -- 车辆识别代号后四位
    status VARCHAR(5) NULL ,                    -- 接口返回信息状态（0：信息有误  1：信息成功）
    msg VARCHAR(200) NULL ,                     -- 接口返回错误信息解释 (status为0时出现)
    vehiclestatus VARCHAR(10) NULL ,            -- 车辆状态
    inspectiondate VARCHAR(20) NULL ,           -- 检车日期
    violationnum INT NULL ,                     -- 违法数量
    PRIMARY KEY (id) ,     						-- 主键
    FOREIGN KEY (userid) REFERENCES tvq_user(id) on delete cascade
);
-- 创建组合索引
ALTER TABLE tvq_vehicle ADD INDEX vehicle (plate, platetype, identification);

-- 机动车违法信息
CREATE TABLE tvq_violation(
    id INT NOT NULL AUTO_INCREMENT ,			-- ID,自动递增
    vehicleid INT NOT NULL ,                    -- tvq_vehicle id
    datetime VARCHAR(30) NULL ,                 -- 违法时间
    place VARCHAR(100) NULL ,                   -- 违法地点
    act VARCHAR(300) NULL ,                     -- 违法行为
    fine VARCHAR(10) NULL ,                     -- 罚款金额
    points INT NULL ,                           -- 扣分分数
    PRIMARY KEY (id) ,    						-- 主键
    FOREIGN KEY (vehicleid) REFERENCES tvq_vehicle(id) on delete cascade
);
