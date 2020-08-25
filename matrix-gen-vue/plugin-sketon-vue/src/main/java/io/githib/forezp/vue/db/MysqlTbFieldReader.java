package io.githib.forezp.vue.db;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import io.githib.forezp.vue.entity.TableField;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static io.githib.forezp.vue.constant.MysqlQueryConstants.SHOW_TABLE_FIELD;

public class MysqlTbFieldReader implements TableFieldReader {

    @Override
    public List<TableField> read(String tableName) {
        String sql = String.format(SHOW_TABLE_FIELD, tableName);
        List<TableField> result = new ArrayList<>();
        try {
            List<Entity> list = Db.use().query(sql);
            if (!CollectionUtil.isEmpty(list)) {
                for (Entity entity : list) {
                    TableField tbf = BeanUtil.toBean(entity, TableField.class);
                    result.add(tbf);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
