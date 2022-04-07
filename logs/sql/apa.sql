-- Account number and vehicle plate、driving license Association

-- 用户信息
CREATE TABLE apa_user(
    id INT NOT NULL AUTO_INCREMENT ,            -- ID,自动递增
    phone VARCHAR(30) NULL ,                    -- 手机号码
    iptv VARCHAR(30) NOT NULL ,                 -- IPTV账号
    remark VARCHAR(50) NULL ,                   -- 备注
    PRIMARY KEY (id) ,     					    -- 主键
    KEY idx_iptv (iptv)
);
-- 也可使用下面语句添加索引
-- ALTER TABLE apa_user ADD INDEX idx_iptv (iptv);

-- 机动车信息
CREATE TABLE apa_vehicle(
    id INT NOT NULL AUTO_INCREMENT ,			-- ID,自动递增
    userid INT NOT NULL ,                       -- apa_user id
    phone VARCHAR(30) NULL ,                    -- 手机号码
    plate VARCHAR(20) NOT NULL ,                -- 机动车号牌
    newenegry VARCHAR(10) NOT NULL ,            -- 是否是新能源车 1:是 0:否
    platetype VARCHAR(10) NOT NULL ,            -- 机动车号牌种类
    identification VARCHAR(10) NOT NULL ,       -- 车辆识别代号后四位
    PRIMARY KEY (id) ,     						-- 主键
    FOREIGN KEY (userid) REFERENCES apa_user(id) on delete cascade ,
    UNIQUE KEY unqiue_vehicle (plate,platetype,identification)  -- 唯一索引
);
-- 创建组合索引
-- ALTER TABLE apa_vehicle ADD INDEX vehicle (plate, platetype, identification);

-- 驾驶证信息
CREATE TABLE apa_license(
    id INT NOT NULL AUTO_INCREMENT ,			-- ID,自动递增
    userid INT NOT NULL ,                       -- apa_user id
    phone VARCHAR(30) NULL ,                    -- 手机号码
    idnumber VARCHAR(20) NOT NULL ,             -- 身份证号后四位
    filenumber VARCHAR(30) NOT NULL ,           -- 12位档案编号
    PRIMARY KEY (id) ,     						-- 主键
    FOREIGN KEY (userid) REFERENCES apa_user(id) on delete cascade ,
    UNIQUE KEY unqiue_license (idnumber,filenumber)  -- 唯一索引
);
