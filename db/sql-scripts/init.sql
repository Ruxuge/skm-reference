CREATE TABLE skmInfo
(
    skmId               BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    numberOfTrains       INT                            NOT NULL,
    numberOfCompartments INT                            NOT NULL,
    compartmentCapacity   INT                            NOT NULL
);

CREATE TABLE trains
(
    trainId           BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    compartmentId     INT                            NOT NULL,

    INDEX (compartmentId) FOREIGN KEY (compartmentId)
        REFERENCE compartments(compartmentId)

    currentStation    VARCHAR(250)                   NOT NULL,
    goingToGdansk    BOOLEAN                        NOT NULL,
    currentPauseTime INT                            NOT NULL
);

CREATE TABLE compartments
(
    compartmentId BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    capacity       INT                            NOT NULL,
    personId      INT                            NOT NULL

);
