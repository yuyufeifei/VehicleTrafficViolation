package com.wxcm.vtvi.service.impl;

import com.wxcm.vtvi.dao.TvqVehicleDao;
import com.wxcm.vtvi.entity.TvqVehicle;
import com.wxcm.vtvi.service.ImportVehicleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GZH
 */
@Service
public class ImportVehicleServiceImpl implements ImportVehicleService {

    private final Logger logger = LogManager.getLogger(ImportVehicleServiceImpl.class);

    @Autowired
    private TvqVehicleDao tvqVehicleDao;

    @Override
    public boolean importVehicleFromCsv(String path, String charset) {
        boolean flag = false;
        BufferedReader br = null;
        try {
            long beginTime = System.currentTimeMillis();
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), charset));
            String line = "";
            while ((line = br.readLine()) != null) {
                String [] tempLine = line.replace("\"", "").split(",");
                if(tempLine.length == 5) {
                    try {
                        tvqVehicleDao.save(new TvqVehicle(tempLine[1] + tempLine[2], tempLine[3], tempLine[4]));
                    } catch (Exception e) {
                        logger.error(e);
                    }
                }
            }
            flag = true;
            long endTime = System.currentTimeMillis();
            logger.info("导入文件：'" + path + "'完成！用时：" + (endTime - beginTime) / 1000f + "秒。");
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if(br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
        return flag;
    }

    @Override
    public boolean importVehicleFromCsvByJdbcTemplate(String path, String charset) {
        boolean flag = false;
        BufferedReader br = null;
        try {
            long beginTime = System.currentTimeMillis();
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), charset));
            String line = "";
            List<TvqVehicle> tvqVehicles = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String [] tempLine = line.replace("\"", "").split(",");
                if(tempLine.length == 5) {
                    tvqVehicles.add(new TvqVehicle(tempLine[1] + tempLine[2], tempLine[3], tempLine[4]));
                    if(tvqVehicles.size() == 500) {
                        long bTime = System.currentTimeMillis();
                        batchInsert(tvqVehicles);
                        long eTime = System.currentTimeMillis();
                        logger.info("保存用时：" + (eTime - bTime) / 1000f + "秒");
                        tvqVehicles.clear();
                    }
                }
            }
            if(!tvqVehicles.isEmpty()) {
                batchInsert(tvqVehicles);
            }
            flag = true;
            long endTime = System.currentTimeMillis();
            logger.info("导入文件：'" + path + "'完成！用时：" + (endTime - beginTime) / 1000f + "秒。");
        } catch (Exception e) {
            logger.error(e);
        } finally {
            if(br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }
        return flag;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void batchInsert(List<TvqVehicle> list) {
        String sql = "insert into tvq_vehicle(plate, platetype, identification) values(?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String plate = list.get(i).getPlate();
                String plateType = list.get(i).getPlatetype();
                String identification= list.get(i).getIdentification();
                ps.setString(1, plate);
                ps.setString(2, plateType);
                ps.setString(3, identification);
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

}
