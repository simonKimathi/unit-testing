package io.jotech.classicmodels.service;


import java.util.List;

import io.jotech.classicmodels.entity.Office;

public interface OfficeService {
    List<Office> getAllOffices(Integer start, Integer limit);

    Office getOffice(String officeCode);

    Office createOffice(Office office);

    Office updateOffice(Office office);

    boolean deleteOffice(String officeCode);
}
