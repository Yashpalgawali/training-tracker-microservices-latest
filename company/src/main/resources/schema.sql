CREATE TABLE IF NOT EXISTS company (
    company_id INT NOT NULL AUTO_INCREMENT,
    company_name VARCHAR(255) NOT NULL,
    created_at DATE NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATE DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (company_id)
);