CREATE TABLE employee (
id VARCHAR PRIMARY KEY,
name VARCHAR
dob VARCHAR
)


CREATE TABLE auditable_employee (
id VARCHAR PRIMARY KEY,
name VARCHAR
dob VARCHAR
created_by INT
updated_by INT
create_at INT
updated_at INT
)
