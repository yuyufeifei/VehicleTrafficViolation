package com.wxcm.vtvi.controller;

import com.wxcm.vtvi.service.ImportVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GZH
 */
@RestController
public class ImportBaseDataController {

    @Autowired
    private ImportVehicleService importVehicleService;

    @RequestMapping(value = "importVehicle", method = RequestMethod.POST)
    public Object importVehicleFromCsv(@RequestParam String path, @RequestParam String charset) {
        return importVehicleService.importVehicleFromCsv(path, charset);
    }

    @RequestMapping(value = "importVehicleByJdbcTpl", method = RequestMethod.POST)
    public Object importVehicleFromCsvByJdbcTemplate(@RequestParam String path, @RequestParam String charset) {
        return importVehicleService.importVehicleFromCsvByJdbcTemplate(path, charset);
    }

}
