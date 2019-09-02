package io.github.forezp.modules.personnel.vo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class VacationDomain {

    private String name;
    private String userId;
    private String reason;
    private String remarks;
    private String nextUserId;
    private String approveComments;
    private String procDefKey;
    private Date applyDate;
    private String processId;
    private String type;
    private Date startTime;
    private Date endTime;
    private String step;
}
