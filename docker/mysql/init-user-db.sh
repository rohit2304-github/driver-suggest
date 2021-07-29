#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$MYSQL_USER" -d "$MYSQL_DATABASE" <<-EOSQL
    GRANT ALL PRIVILEGES ON DATABASE "$MYSQL_DB" TO "$MYSQL_USER";
#    create table if not exists stores
#    (
#      id  bigint not null constraint stores_pkey primary key,
#      location NUMERIC (5, 2),
#    );
#    CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 1;
EOSQL
