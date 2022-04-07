DROP TABLE IF EXISTS tvq_violation;
DROP TABLE IF EXISTS tvq_vehicle;

-- 机动车信息
CREATE TABLE tvq_vehicle(
    id INT NOT NULL AUTO_INCREMENT ,			-- ID,自动递增
    plate VARCHAR(20) NOT NULL ,                -- 机动车号牌
    platetype VARCHAR(10) NOT NULL ,            -- 机动车号牌种类
    identification VARCHAR(10) NOT NULL ,       -- 车辆识别代号后四位
    status VARCHAR(5) NULL ,                    -- 接口返回信息状态（0：信息有误  1：信息成功）
    msg VARCHAR(200) NULL ,                     -- 接口返回错误信息解释 (status为0时出现)
    vehiclestatus VARCHAR(50) NULL ,            -- 车辆状态
    inspectiondate VARCHAR(20) NULL ,           -- 检车日期
    violationnum INT NULL ,                     -- 违法数量
    updatetime DATETIME NULL ,                  -- 更新时间
    PRIMARY KEY (id) ,      					-- 主键
    UNIQUE KEY unqiue_vehicle (plate,platetype,identification)  -- 唯一索引
);
-- 创建组合索引
-- ALTER TABLE tvq_vehicle ADD UNIQUE unqiue_vehicle (plate, platetype, identification);

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

-- 驾驶证信息 driving license
CREATE TABLE tvq_license(
    id INT NOT NULL AUTO_INCREMENT ,			-- ID,自动递增
    idnumber VARCHAR(20) NULL ,                 -- 身份证号后四位
    filenumber VARCHAR(30) NULL ,               -- 12位档案编号
    status VARCHAR(5) NULL ,                    -- 接口返回信息状态（0：信息有误  1：信息成功）
    msg VARCHAR(200) NULL ,                     -- 接口返回错误信息解释 (status为0时出现)
    expirydate VARCHAR(30) NULL ,               -- 有效期止
    verifydate VARCHAR(30) NULL ,               -- 审验日期
    points INT NULL ,                           -- 累积记分
    licensestatus VARCHAR(50) NULL ,            -- 驾驶证状态
    updatetime DATETIME NULL ,                  -- 更新时间
    PRIMARY KEY (id) ,    						-- 主键
    UNIQUE KEY unqiue_license (idnumber,filenumber)  -- 唯一索引
);

-- 机动车限行措施 Traffic Restriction Measures
CREATE TABLE trm_restriction(
    id INT NOT NULL AUTO_INCREMENT ,			-- ID,自动递增
    city VARCHAR(30) NULL ,                     -- 城市
    date VARCHAR(20) NULL ,                     -- 日期
    content VARCHAR(200) NULL ,                 -- 内容
    updatetime DATETIME NULL ,                  -- 更新时间
    remark VARCHAR(100) NULL ,                  -- 备注
    PRIMARY KEY (id) ,    						-- 主键
    KEY idx_city_date (city, date)
);
