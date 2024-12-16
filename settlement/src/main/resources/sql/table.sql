SET SQL_SAFE_UPDATES = 0;
DELETE FROM BATCH_STEP_EXECUTION_CONTEXT;
DELETE FROM BATCH_JOB_EXECUTION_CONTEXT;
DELETE FROM BATCH_STEP_EXECUTION;
DELETE FROM BATCH_JOB_EXECUTION_PARAMS;
DELETE FROM BATCH_JOB_EXECUTION;
DELETE FROM BATCH_JOB_INSTANCE;
DELETE FROM SettleDetail;

create table SettleDetail
(
    id         bigint auto_increment
        primary key,
    customerId bigint null,
    serviceId  bigint null,
    count      bigint null,
    fee        bigint null,
    targetDate date   null
);

create table SettleGroup
(
    id         bigint auto_increment
        primary key,
    customerId bigint   null,
    serviceId  bigint   null,
    totalCount bigint   null,
    totalFee   bigint   null,
    createdAt  datetime null
);