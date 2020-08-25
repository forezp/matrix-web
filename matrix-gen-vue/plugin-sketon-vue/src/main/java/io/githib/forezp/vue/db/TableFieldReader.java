package io.githib.forezp.vue.db;

import io.githib.forezp.vue.entity.TableField;

import java.util.List;

public interface TableFieldReader {

    List<TableField> read(String tableName);
}
